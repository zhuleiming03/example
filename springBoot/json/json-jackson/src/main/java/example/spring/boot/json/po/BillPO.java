package example.spring.boot.json.po;

import example.spring.boot.json.enums.BillTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public class BillPO {

    private Integer billId;

    private BillTypeEnum billType;

    private Integer billIndex;

    private List<SubjectPO> subjects;
}
