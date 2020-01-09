package example.common;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * 上传文件
     *
     * @param ftpFileName
     * @param ftpPath
     * @param localFile
     * @return
     * @throws Exception
     */
    public Boolean storeFile(String ftpFileName, String ftpPath, String localFile) {

        if (localFile == null || localFile.isEmpty()) {
            log.info("localFile is empty");
            return false;
        }

        if (this.ftpClient == null || !ftpClient.isConnected()) {
            log.info("ftpClient is init fail");
            return false;
        }

        if (!createFtpPath(ftpPath)) {
            log.info("ftp path init fail");
            return false;
        }

        try (FileInputStream fileInputStream = new FileInputStream(localFile)) {
            return this.ftpClient.storeFile(String.format("%s/%s", ftpPath, ftpFileName), fileInputStream);
        } catch (IOException e) {
            log.error("retrieve file error,ftpPath:{}\n error stack trace:\n{}",
                    ftpFileName, stringStackTrace(e));
            return false;
        }
    }

    /**
     * 获取ftp文件
     *
     * @param ftpFile
     * @param localPath
     * @param localFileName
     * @return
     */
    public Boolean retrieveFile(String ftpFile, String localPath, String localFileName) {

        if (ftpFile == null || ftpFile.isEmpty()) {
            log.info("ftpFile is empty");
            return false;
        }

        if (this.ftpClient == null || !ftpClient.isConnected()) {
            log.info("ftpClient is init fail");
            return false;
        }

        File file = new File(String.format("%s/%s", localPath, localFileName));
        if (!file.exists()) {
            file = createLocalFile(localPath, localFileName);
            if (file == null) {
                log.info("local file create fail,local file:{}",
                        String.format("%s/%s", localPath, localFileName));
                return false;
            }
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            return this.ftpClient.retrieveFile(ftpFile, fileOutputStream);
        } catch (IOException e) {
            log.error("retrieve file error,ftpPath:{}\n error stack trace:\n{}",
                    ftpFile, stringStackTrace(e));
            return false;
        }
    }

    /**
     * 获取ftp文件Map信息
     *
     * @param ftpFile
     * @return
     * @throws IOException
     */
    public HashMap<Integer, String> retrieveMap(String ftpFile) {

        if (ftpFile == null || ftpFile.isEmpty()) {
            log.info("ftpPath is empty");
            return null;
        }

        if (this.ftpClient == null || !ftpClient.isConnected()) {
            log.info("ftpClient is init fail");
            return null;
        }

        try (InputStream inputStream = ftpClient.retrieveFileStream(ftpFile)) {

            if (inputStream == null || ftpClient.getReplyCode() == FTPReply.FILE_UNAVAILABLE) {
                log.info("ftpPath is not exist,ftpPath:{}", ftpFile);
                return null;
            }

            int line = 0;
            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

                HashMap<Integer, String> result = new HashMap<>(16);
                String text = null;
                while ((text = bufferedReader.readLine()) != null) {
                    result.put(++line, text);
                }
                return result;

            } catch (IOException e) {
                log.error("inputStreamReader or bufferedReader read line:{} error,ftpPath:{}\n error stack trace:\n{}", line, ftpFile, stringStackTrace(e));
                return null;
            }

        } catch (IOException e) {
            log.error("inputStream read error,ftpPath:{}\n error stack trace:\n{}",
                    ftpFile, stringStackTrace(e));
            return null;
        }
    }


    @Override
    public void close() throws IOException {
        if (ftpClient != null && ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
            log.info("ftpClient disconnect success");
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
            log.info("ftpClient connect fail,code:{}", reply);
        }
        //设置文件类型
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        //ftp设置被动模式，不然会阻塞
        ftp.enterLocalPassiveMode();
        // 连接成功，准备上传
        this.ftpClient = ftp;
        log.info("ftpClient connect success");
    }

    private boolean createFtpPath(String path) {

        if (path == null || path.isEmpty()) {
            return false;
        }

        try {

            if (this.ftpClient.changeWorkingDirectory(path)) {
                return true;
            } else {
                //将路径切分，然后循环创建
                String[] paths = path.split("/");
                StringBuilder sb = new StringBuilder("/");
                for (String dir : paths) {
                    if (!dir.isEmpty() && !dir.contains(".")) {
                        sb.append(dir).append("/");
                        this.ftpClient.makeDirectory(sb.toString());
                    }
                }
                return true;
            }
        } catch (Exception e) {
            log.error("create file error,Path:{}\n error stack trace:\n{}",
                    path, stringStackTrace(e));
            return false;
        }
    }

    private static File createLocalFile(String path, String fileName) {
        if (path == null || path.isEmpty()) {
            return null;
        }
        if (fileName == null || fileName.isEmpty()) {
            return null;
        }
        //目录不存在则创建目录
        File dir = new File(path);
        if (!dir.isDirectory()) {
            if (!dir.mkdirs()) {
                return null;
            }
        }
        //文件不存在则创建文件
        File file = new File(String.format("%s/%s", path, fileName));
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    return null;
                }
            } catch (IOException e) {
                log.error("create file error,Path:{},file:{}\n error stack trace:\n{}",
                        path, fileName, stringStackTrace(e));
                return null;
            }
        }
        return file;
    }

    private static String stringStackTrace(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    private static final Logger log = LoggerFactory.getLogger(FtpService.class);

    private FTPClient ftpClient;

    private final static Integer PORT = 21;

    private final static Integer CONNECT_TIMEOUT = 120_000;

    private final static Integer DATA_TIMEOUT = 30_000;
}
