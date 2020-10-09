<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 08.10.2020
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create ticket</title>
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
    <div class="form-label-group">
        <input type="number" id="numberPerson" name="numberPerson" class="form-control" placeholder="Колво мест" required>
        <label for="numberPerson">Колво мест</label>
    </div>

</div>
<div>
    <div class="form-label-group">
        <input type="text" id="name" name="name" class="form-control" placeholder="Имя" required>
        <input type="text" id="surname" name="surname" class="form-control" placeholder="Фамилия" required>
        <input type="text" id="passport" name="passport" class="form-control" placeholder="Паспорт" required>

    </div>

    <button class="btn btn-primary btn-block" id="Save"
            onclick="create()">Оформить
    </button>
</div>
</body>
<script>
    function create() {
        var numberPerson = $('#numberPerson').val();
        var name = $('#name').val();
        var surname = $('#surname').val();
        var passport = $('#passport').val();
        var trainId = 4;
        var ticketDTO = ({
            "numberPerson": numberPerson,
            "trainId": trainId,
            "name": name,
            "surname": surname,
            "passport": passport
        });
        $.ajax({
            type: "Post",
            url: "/ticket/new",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(ticketDTO),
            success: function (res) {
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
</html>
