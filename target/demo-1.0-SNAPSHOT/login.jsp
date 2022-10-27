<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 08/08/2022
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="align-content: center">
<head>
    <title>Login Page</title>
</head>
<body  style="background-color: lightgray">
    <form action="login" method="post">
    <table>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" /></td>
        </tr>

        <tr>
            <td>Password</td>
            <td><input type="password" name="password" /></td>
        </tr>
    </table>
    <input type="submit" value="Submit">
    </form>


</body>
</html>
