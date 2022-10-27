<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 06/08/2022
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title style="align-content: center">Customer Signup </title>
</head>
<body style="background-color: bisque" >
<div align="centre">
    <h1> Customer sign up form </h1>
    <form action="Signup" method="post">
        <table style="width: 60%">
            <tr>
                <td>Username</td>
                <td><input type="text" name="username" /></td>
            </tr>

            <tr>
                <td>Phone number</td>
                <td><input type="text" name="phoneNumber" /></td>
            </tr>

            <tr>
                <td>Email Address</td>
                <td><input type="text" name="email" /></td>
            </tr>

            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form> <br>
        <a href="./login">Already a member? login here </a>
</div>

</body>
</html>
