<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Игра Квест" %></h1>

<form action="StartGame" method="POST">
    Представиться: <input name="playerGame">
    <p><input type='submit' value='Начать игру'/>
</form>

</body>
</html>