package cst438hw2.domain;

//Helper class for City Entity, used to return multiple values from the getTimeAndTemp method within weatherService

public class TempAndTime {
	public double temp;
	public long time;
	public int timezone;

public TempAndTime(double temp, long time, int timezone){
	this.temp = temp;
	this.time = time;
	this.timezone = timezone;
}
	
	public String timeToString() {
		return Long.toString(time);
	}
}


