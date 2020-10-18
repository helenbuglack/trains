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
    <div class="container" style="justify-content: center;">
        <div class="content add-post">
            <h2>Просмотр забронированных билетов</h2>
            <table class="table">
                <thead>
                <tr>
                    <th>id</th>
                    <th scope="col">Откуда</th>
                    <th scope="col">Куда</th>
                    <th scope="col">Дата отправления</th>
                    <th scope="col">Места</th>
                    <th scope="col">ФИ</th>
                    <th scope="col">Паспорт</th>
                </tr>
                </thead>
                <tbody id="tBody">
                <c:forEach var="ticket" items="${tickets}">
                    <tr id="row_" +${ticket.id}>
                        <th>${ticket.fromPoint}</th>
                        <th>${ticket.toPoint}</th>
                        <th>${ticket.exitTime}</th>
                        <th>${ticket.seats}</th>
                        <th>${ticket.name}+${ticket.surname}</th>
                        <th>${ticket.passport}</th>
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <th>
                                <button class="btn btn-primary btn-block" id="signUpBtn"
                                        onclick="cancel()">Отменить
                                </button>
                            </th>
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
