package com.example.java.enumerate;

public enum MonthEnum {

    JANUARRY(1,"JaN."),
    FEBRUARY(2,"Feb."),
    MARCH(3,"Mar."),
    APRIL(4,"Apr."),
    MAY(5,"May."),
    JUNE(6,"Jun."),
    JULY(7,"Jul."),
    AUGUST(8,"Aug."),
    SEPTEMBER(9,"Sept."),
    OCTOBER(10,"Oct."),
    NOVEMBER(11,"Nov."),
    DECEMBER(12,"Dec.");

    private Integer code;
    private String description;

    MonthEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static String getValueByCode(Integer code) {
        for (MonthEnum deductStatusEnum : MonthEnum.values()) {
            if (code.equals(deductStatusEnum.getCode())) {
                return deductStatusEnum.getDescription();
            }
        }
        return null;
    }
}
