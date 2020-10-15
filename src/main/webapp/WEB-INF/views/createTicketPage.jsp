<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 08.10.2020
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="components/header.jsp" %>
<div class="main">
    <div class="container">
        <div class="content add-post">
            <form method="post" class="update">
                <h2>Оформление билета</h2>
                <div class="form-group">
                    <label for="numberPerson">Колво мест</label>
                    <input type="number" id="numberPerson" name="numberPerson" class="form-control" placeholder="Колво мест" required>

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
                            onclick="create()">Оформить
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



