<%@ page import="java.util.List" %>
<%@ page import="ir.maktab.finalproject.onlinequizapplication.model.Person" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/css/course_participants.css">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <title>Participants</title>
</head>


<body>
    <div class="container">
        <div class="row">
            <div class="col">
                <%  if (request.getAttribute("course_participants") != null) { %>
                <div class="table-responsive bg-transparent" style="margin-top: 30px">
                    <table class="table table-bordered table-dark">
                        <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">Username</th>
                            <th scope="col">First Name</th>
                            <th scope="col">Last Name</th>
                            <th scope="col">Role</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% List<Person> participants = (List<Person>) request.getAttribute("course_participants");
                            for (int i = 0; i < participants.size(); i++) {
                        %>
                        <tr>
                            <td> <%= i + 1 %> </td>
                            <td> <%= participants.get(i).getAccount().getUsername() %> </td>
                            <td> <%= participants.get(i).getFirstName() %> </td>
                            <td> <%= participants.get(i).getLastName() %> </td>
                            <td> <%= participants.get(i).getAccount().getRoles().iterator().next().getRoleType() %> </td>
                            <td>
                                <form action="viewParticipants/deletePerson" method="post">
                                    <input type="hidden" id="courseID" name="courseID" value="<%= request.getAttribute("courseID") %>">
                                    <input type="hidden" id="personID" name="personID" value="<%= participants.get(i).getId() %>">
                                    <input type="hidden" id="roleType" name="roleType" value="<%= participants.get(i).getAccount().getRoles().iterator().next().getRoleType().toString() %>">
                                    <button type="submit" class="btn btn-primary">Remove</button>
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

