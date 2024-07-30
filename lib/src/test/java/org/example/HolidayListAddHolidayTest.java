package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;

public class HolidayListAddHolidayTest {

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
	void testAddHolidayCount() {
        // hList.addHoliday(hDate, hType);		
		assertEquals(1, hList.getHolidayList().size(),"The holiday list should have one holiday added");		
	}

	@Test
	void testAddHolidayDate(){
		assertTrue(hList.getHolidayList().containsKey(hDate), "Added holiday should be present in the holiday list");		
	}

	@Test
	void testAddHolidayType(){
		HolidayType t = hList.getHolidayList().get(hDate);
		assertEquals(hType, hList.getHolidayList().get(hDate), "The holiday should be of type HOLIDAY, found " + t);
	}

	@Test
	void testAddHolidayExistingDate(){
		assertThrows(IllegalArgumentException.class, ()->hList.addHoliday(hDate, hType));
	}

	@Test
	void testAddHolidayExistingDateDifferentType(){
		HolidayType t = HolidayType.NON_WORKING_DAY;
		assertThrows(IllegalArgumentException.class, ()->hList.addHoliday(hDate, t));
	}
   
}
