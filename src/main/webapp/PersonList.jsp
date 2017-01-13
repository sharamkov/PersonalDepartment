<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Person List</title>
</head>
<body>


<c:choose>
    <c:when test="${empty personList}">
        <p><c:out value="No results for your request"/></p>
    </c:when>
    <c:otherwise>

        <form action="<c:url value="/person"/>">
                <input type="hidden" name="searchedDepId" value="${param.searchedDepId}">
                <input type="hidden" name="searchedSurname" value="${param.searchedSurname}">
            <p>
            <table style="width: 100%">
                <tr style="text-align: left">
                    <th></th>
                    <th>Surname</th>
                    <th>Name</th>
                    <th>Patronymic</th>
                    <th>Position</th>
                    <th>Department</th>
                </tr>
                <c:forEach var="person" items="${personList}">
                    <tr>
                        <td><input type="radio" name="pId" value="${person.personId}"
                                <c:if test="${personList[0].personId == person.personId}"> checked="checked"</c:if>/>
                        </td>
                        <td><c:out value="${person.personSurname}"/></td>
                        <td><c:out value="${person.personName}"/></td>
                        <td><c:out value="${person.personPatronymic}"/></td>
                        <td><c:out value="${person.personPosition}"/></td>
                        <td><c:out value="${person.personDepartment.departmentName}"/></td>
                    </tr>
                </c:forEach>
            </table>
            <p>
                <input type=submit name="editPerson" value="Edit"/>
                <input formmethod="post" type=submit name="deletePerson" value="Delete"/>
        </form>
    </c:otherwise>
</c:choose>
<p>
<form action="<c:url value="/start"/>" method="get">
    <button type="submit">Start page</button>
</form>

</body>
</html>
