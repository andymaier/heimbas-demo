package de.heimbas.demo.repositories;

import de.heimbas.demo.domain.Customer;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

  List<Customer> findAllByAgeIsBetweenOrderByAge(double start, double end);
  List<Customer> findAllByNameAndLastname(String name, String lastname);
  List<Customer> findAllBySalaryIsBetweenOrderBySalary(double start, double end);

}
