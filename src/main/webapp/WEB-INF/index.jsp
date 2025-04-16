<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Ты потерял память!</h1>

    <%
        request.setCharacterEncoding("UTF-8");
        String nameGamer = request.getParameter("playerGame");       // Получаем значение/ИМЯ из Формы index.jsp
    %>

    <form action = 'servlet' method='POST'>
    Ваше имя: <input name="playerGame">
    <input type='hidden' name='answer' value=''/>
    <input type="hidden" name="id" value="1">
    <p><input type='submit' value='Начать игру'/>
    </form>


</body>
</html>
