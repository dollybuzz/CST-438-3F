package cst438hw2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Gathers the countryCode and countryName values from the country table, does not need to include all columns from table

@Repository
public interface CountryRepository extends JpaRepository<Country, String>{
	Country findByCode(String code);
}
