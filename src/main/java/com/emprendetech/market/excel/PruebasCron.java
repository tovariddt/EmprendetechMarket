package com.emprendetech.market.excel;

import org.springframework.scheduling.annotation.Scheduled;

public class PruebasCron {

	
	@Scheduled(fixedDelay = 1000)
	public void scheduleFixedDelayTask() {
	    System.out.println(
	      "Fixed delay task - " + System.currentTimeMillis() / 1000);
	}
	
	@Scheduled(cron = "0 15 10 15 * ?")
	public void scheduleTaskUsingCronExpression() {
	 
	    long now = System.currentTimeMillis() / 1000;
	    System.out.println(
	      "schedule tasks using cron jobs - " + now);
	}
	
	@Scheduled(fixedDelay = 1000, initialDelay = 1000)
	public void scheduleFixedRateWithInitialDelayTask() {
	 
	    long now = System.currentTimeMillis() / 1000;
	    System.out.println(
	      "Fixed rate task with one second initial delay - " + now);
	}
}
