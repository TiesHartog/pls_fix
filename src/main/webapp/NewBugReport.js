$(document).ready(function () {
    $("#newbug").click(function (){

        console.log("itworks");
        var form = document.createElement('form');
        var title = document.createElement('input');
        var body = document.createElement('textarea');
        var priority = document.createElement('input');
        var submitbtn = document.createElement('button');



        form.setAttribute("class", "bugform");
        title.setAttribute("id", "newtitle");
        title.setAttribute("name", "title");
        title.setAttribute("maxlength", "30");
        title.setAttribute("type", "text");
        body.setAttribute("id", "newbody");
        body.setAttribute("name", "body");
        body.setAttribute("maxlength", "500");
        priority.setAttribute("id", "newpriority");
        priority.setAttribute("type", "number");
        priority.setAttribute("name", "newPriority");
        submitbtn.setAttribute("id", "submitbtn");
        submitbtn.setAttribute("type", "button");
        submitbtn.setAttribute("class", "worknowpls");

        submitbtn.innerHTML = "Submit";

        $(".newStuff").append(form);
        form.appendChild(title);
        form.appendChild(body);
        form.appendChild(priority);
        form.appendChild(submitbtn);


    });
    $(".newStuff").ready(function () {
        $(".newStuff").on('click',".worknowpls", function () {
            console.log("hij reageert");
            var request = $.ajax({
                url: "/pls_fix/restservices/overview/" + window.sessionStorage.getItem("ID"),
                type: "POST",
                beforeSend: function (xhr) {
                    var token = window.sessionStorage.getItem("sessionToken");
                    xhr.setRequestHeader('Authorization', 'Bearer ' + token);
                },
                data: $(".bugform").serialize(),
                success: function (response) {
                    $("#snackbar").empty().append("Comment saved");
                    $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
                },
                error: function (response) {
                    $("#snackbar").empty().append("Something went wrong");
                    $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
                }
            });
            request.done(function () {
                $(".newStuff").empty();
            });

        });
    });



















});