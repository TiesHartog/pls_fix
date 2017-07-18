$(".report").ready(function () {
    $(".report").click((".report").attr("id"), function () {
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
            $(".report").animate({backgroundColor: "#cccccc"});
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
    $("#newreaction").keypress(function (e) {
        if (e.which == 13) {
            e.preventDefault();
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
                    $("#snackbar").empty().append("Comment saved");
                    $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
                },
                error: function (response) {
                    $("#snackbar").empty().append("Something went wrong");
                    $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
                }
            });

        }
    });
});
