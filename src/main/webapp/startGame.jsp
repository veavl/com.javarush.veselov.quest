<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.m3finalprogectv2.entity.Question" %>

<%
    request.setCharacterEncoding("UTF-8");
    HttpSession sess = (HttpSession) request.getSession();
    Integer counter = (Integer) sess.getAttribute("counter");       // СЧЕТЧИК СЕССИИ

    // ПОЛУЧАЕМ АРИБУТЫ ИЗ ЗАПРОСА
    Question QuestionById = (Question) request.getAttribute("ObjectQuestionById");      // ВОПРОС Object
    Integer idQuestion = QuestionById.getId();                                          // Id
    String heading = QuestionById.getHeading();                                         // h1
    String question = QuestionById.getQuestion();                                       // ВОПРОС

    String playerName = (String) request.getAttribute("playerName");                    // ИМЯ ИГРОКА
    String answerTrue = (String) request.getAttribute("answerTrue");                    // ОТВЕТ ДА
    String answerFalse = (String) request.getAttribute("answerFalse");                  // ОТВЕТ НЕТ
%>

<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Вопрос №<%= idQuestion %></title>
</head>
<body>
    <div class = 'session'>
        <%= "Имя: " + playerName %> </br>
        <%= "Число игр: " + counter %>
    </div>

    <h1><%= heading %></h1>
    <h3><%= question %></h3>

    <form action = 'servlet' method='POST'>

        <input type='radio' name='answer' value='yes' checked/><%= answerTrue %><br>
        <input type='radio' name='answer' value='no'/><%= answerFalse %><br>
        <input type="hidden" name="playerName" value="<%= playerName %>">
        <input type="hidden" name="idQuestion" value="<%= idQuestion + 1 %>">

        <p><input type='submit' value='Продолжить'/>

    </form>
</body>
</html>
