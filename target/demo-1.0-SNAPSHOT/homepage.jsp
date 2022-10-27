<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 08/08/2022
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix = "p" uri = "http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@ taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head >
    <title>Homepage</title>
    <style>
        tr:hover {background-color: #eca6a0;}

        /*tr:nth-child(even) {background-color: #f2f2f2;}*/



        table {
            height: 70%;
            width: 80%;
            background-color: #c1dae1;
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
    <div>
        <header>Availabe products</header>
        <div style="overflow-x:auto;" >
            <table style="border:20px" style="width:80%" cellpadding="30%" >
                <thead>
                    <tr>
                        <td>name</td>
                        <td>category</td>
                        <td>price</td>
                    </tr>
                </thead>

                <p:forEach var="product" items="${allProducts}">
                    <tr>
                        <td> ${product.productName} </td>
                        <td> ${product.category} </td>
                        <td> ${product.price} </td>
                       <td>
                           <form action="homepage" method="post">
                                <button type="submit" name="productToLike" value="${product.productName} "> like </button>
                           </form>
                           <form action="homepage" method="post">
                               <button type="submit" name="productToUnlike" value="${product.productName} "> unlike </button>
                           </form>
                           <form action="addToCart" method="post">
                               <button type="submit" name="product" value="${product.productName} "> add to cart </button>
                           </form>
                       </td>
                    </tr>
                </p:forEach>
            </table>
        </div>
    </div>

</body>
</html>
