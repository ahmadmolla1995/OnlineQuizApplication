<%@ page import="ir.maktab.finalproject.onlinequizapplication.model.Course" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/css/manager_courses_panel.css">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <script type="text/javascript" src="/js/manager_courses_panel.js"></script>
    <title>Courses</title>
</head>


<body>
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="modal" data-target="#createCourseModal">CreateCourse<span class="sr-only">(current)</span></a>

                        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="createCourseModal" aria-labelledby="myModal">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header" style="color: white; background-color: #349dff;">
                                        <h5 class="modal-title" id="myModal" style="text-align: center;">Create Course</h5>

                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>

                                    <div class="modal-body">
                                        <div class="form-group">
                                            <form class="text-center" action="managerPanel/createCourse" method="post">
                                                <input type="text" id="courseCode" name="courseCode" required placeholder="Course Code" class="form-control mb-2">

                                                <input type="text" id="title" name="title" required placeholder="Title" class="form-control mb-2">

                                                <input type="date" id="startDate" name="startDate" required placeholder="Start Date" class="form-control mb-2">

                                                <input type="date" id="endDate" name="endDate" required placeholder="End Date" class="form-control mb-2">

                                                <select id="courseStatus" name="courseStatus" class="form-control mb-2">
                                                    <option value="ENABLE">Enable</option>
                                                    <option value="DISABLE">Disable</option>
                                                </select>

                                                <div class="modal-footer" style="background-color: white;">
                                                    <button type="button" style="float: left;" class="btn btn-primary" data-dismiss="modal"> Cancel </button>
                                                    <button type="submit" style="float: right;" class="btn btn-primary"> Save </button>
                                                </div>
                                            </form>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="managerPanel/viewCourses">View Courses</a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="row">
            <div class="col">
                <%  if (request.getAttribute("courses") != null) { %>
                        <div class="table-responsive bg-transparent" style="margin-top: 30px">
                                <table class="table table-bordered table-dark">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Course Code</th>
                                            <th scope="col">Title</th>
                                            <th scope="col">Start Date</th>
                                            <th scope="col">End Date</th>
                                            <th scope="col">Course Status</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <% List<Course> courses = (List<Course>) request.getAttribute("courses");
                                            for (int i = 0; i < courses.size(); i++) {
                                        %>
                                            <tr>
                                                <td> <%= i + 1 %> </td>
                                                <td> <%= courses.get(i).getCourseCode() %> </td>
                                                <td> <%= courses.get(i).getTitle() %> </td>
                                                <td> <%= courses.get(i).getStartDate() %> </td>
                                                <td> <%= courses.get(i).getEndDate() %> </td>
                                                <td> <%= courses.get(i).getCourseStatus() %> </td>

                                                <td>
                                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addStudentModal" onclick="addStudentSetAccountID('<%= courses.get(i).getId() %>')">
                                                        Add Student
                                                    </button>

                                                    <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="addStudentModal" aria-labelledby="studentModal">
                                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header" style="color: white; background-color: #349dff;">
                                                                    <h5 class="modal-title" id="studentModal">Add Student</h5>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>

                                                                <div class="modal-body">
                                                                    <div class="form-group">
                                                                        <form class="text-center" action="addStudent" method="post">
                                                                            <label for="studentID">StudentID</label>
                                                                            <input type="text" id="studentID" name="studentID" required placeholder="Student ID" class="form-control mb-2">
                                                                            <input type="text" id="addStudentCourseID" name="courseID" hidden>

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
                                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addTeacherModal" onclick="addTeacherSetAccountID('<%= courses.get(i).getId() %>')">
                                                        Add Teacher
                                                    </button>

                                                    <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="addTeacherModal" aria-labelledby="teacherModal">
                                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header" style="color: white; background-color: #349dff;">
                                                                    <h5 class="modal-title" id="teacherModal">Add Teacher</h5>

                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>

                                                                <div class="modal-body">
                                                                    <div class="form-group">
                                                                        <form class="text-center" action="addTeacher" method="post">
                                                                            <label for="teacherID">TeacherID</label>
                                                                            <input type="text" id="teacherID" name="teacherID" required placeholder="Teacher ID" class="form-control mb-2">
                                                                            <input type="text" id="addTeacherCourseID" name="courseID" hidden>

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
                                                    <form action="viewParticipants" method="post">
                                                        <input type="hidden" name="courseID" value="<%= courses.get(i).getId() %>">
                                                        <button type="submit" class="btn btn-primary">View Participants</button>
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


    </div>


    <script src="/assets/js/jquery-3.3.1.slim.min.js"></script>
    <script src="/assets/js/jquery-3.4.1.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>

</body>
</html>

