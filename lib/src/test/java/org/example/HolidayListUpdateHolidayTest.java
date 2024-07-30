package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;


public class HolidayListUpdateHolidayTest {
    
    HolidayList hList;
	LocalDate hDate;
	HolidayType hType;

    @BeforeEach
    void init(){
        hList = new HolidayList();
		hDate = LocalDate.parse("2024-01-01");
		hType = HolidayType.HOLIDAY;
		hList.addHoliday(hDate, hType);	
    }

    @Test
    void testHolidayListUpdateHolidayNoException(){
        assertDoesNotThrow(()->hList.udateHoliday(hDate, HolidayType.NON_WORKING_DAY));
    }

    @Test
    void testHolidayListUpdateHolidayValidType(){
        hList.udateHoliday(hDate, HolidayType.NON_WORKING_DAY);
        assertEquals(HolidayType.NON_WORKING_DAY, hList.getHolidayList().get(hDate), "Expected NON_WORKING_DAY as holiday type, found " + hList.getHolidayList().get(hDate));
    }

    @Test
    void testHolidayListUpdateInvalidDate(){
        LocalDate d = LocalDate.parse("2024-12-31");
        assertThrows(IllegalArgumentException.class, ()->hList.udateHoliday(d, hType));
    }

    @Test 
    void testHolidayListUpdateSameType(){
        assertThrows(IllegalArgumentException.class, ()->hList.udateHoliday(hDate, hType));
    }

}
