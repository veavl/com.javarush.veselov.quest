package org.example.m3finalprogectv2.service;

import org.example.m3finalprogectv2.repository.Repository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private Service service;

    @Test
    void checkQuestionListSize() {
        service = new Service();             // 1. ПРОИСХОДИТ СОЗДАНИЕ РЕПОЗИТОРИЯ И ИНИЦИАЛИЗАЦИЯ СПИСКОВ questionList И answerList

        Repository repository = new Repository();       // 2. СОЗДАНИЕ РЕПОЗИТОРИЯ
        repository.initialQuest();                      // МЕТОД ИНИЦИАЛИЗАЦИИ СПИСКА

                    // ОЖИДАЕМОЕ И ФАКТИЧЕСКОЕ ЗНАЧЕНИЯ
        assertEquals(service.getQuestionList().size(), repository.getQuestionList().size());
    }

    @Test                                   // АНАЛОГИЧНЫЙ ТЕСТОВЫЙ МЕТОДОВ ДЛЯ Answer
    void checkAnswerListSize() {
        service = new Service();

        Repository repository = new Repository();
        repository.initialAnswer();

        assertEquals(service.getAnswerList().size(), repository.getAnswerList().size());
    }
}