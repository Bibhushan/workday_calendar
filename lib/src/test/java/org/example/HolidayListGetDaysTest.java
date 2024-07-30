package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class HolidayListGetDaysTest {

    HolidayList hList;
    LocalDate startDate;
    LocalDate endDate;

    @BeforeEach
    void init(){
        hList = new HolidayList();         
    }

    @Test
    void testHolidayListGetHolidaysZero(){
        LocalDate startDate = LocalDate.parse("2022-06-27");
        LocalDate endDate = LocalDate.parse("2022-07-04");
        int holidays = hList.getHolidaysInRange(startDate, endDate);
        assertEquals(0, holidays);
    }

    @Test
    void testHolidayListGetWorkingDaysZeroHolidays(){
        LocalDate startDate = LocalDate.parse("2022-06-27");
        LocalDate endDate = LocalDate.parse("2022-07-04");
        int workingDays = hList.getWorkingDays(startDate, endDate);
        assertEquals(8, workingDays);
    }
    
    @Test 
    void testHolidayListGetWorkingDays(){
        hList.addHoliday(LocalDate.parse("2022-07-01"), HolidayType.HOLIDAY);
        hList.addHoliday(LocalDate.parse("2022-07-02"), HolidayType.NON_WORKING_DAY);
        hList.addHoliday(LocalDate.parse("2022-07-03"), HolidayType.NON_WORKING_DAY);       
        LocalDate startDate = LocalDate.parse("2022-06-27");
        LocalDate endDate = LocalDate.parse("2022-07-04");
        int workingDays = hList.getWorkingDays(startDate, endDate);
        assertEquals(5, workingDays);
    }

    @Test 
    void testHolidayListGetWorkingDaysLeftOnly(){
        hList.addHoliday(LocalDate.parse("2022-07-01"), HolidayType.HOLIDAY);
        hList.addHoliday(LocalDate.parse("2022-07-02"), HolidayType.NON_WORKING_DAY);
        hList.addHoliday(LocalDate.parse("2022-07-03"), HolidayType.NON_WORKING_DAY);       
        LocalDate startDate = LocalDate.parse("2022-06-27");
        LocalDate endDate = LocalDate.parse("2022-07-01");
        int workingDays = hList.getWorkingDays(startDate, endDate);
        assertEquals(4, workingDays);
    }

    @Test 
    void testHolidayListGetWorkingDaysRightOnly(){
        hList.addHoliday(LocalDate.parse("2022-07-01"), HolidayType.HOLIDAY);
        hList.addHoliday(LocalDate.parse("2022-07-02"), HolidayType.NON_WORKING_DAY);
        hList.addHoliday(LocalDate.parse("2022-07-03"), HolidayType.NON_WORKING_DAY);       
        LocalDate startDate = LocalDate.parse("2022-07-01");
        LocalDate endDate = LocalDate.parse("2022-07-04");
        int workingDays = hList.getWorkingDays(startDate, endDate);
        assertEquals(1, workingDays);
    }

    @Test 
    void testHolidayListGetWorkingDaysSubset(){
        hList.addHoliday(LocalDate.parse("2022-06-25"), HolidayType.NON_WORKING_DAY);
        hList.addHoliday(LocalDate.parse("2022-06-26"), HolidayType.NON_WORKING_DAY);
        hList.addHoliday(LocalDate.parse("2022-07-01"), HolidayType.HOLIDAY);
        hList.addHoliday(LocalDate.parse("2022-07-02"), HolidayType.NON_WORKING_DAY);
        hList.addHoliday(LocalDate.parse("2022-07-03"), HolidayType.NON_WORKING_DAY);       
        LocalDate startDate = LocalDate.parse("2022-06-27");
        LocalDate endDate = LocalDate.parse("2022-07-01");
        int workingDays = hList.getWorkingDays(startDate, endDate);
        assertEquals(4, workingDays);
    }

}
