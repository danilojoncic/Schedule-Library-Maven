package org.hardcode;

import org.concrete.Row;
import org.concrete.Schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//lets hardcode it
public class RAFService {
    public static void implementDesiredFormat(Schedule schedule,
                                              LocalDate startDate,
                                              LocalDate endDate,
                                              boolean simpleImplementation) {
        if (simpleImplementation) {
            schedule.addHeaderColumn("Pocetak");
            schedule.addHeaderColumn("Kraj");

            List<Row> rows = schedule.getAllRows();
            for (Row row : rows) {
                int startDateIndex = schedule.getHeader().getContents().size() - 2;
                int endDateIndex = schedule.getHeader().getContents().size() - 1;
                schedule.modifyRowField(row, startDateIndex, startDate.toString());
                schedule.modifyRowField(row, endDateIndex, endDate.toString());
            }
        } else {
            schedule.addHeaderColumn("Datum");

            List<Row> newRows = new ArrayList<>();
            List<Row> rows = schedule.getAllRows();
            for (Row row : rows) {
                List<LocalDate> eventDates = calculateEventDates(startDate, endDate, row);
                int eventDateIndex = schedule.getHeader().getContents().size() - 1;

                for (LocalDate eventDate : eventDates) {
                    Row newRow = new Row(new ArrayList<>(row.getContents()));
                    schedule.modifyRowField(newRow, eventDateIndex, eventDate.toString());
                    newRows.add(newRow);
                }
            }
            schedule.setAllRows(newRows);
            schedule.reformUnifiedMap();
        }
    }

    private static List<LocalDate> calculateEventDates(LocalDate startDate, LocalDate endDate, Row row) {
        List<LocalDate> dates = new ArrayList<>();
        String dayOfWeekString = row.getContents().get(4).trim();
        DayOfWeek dayOfWeek = convertToDayOfWeek(dayOfWeekString);

        LocalDate current = startDate.with(dayOfWeek);
        if (current.isBefore(startDate)) {
            current = current.plusWeeks(1);
        }
        while (!current.isAfter(endDate)) {
            dates.add(current);
            current = current.plusWeeks(1);
        }
        return dates;
    }

    private static DayOfWeek convertToDayOfWeek(String day) {
        if (day.toUpperCase().contains("PON")) return DayOfWeek.MONDAY;
        if (day.toUpperCase().contains("UTO")) return DayOfWeek.TUESDAY;
        if (day.toUpperCase().contains("SRE")) return DayOfWeek.WEDNESDAY;
        if (day.toUpperCase().contains("ČET")) return DayOfWeek.THURSDAY;
        if (day.toUpperCase().contains("PET")) return DayOfWeek.FRIDAY;
        throw new IllegalArgumentException("Unknown day: " + day);
    }
}
