package cst438hw2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Retrieves the data from city table

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	List<City> findByName(String name);
}
