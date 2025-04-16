package org.example.m3finalprogectv2.controller;

import java.io.*;
import java.util.List;
import java.util.Map;

import org.example.m3finalprogectv2.entity.Answer;
import org.example.m3finalprogectv2.service.Service;
import org.example.m3finalprogectv2.util.Settings;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Servlet", value = "/servlet")
public class Servlet extends HttpServlet {

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        Map<String, Integer> counterNameMap = Settings.counterUserName;          // MAP'А ДЛЯ USER'ОВ И counter СЕССИИ
        Service service = new Service();
        List<Answer> answerList = service.getAnswerList();

        String playerName = request.getParameter("playerName");                         // ДАННЫЕ ИЗ ФОРМЫ
        Integer idQuestion = Integer.parseInt(request.getParameter("idQuestion"));
        String answerGT = request.getParameter("answer");

        HttpSession session = request.getSession();
        request.setAttribute("playerName", playerName);                    // В СЕССИЮ ИМЯ ИГРОКА
        Integer counter = (Integer) session.getAttribute("counter");       // ИЗ СЕССИИ АТРИБУТ 'counter'

        // НАЧАЛО СЕССИИ + ЗАНЕСЕНИЕ В MAP'У ИМЕНИ ИГРОКА И counter
        if (counter == null) {
            session.setAttribute("counter", 1);
            counter = 1;
            counterNameMap.put(playerName, counter);
        }
        else if (idQuestion == 1) {
            if (counterNameMap.containsKey(playerName)) {
                counter = counterNameMap.get(playerName) + 1;
                counterNameMap.put(playerName, counter);
                session.setAttribute("counter", counter);
            } else {
                session.setAttribute("counter", 1);
                counter = 1;
                counterNameMap.put(playerName, counter);
            }
        }
         // 1. ПОКА ЕСТЬ ВОПРОСЫ - РАБОТАЕМ С НИМИ И С ОТВЕТАМИ
         if (idQuestion <= service.getQuestionList().size()) {
             request.setAttribute("ObjectQuestionById", service.getQuestionById(idQuestion));

            for (Answer answer : answerList) {
                if (answer.getId() == idQuestion && answer.getDuality() == true) {
                    request.setAttribute("answerTrue", answer.getAnswer());
                } else if (answer.getId() == idQuestion && answer.getDuality() == false) {
                    request.setAttribute("answerFalse", answer.getAnswer());
                }
            }

            // ПРИ ОТВЕТЕ НЕТ - И ЭТО "ПОРАЖЕНИЕ" -- МЫ ДЕЛАЕМ ОТДЕЛЬНУЮ ВЫБОРКУ! ЧТОБЫ ПОЛУЧИТЬ AnswerDie
            service.setAttributeAnswerDie(answerList, idQuestion, request);

            if (answerGT.equals("yes") || answerGT.isBlank()) {     // isBlank() - при первом переходе из index.jsp
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("startGame.jsp");
                requestDispatcher.forward(request, response);
            } else if (answerGT.equals("no")) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("outGame.jsp");
                requestDispatcher.forward(request, response);
            }
        }
        // 2. ИНАЧЕ НУЖНО ВЫХОДИТЬ ИЗ ИГРЫ
        else {
            service.setAttributeAnswerDie(answerList, idQuestion, request);

            if (answerGT.equals("yes")) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("endGame.jsp");
                requestDispatcher.forward(request, response);
            }
            else if (answerGT.equals("no")) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("outGame.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }

    public void destroy() {
    }
}