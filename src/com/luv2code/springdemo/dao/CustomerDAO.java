package com.luv2code.springdemo.dao;

import java.util.List;

import com.luv2code.springdemo.entity.Customer;

public interface CustomerDAO {
  List<Customer> getCustomers(int sortField);

  void saveCustomer(Customer customer);

  Customer getCustomer(int id);

  void deleteCustomer(int id);

  List<Customer> searchCustomers(String searchName);
}
