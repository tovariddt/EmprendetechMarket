package com.emprendetech.market.utils;

import java.sql.Timestamp;
import java.util.Date;

public class Utils {
	
	public  Timestamp  currentDate () {
	Date date = new Date();  
    Timestamp ts=new Timestamp(date.getTime());
	return ts;  
    
	
	
	
	}

}
