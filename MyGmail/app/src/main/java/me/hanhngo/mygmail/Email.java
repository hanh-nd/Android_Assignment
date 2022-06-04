package me.hanhngo.mygmail;

import java.util.Random;

public class Email {
    private String name;
    private String subject;
    private String body;
    private String time;
    private boolean isMarked;

    public Email(String name, String subject, String body, String time) {
        this.name = name;
        this.subject = subject;
        this.body = body;
        this.time = time;
        this.isMarked = new Random().nextInt(2) % 2 == 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }
}
