<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 15.10.2020
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="components/header.jsp" %>
<div class="main">
    <div class="container" style="justify-content: center;" >
        <div class="content add-post">
            <h2>Просмотр забронированных билетов</h2>
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
                    <th scope="col">ФИ</th>
                    <th scope="col">Паспорт</th>
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <th scope="col">Отменить</th>
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
                                        onclick="cancel()">Отменить
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
    function cancel(id) {
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
