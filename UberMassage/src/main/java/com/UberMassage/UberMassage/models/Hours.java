package com.UberMassage.UberMassage.models;

import javax.persistence.*;

@Embeddable
public class Hours {

    int startTime;
    int finishTime;

    boolean isStartAM;
    boolean isFinishAM;

    public Hours() {}

    public Hours(int startTime, int finishTime, boolean isStartAM,
                 boolean isFinishAM) {

        this.isStartAM = isStartAM;
        this.isFinishAM = isFinishAM;

        this.startTime = turnMilitaryTime(startTime, isStartAM);
        this.finishTime = turnMilitaryTime(finishTime, isFinishAM);

    }

    private int turnMilitaryTime(int time, boolean am) {
        int militaryTime;

        if (am == true) {
            if (time == 12) {
                militaryTime = 0;
            } else {
                militaryTime = time;
            }
        } else {
            if (time == 12) {
                militaryTime = 12;
            } else {
                militaryTime = time + 12;
            }
        }

        return militaryTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime, boolean am) {
        this.startTime = turnMilitaryTime(startTime, am);
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime, boolean am) {
        this.finishTime = turnMilitaryTime(finishTime, am);
    }
}
