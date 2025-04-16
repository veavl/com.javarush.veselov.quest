package org.example.m3finalprogectv2.service;

import org.example.m3finalprogectv2.entity.Answer;
import org.example.m3finalprogectv2.entity.Question;
import org.example.m3finalprogectv2.repository.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Service {

    public Repository repository;

    public Service () {
        repository = new Repository();
        repository.initialQuest();
        repository.initialAnswer();
    }

    public Repository getRepository() {
        return repository;
    }

    public List<Question> getQuestionList() {
        return repository.questionList;
    }

    public Question getQuestionById(Integer id) {
        return repository.questionList.get(id - 1);
    }

    public List<Answer> getAnswerList() {
        return repository.answerList;
    }

    public Answer getAnswerById(Integer id) {
        return repository.answerList.get(id - 1);
    }

    // УСТАНАВЛИВАЕМ АТРИБУТ ДЛЯ ЗАПРОСА В ТОМ СЛУЧАЕ, КОГДА ОТВЕТ НЕТ И ЭТО ПОРАЖЕНИЕ. ПОЛУЧАЕМ AnswerDie
    public void setAttributeAnswerDie (List<Answer> answerList, Integer idQuestion, HttpServletRequest request) {
        for (Answer answer: answerList) {
            if (answer.getId() == idQuestion - 1 && answer.getDuality() == false) {
                request.setAttribute("answerDie", answer.getAnswerDie());
            }
        }
    }


}
