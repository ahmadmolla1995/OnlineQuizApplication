<%@ page import="ir.maktab.finalproject.onlinequizapplication.model.Account" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/css/manager_panel.css">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <script type="text/javascript" src="/js/manager_panel.js"></script>
    <title>Manager Panel</title>
</head>


<body>
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="managerPanel/viewAccountDetail">View</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="managerPanel/ListUnconfirmedAccounts">Confirm Accounts<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="managerPanel/searchCourses">Search Courses</a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="row ">
            <div class="col">
                <div class="table-responsive" style="margin-top: 30px">
                    <table class="table table-bordered table-dark" id="unconfirmed_accounts_table">
                        <thead>
                            <tr>
                                <th scope="col">Account ID</th>
                                <th scope="col">Username</th>
                                <th scope="col">First Name</th>
                                <th scope="col">Last Name</th>
                                <th scope="col">Role</th>
                                <th scope="col">Account Status</th>
                            </tr>
                        </thead>

                        <tbody>
                            <%
                                if (request.getAttribute("unconfirmed_accounts") != null) {
                                    List<Account> accounts = (List<Account>) request.getAttribute("unconfirmed_accounts");
                                    for (int i = 0; i < accounts.size(); i++) {
                            %>

                            <tr>
                                <td> <%= accounts.get(i).getId() %> </td>
                                <td> <%= accounts.get(i).getUsername() %> </td>
                                <td> <%= accounts.get(i).getPerson().getFirstName() %> </td>
                                <td> <%= accounts.get(i).getPerson().getLastName() %> </td>
                                <td> <%= accounts.get(i).getRoles() %> </td>
                                <td> <%= accounts.get(i).getAccountStatus().toString() %> </td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#view">View</button>
                                </td>
                                <td>
                                    <form action="confirmAccount" method="post">
                                        <input type="hidden" name="accountID" value="<%= accounts.get(i).getId() %>">
                                        <button type="submit" class="btn btn-primary">Confirm</button>
                                    </form>
                                </td>

                                <td>
                                    <form action="rejectAccount" method="post">
                                        <input type="hidden" name="accountID" value="<%= accounts.get(i).getId() %>">
                                        <button type="submit" class="btn btn-primary">Reject</button>
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


</body>
</html>