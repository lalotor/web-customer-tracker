package com.luv2code.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String url = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
    String user = "hbstudent";
    String pass = "hbstudent";
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    PrintWriter out = resp.getWriter();
    out.println("Connecting to DB: " + url);
    try (var conn = DriverManager.getConnection(url, user, pass);
        var ps = conn.prepareStatement("select count(0) from customer");
        var rs = ps.executeQuery()) {
      out.println("Connection successful!");
      if (rs.next()) {
        out.println("Customers found = " + rs.getInt(1));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
