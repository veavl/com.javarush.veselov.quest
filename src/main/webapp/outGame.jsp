<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    HttpSession sess = request.getSession();
    Integer counter = (Integer) sess.getAttribute("counter");       // СЧЕТЧИК СЕССИИ

    // ПОЛУЧАЕМ АРИБУТЫ и ПАРАМЕТРЫ ИЗ ЗАПРОСА
    String answerDie = (String) request.getAttribute("answerDie");  // ТЕКСТ, КОТОРЫЙ МЫ УВИДИМ ПРИ ПОРАЖЕНИИ
    String playerName = request.getParameter("playerName");         // ИМЯ ИГРОКА
%>

<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Поражение</title>
</head>
<body>

    <div class = 'session'>
        <%= "Имя: " + playerName %> </br>
        <%= "Число игр: " + counter %>
    </div>

    <h1><%= answerDie %></h1>
    <h4>Поражение</h4>

    <p><a href='index.jsp'>Начать игру заново</a>

</body>
</html>
