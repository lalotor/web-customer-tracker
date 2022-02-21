package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.util.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public List<Customer> getCustomers(int sortField) {
    Session session = sessionFactory.getCurrentSession();

    // determine sort field
    String fieldName = null;

    switch (sortField) {
      case SortUtils.FIRST_NAME:
        fieldName = "firstName";
        break;
      case SortUtils.EMAIL:
        fieldName = "email";
        break;
      case SortUtils.LAST_NAME:
      default:
        // if nothing matches the default to sort by lastName
        fieldName = "lastName";
    }

    Query<Customer> query = session.createQuery("FROM Customer ORDER BY " + fieldName, Customer.class);

    return query.getResultList();
  }

  @Override
  public void saveCustomer(Customer customer) {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(customer);
  }

  @Override
  public Customer getCustomer(int id) {
    Session session = sessionFactory.getCurrentSession();

    return session.get(Customer.class, id);
  }

  @Override
  public void deleteCustomer(int id) {
    Session session = sessionFactory.getCurrentSession();

//    session.delete(getCustomer(id));

    Query query = session.createQuery("DELETE FROM Customer WHERE id=:customerId");
    query.setParameter("customerId", id);

    query.executeUpdate();
  }

  @Override
  public List<Customer> searchCustomers(String searchName) {
    Session session = sessionFactory.getCurrentSession();

    Query query = null;

    if (searchName != null && searchName.trim().length() > 0) {
      query = session.createQuery("FROM Customer WHERE lower(firstName) LIKE :name OR lower(lastName) LIKE :name ORDER BY lastName", Customer.class);
      query.setParameter("name", "%" + searchName.toLowerCase() + "%");
    } else {
      query = session.createQuery("FROM Customer ORDER BY lastName", Customer.class);
    }

    return query.getResultList();
  }
}
