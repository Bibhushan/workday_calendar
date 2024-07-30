package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;


public class HolidayListJSONWriteTest {

    HolidayList hList;
    static String expectedJson = "{\r\n  \"2023-12-31\" : \"NON_WORKING_DAY\",\r\n  \"2023-12-30\" : \"NON_WORKING_DAY\",\r\n  \"2024-01-14\" : \"NON_WORKING_DAY\",\r\n  \"2024-01-13\" : \"NON_WORKING_DAY\",\r\n  \"2023-12-25\" : \"HOLIDAY\",\r\n  \"2024-01-07\" : \"NON_WORKING_DAY\",\r\n  \"2024-01-06\" : \"NON_WORKING_DAY\",\r\n  \"2024-01-01\" : \"HOLIDAY\"\r\n}";
	
    @BeforeEach
    void init(){
        hList = new HolidayList();	
        hList.addHoliday(LocalDate.parse("2023-12-25"), HolidayType.HOLIDAY);
        hList.addHoliday(LocalDate.parse("2023-12-30"), HolidayType.NON_WORKING_DAY);	
        hList.addHoliday(LocalDate.parse("2023-12-31"), HolidayType.NON_WORKING_DAY);
        hList.addHoliday(LocalDate.parse("2024-01-01"), HolidayType.HOLIDAY);
        hList.addHoliday(LocalDate.parse("2024-01-06"), HolidayType.NON_WORKING_DAY);	
        hList.addHoliday(LocalDate.parse("2024-01-07"), HolidayType.NON_WORKING_DAY);
        hList.addHoliday(LocalDate.parse("2024-01-13"), HolidayType.NON_WORKING_DAY);	
        hList.addHoliday(LocalDate.parse("2024-01-14"), HolidayType.NON_WORKING_DAY);	
    }

    @Test
    void testHolidayListToJsonNoException(){
        assertDoesNotThrow(()->hList.toJson());        
    }

    @Test
    void testHolidayListToJson(){
        assertEquals(expectedJson, hList.toJson(), "The converted JSON does not match with expected Json.");
    }
    
}
