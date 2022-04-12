<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.luv2code.springdemo.util.SortUtils" %>

<html>
    <head>
        <title>List Customer</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>CRM - Customer Relationship Manager</h2>
            </div>
        </div>
        <div id="container">
            <div id="content">

                <input type="button" value="Add Customer" class="add-button"
                    onclick="window.location.href='showFormForAdd'; return false;" />

                <form:form action="search" method="GET">
                    Search customer: <input type="text" name="searchName" />
                    <input type="submit" value="Search" class="add-button" />
                </form:form>

                <c:url var="sortLinkFirstName" value="/customer/list">
                    <c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>" />
                </c:url>
                <c:url var="sortLinkLastName" value="/customer/list">
                    <c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>" />
                </c:url>
                <c:url var="sortLinkEmail" value="/customer/list">
                    <c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>" />
                </c:url>

                <table>
                    <tr>
                        <th><a href="${sortLinkFirstName}">First Name</a></th>
                        <th><a href="${sortLinkLastName}">Last Name</a></th>
                        <th><a href="${sortLinkEmail}">Email</a></th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="customer" items="${customers}">
                        <c:url var="updateLink" value="/customer/showFormForUpdate">
                            <c:param name="customerId" value="${customer.id}" />
                        </c:url>
                        <c:url var="deleteLink" value="/customer/delete">
                            <c:param name="customerId" value="${customer.id}" />
                        </c:url>
                        <tr>
                            <td>${customer.firstName}</td>
                            <td>${customer.lastName}</td>
                            <td>${customer.email}</td>
                            <td>
                                <a href="${updateLink}">Update</a> |
                                <a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">
                                    Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>