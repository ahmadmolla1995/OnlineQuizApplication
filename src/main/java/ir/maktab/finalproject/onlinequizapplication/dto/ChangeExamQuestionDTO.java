package ir.maktab.finalproject.onlinequizapplication.dto;


public class ChangeExamQuestionDTO {
    private final String request;
    private final String response;

    public ChangeExamQuestionDTO(String request, String response) {
        this.request = request;
        this.response = response;
    }


    public String getRequest() {
        return request;
    }

    public String getResponse() {
        return response;
    }
}

