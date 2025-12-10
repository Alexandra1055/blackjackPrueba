<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Historic of decks</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            font-size: 16px;
            text-align: left;
        }

        thead th {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border-bottom: 2px solid #ddd;
        }

        tbody tr {
            border-bottom: 1px solid #ddd;
        }

        tbody td {
            padding: 10px;
        }

        tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tbody tr:hover {
            background-color: #f1f1f1;
        }

        tbody td a {
            text-decoration: none;
            color: #4CAF50;
            font-weight: bold;
        }

        tbody td a:hover {
            color: #45a049;
            text-decoration: underline;
        }

    </style>
</head>
<body>
<header>
    <nav>
        <a href="${pageContext.request.contextPath}/">New deck</a>
    </nav>
</header>

<main>
    <h1>Historic of Decks</h1>
    <c:if test="${not empty decks}">
        <table>
            <thead>
            <tr>
                <th>Deck ID</th>
                <th>Remaining</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="deck" items="${decks}">
                <tr>
                    <td>${deck.id}</td>
                    <td>${deck.remaining}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/?id=${deck.id}">Play deck</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty decks}">
        <p>No decks available.</p>
    </c:if>

</main>
</body>
</html>