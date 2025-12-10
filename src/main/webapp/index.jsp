<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>BlackJack</title>
</head>
<body>
<header>
<nav>
    <a href="${pageContext.request.contextPath}/">New deck</a> | <a href="${pageContext.request.contextPath}/decks">Historic of decks</a>
</nav>
</header>

<main>
    <c:choose>
        <c:when test="${not empty deck}">
            <h2>Deck ID: ${deck.id}</h2>
            <h3>Remaining cards: ${deck.remaining}</h3>
        </c:when>
        <c:otherwise>
            <h2>Create a new deck</h2>
        </c:otherwise>
    </c:choose>


    <c:choose>
        <c:when test="${not empty deck}">
            <form action="draw" method="post">
                <input type="hidden" name="deckId" value="${deck.id}">
                <label for="numberOfCards">Cards to draw</label>
                <input type="number" id="numberOfCards" name="numberOfCards" value="1" min="1">
                <input type="submit" value="Draw cards">
            </form>
            <h2>Drawn cards: ${deck.drawnCards.size()}</h2>
            <c:forEach var="card" items="${deck.drawnCards}">
                <img src="${card.image}" alt="${card.code}" width="150px"/>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <form action="deck" method="post">
                <label for="numberOfDecks">Number of deck sets</label>
                <input type="number" id="numberOfDecks" name="numberOfDecks" value="1" min="1">
                <input type="submit" value="New deck">
            </form>
        </c:otherwise>
    </c:choose>

</main>
</body>
</html>