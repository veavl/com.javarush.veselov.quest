package org.example.m3finalprogectv2.entity;

public class Question {
    private Integer id;
    private String heading;
    private String question;

    public Question(Integer id, String heading, String question) {
        this.id = id;
        this.heading = heading;
        this.question = question;
    }

    public Integer getId() {
        return id;
    }

    public String getHeading() {
        return heading;
    }

    public String getQuestion() {
        return question;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
