<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 08.10.2020
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="components/header.jsp" %>
<h3>Вы выбрали маршрут ${train.fromPoint}->${train.toPoint} на ${train.exitDate} </h3>
<div class="main">
    <div class="container">
        <div class="content add-post">
            <form method="post" class="update">
                <h2>Оформление билета</h2>
                <div class="form-group">
                    <label for="numberPerson">Колво мест</label>
                    <input type="number" id="numberPerson" name="numberPerson" class="form-control" placeholder="Колво мест"
                           required>

                </div>
                <div class="form-group">
                    <label for="name">Имя</label>
                    <input type="text" id="name" name="name" class="form-control" placeholder="Имя" required>
                </div>
                <div class="form-group">
                    <label for="surname">Фамилия</label>
                    <input type="text" id="surname" name="surname" class="form-control" placeholder="Фамилия" required>
                </div>
                <div class="form-group">
                    <label for="passport">Паспорт</label>
                    <input type="text" id="passport" name="passport" class="form-control" placeholder="Паспорт" required>
                </div>

                <div class="buttons">
                    <button class="btn btn-primary btn-block" id="Save"
                            onclick="create();return false;">Оформить
                    </button>
                </div>
            </form>

        </div>

        <div class="sidebar">
            <%@ include file="components/sidebar.jsp" %>
        </div>
    </div>
</div>

</body>
<script>
    function create() {
        var numberPerson = $('#numberPerson').val();
        var name = $('#name').val();
        var surname = $('#surname').val();
        var passport = $('#passport').val();
        var trainId = ${train.id};
        var ticketDTO = ({
            "numberPerson": numberPerson,
            "seats": "1,2",
            "trainId": trainId,
            "name": name,
            "surname": surname,
            "passport": passport
        });
        alert(JSON.stringify(ticketDTO));
        $.ajax({
            type: "Post",
            url: "/ticket/new",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(ticketDTO),
            success: function () {
                window.location.replace("http://localhost:8080/ticket/success");
            },
            error: function (res) {

                alert("error");
            }
        })
    };
</script>
</html>



