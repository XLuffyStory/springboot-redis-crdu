package com.redis.crdu;

import java.util.Map;

public interface CustomerRepository {
 
  void save(Customer customer);
  Customer findById(Long id);
  Map<Long, Customer> findAll();
  void update(Customer customer);
  void delete(Long id);
}