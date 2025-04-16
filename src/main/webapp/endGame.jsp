<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    request.setCharacterEncoding("UTF-8");
    HttpSession sess = request.getSession();
    Integer counter = (Integer) sess.getAttribute("counter");       // СЧЕТЧИК СЕССИИ

    // ПОЛУЧАЕМ ПАРАМЕТРЫ ИЗ ЗАПРОСА
    String playerName = request.getParameter("playerName");        // ИМЯ ИГРОКА
%>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Победа</title>
</head>
<body>

    <div class = 'session'>
        <%= "Имя: " + playerName %> </br>
        <%= "Число игр: " + counter %>
    </div>

    <h1>Тебя вернули домой</h1>
    <h3>Победа</h3>

    <p><a href='index.jsp'>Начать игру заново</a>

</body>
</html>