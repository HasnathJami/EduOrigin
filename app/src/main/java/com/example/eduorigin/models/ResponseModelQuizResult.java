package com.example.eduorigin.models;

public class ResponseModelQuizResult {
    String email,verdict;
    Integer result;

    public ResponseModelQuizResult() {
    }

    public ResponseModelQuizResult(String email, String verdict, Integer result) {
        this.email = email;
        this.verdict = verdict;
        this.result = result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
