package com.luv2code.springdemo.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.luv2code.springdemo.entity.Customer;

@Service
public class CustomerServiceRestClientImpl implements CustomerService {

  private RestTemplate restTemplate;

  private String crmRestUrl;

  private Logger logger = Logger.getLogger(getClass().getName());

  @Autowired
  public CustomerServiceRestClientImpl(RestTemplate restTemplate, @Value("${crm.rest.url}") String crmRestUrl) {
    this.restTemplate = restTemplate;
    this.crmRestUrl = crmRestUrl;

    logger.info("Loaded property: crm.rest.url=" + this.crmRestUrl);
  }

  @Override
  public List<Customer> getCustomers() {
    logger.info("in getCustomers(): Calling REST API " + crmRestUrl);

    ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(crmRestUrl, HttpMethod.GET, null,
        new ParameterizedTypeReference<List<Customer>>() {});

    return responseEntity.getBody();
  }


  @Override
  public Customer getCustomer(int id) {
    logger.info("in getCustomer(): Calling REST API " + crmRestUrl + " id: " + id);

    return restTemplate.getForObject(crmRestUrl + "/" + id, Customer.class);
  }

  @Override
  public void saveCustomer(Customer customer) {
    logger.info("in saveCustomer(): Calling REST API " + crmRestUrl + " customer: " + customer);

    if (customer.getId() == null || customer.getId() == 0) {
      restTemplate.postForEntity(crmRestUrl, customer, String.class);
    } else {
      restTemplate.put(crmRestUrl, customer);
    }
  }

  @Override
  public void deleteCustomer(int id) {
    logger.info("in deleteCustomer(): Calling REST API " + crmRestUrl + " id: " + id);

    restTemplate.delete(crmRestUrl + "/" + id);
  }

  @Override
  public List<Customer> searchCustomers(String searchName) {
    return null;
  }
}
