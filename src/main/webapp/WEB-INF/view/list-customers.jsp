<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>List Customers</title>

    <!-- reference style sheet -->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relation Manager</h2>
        </div>
    </div>

    <div id="container">
        <div id="content">

            <input type="button" value="Add Customer"
                    onclick="window.location.href='showFormForAdd'; return false"
                    class="add-button"/>

            <!-- add search box-->
            <form:form action="search" method="get">
                Search Customer:<input type="text" name="searchName"/>
                <input type="submit" value="Search" class="add-button"/>
            </form:form>

            <!-- add html table -->
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
                <!-- loop over and print customers -->
                <c:forEach var="tempCustomer" items="${customers}">
                    <!-- construct update link with customer id -->
                    <c:url var="updateLink" value="/customer/showFormForUpdate">
                        <c:param name="customerId" value="${tempCustomer.id}"/>
                    </c:url>
                    <!-- construct delete link with customer id -->
                    <c:url var="deleteLink" value="/customer/delete">
                        <c:param name="customerId" value="${tempCustomer.id}"/>
                    </c:url>
                    <tr>
                        <td>${tempCustomer.firstName}</td>
                        <td>${tempCustomer.lastName}</td>
                        <td>${tempCustomer.email }</td>
                        <td><a href="${updateLink}">Update</a>
                            |
                            <a href="${deleteLink}"
                               onclick="if (!(confirm('Are you sure that this customer should be deleted?'))) return false">Delete</a> </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</body>
</html>
