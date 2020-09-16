package de.heimbas.demo.controller;

import de.heimbas.demo.domain.Customer;
import de.heimbas.demo.repositories.CustomerRepo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class CustomerController {

  @Autowired
  CustomerRepo customerRepo;

  @PostMapping("{count}")
  public List<Customer> createCustomer(@PathVariable int count) {

    List<Customer> list = new ArrayList<>();

    for (int i = 0; i < count; i++) {
      Customer customer = new Customer();
      //customer.setId(UUID.randomUUID().toString());
      customer.setName("Name_" + Math.random());
      customer.setLastname("Lastname_" + Math.random());
      customer.setAge(Math.random());
      customer.setMood("Good!");
      customer.setSalary(100.0 + i * 2);
      list.add(customerRepo.save(customer));
    }
    return list;
  }

  @GetMapping
  public List<Customer> get() {
    return customerRepo.findAll();
  }

  @GetMapping("{uuid}")
  public Customer get(@PathVariable String uuid) {
    System.out.println("Test: " + uuid);
    return customerRepo.findById(Integer.valueOf(uuid)).orElse(new Customer());
  }

  @DeleteMapping("{uuid}")
  public void delete(@PathVariable String uuid) {
    customerRepo.delete(get(uuid));
  }

  @GetMapping("{start}/{end}")
  public List<Customer> between(@PathVariable double start, @PathVariable double end) {
    return customerRepo.findAllByAgeIsBetweenOrderByAge(start, end);
  }

  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Alles war erfolgreich"),
      @ApiResponse(code = 404, message = "nicht möglich"),
      @ApiResponse(code = 401, message = "Du bist nicht autorisiert"),
      @ApiResponse(code = 403, message = "Du darfs nicht.")
  })
  @ApiOperation("Diese Methode liefert die Kunden mit dem Gehaltsfilter von/bis.")
  @GetMapping("/salary/{von}/{bis}")
  public List<Customer> getByFirstLastName(@PathVariable double von, @PathVariable double bis) {
    return customerRepo.findAllBySalaryIsBetweenOrderBySalary(von, bis);
  }
}
