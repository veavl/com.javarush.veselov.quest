package org.example.m3finalprogectv2.repository;

import org.example.m3finalprogectv2.entity.Answer;
import org.example.m3finalprogectv2.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    public final List<Question> questionList = new ArrayList<>();
    public final List<Answer> answerList = new ArrayList<>();

    public void initialQuest() {
                                  // id, heading, question
        questionList.add(new Question(1, "Ты потерял память!", "Принять вызов НЛО?"));
        questionList.add(new Question(2, "Ты принял вызов", "Поднимаешься на мостик к капитану?"));
        questionList.add(new Question(3, "Ты поднялся на мостик", "Рассказать правду о себе?"));
//        questionList.add(new Question(4, "ЭТО ТЕСТ", "ЭТО ТЕСТ?"));       --      ВСЕ РАБОТАЕТ
    }

    public void initialAnswer() {
                              // id, questId, duality, answer, answerDie
        answerList.add(new Answer(1, true, "Принять вызов", null));
        answerList.add(new Answer(1, false, "Отклонить вызов", "Ты отклонил вызов"));
        answerList.add(new Answer(2, true, "Подниматься на мостик", null));
        answerList.add(new Answer(2, false, "Отказаться подниматься на мостик", "Ты не пошел на переговоры"));
        answerList.add(new Answer(3, true, "Рассказать правду о себе", null));
        answerList.add(new Answer(3, false, "Солгать о себе", "Твою ложь разоблачили"));
//        answerList.add(new Answer(4, true, "ЭТО ТЕСТ ДА", null));
//        answerList.add(new Answer(4, false, "ЭТО ТЕСТ НЕТ", "ЭТО ТЕСТ"));
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public Question getQuestionById(Integer id) {
        return questionList.get(id - 1);
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public Answer getAnswerById(Integer id) {
        return answerList.get(id - 1);
    }

}
