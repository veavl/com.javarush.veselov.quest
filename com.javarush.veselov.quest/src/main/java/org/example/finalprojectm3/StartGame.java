package org.example.finalprojectm3;

import java.io.*;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.util.Objects.isNull;

@WebServlet(name = "startGame", value = "/StartGame")
public class StartGame extends HttpServlet {
    private String message;

    public void init() {
        message = "Ты потерял память!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        Integer counter = (Integer) session.getAttribute("counter");
        PrintWriter out = response.getWriter();

        String playerGame = Settings.getParameter(request, Settings.inputName);

        session.setAttribute("name", request.getParameter(Settings.inputName));
        session.setMaxInactiveInterval(120);

        out.println("<!doctype html>");
        out.println("<html><head>\n" +
                "    <title>Start Game</title>\n" +
                "    <link rel='stylesheet' href='style.css'>\n" +
                "</head><body>");

        // Если в рамках одной Сессии играют разные User'ы -- у каждого свой счетчик "Количество игр"
        Map<String, Integer> counterNameMap = Settings.counterName;
        if (counter == null) {
            session.setAttribute("counter", 1);
            counter = 1;
            counterNameMap.put(playerGame, counter);
        } else {
            if (counterNameMap.containsKey(playerGame)) {
                counter = counterNameMap.get(playerGame) + 1;
                counterNameMap.put(playerGame, counter);
                session.setAttribute("counter", counter);
            } else {
                session.setAttribute("counter", 1);
                counter = 1;
                counterNameMap.put(playerGame, counter);
            }
        }

        if (playerGame.isBlank()) {
            out.println("<p>Вы не представились!" + "<p><a href='index.jsp'>Вернуться и указать имя</a>");
        } else {
            out.println("<div class = 'session'>Имя: " + playerGame + "<br>");
            out.println("Количество игр: " + counter + "</div>");
            out.println("<h1>" + message + "</h1>");

            out.println("<form action = 'TakeCall' method='POST'>" +
                    "<h3>Принять вызов НЛО?</h3> " +
                    "<input type='hidden' name='name' value=' ' />" +
                    "<input type='radio' name='call' value='yesCall' checked/>Принять вызов<br>" +
                    "<input type='radio' name='call' value='noCall'/>Отклонить вызов<br>" +
                    "<p><input type='submit' value='Продолжить'/>" +
                    "</form>"
            );
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }
}