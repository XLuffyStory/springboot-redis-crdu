package com.redis.crdu;
import java.util.Map;
 
import javax.annotation.PostConstruct;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
 
 
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
 
  private static final String KEY = "Customer";
 
  private RedisTemplate<String, Object> redisTemplate;
  private HashOperations<String, Long, Customer> hashOperations;
 
  public CustomerRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
    hashOperations = redisTemplate.opsForHash();
  }
 
//  @PostConstruct
//  private void init() {
//    hashOperations = redisTemplate.opsForHash();
//  }
 
  @Override
  public void save(Customer customer) {
    hashOperations.put(KEY, customer.getId(), customer);
  }
 
  @Override
  public Customer findById(Long id) {
    return hashOperations.get(KEY, id);
  }
 
  @Override
  public Map<Long, Customer> findAll() {
    return hashOperations.entries(KEY);
  }
 
  @Override
  public void update(Customer customer) {
    hashOperations.put(KEY, customer.getId(), customer);
  }
 
  @Override
  public void delete(Long id) {
    hashOperations.delete(KEY, id);
  }
 
}