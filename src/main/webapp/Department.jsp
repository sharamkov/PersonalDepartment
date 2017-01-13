<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Edit/Add</title>
</head>
<body>

<form id="department" action="<c:url value="/department"/>" method="post">
    <p>
    <table>
        <tr><input type="hidden" name="dId" value="${department.departmentId}"/></tr>
        <tr>
            <td>Name</td>
            <td><input type="text" required="required" name="dName" value="${department.departmentName}"/></td>
        </tr>
        <tr>
            <td>Code</td>
            <td><input type="number" required="required" min="1" max="999" name="dCode"
                       value="${department.departmentCodeNumber}"/></td>
        </tr>
    </table>
</form>
<input form="department" type="submit" name="departmentOk" value="OK"/>
<button onclick="goBack()">Cancel</button>


<script>

    function goBack() {
        window.history.back();
    }

</script>
</body>
</html>
