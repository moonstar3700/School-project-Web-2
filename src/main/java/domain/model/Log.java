package domain.model;

import java.time.LocalTime;

public class Log {
    private LocalTime time;
    private String value;

    public Log(LocalTime time, String value) {
        this.time = time;
        this.value = value;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
