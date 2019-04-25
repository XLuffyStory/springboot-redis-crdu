package com.redis.crdu;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
 
@RestController
public class CustomerResource {
 
  @Autowired
  private CustomerRepository customerRepository;
 
  @RequestMapping("/save")
  public Map<Long, Customer> save() {
    // save a single Customer
    customerRepository.save(new Customer(1, "Jack", "Smith"));
    customerRepository.save(new Customer(2, "Adam", "Johnson"));
    customerRepository.save(new Customer(3, "Kim", "Smith"));
    customerRepository.save(new Customer(4, "David", "Williams"));
    customerRepository.save(new Customer(5, "Peter", "Davis"));
 
    return customerRepository.findAll();
  }
 
  @RequestMapping("/findall")
  public String findAll() {
    String result = "";
    Map<Long, Customer> customers = customerRepository.findAll();
 
    for (Customer customer : customers.values()) {
      result += customer.toString() + "\n";
    }
 
    return result;
  }
 
  @RequestMapping("/find/{id}")
  public String findById(@PathVariable( "id") Long id) {
    String result = "";
    if( customerRepository.findById(id) == null) {
	return String.format("Can't find the specified Customer with ID as %s !", String.valueOf(id));
    }
    result = customerRepository.findById(id).toString();
    return result;
  }
 
  @RequestMapping(value = "/uppercase/{id}")
  public String postCustomer(@PathVariable("id") Long id) {
    Customer customer = customerRepository.findById(id);
    customer.setFirstName(customer.getFirstName().toUpperCase());
    customer.setLastName(customer.getLastName().toUpperCase());
 
    customerRepository.update(customer);
    return customerRepository.findById(id).toString();
 
//    return "Done";
  }
 
  @RequestMapping("/delete")
  public String deleteById(@RequestParam("id") Long id) {
    customerRepository.delete(id);
 
    return findAll();
  }
}