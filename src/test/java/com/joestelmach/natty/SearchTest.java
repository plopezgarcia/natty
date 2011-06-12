package com.joestelmach.natty;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.joestelmach.natty.CalendarSource;
import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

/**
 * 
 * @author Joe Stelmach
 */
public class SearchTest extends AbstractTest {
  
  @Test
  public void test() throws Exception {
    Date reference = DateFormat.getDateInstance(DateFormat.SHORT).parse("2/20/2011");
    CalendarSource.setBaseDate(reference);
    
    Parser parser = new Parser();
    List<DateGroup> groups = parser.parse("golf tomorrow at 9 AM at pebble beach");
    Assert.assertEquals(1, groups.size());
    DateGroup group = groups.get(0);
    Assert.assertEquals(1, group.getLine());
    Assert.assertEquals(5, group.getPosition());
    Assert.assertEquals(1, group.getDates().size());
    validateDate(group.getDates().get(0), 2, 21, 2011);
    validateTime(group.getDates().get(0), 9, 0, 0);
    
    groups = parser.parse("golf with friends tomorrow at 10 ");
    Assert.assertEquals(1, groups.size());
    group = groups.get(0);
    Assert.assertEquals(1, group.getLine());
    Assert.assertEquals(18, group.getPosition());
    Assert.assertEquals(1, group.getDates().size());
    validateDate(group.getDates().get(0), 2, 21, 2011);
    validateTime(group.getDates().get(0), 10, 0, 0);
    
    parser = new Parser();
    groups = parser.parse("golf with freinds tomorrow at 9 or Thursday at 10 am");
    Assert.assertEquals(1, groups.size());
    List<Date> dates = groups.get(0).getDates();
    Assert.assertEquals(2, dates.size());
    validateDate(dates.get(0), 2, 21, 2011); 
    validateTime(dates.get(0), 9, 0, 0); 
    validateDate(dates.get(1), 2, 24, 2011); 
    validateTime(dates.get(1), 10, 0, 0); 
    
    groups = parser.parse("golf with friends tomorrow at 9 or Thursday at 10");
    Assert.assertEquals(1, groups.size());
    dates = groups.get(0).getDates();
    Assert.assertEquals(2, dates.size());
    validateDate(dates.get(0), 2, 21, 2011); 
    validateTime(dates.get(0), 9, 0, 0); 
    validateDate(dates.get(1), 2, 24, 2011); 
    validateTime(dates.get(1), 10, 0, 0); 
    
    groups = parser.parse("I want to go to park tomorrow and then email john@aol.com");
    Assert.assertEquals(1, groups.size());
    dates = groups.get(0).getDates();
    Assert.assertEquals(1, dates.size());
    validateDate(dates.get(0), 2, 21, 2011); 
    
    groups = parser.parse("I want to pay off all my debt in the next two years.");
    Assert.assertEquals(1, groups.size());
    dates = groups.get(0).getDates();
    Assert.assertEquals(2, dates.size());
    validateDate(dates.get(0), 2, 20, 2011); 
    validateDate(dates.get(1), 2, 20, 2013); 
    
    groups = parser.parse("I want to purchase a car in the next month.");
    Assert.assertEquals(1, groups.size());
    dates = groups.get(0).getDates();
    Assert.assertEquals(1, dates.size());
    validateDate(dates.get(0), 3, 20, 2011); 
    
    groups = parser.parse("I want to plan a get-together with my friends for this Friday.");
    Assert.assertEquals(1, groups.size());
    dates = groups.get(0).getDates();
    Assert.assertEquals(1, dates.size());
    validateDate(dates.get(0), 2, 25, 2011); 
    
    groups = parser.parse("I want to lose five pounds in the next two months.");
    Assert.assertEquals(1, groups.size());
    dates = groups.get(0).getDates();
    Assert.assertEquals(2, dates.size());
    validateDate(dates.get(0), 2, 20, 2011); 
    validateDate(dates.get(1), 4, 20, 2011); 
    
    groups = parser.parse("I want to finalize my college schedule by next week.");
    Assert.assertEquals(1, groups.size());
    dates = groups.get(0).getDates();
    Assert.assertEquals(1, dates.size());
    validateDate(dates.get(0), 2, 27, 2011); 
    
    groups = parser.parse("I want to read this weekend.");
    Assert.assertEquals(1, groups.size());
    dates = groups.get(0).getDates();
    Assert.assertEquals(1, dates.size());
    validateDate(dates.get(0), 2, 26, 2011); 
    
    groups = parser.parse("I want to travel a big chunk of world next year.");
    Assert.assertEquals(1, groups.size());
    dates = groups.get(0).getDates();
    Assert.assertEquals(1, dates.size());
    validateDate(dates.get(0), 2, 20, 2012); 
    
    groups = parser.parse("last 2 weeks");
    Assert.assertEquals(1, groups.size());
    dates = groups.get(0).getDates();
    Assert.assertEquals(2, dates.size());
    validateDate(dates.get(0), 2, 20, 2011); 
    validateDate(dates.get(1), 2, 6, 2011); 
    
    groups = parser.parse("last 5 years");
    Assert.assertEquals(1, groups.size());
    dates = groups.get(0).getDates();
    Assert.assertEquals(2, dates.size());
    validateDate(dates.get(0), 2, 20, 2011); 
    validateDate(dates.get(1), 2, 20, 2006); 
    
    groups = parser.parse("next 5 years");
    Assert.assertEquals(1, groups.size());
    dates = groups.get(0).getDates();
    Assert.assertEquals(2, dates.size());
    validateDate(dates.get(0), 2, 20, 2011); 
    validateDate(dates.get(1), 2, 20, 2016); 
  }
}
