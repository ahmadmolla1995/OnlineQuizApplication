<%@ page import="ir.maktab.finalproject.onlinequizapplication.model.Course" %>
<%@ page import="java.util.Set" %>
<%@ page import="ir.maktab.finalproject.onlinequizapplication.model.Exam" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/css/student_panel.css">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <title>Student Panel</title>
</head>


<body>
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="studentPanel/viewCourses">ViewCourses<span class="sr-only">(current)</span></a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="row ">
            <div class="col">
                <% if (request.getAttribute("courses") != null) { %>
                <div class="table-responsive bg-transparent" style="margin-top: 30px">
                    <table class="table table-bordered table-dark">
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
    <script src="/js/student_panel.js"></script>

</body>
</html>