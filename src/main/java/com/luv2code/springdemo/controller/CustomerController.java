package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("customer")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

//  @RequestMapping("list")
  @GetMapping("/list")
  public String listCustomers(Model model, @RequestParam(required=false) String sort) {

    // get customers from the service
    List<Customer> customers = null;

//    // check for sort field
//    if (sort != null) {
//      int sortField = Integer.parseInt(sort);
//      customers = customerService.getCustomers(sortField);
//    }
//    else {
//      // no sort field provided ... default to sorting by last name
//      customers = customerService.getCustomers(SortUtils.LAST_NAME);
//    }
    customers = customerService.getCustomers();

    // add the customers to the model
    model.addAttribute("customers", customers);

    return "list-customers";
  }

  @GetMapping("/showFormForAdd")
  public String showFormForAdd(Model model) {
    model.addAttribute("customer", new Customer());

    return "customer-form";
  }

  @PostMapping("/saveCustomer")
  public String saveCustomer(@ModelAttribute("customer") Customer customer) {
    customerService.saveCustomer(customer);

    return "redirect:/customer/list";
  }

  @GetMapping("/showFormForUpdate")
  public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {
    model.addAttribute("customer", customerService.getCustomer(id));

    return "customer-form";
  }

  @GetMapping("/delete")
  public String deleteCustomer(@RequestParam("customerId") int id) {
    customerService.deleteCustomer(id);

    return "redirect:/customer/list";
  }

  @GetMapping("/search")
  public String searchCustomers(@RequestParam("searchName") String searchName, Model model) {
    model.addAttribute("customers", customerService.searchCustomers(searchName));
    return "list-customers";
  }

}
