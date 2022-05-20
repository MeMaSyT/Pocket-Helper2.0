package com.example.myapplication.exams;

public class Exam {
    int id;
    String image, title, color, text, time, needClass;

    public Exam(int id, String image, String title, String color, String text, String time, String needClass) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.color = color;
        this.text = text;
        this.time = time;
        this.needClass = needClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNeedClass() {
        return needClass;
    }

    public void setNeedClass(String needClass) {
        this.needClass = needClass;
    }
}
