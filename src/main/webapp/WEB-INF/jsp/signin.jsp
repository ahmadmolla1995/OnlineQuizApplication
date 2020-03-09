<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/css/signin.css">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <title>Login</title>

</head>
<body>
    <div class="container mt-3" style="max-width: 23rem">
        <div class="row">
            <div class="col">
                <div class="card border-warning mb-3">
                    <div class="card-header text-center"> <h2>Sign In</h2> </div>

                    <div class="card-body text-warning">
                        <div class="form-group">
                            <form class="text-center" action="signIn/signInCheck" method="post">
                                <label for="username"></label>
                                <input type="text" id="username" name="username" required class="form-control mb-2" placeholder="Username">
                                <label for="password"></label>
                                <input type="password" id="password" name="password" required class="form-control mb-2" placeholder="Password">
                                <button class="btn btn-info btn-block" type="submit">Sign in</button>
                            </form>
                        </div>

                        <h6 class="text-center"> <a href="signUp">Dont't You Have Account? Sign Up</a> </h6>

                    </div>
                </div>
            </div>
        </div>
    </div>


    <script src="/assets/js/jquery-3.4.1.min.js"></script>
    <script src="/assets/js/jquery-3.3.1.slim.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/css/bootstrap.min.css"></script>

</body>
</html>
