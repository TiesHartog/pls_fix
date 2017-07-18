$(document).ready(function () {
    var id = window.sessionStorage.getItem("ID");
    $("#snackbar").hide();
    $("#onlybugs").click(function () {
        var request = $.get({
            url: "/pls_fix/restservices/overview/bugreports",
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
            console.log(data);
            data.forEach(function (obj) {
                // Create elements
                var div = document.createElement('div');
                var title = document.createElement('h3');
                var body = document.createElement('p');
                var date = document.createElement('a');
                var username = document.createElement('a');
                var status = document.createElement('a');
                var priority = document.createElement('a');
                // Set Attributes
                div.setAttribute('class', 'report');
                div.setAttribute('id', obj.id);
                title.setAttribute('id', "title");
                body.setAttribute('id', "body");
                date.setAttribute('id', "date");
                username.setAttribute('id', "username");
                status.setAttribute("id", "status");
                priority.setAttribute("id", "priority");

                // Add Data
                title.innerHTML = obj.title;
                body.innerHTML = obj.body;
                date.innerHTML = obj.date;
                username.innerHTML = obj.username;
                status.innerHTML = obj.status;
                priority.innerHTML = obj.priority;
                // append to DOM
                $(".reportlist").append(div);
                div.appendChild(title);
                div.appendChild(body);
                div.appendChild(username);
                div.appendChild(status);
                div.appendChild(priority);
                div.appendChild(date);
            })
        })

    });

});