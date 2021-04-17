package internship.rest.car.dao;

import internship.rest.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDAO extends JpaRepository<Car, Long> {
}
