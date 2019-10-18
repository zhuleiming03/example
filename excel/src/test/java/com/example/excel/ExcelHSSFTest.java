package com.example.excel;

import com.example.excel.domain.DeductPlatformPO;
import com.example.excel.service.HSSFService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ExcelHSSFTest {

    @Autowired
    private HSSFService service;

    @Test
    public void importTest() {

        try {
            String curruentUrl = System.getProperty("user.dir");
            String path = String.format("%s/templet/DeductPlatform.xls", curruentUrl);

            log.info(">>path:{}", path);

            List<DeductPlatformPO> deductPlatformPOS = new LinkedList<>();

            DeductPlatformPO po1 = new DeductPlatformPO();
            po1.setPlatformCname("支付宝");
            po1.setPlatformEname("Alipay");
            po1.setReceiveType(110);
            po1.setCreatetime(new Date());
            po1.setValid(true);
            deductPlatformPOS.add(po1);

            DeductPlatformPO po2 = new DeductPlatformPO();
            po2.setPlatformCname("百度");
            po2.setPlatformEname("BaiDu");
            po2.setReceiveType(104);
            po2.setCreatetime(new Date());
            po2.setValid(false);
            deductPlatformPOS.add(po2);

            HSSFService.exportExcel(path, deductPlatformPOS);

            log.info(">>successful!");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
