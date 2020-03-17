package cst438hw2.domain;

//A helper class used to return multiple values from the getTimeAndTemp method

public class TempAndTime {
	public double temp;
	public String time;
	public int timezone;
	
	public TempAndTime(double temp, String time, int timezone){
		this.temp = temp;
		this.time = time;
		this.timezone = timezone;
	}
 }
