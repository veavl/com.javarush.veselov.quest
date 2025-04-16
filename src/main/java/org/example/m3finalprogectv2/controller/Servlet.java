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
        Map<String, Integer> counterNameMap = Settings.counterUserName;                 // МАПА ДЛЯ USER'ОВ И counter СЕССИИ
        Service service = new Service();

        String playerName = request.getParameter("playerName");                         // ПОЛУЧАЕМ ДАННЫЕ ИЗ ФОРМЫ
        Integer idQuestion = Integer.parseInt(request.getParameter("idQuestion"));
        String answerGT = request.getParameter("answer");

        HttpSession session = request.getSession();
        request.setAttribute("playerName", playerName);                    // ЗАНОСИМ В СЕССИЮ ИМЯ ИГРОКА
        Integer counter = (Integer) session.getAttribute("counter");       // БЕРЕМ У СЕССИИ АТРИБУТ 'counter'. ИЗНАЧАЛЬНО == null, ТАК КАК ТАКОГО АТРИБУТА ПРОСТО НЕТ

        // ИТАК, ЕСЛИ counter == null, ТО ЭТО НАЧАЛО СЕССИИ, ПОЭТОМУ ДЕЛАЕМ counter == 1 И КЛАДЕМ ЕГО И ИМЯ ИГРОКА В MAP'У
        if (counter == null) {
            session.setAttribute("counter", 1);
            counter = 1;
            counterNameMap.put(playerName, counter);
        }
        // ЕСЛИ ЖЕ counter != 0, ТО КТО-ТО УЖЕ ЕСТЬ В MAP'Е
        // И МЫ ДЕЛАЕМ ТЕ ЖЕ ПРОВЕРКИ И ДЕЙСТВИЯ, ЧТО И В ПРОЕКТЕ НА ОДНИХ СЕРВЛЕТАХ
        // НО СНАЧАЛА ПРОВЕРЯЕМ idQuestion == 1 ИЛИ НЕТ, ТАК КАК ЕСЛИ idQuestion != 1,
        // ТО ПРОДОЛЖАЕТСЯ ТЕКУЩАЯ ИГРА И МЫ С ТЕКУЩИМ USER'ОМ И ТЕКУЩЕМ counter'ОМ
        // ПРОСТО ПЕРЕХОДИМ ОТ ВОПРОСА К ВОПРОСУ
        // И В РАМКАХ ТЕКУЩЕЙ ИГРЫ НЕ НУЖНО НИЧЕГО ДЕЛАТЬ НИ С USER'ОМ НИ С ТЕКУЩИМ counter'ОМ
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

        // 1.1 ПЕРВЫЙ РАЗ ИЗ ФОРМЫ index.jsp МЫ БЕРЕМ ПАРАМЕТРЫ
        // Имя
        // ID вопроса = 1
        // Ответ - пустая строка

        // 1.2 ПОСЛЕДУЮЩИЕ РАЗЫ ИЗ ФОРМЫ startGame.jsp БЕРЕМ (ID вопроса++) И Ответ ДА ИЛИ НЕТ
        // ЕСЛИ ОТВЕТ ДА --> ВОЗВРАЩАЕМСЯ В startGame.jsp И НОВЫЙ ВОПРОС
        // ЕСЛИ ОТВЕТ НЕТ --> ПОРАЖЕНИЕ outGame.jsp

        // (I.) ЕСЛИ id МЕНЬШЕ ИЛИ РАВНО ЧИСЛУ ВОПРОСОВ - ТО РАБОТАЕМ С ВОПРОСАМИ И ОТВЕТАМИ ДА ИЛИ НЕТ
        if (idQuestion <= service.getQuestionList().size()) {

            // А) ПЕРЕДАЕМ В ЗАПРОС: ОБЪЕКТ ВОПРОСА
            request.setAttribute("ObjectQuestionById", service.getQuestionById(idQuestion));

            List<Answer> answerList = service.getAnswerList();

            // БЕРЕМ ИЗ answerList ВАРИАНТЫ ОТВЕТОВ НА ВОПРОС: answerTrue И answerFalse - ЭТО ПРОСТО СТРОКИ! В СООТ-ИИ С ID ВОПРОСА
            for (Answer answer : answerList) {
                // Б) ПЕРЕДАЕМ В ЗАПРОС ОТВЕТ ДА ИЛИ НЕТ
                if (answer.getId() == idQuestion && answer.getDuality() == true) {
                    request.setAttribute("answerTrue", answer.getAnswer());
                } else if (answer.getId() == idQuestion && answer.getDuality() == false) {
                    request.setAttribute("answerFalse", answer.getAnswer());
                }
            }

            // ПРИ ОТВЕТЕ НЕТ - ЭТО "ПОРАЖЕНИЕ" -- МЫ ДЕЛАЕМ ОТДЕЛЬНУЮ ВЫБОРКУ! ПОЧЕМУ?
            // КАК ВСЕ ПРОИСХОДИТ - РАБОТА С САМОГО НАЧАЛА?
            // 1. index.jsp id = 1. НАЖИМАЕМ ПРОДОЛЖИТЬ -- ПОПАДАЕМ В СЕРВЛЕТ ПО id = 1 ДЕЛАЕТСЯ ВЫБОРКА:
            // ЗАГОЛОВОК, ВОПРОС И ВАРИАНТЫ ОТВЕТОВ.
            // 1.1.ЕСЛИ МЫ ВЫБИРАЕМ "ДА" - ТО СНОВА СЮДА id = 2 И НОВЫЕ ДАННЫЕ ДЛЯ ВОПРОСА №2.
            // ЗА ОТОБРАЖЕНИЕ ОТВЕЧАЕТ startGame.jsp
            // 1.2 ЕСЛИ МЫ ВЫБИРАЕМ "НЕТ" - ТО СНОВА СЮДА id = 2. ЗНАЧИТ И ДАННЫЕ ДЛЯ "ПОРАЖЕНИЕ" - ЭТО №2!
            // А НАМ НУЖА ДЛЯ "ПОРАЖЕНИЯ" ВСЕ ЕЩЕ РЕАКЦИЯ ДЛЯ ПЕРВОГО ВОПРОСА - new Answer(1,...
            // ЗА ОТОБРАЖЕНИЕ ОТВЕЧАЕТ outGame.jsp
            for (Answer answer : answerList) {
                if (answer.getId() == idQuestion - 1 && answer.getDuality() == false) {
                    request.setAttribute("answerDie", answer.getAnswerDie());           // answerDie из Repo
                }
            }

            // ЕСЛИ ОТВЕТ ДА - ПРОДОЛЖАЕМ РАБОТАТЬ С ВОПРОСАМИ ДО ПОСЛЕДНЕГО
            if (answerGT.equals("yes") || answerGT.isBlank()) {     // isBlank() - при первом переходе из index.jsp
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("startGame.jsp");
                requestDispatcher.forward(request, response);
            // ЕСЛИ ОТВЕТ НЕТ - УХОДИМ В outGame.jsp
            } else if (answerGT.equals("no")) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("outGame.jsp");
                requestDispatcher.forward(request, response);
            }
        }

        // (II.) КОГДА ЗАДЕТСЯ ПОСЛЕДНИЕ ВОПРОС --> ПРОДОЛЖИТЬ И МЫ СНОВА ЗДЕСЬ.
        // ПРИ ЭТОМ id БОЛЬШЕ ЧИСЛА ВОПРОСОВ -- НУЖНО ВЫХОДИТЬ ИЗ ИГРЫ
        else {
            // СНОВА ПРИ ОТВЕТЕ НЕТ - ЭТО "ПОРАЖЕНИЕ" -- МЫ ДЕЛАЕМ ОТДЕЛЬНУЮ ВЫБОРКУ!
            List<Answer> answerList = service.getAnswerList();
            for (Answer answer : answerList) {
                if (answer.getId() == idQuestion - 1 && answer.getDuality() == false) {
                    request.setAttribute("answerDie", answer.getAnswerDie());           // answerDie из Repo
                }
            }

            // ЕСЛИ ОТВЕТ НА ПОСЛЕДНИЕ ВОПРОС ДА -- endGame.jsp
            if (answerGT.equals("yes")) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("endGame.jsp");
                requestDispatcher.forward(request, response);
            }
            // ЕСЛИ ОТВЕТ НА ПОСЛЕДНИЕ ВОПРОС НЕТ -- outGame.jsp
            else if (answerGT.equals("no")) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("outGame.jsp");
                requestDispatcher.forward(request, response);
            }
        }

    }

    public void destroy() {
    }
}