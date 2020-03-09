let loginUsername = '';


function showAlert(type, message) {
    $("#response").animate({}, 300);
    $('<div class="alert alert-' + type + '">' +
        '<button type="button" class="close" data-dismiss="alert">' + '&times;</button>' +
        message + '</div>'
    ).hide().appendTo('#response').fadeIn(1000);

    $(".alert").delay(3000).fadeOut(
        "normal",
        function () {
            $(this).remove();
        })
}


function showPassword() {
    const x = document.getElementById("password");
    if (x.type === "password") {
        x.type = "text";
        document.getElementById("passIcon").setAttribute("class", "fa fa-eye");
    } else {
        x.type = "password";
        document.getElementById("passIcon").setAttribute("class", "fa fa-eye-slash");
    }
}


$(document).ready(function () {
    $("#loginForm").submit(function (event) {
        event.preventDefault();
        event.stopPropagation();

        let username = $("#username").val();
        let password = $("#password").val();

        if (username !== '' && password !== '') {
            loginToAccount();
        }
    });
});


function loginToAccount () {
    const username = $("#username").val();
    const password = $("#password").val();

    const LoginToAccountByUserCommand = {"username": username, "password": password};

    $.ajax({
        url: "http://localhost:8080/login",
        type: "POST",
        data: JSON.stringify(LoginToAccountByUserCommand),
        headers: { "Authorization": "Basic " + btoa(username + ":" + password) },
        contentType: "application/json; charset=utf-8",

        success: function (data) {
            loginUsername = data.username;
            let guest = false;

            for (let i = 0; i < data.roles.length; i++) {
                if (data.roles[i].roleType === "ROLE_GUEST") {
                    guest = true;
                    break;
                }
            }

            if (guest) {
                location.replace("/components/panels/guest/index/guest-panel.html?" + btoa( username + ":" + password))
            } else {
                for (let i = 0; i < data.roles.length; i++) {
                    if (data.roles[i].roleType === "ROLE_STUDENT") {
                        location.replace("https://www.student.com")
                    }
                    if (data.roles[i].roleType === "ROLE_TEACHER") {
                        location.replace("https://www.teacher.com")
                    }
                    if (data.roles[i].roleType === "ROLE_MANAGER") {
                        location.replace("/components/panels/manager/index/manager-panel.html?" + btoa( username + ":" + password))
                    }
                }
            }
        },

        error: function (errorMessage) {
            showAlert("danger", 'اطلاعات وارد شده صحیح نمی باشد');
        }
    });
}

