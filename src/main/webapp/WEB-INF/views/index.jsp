<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 03.10.2020
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" type="text/css"/>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="/css/navbar-style.css" type="text/css">
    <link rel="stylesheet" href="/css/forWelcomePage.css" type="text/css">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>
<div>
    <form id="loginForm" action="/j_spring_security_check" method="post">
        <div class="form-label-group">
            <input type="email" id="email" name="email" class="form-control" placeholder="Email">
            <label for="email">Email</label>
        </div>
        <div class="form-label-group">
            <input type="password" id="password" name="password" class="form-control" placeholder="Password">
            <label for="password">Пароль</label>
        </div>
        <c:if test="${not empty error}">
            <div id="loginMistake"><p class='alert alert-danger' role='alert'>${error}</p></div>
        </c:if>
        <button class="btn btn-primary btn-block" id="loginBtn"
                type="submit">Войти
        </button>
    </form>
</div>


<div>
    <div class="form-label-group">
        <input type="text" id="nameSignUp" name="name" class="form-control" placeholder="Name" required>
        <label for="nameSignUp">Имя</label>
    </div>
    <div class="form-label-group">
        <input type="email" id="emailSignUp" name="email" class="form-control" placeholder="Email">
        <label for="emailSignUp">Email</label>
    </div>
    <div class="form-label-group">
        <input type="text" id="phoneSignUp" name="phone" class="form-control">
        <label for="phoneSignUp">Номер телефона</label>
    </div>
    <div class="form-label-group">
        <input type="password" id="passwordSignUp" name="password" class="form-control">
        <label for="passwordSignUp">Пароль</label>
    </div>

    <button class="btn btn-primary btn-block" id="signUpBtn"
            onclick="signUp()">Зарегистрироваться
    </button>
</div>
<script>
    function signUp() {
        alert("'here");
        var name = $('#nameSignUp').val();
        var phone = $('#phoneSignUp').val();
        var email = $('#emailSignUp').val();
        var password = $('#passwordSignUp').val();
        var accountDTO = ({
            "name": name,
            "phone": phone,
            "email": email,
            "password": password
        });
        $.ajax({
            type: "Post",
            url: "/account/registration",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(accountDTO),
            success: function (res) {
                $("#signUpModal").modal("hide");
                alert("success");
            },
            error: function (res) {
                // if (res.responseJSON === "Unique fields error") {
                //     $('#uniqueFieldSignUpMistake').show();
                // } else if (res.responseJSON === "Empty fields error") {
                //     $('#emptyFieldSignUpMistake').show();
                // } else {
                //     $("#signUpModal").modal("hide");
                //     $("#errorForm")[0].reset();
                //     $("#errorModal").modal();
                // }
                alert("error");
            }
        })
    };
</script>
</body>
</html>
