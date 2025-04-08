package org.example.finalprojectm3;

import java.io.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "takeCall", value = "/TakeCall")
public class TakeCall extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");

        HttpSession session = request.getSession();
        Integer counter = (Integer) session.getAttribute("counter");

        PrintWriter out = response.getWriter();
        out.println("<!doctype html>");
        out.println("<html><body>");

        out.println("Имя: " + session.getAttribute("name") + "<br>");
        out.println("Количество игр: " + counter);

        String answer = request.getParameter("call");     // Из request получаем данные переданные из ФОРМЫ

        if (answer.equals("yesCall")) {

            out.println("<form action = 'GoUp' method='POST'>" +
                    "<h1>Ты принял вызов.</h1> " +
                    "<h3>Поднимаешься на мостик к капитану?</h3> " +
                    "<input type='radio' name='goUp' value='yesGoUp' checked/>Подниматься на мостик <br>" +
                    "<input type='radio' name='goUp' value='noGoUp'/>Отказаться подниматься на мостик  <br>" +
                    "<p><input type='submit' value='Продолжить'/>" +
                    "</form>"
            );
        }
        else if (answer.equals("noCall")) {
            out.println("<h2>Ты отклонил вызов.</h2> " +
                    "<h4>Поражение</h4>");
            out.println("<p><a href='index.jsp'>Начать игру заново</a>");
        }
        else
            out.println("Ваш запрос НЕ прошел!");

        out.println("</body></html>");
    }
}
