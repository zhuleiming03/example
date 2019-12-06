package example.java.enums;

import org.junit.Test;

public class GenderEnumTest {

    @Test
    public void baseTest() {
        System.out.println(">>" + GenderEnum.FEMALE);
        System.out.println(">>" + GenderEnum.MALE.getCode());
        System.out.println(">>" + GenderEnum.MALE.getName());
        System.out.println(">>" + GenderEnum.MALE.getValueByCode(1));
    }
}
