<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Personal Department</title>
</head>

<body>
<h3>Personal Department</h3>

<p>

<form action="<c:url value="/department"/>" method="get">
    <table>
        <tr>
            <td><input type="submit" name="allDepartments" value="Departments"/></td>
        </tr>
        <tr>
            <td><input type="submit" name="addDepartment" value="Add Department"/></td>
        </tr>
    </table>
</form>

<form action="<c:url value="/person"/>" method="get">
    <table>

        <tr>
            <td><input type="submit" name="allPersons" value="Persons"></td>
        </tr>

        <tr>
            <td><input type="submit" name="addPerson" value="Add Person"/></td>
        </tr>

        <tr>
            <td><b>Person search:</b></td>
        </tr>

        <tr>
            <td>
                <select name="searchedDepId">
                    <option value="-1" selected>
                        All Departments
                    </option>
                    <c:forEach var="department" items="${departments}">
                        <option value="${department.departmentId}">
                            <c:out value="${department.departmentName}"/>
                        </option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="text" name="searchedSurname" size="30" placeholder="Surname(optional)"/>
            </td>
            <td><input type="submit" name="search" value="Search"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
