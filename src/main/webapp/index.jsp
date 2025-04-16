<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  page import="org.example.m3finalprogectv2.util.Settings" %>

<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Игра Квест НЛО</title>
</head>
<body>
<h1>Игра Квест</h1>

<div class="hello">
    <p>Привет, НЕЗНАКОМЕЦ.
    <h2>СЕЙЧАС <c:out value="${3000 + Settings.getRandom()}"/> ГОД.</h2>
    <p>Введи свое имя. Пока помнишь.
    <p>Скоро ты его забудешь.
</div>

    <form action = 'servlet' method='POST'>
    <p>Ваше имя:
    <label>
        <input name="playerName">
    </label>
    <input type="hidden" name="idQuestion" value="1">
    <input type='hidden' name='answer' value=''/>
    <p><input type='submit' value='Начать игру'/>
    </form>

</body>
</html>