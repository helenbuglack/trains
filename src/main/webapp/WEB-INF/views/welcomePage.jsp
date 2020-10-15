<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 03.10.2020
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="components/header.jsp" %>
<div class="main">
    <div class="container" style="    justify-content: center;" >
        <div class="content add-post">
            <h2>Поиск поездов</h2>
            <form method="post" class="search">
                <div class="form-group">
                    <label for="fromPoint">Откуда</label>
                    <input type="text" id="fromPoint" name="fromPoint" value="Минск" />

                </div>
                <div class="form-group">
                    <label for="toPoint">Куда</label>
                    <input type="text" id="toPoint" name="toPoint" value="Москва" />
                </div>
                <div class="form-group">
                    <label for="exitDate">Дата отправления</label>
                    <input type="date" id="exitDate" name="exitDate" value="" />
                </div>
                <div  class="buttons" style="align-items: flex-end; margin-bottom: 16px ">
                    <button class="update-button"  onclick="search()" >Поиск</button>
                </div>
            </form>
            <hr> <br>

            <table  class="table">
                <thead>
                <tr>
                    <th>id</th>
                    <th scope="col">Откуда</th>
                    <th scope="col">Куда</th>
                    <th scope="col">Дата отправления</th>
                    <th scope="col">Дата прибытия</th>
                    <th scope="col">Тип</th>
                    <th scope="col">Стоимость</th>
                    <th scope="col">Количество мест</th>
                    <th scope="col">Маршрут</th>
                    <th scope="col">Длительность</th>
<sec:authorize access="hasRole('ROLE_USER')">
                    <th scope="col">Забронировать</th>
</sec:authorize>
                </tr>
                </thead>
                <tbody id="tBody">
                <c:forEach var="train" items="${trains}">
                    <tr id="row_" +${train.id}>
                        <th>${train.id}</th>
                        <th>${train.fromPoint}</th>
                        <th>${train.toPoint}</th>
                        <th>${train.exitDate} ${train.exitTime}</th>
                        <th>${train.arrivalDate} ${train.arrivalTime}</th>
                        <th>${train.type}</th>
                        <th>${train.cost}</th>
                        <th>${train.seatingCapacity}</th>
                        <th>${train.routePoints}</th>
                        <th>${train.duration}</th>
                        <sec:authorize access="hasRole('ROLE_USER')">
                       <th><button class="btn btn-primary btn-block" id="signUpBtn"
                                onclick="booking()">Забронировать
                        </button></th>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>


            </table>
        </div>

<div class="sidebar">
        <%@ include file="components/sidebar.jsp" %>
    </div>
    </div>
</div>
<script>
    function search() {
        var fromPoint = $('#fromPoint').val();
        var toPoint = $('#toPoint').val();
        var exitDate = $('#exitDate').val();
        var trainDTO = ({
            "fromPoint": fromPoint,
            "toPoint": toPoint,
            "exitDate": exitDate,
        });

        $.ajax({
            type: "Post",
            url: "/train/search",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(trainDTO),
            success: function (res) {
                location.reload();
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

    function booking(id) {
        $.ajax({
            type: "post",
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
