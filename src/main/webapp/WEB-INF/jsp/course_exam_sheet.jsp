<%@ page import="ir.maktab.finalproject.onlinequizapplication.model.ExamSheet" %>
<%@ page import="ir.maktab.finalproject.onlinequizapplication.model.QuestionItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/css/teacher_panel.css">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <title>ExamSheet Page</title>
</head>


<body>
    <div class="container">
        <div class="row ">
            <div class="col">
                <% if (request.getAttribute("exam_sheets") != null) { %>
                    <div class="table-responsive bg-transparent" style="margin-top: 30px">
                        <table class="table table-bordered table-dark" id="unconfirmed_accounts_table">
                            <thead>
                                <tr>
                                    <th scope="col">StudentID</th>
                                </tr>
                            </thead>

                            <tbody>
                                <%
                                    Set<ExamSheet> examSheets = (Set<ExamSheet>) request.getAttribute("exam_sheets");
                                    for (ExamSheet examSheet: examSheets) {
                                %>
                                <tr>
                                    <td> <%= examSheet.getStudentID() %> </td>
                                    <td>
                                        <form action="viewExamSheets/viewSheetQuestions" method="get">
                                            <input type="hidden" id="examSheetID" name="examSheetID" value="<%= examSheet.getId() %>">
                                            <button type="submit" class="btn btn-primary"> ViewQuestions </button>
                                        </form>
                                    </td>
                                </tr>
                                <%      }
                                }
                                %>
                            </tbody>
                        </table>
                    </div>

                <% if (request.getAttribute("sheet_questions") != null) { %>
                <div class="table-responsive bg-transparent" style="margin-top: 30px">
                    <table class="table table-bordered table-dark">
                        <thead>
                            <tr>
                                <th scope="col">QuestionItemID</th>
                                <th scope="col">ProblemDescription</th>
                                <th scope="col">Response</th>
                            </tr>
                        </thead>

                        <tbody>
                        <%
                            List<QuestionItem> sheetQuestions = (List<QuestionItem>) request.getAttribute("sheet_questions");
                            for (QuestionItem questionItem: sheetQuestions) {
                        %>
                        <tr>
                            <td> <%= questionItem.getId() %> </td>
                            <td> <%= questionItem.getProblemDescription() %> </td>
                            <td> <%= questionItem.getResponse() %> </td>
                            <td>
                                <form action="viewSheetQuestions/markQuestion" method="get">
                                    <input type="hidden" name="examSheetID" value="<%= request.getAttribute("examSheetID") %>">
                                    <input type="hidden" name="questionItemID" value="<%= questionItem.getId() %>">
                                    <input type="text" id="grade" name="grade" required placeholder="Grade" style="text-align:center" class="form-control mb-2">
                                    <button type="submit" class="btn btn-primary form-control mb-2"> Save </button>
                                </form>
                            </td>
                        </tr>
                        <%      }
                        }
                        %>
                        </tbody>
                    </table>
                </div>

                <% if (request.getAttribute("exam_results") != null) { %>
                <div class="table-responsive bg-transparent" style="margin-top: 30px">
                    <table class="table table-bordered table-dark">
                        <thead>
                            <tr>
                                <th scope="col">StudentName</th>
                                <th scope="col">Grade</th>
                                <th scope="col">Number of Participants</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td></td>
                                <td></td>
                                <td><%= request.getAttribute("num_of_participants") %></td>
                            </tr>
                        <%
                            List<Double> exam_results = (List<Double>) request.getAttribute("exam_results");
                            List<String> participants = (List<String>) request.getAttribute("participants");
                            for (int i = 0; i < exam_results.size(); i++) {
                        %>
                        <tr>
                            <td> <%= participants.get(i) %> </td>
                            <td> <%= exam_results.get(i) %> </td>
                            <td> </td>
                        </tr>
                        <%      }
                        }
                        %>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>

    <script src="/assets/js/jquery-3.3.1.slim.min.js"></script>
    <script src="/assets/js/jquery-3.4.1.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/js/teacher_panel.js"></script>

</body>
</html>