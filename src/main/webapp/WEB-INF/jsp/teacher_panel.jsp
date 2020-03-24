<%@ page import="ir.maktab.finalproject.onlinequizapplication.model.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="ir.maktab.finalproject.onlinequizapplication.model.Exam" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/css/teacher_panel.css">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <title>Teacher Panel</title>
</head>


<body>
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="teacherPanel/viewCourses">viewCourses<span class="sr-only">(current)</span></a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="row ">
            <div class="col">
                <% if (request.getAttribute("courses") != null) { %>
                <div class="table-responsive bg-transparent" style="margin-top: 30px">
                    <table class="table table-bordered table-dark" id="unconfirmed_accounts_table">
                        <thead>
                            <tr>
                                <th scope="col">Course Code</th>
                                <th scope="col">Title</th>
                                <th scope="col">Course Status</th>
                                <th scope="col">Start Date</th>
                                <th scope="col">End Date</th>
                            </tr>
                        </thead>

                        <tbody>
                            <%
                                Set<Course> courses = (Set<Course>) request.getAttribute("courses");
                                for (Course course: courses) {
                            %>
                            <tr>
                                <td> <%= course.getCourseCode() %> </td>
                                <td> <%= course.getTitle() %></td>
                                <td> <%= course.getCourseStatus().toString() %></td>
                                <td> <%= course.getStartDate().toString() %></td>
                                <td> <%= course.getEndDate().toString() %></td>
                                <td>
                                    <form action="viewCourses/viewExams" method="post">
                                        <input type="hidden" name="courseID" value="<%= course.getId() %>">
                                        <button type="submit" class="btn btn-primary">View Exams</button>
                                    </form>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createExamModal" onclick="setCourseID('<%= course.getId() %>')">
                                        Create Exam
                                    </button>

                                    <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="createExamModal" aria-labelledby="examModal">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header" style="color: white; background-color: #349dff;">
                                                    <h5 class="modal-title" id="examModal">Create Exam</h5>

                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>

                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <form class="text-center" action="viewCourses/createExam" method="post">
                                                            <input type="text" name="title" required placeholder="Title" class="form-control mb-2">
                                                            <input type="text" name="description" required placeholder="Description" class="form-control mb-2">
                                                            <input type="text" name="duration" required placeholder="Duration" class="form-control mb-2">
                                                            <input type="text" id="courseID" name="courseID" hidden>

                                                            <div class="modal-footer" style="background-color: white;">
                                                                <button type="button" class="btn btn-primary" data-dismiss="modal"> Cancel </button>
                                                                <button type="submit" class="btn btn-primary"> Create </button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <%      }
                            }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="row ">
            <div class="col">
                <% if (request.getAttribute("exams") != null) { %>
                <div class="table-responsive bg-transparent" style="margin-top: 30px">
                    <table class="table table-bordered table-dark" id="course_exams_table">
                        <thead>
                            <tr>
                                <th scope="col">Title</th>
                                <th scope="col">Description</th>
                                <th scope="col">Duration</th>
                                <th scope="col">Total Grade</th>
                            </tr>
                        </thead>

                        <tbody>
                            <%
                                List<Exam> exams = (List<Exam>) request.getAttribute("exams");
                                for (Exam exam: exams) {
                            %>
                            <tr>
                                <td> <%= exam.getTitle() %> </td>
                                <td> <%= exam.getDescription() %></td>
                                <td> <%= exam.getDuration() %></td>
                                <td> <%= exam.getTotalGrade() %> </td>

                                <td>
                                    <form action="viewExams/deleteExam" method="post">
                                        <input type="hidden" name="examID" value="<%= exam.getId() %>">
                                        <button type="submit" class="btn btn-primary">Delete</button>
                                    </form>
                                </td>

                                <td>
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editExamExplanationModal"
                                            onclick="setExamExplanation(
                                                    '<%= exam.getId() %>',
                                                    '<%= exam.getTitle() %>',
                                                    '<%= exam.getDescription() %>',
                                                    '<%= exam.getDuration() %>'
                                                    )">
                                        Edit Details
                                    </button>

                                    <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="editExamExplanationModal" aria-labelledby="editModal">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header" style="color: white; background-color: #349dff;">
                                                    <h5 class="modal-title" id="editModal">Edit Details</h5>

                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>

                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <form class="text-center" action="viewExams/editExam" method="post">
                                                            <input type="text" name="title" id="editTitle" required placeholder="Title" class="form-control mb-2">
                                                            <input type="text" name="description" id="editDescription" required placeholder="Description" class="form-control mb-2">
                                                            <input type="text" name="duration" id="editDuration" required placeholder="Duration" class="form-control mb-2">
                                                            <input type="text" name="examID" id="examID" hidden>

                                                            <div class="modal-footer" style="background-color: white;">
                                                                <button type="button" class="btn btn-primary" data-dismiss="modal"> Cancel </button>
                                                                <button type="submit" class="btn btn-primary"> Edit </button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </td>

                                <td>
                                    <form action="viewExams/viewQuestions" method="post">
                                        <input type="hidden" id="viewExamsQuestion" name="examID" value="<%= exam.getId() %>">
                                        <button type="submit" class="btn btn-primary">View Questions</button>
                                    </form>
                                </td>

                                <td>
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addDescriptiveQuestionModal" onclick="setExamID('<%= exam.getId() %>')">
                                        Descriptive Question
                                    </button>

                                    <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="addDescriptiveQuestionModal" aria-labelledby="descriptiveQuestionModal">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header" style="color: white; background-color: #349dff;">
                                                    <h5 class="modal-title" id="descriptiveQuestionModal">Add Question</h5>

                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>

                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <form class="text-center" action="viewExams/createDescriptiveQuestion" method="post">
                                                            <input type="text" name="title" id="questionTitle" required placeholder="Question Title" class="form-control mb-2">
                                                            <input type="text" name="problemDescription" id="problemDescription" required placeholder="Problem Description" class="form-control mb-2">
                                                            <input type="text" name="grade" id="questionGrade" required placeholder="Question Grade" class="form-control mb-2">
                                                            <input type="hidden" name="examID" id="addDescriptiveQuestionExamID" hidden>

                                                            <div class="modal-footer" style="background-color: white;">
                                                                <button type="button" class="btn btn-primary" data-dismiss="modal"> Cancel </button>
                                                                <button type="submit" class="btn btn-primary"> Add </button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>

                                <td>
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addMultipleChoiceQuestionModal" onclick="setExamID('<%= exam.getId() %>')">
                                        MultipleChoice Question
                                    </button>

                                    <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="addMultipleChoiceQuestionModal" aria-labelledby="multipleChoiceQuestionModal">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header" style="color: white; background-color: #349dff;">
                                                    <h5 class="modal-title" id="multipleChoiceQuestionModal">Add Question</h5>

                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>

                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <form class="text-center" action="viewExams/createMultipleChoiceQuestion" method="post">
                                                            <input type="text" name="title" id="multipleChoiceQuestionTitle" required placeholder="Question Title" class="form-control mb-2">
                                                            <input type="text" name="problemDescription" id="multipleChoiceQuestionProblemDescription" required placeholder="Problem Description" class="form-control mb-2">
                                                            <input type="text" name="grade" id="multipleChoiceQuestionGrade" required placeholder="Question Grade" class="form-control mb-2">
                                                            <input type="text" name="options" id="options" required placeholder="Options" class="form-control mb-2">
                                                            <input type="text" name="correctResponse" id="correctResponse" required placeholder="Correct Response" class="form-control mb-2">
                                                            <input type="hidden" name="examID" id="multipleChoiceQuestionExamID" hidden>

                                                            <div class="modal-footer" style="background-color: white;">
                                                                <button type="button" class="btn btn-primary" data-dismiss="modal"> Cancel </button>
                                                                <button type="submit" class="btn btn-primary"> Add </button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>

                                <td>
                                    <form action="viewExams/viewArchivedTests" method="get">
                                        <input type="hidden" id="archivedTestExamID" name="examID" value="<%= exam.getId() %>">
                                        <input type="hidden" id="archivedTestCourseID" name="courseID" value="<%= request.getAttribute("courseID") %>">
                                        <button type="submit" class="btn btn-primary">Archived Tests</button>
                                    </form>
                                </td>

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