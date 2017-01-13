<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Department List</title>
</head>
<body>
<c:choose>
    <c:when test="${empty departments}">
        <p><c:out value="No results for your request"/></p>
    </c:when>
    <c:otherwise>
        <form action="<c:url value="/department"/>">
            <p>
            <table>
                <tr>
                    <th></th>
                    <th>Name</th>
                    <th>Code</th>
                </tr>
                <c:forEach items="${departments}" var="department">
                    <tr style="text-align: center">
                        <td><input type="radio" name="dId" value="${department.departmentId}"
                                <c:if test="${department.departmentId == departments[0].departmentId}">checked="checked"</c:if> /></td>
                        <td><c:out value="${department.departmentName}"/></td>
                        <td><c:out value="${department.departmentCodeNumber}"/></td>
                    </tr>
                </c:forEach>
            </table>
            <p>
            <input type="submit" name="editDepartment" value="Edit"/>
            <input formmethod="post" type="submit"  name="deleteDepartment" value="Delete"/>
        </form>

    </c:otherwise>
</c:choose>
<p>
<form action="<c:url value="/start"/>" method="get">
    <button type="submit">Start page</button>
</form>

</body>
</html>
