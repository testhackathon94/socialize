package com.amazon.netty.blm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AmazonCalendar {
	private DateFormat convertedFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Calendar calendar = Calendar.getInstance();
	private static AmazonCalendar instance = null;
	private AmazonCalendar(){
	}
	
	public static AmazonCalendar getInstance(){
		if(instance == null){
			synchronized (AmazonCalendar.class) {
				if(instance == null){
					instance = new AmazonCalendar();
				}
			}
			
		}
		
		return instance;
	}
	
	public String addDay(int noDay){
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, noDay);
		return convertedFormat.format(calendar.getTime());
	}
	
	public String getFirstDayOfMonth(){
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return convertedFormat.format(calendar.getTime());
	}
	
	public String getFirstDayOfNextMonth(){
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return convertedFormat.format(calendar.getTime());
	}
	
	public String getLastDayOfNextMonth(){
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return convertedFormat.format(calendar.getTime());
	}
	
	//Convert string to date with format, return with string format
	public String convertDateFormatasString(String date,String format){
		if(date!=null && format!=null){
			DateFormat realFormat = new SimpleDateFormat(format);
			try {
				Date current = realFormat.parse(date);
				return convertedFormat.format(current);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//Convert string to date, return with date format 
	public Date convertDate(String date,String format){
		if(date!=null && format!=null){
			DateFormat realFormat = new SimpleDateFormat(format);
			try {
				return realFormat.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public int getDayOfWeak(Date date){
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		return day;
	}
	
	
}
