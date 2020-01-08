package example.common;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.HashMap;

public class FtpService implements Closeable {

    public FtpService(String ftpHost, String ftpAccount, String ftpPassword) throws IOException {
        this(ftpHost, PORT, ftpAccount, ftpPassword);
    }

    public FtpService(String ftpHost, Integer ftpPort, String ftpAccount, String ftpPassword) throws IOException {
        connect(ftpHost, ftpPort, ftpAccount, ftpPassword);
    }

    /**
     * 获取字符串行文本map
     *
     * @param ftpPath
     * @return
     * @throws IOException
     */
    public HashMap<Integer, String> getFtpStringMap(String ftpPath) throws IOException {

        if (ftpPath == null || ftpPath.isEmpty()) {
            return null;
        }

        if (this.ftpClient == null || !ftpClient.isConnected()) {
            return null;
        }

        try (InputStream inputStream = ftpClient.retrieveFileStream(ftpPath)) {

            if (inputStream == null || ftpClient.getReplyCode() == FTPReply.FILE_UNAVAILABLE) {
                return null;
            }

            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

                HashMap<Integer, String> result = new HashMap<>(16);
                String text = null;
                int line = 0;
                while ((text = bufferedReader.readLine()) != null) {
                    result.put(++line, text);
                }
                return result;

            } catch (IOException e) {
                throw e;
            }

        } catch (IOException e) {
            throw e;
        }
    }


    @Override
    public void close() throws IOException {
        if (ftpClient != null && ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    private void connect(String ftpHost, Integer ftpPort, String ftpAccount, String ftpPassword) throws IOException {
        // 创建一个ftp对象
        FTPClient ftp = new FTPClient();
        ftp.setConnectTimeout(CONNECT_TIMEOUT);
        ftp.setDataTimeout(DATA_TIMEOUT);
        // ftp连接上去
        ftp.connect(ftpHost, ftpPort);
        // ftp登录上去
        ftp.login(ftpAccount, ftpPassword);
        // 拿到返回码，进行判断是否连接成功
        int reply = ftp.getReplyCode();
        // 连接失败
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
        }
        //设置文件类型
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        //ftp设置被动模式，不然会阻塞
        ftp.enterLocalPassiveMode();
        // 连接成功，准备上传
        this.ftpClient = ftp;
    }

    private FTPClient ftpClient;

    private final static Integer PORT = 21;

    private final static Integer CONNECT_TIMEOUT = 120_000;

    private final static Integer DATA_TIMEOUT = 30_000;
}
