<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 04.10.2020
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Trains</title>
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" type="text/css"/>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="/css/navbar-style.css" type="text/css">
    <link rel="stylesheet" href="/css/forWelcomePage.css" type="text/css">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>
<table cellspacing='0' width='100%'>
    <thead class="black white-text">
    <tr>
        <th scope="col">id</th>
        <th scope="col">Откуда</th>
        <th scope="col">Куда</th>
        <th scope="col">Дата 1</th>
        <th scope="col">Время 1</th>
        <th scope="col">Дата 2</th>
        <th scope="col">Время 2</th>
        <th scope="col">Тип</th>
        <th scope="col">Стоимость</th>
        <th scope="col">Количество мест</th>
        <th scope="col">Маршрут</th>
        <th scope="col">Продолжительность</th>
        <th scope="col">Удалить</th>
    </tr>
    </thead>
    <tbody id='tableBody'>
    <c:forEach var="train" items="${trains}">
        <tr id="row_" +${train.id}>
            <th>${train.id}</th>
            <th>${train.fromPoint}</th>
            <th>${train.toPoint}</th>
            <th>${train.exitDate}</th>
            <th>${train.exitTime}</th>
            <th>${train.arrivalDate}</th>
            <th>${train.arrivalTime}</th>
            <th>${train.type}</th>
            <th>${train.cost}</th>
            <th>${train.seatingCapacity}</th>
            <th>${train.routePoints}</th>
            <th>${train.duration}</th>
            <th>
                <button onclick="deleteRecord(${train.id})">Удалить</button>
            </th>
        </tr>
    </c:forEach>
    </tbody>
</table>


<div>
    <input type="text" id="fromPoint" name="fromPoint" class="form-control" placeholder="Откуда" required>
    <input type="text" id="toPoint" name="toPoint" class="form-control" placeholder="Куда" required>
    <input type="date" id="exitDate" name="exitDate" class="form-control" placeholder="Первая дата" required>
    <input type="date" id="arrivalDate" name="arrivalDate" class="form-control" placeholder="Вторая дата" required>
    <input type="time" id="exitTime" name="exitTime" class="form-control" placeholder="время 1" required>
    <input type="time" id="arrivalTime" name="arrivalTime" class="form-control" placeholder="время 2" required>
    <input type="text" id="type" name="type" class="form-control" placeholder="тип" required>
    <input type="text" id="cost" name="cost" class="form-control" placeholder="цена" required>
    <input type="text" id="seatingCapacity" name="seatingCapacity" class="form-control" placeholder="Кол-во мест" required>
    <input type="text" id="routePoints" name="routePoints" class="form-control" placeholder="Точки" required>
    <input type="text" id="duration" name="duration" class="form-control" placeholder="Продолжительнсоть" required>


    <button class="btn btn-primary btn-block" id="signUpBtn"
            onclick="save()">Сохранить
    </button>
</div>
<script>
    function save() {
        alert("'here");
        var fromPoint = $('#fromPoint').val();
        var toPoint = $('#toPoint').val();
        var exitDate = $('#exitDate').val();
        var arrivalDate = $('#arrivalDate').val();
        var exitTime = $('#exitTime').val();
        var arrivalTime = $('#arrivalTime').val();
        var type = $('#type').val();
        var cost = $('#cost').val();
        var seatingCapacity = $('#seatingCapacity').val();
        var routePoints = $('#routePoints').val();
        var duration = $('#duration').val();
        var trainDTO = ({
            "fromPoint": fromPoint,
            "toPoint": toPoint,
            "exitDate": exitDate,
            "arrivalDate": arrivalDate,
            "exitTime": exitTime,
            "arrivalTime": arrivalTime,
            "type": type,
            "cost": cost,
            "seatingCapacity": seatingCapacity,
            "routePoints": routePoints,
            "duration": duration,
        });
        $.ajax({
            type: "Post",
            url: "/train/new",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(trainDTO),
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

    function deleteRecord(id) {
        $.ajax({
            type: "delete",
            url: "/train/delete/" + id,
            success: function (res) {
                $('#row' + id).remove();
                location.reload();
            },
            error: function (res) {

            }
        });
    };
</script>
</body>
</html>
