$(document).ready(function () {
    var id = window.sessionStorage.getItem("ID");
    $("#snackbar").hide();
    $("#onlyfeedback").click(function () {
        var request = $.get({
            url: "/pls_fix/restservices/overview/feedback",
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

                // Set Attributes
                div.setAttribute('class', 'feedback');
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
    $(".feedback").ready(function () {
        $("body").on('click', ".feedback", function () {
            var rid = $(this).attr("id");
            var request = $.get({
                url: "/pls_fix/restservices/report/reactions",
                data: {id: $(this).attr("id")},
                beforeSend: function (xhr) {
                    var token = window.sessionStorage.getItem("sessionToken");
                    xhr.setRequestHeader('Authorization', 'Bearer ' + token);
                }
            });
            request.done(function (data) {
                $(".feedback").animate({backgroundColor: "#cccccc"});
                $('#' + rid).animate({backgroundColor: "white"});
                $(".reactielist").empty();
                console.log(data);
                data.forEach(function (obj) {
                    // Create elements
                    var div = document.createElement('div');
                    var body = document.createElement('p');
                    var username = document.createElement('a');

                    // Set Attributes
                    div.setAttribute('class', 'reactie');
                    body.setAttribute('id', "reactiebody");
                    username.setAttribute('id', "reactieusername");
                    username.setAttribute('font-size', "10px");


                    // Add Data
                    body.innerHTML = obj.body;
                    username.innerHTML = obj.username;

                    // append to DOM
                    $(".reactielist").append(div);
                    div.appendChild(body);
                    div.appendChild(username);


                });
                // var createreaction = document.createElement('div');
                var reactform = document.createElement('form');
                var react = document.createElement('input');
                var reportID = document.createElement('input');

                reactform.setAttribute('class', 'reactform');

                react.setAttribute('maxlength', '200');
                react.setAttribute('type', 'text');
                react.setAttribute('name', 'react');
                react.setAttribute('id', 'newreaction');
                react.setAttribute('placeholder', 'React to this...');
                reportID.setAttribute("hidden", "true");
                reportID.setAttribute("name", 'reportid');
                reportID.setAttribute("id", 'reportid');
                reportID.setAttribute("value", rid);
                $(".reactielist").append(reactform);
                reactform.appendChild(react);
                reactform.appendChild(reportID)


            });
            request.fail(function (jqXHR, textStatus, errorThrown) {
                $("#snackbar").empty().append(textStatus + " " + errorThrown);
                $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
                console.log(textStatus);
                console.log(errorThrown);

            });
        });
        $(".search").on('keyup', function (e) {
            if (e.keyCode == 13) {
                $("#snackbar").empty().append("This function is still WIP")
                $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
            }
        });
        $("#newreaction").ready(function () {
            $("body").on('keyup', $("#newreaction"), function (e) {
                if (e.keyCode == 13) {
                    console.log("hij reageert");
                    var request = $.ajax({
                        url: "/pls_fix/restservices/report/" + window.sessionStorage.getItem("ID"),
                        type: "put",
                        beforeSend: function (xhr) {
                            var token = window.sessionStorage.getItem("sessionToken");
                            xhr.setRequestHeader('Authorization', 'Bearer ' + token);
                        },
                        data: $(".reactform").serialize(),
                        success: function (response) {
                            $("#snackbar").empty().append("Comment saved")
                            $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
                        },
                        error: function (response) {
                            $("#snackbar").empty().append("Something went wrong")
                            $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
                        }
                    });

                }
            });
        });
    })


});
