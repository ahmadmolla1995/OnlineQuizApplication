<%@ page import="ir.maktab.finalproject.onlinequizapplication.model.QuestionItem" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/css/exam_questions.css">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <title>Exam Page</title>
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col">
                <%  if (request.getAttribute("question") != null) { %>
                <div class="table-responsive bg-transparent" style="margin-top: 30px">
                    <table class="table table-bordered table-dark">
                        <tbody>
                            <%
                                QuestionItem questionItem = (QuestionItem) request.getAttribute("question");
                            %>
                            
                            <div class="container mt-3" style="max-width: 23rem">
                                <div class="row">
                                    <div class="col">
                                        <div class="card border-warning mb-3">
                                            <div class="card-header text-center"> <h2>Question</h2> </div>

                                            <div class="card-body text-warning">
                                                <div class="form-group">
                                                    <form action="changeQuestion" method="post" class="text-center">
                                                        <label for="problemDescription" style="float: left; color: red;">Problem Description</label>
                                                        <input type="text" id="problemDescription" name="problemDescription" value="<%= questionItem.getProblemDescription() %>" readonly class="form-control mb-2">
                                                        <label for="response" style="float: left; color: red;">Response</label>
                                                        <input type="text" id="response" name="response" value="<%= questionItem.getResponse() %>" class="form-control mb-2">

                                                        <button type="submit" name="request" value="next" class="btn btn-info btn-block">Next</button>
                                                        <button type="submit" name="request" value="previous" class="btn btn-info btn-block">Previous</button>
                                                    </form>

                                                    <form action="submitExamSheet" method="post" class="text-center">
                                                        <button type="submit" class="btn btn-info btn-block">Submit</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
