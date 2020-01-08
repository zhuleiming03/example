package example.common;

import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class FtpTest {

    @Test
    public void ftpDownloadByMap() {

        String ftpHost = "localhost";
        String ftpAccount = "sa";
        String ftpPassword = "sa";
        String ftpPath = "/VFPAdvanceDeductFlow/YLYH/20191023/COMPENSATORY_REPO_20191023.txt";

        try (FtpService ftpService = new FtpService(ftpHost, ftpAccount, ftpPassword)) {

            Map<Integer, String> map = ftpService.getFtpStringMap(ftpPath);

            for (Map.Entry item : map.entrySet()) {
                System.out.println(String.format("Line:%3s >> %s", item.getKey(), item.getValue()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
