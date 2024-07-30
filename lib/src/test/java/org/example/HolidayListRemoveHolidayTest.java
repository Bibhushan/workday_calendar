package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;

public class HolidayListRemoveHolidayTest {

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
	void testRemoveHolidayValidNoException(){
		assertDoesNotThrow(()->hList.removeHoliday(hDate, hType));
	}

	@Test
	void testRemoveHolidayValidCount(){
		hList.removeHoliday(hDate, hType);
		assertEquals(0, hList.getHolidayList().size(), "Expected zero holidays in holiday list, found " + hList.getHolidayList().size());
	}

	@Test 
	void testRemoveHolidayInvalidDate(){
		LocalDate d = LocalDate.parse("2024-12-31");
		assertThrows(IllegalArgumentException.class, ()->hList.removeHoliday(d, hType));
	}

	@Test 
	void testRemoveHolidayInvalidType(){
		HolidayType t = HolidayType.NON_WORKING_DAY;
		assertThrows(IllegalArgumentException.class, ()->hList.removeHoliday(hDate, t));
	}
}