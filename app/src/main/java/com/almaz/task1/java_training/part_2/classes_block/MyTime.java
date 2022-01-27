package com.almaz.task1.java_training.part_2.classes_block;

    /*
      IV

      Составить описание класса для представления времени.
      Предусмотреть возможности установки времени и изменения его отдельных полей
      (час, минута, секунда) с проверкой допустимости вводимых значений.
      В случае недопустимых значений полей выбрасываются исключения.
      Создать методы изменения времени на заданное количество часов, минут и секунд.
     */

public class MyTime {
    private int hour;
    private int minute;
    private int second;
    private final int HOURS_IN_DAY = 24;
    private final int MINUTES_IN_HOUR = 60;
    private final int SECONDS_IN_MINUTE = 60;

    public MyTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        if (hour < 0 || hour >= HOURS_IN_DAY) {
            throw new IllegalArgumentException();
        }
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        if (minute < 0 || minute >= MINUTES_IN_HOUR) {
            throw new IllegalArgumentException();
        }
        this.minute = minute;
    }

    public int getSecond() {
        if (second < 0 || second >= SECONDS_IN_MINUTE) {
            throw new IllegalArgumentException();
        }
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void addSecond(int second) {
        if (getSecond() + second >= SECONDS_IN_MINUTE) {
            addMinute((getSecond() + second) / SECONDS_IN_MINUTE);
            setSecond((getSecond() + second) % SECONDS_IN_MINUTE);
        } else {
            setSecond(getSecond() + second);
        }

    }

    public void addMinute(int minute) {
        if (getMinute() + minute >= MINUTES_IN_HOUR) {
            addHour((getMinute() + minute) / MINUTES_IN_HOUR);
            setMinute((getMinute() + minute) % MINUTES_IN_HOUR);
        } else {
            setMinute(getMinute() + minute);
        }
    }

    public void addHour(int hour) {
        setHour((getHour() + hour) % HOURS_IN_DAY);
    }
}
