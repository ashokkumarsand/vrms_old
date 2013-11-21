/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.dao;

import com.vrms.model.Location;
import com.vrms.model.Person;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Ashok
 */
public class RequestObject {
    
    Calendar startDate;
    Calendar endDate;
    String purpose;
    List<Location> locations;
    List<Person> persons;
    
    
}
