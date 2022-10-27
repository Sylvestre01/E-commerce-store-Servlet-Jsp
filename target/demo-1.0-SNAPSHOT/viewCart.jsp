<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 09/08/2022
  Time: 00:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        tr:hover {background-color: #b8e0e8;}

        tr:nth-child(even) {background-color: #f2f2f2;}

        table {
            height: 70%;
            width: 80%;
        }

        th {
            height: 50px;
        }

        th, td {
            border-bottom: 1px solid #ddd;
        }

        header {
            background-color: #F1F1F1;
            text-align: center;
            padding: 20px;
            font-size: x-large;
        }
    </style>
</head>
<body>
<form action="addToCart" method="get">
<div>
    <header>My Cart</header>
    <table >
        <thead>
        <tr>
            <td>customer id</td>
            <td>product</td>
            <td>quantity</td>
            <td>date</td>
        </tr>
        </thead>

        <p:forEach var="entry" items="${loggedInUserCart}">
            <tr>
                <td> ${entry.value.customerId} </td>
                <td> ${entry.key} </td>
                <td> ${entry.value.quantity} </td>
                <td> ${entry.value.date} </td>
                <td>
                    <form action="cartWish" method="post">
                        <button type="submit" name="productToRemove" value="${entry.key}"> remove from cart </button>
                    </form>
                </td>
            </tr>
        </p:forEach>

    </table>
</div>


<a href="homepage.jsp">back to all products</a>
<%--<a href="./addToCart">Add product to cart</a>--%>
</form>
</body>
</html>
