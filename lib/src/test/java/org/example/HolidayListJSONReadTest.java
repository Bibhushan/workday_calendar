package org.example;

import org.example.HolidayList;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.util.TextBuffer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;


public class HolidayListJSONReadTest {

    HolidayList hList;
	
    @BeforeEach
    void init(){
        hList = new HolidayList();		
    }

    @Test
    void testHolidayListToJson() throws IOException, URISyntaxException{
        Path filePath = Paths.get(Thread.currentThread().getContextClassLoader().getResource("holidayList.json").toURI());
        String json = new String(Files.readAllBytes(filePath));
        assertDoesNotThrow(()->hList = new HolidayList(json));        
    }
    
}
