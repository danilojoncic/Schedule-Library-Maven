package org.concrete;

import org.abstraction.ScheduleServiceAbstraction;

public class Manager {
    private static ScheduleServiceAbstraction scheduleService;

    public static ScheduleServiceAbstraction getScheduleService() {
        return scheduleService;
    }

    public static void setScheduleService(ScheduleServiceAbstraction scheduleService) {
        Manager.scheduleService = scheduleService;
    }
}
