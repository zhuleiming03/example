package example.spring.boot.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.spring.boot.json.enums.BillTypeEnum;
import example.spring.boot.json.po.BillPO;
import example.spring.boot.json.po.SubjectPO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 这是个综合实例
 */
@SpringBootTest
class JsonBillTests {

    @Test
    void billToString() throws JsonProcessingException {

        List<BillPO> pos = initBill();

        String json = objectMapper.writeValueAsString(pos);
        System.out.println(json);
    }

    @Test
    void stringToBill() throws JsonProcessingException {

        String json = "[{\"billId\":201901,\"billType\":\"Normal\",\"billIndex\":1,\"subjects\":[{\"billId\":201901,\"dueAmount\":1535.51,\"receiveAmount\":0,\"name\":\"Capital\",\"billDay\":\"2019-12-06\",\"deductTime\":\"11:28\",\"createTime\":\"2019-12-06 11:28:14\",\"updateTime\":\"2019-12-06 11:28:14\",\"vaild\":true,\"ID\":1575602894731},{\"billId\":201901,\"dueAmount\":256.19,\"receiveAmount\":0,\"name\":\"Interest\",\"billDay\":\"2019-12-06\",\"deductTime\":\"11:28\",\"createTime\":\"2019-12-06 11:28:14\",\"updateTime\":\"2019-12-06 11:28:14\",\"vaild\":true,\"ID\":1575602894736}]},{\"billId\":201902,\"billType\":\"Normal\",\"billIndex\":2,\"subjects\":[{\"billId\":201902,\"dueAmount\":1985.51,\"receiveAmount\":0,\"name\":\"Capital\",\"billDay\":\"2019-12-06\",\"deductTime\":\"11:28\",\"createTime\":\"2019-12-06 11:28:14\",\"updateTime\":\"2019-12-06 11:28:14\",\"vaild\":true,\"ID\":1575602894736},{\"billId\":201902,\"dueAmount\":535.51,\"receiveAmount\":0,\"name\":\"ServiceFee\",\"billDay\":\"2019-12-06\",\"deductTime\":\"11:28\",\"createTime\":\"2019-12-06 11:28:14\",\"updateTime\":\"2019-12-06 11:28:14\",\"vaild\":true,\"ID\":1575602894736}]},{\"billId\":201903,\"billType\":\"Advance\",\"billIndex\":3,\"subjects\":[{\"billId\":201903,\"dueAmount\":325.51,\"receiveAmount\":0,\"name\":\"Capital\",\"billDay\":\"2019-12-06\",\"deductTime\":\"11:28\",\"createTime\":\"2019-12-06 11:28:14\",\"updateTime\":\"2019-12-06 11:28:14\",\"vaild\":true,\"ID\":1575602894736},{\"billId\":201903,\"dueAmount\":16.51,\"receiveAmount\":0,\"name\":\"GuaranteeFee\",\"billDay\":\"2019-12-06\",\"deductTime\":\"11:28\",\"createTime\":\"2019-12-06 11:28:14\",\"updateTime\":\"2019-12-06 11:28:14\",\"vaild\":true,\"ID\":1575602894736}]},{\"billId\":201904,\"billType\":\"Normal\",\"billIndex\":4,\"subjects\":[{\"billId\":201904,\"dueAmount\":15684.51,\"receiveAmount\":0,\"name\":\"Capital\",\"billDay\":\"2019-12-06\",\"deductTime\":\"11:28\",\"createTime\":\"2019-12-06 11:28:14\",\"updateTime\":\"2019-12-06 11:28:14\",\"vaild\":true,\"ID\":1575602894736},{\"billId\":201904,\"dueAmount\":985.10,\"receiveAmount\":0,\"name\":\"PunitiveInterest\",\"billDay\":\"2019-12-06\",\"deductTime\":\"11:28\",\"createTime\":\"2019-12-06 11:28:14\",\"updateTime\":\"2019-12-06 11:28:14\",\"vaild\":true,\"ID\":1575602894736}]}]";

        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, BillPO.class);
        List<BillPO> billPOList = objectMapper.readValue(json, javaType);
        System.out.println(billPOList);
    }


    @Resource
    private ObjectMapper objectMapper;

    private List<BillPO> initBill() {

        List<BillPO> billPOList = new LinkedList<>();

        BillPO billPO1 = new BillPO();
        billPO1.setBillId(201901);
        billPO1.setBillIndex(1);
        billPO1.setBillType(BillTypeEnum.Normal);
        List<SubjectPO> b1_subject = new LinkedList<>();
        SubjectPO b1_Capital = new SubjectPO(billPO1.getBillId(), 1535.52, "Capital");
        b1_subject.add(b1_Capital);
        SubjectPO interest = new SubjectPO(billPO1.getBillId(), 256.2, "Interest");
        b1_subject.add(interest);
        billPO1.setSubjects(b1_subject);
        billPOList.add(billPO1);

        BillPO billPO2 = new BillPO();
        billPO2.setBillId(201902);
        billPO2.setBillIndex(2);
        billPO2.setBillType(BillTypeEnum.Normal);
        List<SubjectPO> b2_subject = new LinkedList<>();
        SubjectPO b2_Capital = new SubjectPO(billPO2.getBillId(), 1985.52, "Capital");
        b2_subject.add(b2_Capital);
        SubjectPO serviceFee = new SubjectPO(billPO2.getBillId(), 535.52, "ServiceFee");
        b2_subject.add(serviceFee);
        billPO2.setSubjects(b2_subject);
        billPOList.add(billPO2);

        BillPO billPO3 = new BillPO();
        billPO3.setBillId(201903);
        billPO3.setBillIndex(3);
        billPO3.setBillType(BillTypeEnum.Advance);
        List<SubjectPO> b3_subject = new LinkedList<>();
        SubjectPO b3_Capital = new SubjectPO(billPO3.getBillId(), 325.52, "Capital");
        b3_subject.add(b3_Capital);
        SubjectPO guaranteeFee = new SubjectPO(billPO3.getBillId(), 16.51, "GuaranteeFee");
        b3_subject.add(guaranteeFee);
        billPO3.setSubjects(b3_subject);
        billPOList.add(billPO3);

        BillPO billPO4 = new BillPO();
        billPO4.setBillId(201904);
        billPO4.setBillIndex(4);
        billPO4.setBillType(BillTypeEnum.Normal);
        List<SubjectPO> b4_subject = new LinkedList<>();
        SubjectPO b4_Capital = new SubjectPO(billPO4.getBillId(), 15684.51, "Capital");
        b4_subject.add(b4_Capital);
        SubjectPO PunitiveInterest = new SubjectPO(billPO4.getBillId(), 985.1, "PunitiveInterest");
        b4_subject.add(PunitiveInterest);
        billPO4.setSubjects(b4_subject);
        billPOList.add(billPO4);

        return billPOList;
    }

}
