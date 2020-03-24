<%@ page import="ir.maktab.finalproject.onlinequizapplication.model.Question" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/css/exam_questions.css">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <title>Questions</title>
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col">
                <%  if (request.getAttribute("questions") != null) { %>
                <div class="table-responsive bg-transparent" style="margin-top: 30px">
                    <table class="table table-bordered table-dark">
                        <thead>
                            <tr>
                                <th scope="col">Question ID</th>
                                <th scope="col">Title</th>
                                <th scope="col">Problem Description</th>
                                <th scope="col">Grade</th>
                            </tr>
                        </thead>

                        <tbody>
                            <% Set<Question> questions = (Set<Question>) request.getAttribute("questions");
                                for (Question question: questions) {
                            %>
                            <tr>
                                <td> <%= question.getId() %> </td>
                                <td> <%= question.getTitle() %> </td>
                                <td> <%= question.getProblemDescription() %> </td>
                                <td> <%= question.getGrade() %> </td>
                                <td>
                                    <form action="viewQuestions/deleteQuestion" method="post">
                                        <input type="hidden" id="examID" name="examID" value="<%= request.getAttribute("examID") %>">
                                        <input type="hidden" id="questionID" name="questionID" value="<%= question.getId() %>">
                                        <button type="submit" class="btn btn-primary">Delete</button>
                                    </form>
                                </td>
                            </tr>
                            <%  } %>
                        </tbody>
                    </table>
                </div>
                <%  } %>
            </div>
        </div>
    </div>


    <script src="/assets/js/jquery-3.3.1.slim.min.js"></script>
    <script src="/assets/js/jquery-3.4.1.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>

</body>
</html>

