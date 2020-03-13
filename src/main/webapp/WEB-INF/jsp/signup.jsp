<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/css/signup.css">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">

    <title>SignUp</title>
</head>


<body>
    <div class="container mt-3" style="max-width: 23rem">
        <div class="row">
            <div class="col">
                <div class="card border-warning mb-3">
                    <div class="card-header text-center"> <h2>Sign Up</h2> </div>

                    <div class="card-body text-warning">
                        <div class="form-group">
                            <form class="text-center" action="/account/signUp/signUpCheck" method="post">
                                <div class="col">
                                    <div class="row">
                                        <label for="firstName"></label>
                                        <input type="text" id="firstName" name="firstName" class="form-control mb-2"
                                               required placeholder="First Name">

                                        <label for="lastName"></label>
                                        <input type="text" id="lastName" name="lastName" class="form-control mb-2"
                                               required placeholder="Last Name">
                                    </div>

                                    <div class="row">
                                        <label for="username"></label>
                                        <input type="text" id="username" name="username" class="form-control mb-2"
                                               required placeholder="Username">

                                        <label for="password"></label>
                                        <input type="password" id="password" name="password" class="form-control mb-2"
                                               required placeholder="Password">
                                    </div>

                                    <div class="row">
                                        <label for="education"></label>
                                        <select id="education" name="education" class="form-control mb-2">
                                            <option value="DIPLOMA">DIPLOMA</option>
                                            <option value="BACHELOR">BACHELOR</option>
                                            <option value="MASTER">MASTER</option>
                                            <option value="DOCTORAL">DOCTORAL</option>
                                            <option value="POST_DOCTORAL">POST DOCTORAL</option>
                                        </select>

                                        <label for="homeNumber"></label>
                                        <input type="text" id="homeNumber" name="homeNumber" class="form-control mb-2"
                                               required placeholder="Home Number">
                                    </div>

                                    <div class="row">
                                        <label for="cellPhoneNumber"></label>
                                        <input type="text" id="cellPhoneNumber" name="cellPhoneNumber" class="form-control mb-2"
                                               required placeholder="Phone Number">

                                        <label for="email"></label>
                                        <input type="email" id="email" name="email" class="form-control mb-2"
                                               required placeholder="Email">
                                    </div>

                                    <div class="row">
                                        <label for="roleType"></label>
                                        <select id="roleType" name="roleType" class="form-control mb-2">
                                            <option value="ROLE_STUDENT">STUDENT</option>
                                            <option value="ROLE_TEACHER">TEACHER</option>
                                            <option value="ROLE_MANAGER">MANAGER</option>
                                            <option value="ROLE_GUEST">GUEST</option>
                                            <option value="ROLE_TEACHING_ASSISTANT">TEACHING ASSISTANT</option>
                                        </select>

                                        <label for="address"></label>
                                        <input type="text" id="address" name="address" class="form-control mb-2"
                                               required placeholder="Address">
                                    </div>

                                </div>

                                <button class="btn btn-info btn-block" type="submit">SignUp</button>
                            </form>
                        </div>
                    </div>
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
