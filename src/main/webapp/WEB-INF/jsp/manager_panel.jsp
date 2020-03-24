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
                    <a class="nav-link" href="managerPanel/ListUnconfirmedAccounts">Accounts<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../course/managerPanel">Courses</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="modal" data-target="#searchUsersModal">Search Users<span class="sr-only">(current)</span></a>

                    <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="searchUsersModal" aria-labelledby="searchModal">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header" style="color: white; background-color: #349dff;">
                                    <h5 class="modal-title" id="searchModal" style="text-align: center;">Search Users</h5>

                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>

                                <div class="modal-body">
                                    <div class="form-group">
                                        <form class="text-center" action="managerPanel/filterUsers" method="post">
                                            <input type="text" name="username" placeholder="Username" class="form-control mb-2">
                                            <input type="text" name="firstName" placeholder="First Name" class="form-control mb-2">
                                            <input type="text" name="lastName" placeholder="Last Name" class="form-control mb-2">
                                            <input type="text" name="address" placeholder="Address" class="form-control mb-2">

                                            <select name="role" class="form-control mb-2">
                                                <option value="ROLE_MANAGER">Manager</option>
                                                <option value="ROLE_TEACHER">Teacher</option>
                                                <option value="ROLE_STUDENT">Student</option>
                                                <option value="ROLE_TEACHING_ASSISTANT">Teaching Assistant</option>
                                                <option value="ROLE_GUEST">Guest</option>
                                            </select>

                                            <select name="accountStatus" class="form-control mb-2">
                                                <option value="CONFIRMED">Confirmed</option>
                                                <option value="WAITING_CONFIRMATION">Waiting Confirmation</option>
                                                <option value="REJECTED">Rejected</option>
                                            </select>

                                            <div class="modal-footer" style="background-color: white;">
                                                <button type="button" class="btn btn-primary" data-dismiss="modal"> Cancel </button>
                                                <button type="submit" class="btn btn-primary"> Save </button>
                                            </div>
                                        </form>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                </li>
            </ul>
        </div>
    </nav>

    <div class="row ">
        <div class="col">
            <% if (request.getAttribute("unconfirmed_accounts") != null) { %>
            <div class="table-responsive bg-transparent" style="margin-top: 30px">
                <table class="table table-bordered table-dark" id="unconfirmed_accounts_table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Username</th>
                        <th scope="col">First Name</th>
                        <th scope="col">Last Name</th>
                        <th scope="col">Role</th>
                        <th scope="col">Account Status</th>
                    </tr>
                    </thead>

                    <tbody>
                    <%
                        List<Account> accounts = (List<Account>) request.getAttribute("unconfirmed_accounts");
                        for (int i = 0; i < accounts.size(); i++) {
                    %>

                    <tr>
                        <td> <%= i + 1 %> </td>
                        <td> <%= accounts.get(i).getUsername() %> </td>
                        <td> <%= accounts.get(i).getPerson().getFirstName() %> </td>
                        <td> <%= accounts.get(i).getPerson().getLastName() %> </td>
                        <td> <%= accounts.get(i).getRoles().iterator().next().getRoleType().toString() %> </td>
                        <td> <%= accounts.get(i).getAccountStatus().toString() %> </td>
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

                        <td>
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#viewAccountDetail"
                                    onclick="displayAccount(
                                            '<%= accounts.get(i).getId() %>',
                                            '<%= accounts.get(i).getUsername() %>',
                                            '<%= accounts.get(i).getPerson().getFirstName() %>',
                                            '<%= accounts.get(i).getPerson().getLastName() %>',
                                            '<%= accounts.get(i).getAccountStatus() %>',
                                            '<%= accounts.get(i).getPerson().getAddress() %>',
                                            '<%= accounts.get(i).getPerson().getEmail() %>',
                                            '<%= accounts.get(i).getPerson().getHomeNumber() %>',
                                            '<%= accounts.get(i).getPerson().getCellPhoneNumber() %>'
                                            )">
                                Edit
                            </button>

                            <div class="modal fade" id="viewAccountDetail" tabindex="-1" role="dialog" aria-labelledby="showAccount" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content bg-dark">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="showAccount">Account Info</h5>

                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>

                                        <div class="modal-body">
                                            <div class="form-group">
                                                <form class="text-center" action="editAccount" method="post">
                                                    <label for="username">Username:</label>
                                                    <input type="text" id="username" name="username" contenteditable="true" class="form-control mb-2">

                                                    <label for="firstName">First Name:</label>
                                                    <input type="text" id="firstName" name="firstName" readonly class="form-control mb-2">

                                                    <label for="lastName">Last Name:</label>
                                                    <input type="text" id="lastName" name="lastName" readonly class="form-control mb-2">

                                                    <label for="role">Role:</label>
                                                    <select id="role" name="role" class="form-control mb-2">
                                                        <option value="ROLE_STUDENT">Student</option>
                                                        <option value="ROLE_TEACHER">Teacher</option>
                                                        <option value="ROLE_TEACHING_ASSISTANT">Teaching Assistant</option>
                                                        <option value="ROLE_GUEST">Guest</option>
                                                    </select>

                                                    <label for="accountStatus">Account Status:</label>
                                                    <input type="text" id="accountStatus" name="accountStatus" readonly class="form-control mb-2">

                                                    <label for="address">Address:</label>
                                                    <input type="text" id="address" name="address" readonly class="form-control mb-2">

                                                    <label for="email">Email:</label>
                                                    <input type="email" id="email" name="email" readonly class="form-control mb-2">

                                                    <label for="homeNumber">Home Number</label>
                                                    <input type="text" id="homeNumber" name="homeNumber" readonly class="form-control mb-2">

                                                    <label for="cellPhoneNumber">Cell Phone Number</label>
                                                    <input type="text" id="cellPhoneNumber" name="cellPhoneNumber" readonly class="form-control mb-2">

                                                    <input type="text" id="accountID" name="accountID" hidden>

                                                    <div class="modal-footer bg-dark">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal"> Cancel </button>
                                                        <button type="submit" class="btn btn-primary"> Save </button>
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
</div>

<script src="/assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="/assets/js/jquery-3.4.1.min.js"></script>
<script src="/assets/js/popper.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>


</body>
</html>