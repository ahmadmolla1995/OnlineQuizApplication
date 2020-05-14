<%@ page import="ir.maktab.finalproject.onlinequizapplication.model.Account" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/css/user_filter_result.css">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <title>User Filter Result</title>
</head>


<body>
<%  if (request.getAttribute("filteredAccounts") != null) { %>
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="table-responsive bg-transparent" style="margin-top: 30px">
                    <table class="table table-bordered table-dark">
                        <thead>
                            <tr>
                                <th scope="col"></th>
                                <th scope="col">Username</th>
                                <th scope="col">First Name</th>
                                <th scope="col">Last Name</th>
                                <th scope="col">Education</th>
                                <th scope="col">Account Status</th>
                                <th scope="col">Home Number</th>
                                <th scope="col">CellPhoneNumber</th>
                            </tr>
                        </thead>

                        <tbody>
                            <% List<Account> accounts = (List<Account>) request.getAttribute("filteredAccounts");
                                for (int i = 0; i < accounts.size(); i++) {
                            %>
                            <tr>
                                <td> <%= i + 1 %> </td>
                                <td> <%= accounts.get(i).getUsername() %> </td>
                                <td> <%= accounts.get(i).getPerson().getFirstName() %> </td>
                                <td> <%= accounts.get(i).getPerson().getLastName() %> </td>
                                <td> <%= accounts.get(i).getPerson().getEducation() %> </td>
                                <td> <%= accounts.get(i).getAccountStatus() %> </td>
                                <td> <%= accounts.get(i).getPerson().getHomeNumber() %> </td>
                                <td> <%= accounts.get(i).getPerson().getCellPhoneNumber() %> </td>
                            </tr>
                            <%  } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
<%  } %>


<script src="/assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="/assets/js/jquery-3.4.1.min.js"></script>
<script src="/assets/js/popper.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>


</body>
</html>

