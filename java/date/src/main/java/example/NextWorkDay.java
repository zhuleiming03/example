package example;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextWorkDay implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        //读取当前星期几
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayToAdd = 1;
        switch (dow) {
            case FRIDAY:
                dayToAdd = 3;
                break;
            case SATURDAY:
                dayToAdd = 2;
                break;
            default:
                break;
        }
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }
}
