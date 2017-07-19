$(document).ready(function () {
    var id = window.sessionStorage.getItem("ID");
    $("#snackbar").hide();
    $("#onlyfeedback").click(function () {
        var request = $.get({
            url: "restservices/reports/feedback",
            beforeSend: function (xhr) {
                var token = window.sessionStorage.getItem("sessionToken");
                xhr.setRequestHeader('Authorization', 'Bearer ' + token);
            }
        });
        request.fail(function (jqXHR, textStatus, errorThrown) {
            $("#snackbar").empty().append(textStatus + " " + errorThrown);
            $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
            console.log(textStatus);
            console.log(errorThrown);
        });
        request.done(function (data) {
            $(".reactielist").empty();
            $(".reportlist").empty();
            data.forEach(function (obj) {
                // Create elements
                var div = document.createElement('div');
                var title = document.createElement('h3');
                var body = document.createElement('p');
                var date = document.createElement('a');
                var username = document.createElement('a');
                var status = document.createElement('a');

                // Set Attributes
                div.setAttribute('class', 'report');
                div.setAttribute('id', obj.id);
                title.setAttribute('id', "title");
                body.setAttribute('id', "body");
                date.setAttribute('id', "date");
                username.setAttribute('id', "username");
                status.setAttribute("id", "status");

                // Add Data
                title.innerHTML = obj.title;
                body.innerHTML = obj.body;
                date.innerHTML = obj.date;
                username.innerHTML = obj.username;
                status.innerHTML = obj.status;
                // append to DOM
                $(".reportlist").append(div);
                div.appendChild(title);
                div.appendChild(body);
                div.appendChild(username);
                div.appendChild(status);
                div.appendChild(date);
            })
        })

    });

});
