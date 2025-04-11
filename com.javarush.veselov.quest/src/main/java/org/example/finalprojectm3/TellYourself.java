package org.example.finalprojectm3;

import java.io.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "TellYourself", value = "/TellYourself")
public class TellYourself extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        Integer counter = (Integer) session.getAttribute("counter");

        PrintWriter out = response.getWriter();
        out.println("<!doctype html>");
        out.println("<html><head>\n" +
                "    <title>Tell YouSelf</title>\n" +
                "    <link rel='stylesheet' href='style.css'>\n" +
                "</head><body>");

        out.println("<div class = 'session'>Имя: " + session.getAttribute("name") + "<br>");
        out.println("Количество игр: " + counter + "</div>");

        String answer = request.getParameter("tellYourself");
        if (answer.equals("tellTrue")) {
            out.println("<h1>Тебя вернули домой.</h1>" +
                    "<h3>Победа</h3>");
            out.println("<p><a href='index.jsp'>Начать игру заново</a>");
        }
        else if (answer.equals("tellLie")) {
            out.println("<h2>Твою ложь разоблачили.</h2>" +
                    "<h4>Поражение</h4>");
            out.println("<p><a href='index.jsp'>Начать игру заново</a>");
        }
        else
            out.println("Ваш запрос НЕ прошел!");

        out.println("</body></html>");
    }
}
