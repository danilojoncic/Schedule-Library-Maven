package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.abstraction.ScheduleServiceAbstraction;
import org.concrete.Schedule;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Keeper {
    private static Keeper instance = null;
    private Schedule schedule;
    private ScheduleServiceAbstraction scheduleServiceAbstraction;
    private String filterString = "";

    private Keeper(){}
    public static Keeper getInstance(){
        if(instance == null)instance = new Keeper();
        return instance;
    }
}
