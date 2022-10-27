<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 07/08/2022
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
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
        }
    </style>
</head>
<body>
    <form action="addProduct" method="post">
        <h1>Add Product to Stock</h1>
    <table>
        <tr>
            <td>name</td>
            <td><input type="text" name="name" /></td>
        </tr>
        <tr>
            <td>quantity</td>
            <td><input type="text" name="quantity"/></td>
        </tr>
        <tr>
            <td>category</td>
            <td><input type="text" name="category"/></td>
        </tr>
        <tr>
            <td>price</td>
            <td><input type="text" name="price"/></td>
        </tr>
    </table>
    <input type="submit" value="Submit"/>
    </form>

</body>
</html>
