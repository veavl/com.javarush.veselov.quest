<%@ page import="org.example.finalprojectm3.Settings" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Игра Квест НЛО</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1><%= "Игра Квест" %></h1>

<div class="hello">
    <p><c:out value="Привет ДРУГ. Введи свое имя. Пока помнишь." />
    <p>Скоро ты его забудешь.
    <p>Но тебя ждут настоящие приключения!
</div>
<p>
<form action="StartGame" method="POST">
    Ваше имя: <input name="<%= Settings.inputName %>">
    <p><input type='submit' value='Начать игру'/>
</form>

</body>
</html>