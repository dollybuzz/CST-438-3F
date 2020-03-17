package cst438hw2;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Retrieves the countryCode and countryName, does not need to include all columns of the country table

@Entity
@Table(name="country")
public class Country {

	@Id
	private String countryCode;
	
	private String countryName;
	
	public Country() {}		//default constructor
	
	public Country(String countryCode, String countryName) {
		this.countryCode = countryCode;
		this.countryName = countryName;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	@Override
	public String toString() {
		return "Country [Code=" + countryCode + ", Name=" + countryName + "]";
	}
	
}
