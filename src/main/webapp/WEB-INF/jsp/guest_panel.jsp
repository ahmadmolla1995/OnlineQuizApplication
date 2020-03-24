<%@ page import="java.util.List" %>
<%@ page import="ir.maktab.finalproject.onlinequizapplication.model.Course" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/css/guest_panel.css">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <title>Guest Panel</title>
</head>


<body>
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="guestPanel/viewCourses">ViewCourses<span class="sr-only">(current)</span></a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="row ">
            <div class="col">
                <% if (request.getAttribute("allCourses") != null) { %>
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
                            List<Course> courses = (List<Course>) request.getAttribute("allCourses");
                            for (Course course: courses) {
                        %>
                        <tr>
                            <td> <%= course.getCourseCode() %> </td>
                            <td> <%= course.getTitle() %></td>
                            <td> <%= course.getCourseStatus().toString() %></td>
                            <td> <%= course.getStartDate().toString() %></td>
                            <td> <%= course.getEndDate().toString() %></td>
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

</body>
</html>