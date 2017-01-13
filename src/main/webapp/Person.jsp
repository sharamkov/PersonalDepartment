<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Edit/Add</title>
</head>
<body>

<c:url var="url" value="/person">
    <c:param name="searchedDepId" value="${param.searchedDepId}"/>
    <c:param name="searchedSurname" value="${param.searchedSurname}"/>
</c:url>


<form id="person" action="${url}" method="post">
    <p>
    <table>
        <tr>
            <td><input type="hidden" name="pId" value="${personModel.personId}"/></td>
        </tr>
        <tr>
            <td>Surname</td>
            <td><input type="text" name="pSurname" value="${personModel.personSurname}" size="30" required="required"/>
            </td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="pName" value="${personModel.personName}" size="30" required="required"/></td>
        </tr>
        <tr>
            <td>Patronymic</td>
            <td><input type="text" name="pPatronymic" value="${personModel.personPatronymic}" size="30"
                       required="required"/></td>
        </tr>
        <tr>
            <td>Birth date(day, month, year)</td>
            <td>
                <input type="number" min="1" max="31" name="bDay" value="${personModel.personDateOfBirth[0]}"
                       required="required"
                       placeholder="day"/>
                <input type="number" min="1" max="12" name="bMonth" value="${personModel.personDateOfBirth[1]}"
                       required="required"
                       placeholder="month"/>
                <input type="number" min="1900" max="2100" name="bYear" value="${personModel.personDateOfBirth[2]}"
                       required="required"
                       placeholder="year"/>
            </td>
        </tr>
        <tr>
            <td>Position</td>
            <td><input type="text" name="pPositon" value="${personModel.personPosition}" size="30" required="required"/>
            </td>
        </tr>
        <tr>
            <td>Date of employment(day, month, year)</td>
            <td>
                <input type="number" min="1" max="31" name="empDay" value="${personModel.employmentDate[0]}"
                       required="required"
                       placeholder="day"/>
                <input type="number" min="1" max="12" name="empMonth" value="${personModel.employmentDate[1]}"
                       required="required"
                       placeholder="month"/>
                <input type="number" min="1900" max="2100" name="empYear" value="${personModel.employmentDate[2]}"
                       required="required"
                       placeholder="year"/>
            </td>
        </tr>
        <tr>
            <td>Department</td>
            <td><select name="pDepartmentId">
                <c:forEach items="${personModel.departments}" var="department">
                    <option value="${department.departmentId}"
                            <c:if test="${department.departmentId == personModel.personDepartment.departmentId}">selected="selected"</c:if>>
                        <c:out value="${department.departmentName}"/>
                    </option>
                </c:forEach>
            </select></td>
        </tr>
    </table>
</form>
<input form="person" type="submit" name="personOk" value="OK">
<button onclick="goBack()">Cancel</button>


<script>

    function goBack() {
        window.history.back();
    }

</script>

</body>
</html>
