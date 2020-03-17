package cst438hw2.domain;

//A helper class used to return multiple values from the getTimeAndTemp method

public class TempAndTime {
	public double temp;
	public long time;
	public int timezone;

public TempAndTime(double temp, long time, int timezone){
	this.temp = temp;
	this.time = time;
	this.timezone = timezone;
}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	public String timeToString(long time) {
		return Long.toString(time);
	}

	public int getTimezone() {
		return timezone;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}
	
	@Override
	public String toString() {
		return "TempAndTime [Temp=" + temp + ", Time=" + time + ", TimeZone=" + timezone + "]";
	}
}


