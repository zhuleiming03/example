package example.common;

import java.io.IOException;
import java.util.Map;

public class FtpInstance {

    public static void instance() {

        String ftpHost = "localhost";
        String ftpAccount = "sa";
        String ftpPassword = "sa";


        String localPath = "./localFile/download";
        String localFileName = "FtpFile.txt";
        String localFile = "./localFile/Demo.txt";

        String ftpPath = "/aa/bb";
        String ftpFileName = "FtpFile.txt";
        String ftpFile = String.format("%s/%s", ftpPath, ftpFileName);


        try (FtpService ftpService = new FtpService(ftpHost, ftpAccount, ftpPassword)) {

            //1、上传本地文件 Demo.txt
            Boolean result = ftpService.storeFile(ftpFileName, ftpPath, localFile);
            if (result) {
                System.out.println("1、local file upload success");
            }

            //2、下载Ftp文件 FtpFile.txt
            result = ftpService.retrieveFile(ftpFile, localPath, localFileName);
            if (result) {
                System.out.println("2、ftp file download success");
            }

            //3、获取Ftp文件Map
            Map<Integer, String> map = ftpService.retrieveMap(ftpFile);
            if (map != null) {
                System.out.println("3、ftp file content:");
                for (Map.Entry item : map.entrySet()) {
                    System.out.println(String.format("Line:%3s >> %s", item.getKey(), item.getValue()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
