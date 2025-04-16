package org.example.m3finalprogectv2.entity;

public class Answer {

    private Integer id;
    private boolean duality;
    private String answer;
    private String answerDie;

    public Answer(Integer id, boolean duality, String answer, String answerDie) {
        this.id = id;
        this.answerDie = answerDie;
        this.duality = duality;
        this.answer = answer;
    }

    public Integer getId() {
        return id;
    }

    public String getAnswerDie() {
        return answerDie;
    }

    public boolean getDuality() {
        return duality;
    }

    public String getAnswer() {
        return answer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAnswerDie(String answerDie) {
        this.answerDie = answerDie;
    }

    public void setDuality(boolean duality) {
        this.duality = duality;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answerDie=" + answerDie +
                ", duality=" + duality +
                ", answer='" + answer + '\'' +
                '}';
    }
}
