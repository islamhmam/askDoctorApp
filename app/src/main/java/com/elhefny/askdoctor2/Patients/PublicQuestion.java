package com.elhefny.askdoctor2.Patients;

public class PublicQuestion {
    private String questionBody, date, answer;
    Patient patient;


    public PublicQuestion() {
    }

    public PublicQuestion(String questionBody, String date, String answer, Patient patient) {
        this.questionBody = questionBody;
        this.date = date;
        this.answer = answer;
        this.patient = patient;
    }


    public String getQuestionBody() {
        return questionBody;
    }

    public String getDate() {
        return date;
    }


    public String getAnswer() {
        return answer;
    }

    public Patient getPatient() {
        return patient;
    }
}
