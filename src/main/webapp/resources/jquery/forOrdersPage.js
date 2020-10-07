function returnStatus(status) {
    if (status === "IN_PROCESS") {
        return "Обрабатывается";
    } else if (status === "APPROVED") {
        return "Одобрено"
    } else if (status === "DENIED") {
        return "Отклонено"
    }
}

function loadOrders() {
    $.get("/reservation/load", function (data) {
        if (data.length > 0) {
            $('#noReservationMessage').empty();
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr id='row_" + data[i].id + "'><td>" + data[i].username + "</td><td>" + data[i].phone + "</td><td>"
                    + data[i].numberPerson + "</td><td><a href='/tour/"
                    + data[i].tourId + "' class='tour-href'>" + data[i].nameTour + "</a></td><td>" + returnStatus(data[i].status) + "</td>";

            }
            $('#tableBody').append(html);
        } else {
            $('#table').detach();


        }
    });
}

function
loadReservations() {
    $.get("/reservation/company/load", function (data) {
        if (data.length > 0) {
            $('#noReservationMessage').empty();
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr id='row_" + data[i].id + "'><td>" + data[i].username + "</td><td>" + data[i].phone + "</td><td>"
                    + data[i].numberPerson + "</td><td><a href='/tour/"
                    + data[i].tourId + "' class='tour-href'>" + data[i].nameTour + "</a></td><td>" + returnStatus(data[i].status) + "</td>";
                html += "<td>" + "<button class='btn btn-primary' onclick='approve(" + data[i].id +
                    ")'>Approve</button>" + "</td>";
                html += "<td>" + "<button class='btn btn-danger' onclick='deny(" + data[i].id + ")'>Deny</button>" + "</td></tr>";

            }
            $('#tableBody').append(html);
        } else {
            $('#table').detach();


        }
    });
}

function approve(id) {

    var status = "APPROVED";
    $.ajax({
        type: "Post",
        contentType: "application/json;charset=utf-8",
        url: "/reservation/update/"+id,
        data: JSON.stringify(status),
        success: function (res) {
            window.location.replace("http://localhost:8080/showReservations");
        },
        error: function (res) {


        }
    });
}

function deny(id) {

    var status = "DENIED";
    $.ajax({
        type: "Post",
        contentType: "application/json;charset=utf-8",
        url: "/reservation/update/"+id,
        data: JSON.stringify(status),
        success: function (res) {
            window.location.replace("http://localhost:8080/showReservations");
        },
        error: function (res) {


        }
    });
}