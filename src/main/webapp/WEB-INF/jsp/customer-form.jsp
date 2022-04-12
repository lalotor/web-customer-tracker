<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Save Customer</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/add-customer-style.css">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>CRM - Customer Relationship Manager</h2>
            </div>
        </div>

        <div id="container">
            <h3>Save Customer</h3>
            <form:form action="saveCustomer" modelAttribute="customer" method="POST">

                <!-- Add customer id -->
                <form:hidden path="id" />

                <table>
                    <tbody>
                        <tr>
                            <td><label>First Name:</label></td>
                            <td><form:input path="firstName" /></td>
                        </tr>
                        <tr>
                            <td><label>Last Name:</label></td>
                            <td><form:input path="lastName" /></td>
                        </tr>
                        <tr>
                            <td><label>Email:</label></td>
                            <td><form:input path="email" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Save" class="save" /></td>
                        </tr>
                    </tbody>
                </table>
            </form:form>
            <div style="clear; both;">
                <a href="${pageContext.request.contextPath}/customer/list">Back to List</a>
            </div>
        </div>
    </body>
</html>