package example.spring.boot.json.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BillTypeEnum {

    /**
     * 普通
     */
    Normal(1,"Normal"),
    /**
     * 提前结清
     */
    Advance(3,"Advance");

    private Integer code;

    private String name;

    private BillTypeEnum(Integer code,String name) {
        this.code = code;
        this.name = name;
    }

    private Integer getCode(){
        return this.code;
    }

    @JsonValue
    private String getName(){
        return this.name;
    }

    private void setCode(Integer code) {
        this.code = code;
    }

    private void setName(String name) {
        this.name = name;
    }
}
