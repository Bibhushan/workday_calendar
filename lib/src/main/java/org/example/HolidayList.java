package org.example;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@JsonIgnoreProperties(ignoreUnknown = true)
public class HolidayList {

    private Map<LocalDate, HolidayType> holidayList = new HashMap<LocalDate, HolidayType>();
    private ObjectMapper objectMapper;
    private ObjectWriter objectWriter;

    public Map<LocalDate, HolidayType> getHolidayList() {
        return holidayList;
    }  
    
    public void setHolidayList(Map<LocalDate, HolidayType> holidayList) {
        this.holidayList = holidayList;
    }

    public HolidayList(){
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());  
        objectWriter = objectMapper.writer();      
    }

    public HolidayList(String Json) throws Exception{
        this();
        fromJson(Json);
    }

    private void fromJson(String jsonText) throws Exception{
        Map<LocalDate, HolidayType> hList;
        try {
            hList = objectMapper.readValue(jsonText, new TypeReference<Map<LocalDate, HolidayType>>(){});
            this.holidayList = hList;
        } catch (JsonMappingException e) {            
            e.printStackTrace();
            throw new Exception("Please check the Json format. The key should be date and value should be one of the HolidayTypes.");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new Exception("Error occured when processing the json to Holiday List.");
        }

    }

    public void addHoliday(LocalDate date, HolidayType type){
        if (holidayList.containsKey(date)){
            if (holidayList.get(date).equals(type)){
                throw new IllegalArgumentException("Holiday is already present in holiday list.");
            }
            else {
                throw new IllegalArgumentException("Date is already present in holiday list with a different type.");
            }            
        }
        else {
            holidayList.put(date, type);
        }
    }

    public void udateHoliday(LocalDate date, HolidayType type){
        if (holidayList.containsKey(date)){
            if (holidayList.get(date) != type){
                holidayList.put(date, type);
            }
            else {
                throw new IllegalArgumentException("The holiday already has the specified type. Not updating.");
            }
        } 
        else {
            throw new IllegalArgumentException("The specified date does not exist in the holiday list");
        }
    }

    public void removeHoliday(LocalDate date, HolidayType type){
        if (holidayList.containsKey(date)){
            if (holidayList.get(date) != type) throw new IllegalArgumentException("Holiday type mismatch: holiday or a different type exists for the given date.");
            holidayList.remove(date);
        }  
        else {
            throw new IllegalArgumentException("The specified date does not exist in the holiday list");
        }
    }

    public Integer getHolidaysInRange(LocalDate startDate, LocalDate endDate){
        Integer holidayCount = 0;
        for (LocalDate d = startDate; d.isBefore(endDate); d = d.plusDays(1)){
            if (holidayList.containsKey(d)){
                holidayCount++;
            }
        }

        if (holidayList.containsKey(endDate)){
            holidayCount++ ;
        }

        return holidayCount;
        
    }

    public int getWorkingDays(LocalDate startDate, LocalDate endDate){
        int daysInRange = startDate.until(endDate).getDays() + 1;
        return daysInRange - getHolidaysInRange(startDate, endDate);
    }
    
    public String toJson() {

        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(holidayList);
            return json;
        }
        catch (JsonParseException e){
            System.out.println("Error occured when writing holiday list to Json.");
        }
        catch (Exception e){
            System.out.println("Unknown exception occured when writing holiday list to json");
        }

        return null;

    }

    @Override
    public String toString() {
        return "HolidayList [holidayList=" + holidayList + "]";
    }
}
