package org.example.finalprojectm3;

import java.io.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "GoUp", value = "/GoUp")
public class GoUp extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        Integer counter = (Integer) session.getAttribute("counter");

        PrintWriter out = response.getWriter();
        out.println("<!doctype html>");
        out.println("<html><head>\n" +
                "    <title>JSP - Hello World</title>\n" +
                "    <link rel='stylesheet' href='style.css'>\n" +
                "</head><body>");

        out.println("<div class = 'session'>Имя: " + session.getAttribute("name") + "<br>");
        out.println("Количество игр: " + counter + "</div>");

        String answer = request.getParameter("goUp");
        if (answer.equals("yesGoUp")) {
            out.println("<form action = 'TellYourself' method='POST'>" +
                    "<h1>Ты поднялся на мостик.</h1>" +
                    "<h3>Ты кто?</h3>" +
                    "<input type='radio' name='tellYourself' value='tellTrue' checked/>Рассказать правду о себе <br>" +
                    "<input type='radio' name='tellYourself' value='tellLie'/>Солгать о себе  <br>" +
                    "<p><input type='submit' value='Продолжить'/>" +
                    "</form>"
            );
        }
        else if (answer.equals("noGoUp")) {
            out.println("<h2>Ты не пошел на переговоры.</h2> " +
                    "<h4>Поражение</h4>");
            out.println("<p><a href='index.jsp'>Начать игру заново</a>");
        }
        else
            out.println("Ваш запрос НЕ прошел!");

        out.println("</body></html>");
    }
}
