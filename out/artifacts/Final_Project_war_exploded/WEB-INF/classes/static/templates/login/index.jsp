<!DOCTYPE html>
<html lang="en" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>Login</title>

    <link rel="stylesheet" href="/assets/fonts/font.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.1/css/all.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.1/css/v4-shims.css">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="templates/login/backgournd.png">

    <script src="/assets/js/validation.js"></script>
    <script src="/assets/js/jquery-3.4.1.min.js"></script>
    <script src="/templates/login/js/login.js"></script>
    <script src="/environments/prod.environment.js"></script>

</head>

<body>
    <div class="container mt-5" style="max-width: 23rem">
        <div id="response"></div>

        <div class="row">
            <div class="col">
                <div class="card border-info mb-3">
                    <div class="card-header text-center">
                        <h4>ورود</h4>
                    </div>

                    <form class="needs-validation" novalidate id="loginForm">
                        <div class="form-row justify-content-center">
                            <div class="form-group col-10 mt-3">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fa fa-user"></i></span>
                                    </div>

                                    <input type="text" class="form-control" id="username" placeholder="نام کاربری"
                                           aria-describedby="inputGroupPrepend" required>
                                </div>
                            </div>

                            <div class="form-group col-10 mt-3" id="pwd-container">
                                <div class="mb-2">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" onclick="showPassword()"><i class="fa fa-eye-slash" id="passIcon"></i></span>
                                        </div>

                                        <input type="password" class="form-control" id="password" placeholder="رمز عبور"
                                               aria-describedby="inputGroupPrepend" required>

                                    </div>
                                </div>
                            </div>


                            <div class="col-10 text-center mb-3">
                                <button class="btn btn-primary col-12" type="submit">ورود</button>
                            </div>

                            <div class="col-10 mb-3 text-center">
                                <h6 class="text-center"><a class="page-link" href="/templates/register/register.html">ثبت نام</a></h6>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>


    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.12.1/js/all.js"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.12.1/js/v4-shims.js"></script>

</body>
</html>
