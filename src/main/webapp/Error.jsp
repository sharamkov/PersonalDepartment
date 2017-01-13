<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>Error information</h1>

<p style="color:red">${message}</p>
<p><button onclick="goBack()">Back</button> </p>

<script>

    function goBack() {
        window.history.back();
    }

</script>

</body>
</html>
