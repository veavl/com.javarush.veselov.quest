<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Игра Квест НЛО</title>
</head>
<body>
<h1>Игра Квест</h1>

<div class="hello">
    <p>Привет, ДРУГ. Введи свое имя. Пока помнишь.
    <p>Скоро ты его забудешь.
    <p>Тебя ждут настоящие приключения!
</div>

    <form action = 'servlet' method='POST'>
    <p>Ваше имя:
    <input name="playerName"/>
    <input type="hidden" name="idQuestion" value="1">
    <input type='hidden' name='answer' value=''/>
    <p><input type='submit' value='Начать игру'/>
    </form>

</body>
</html>
