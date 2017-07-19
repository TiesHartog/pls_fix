$(document).ready(function () {
    $("#newbug").click(function (){

        var form = document.createElement('form');
        var title = document.createElement('input');
        var body = document.createElement('textarea');
        var priority = document.createElement('input');
        var submitbtn = document.createElement('button');



        form.setAttribute("class", "bugform");
        title.setAttribute("id", "newtitle");
        title.setAttribute("name", "title");
        title.setAttribute("maxlength", "30");
        title.setAttribute("placeholder", "title, description in a few words...");
        title.setAttribute("type", "text");
        // title.setAttribute("class", "newstufform");

        body.setAttribute("id", "newbody");
        body.setAttribute("name", "body");
        body.setAttribute("maxlength", "500");
        // body.setAttribute("class", "newstufform");
        body.setAttribute("placeholder", "Full description of the bug...max 500 chars");

        priority.setAttribute("id", "newpriority");
        priority.setAttribute("type", "number");
        priority.setAttribute("name", "newPriority");
        // priority.setAttribute("class", "newstufform");

        submitbtn.setAttribute("id", "submitbtn");
        submitbtn.setAttribute("type", "button");
        submitbtn.setAttribute("class", "submitbug");
        // submitbtn.setAttribute("class", "newstufform");
        submitbtn.innerHTML = "Submit";

        $(".newStuff").append(form);
        form.appendChild(title);
        form.appendChild(body);
        form.appendChild(priority);
        form.appendChild(submitbtn);


    });
    $(".newStuff").ready(function () {
        $(".newStuff").on('click',".submitbug", function () {
            var request = $.ajax({
                url: "restservices/reports/newbug" + window.sessionStorage.getItem("ID"),
                type: "POST",
                beforeSend: function (xhr) {
                    var token = window.sessionStorage.getItem("sessionToken");
                    xhr.setRequestHeader('Authorization', 'Bearer ' + token);
                },
                data: $(".bugform").serialize(),
                success: function (response) {
                    $("#snackbar").empty().append("Report saved");
                    $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
                },
                error: function (response) {
                    $("#snackbar").empty().append("Something went wrong");
                    $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
                }
            });
            request.done(function () {
                $(".newStuff").fadeOut(250);
                $(".newStuff").delay(249).empty();
                $(".bugform").fadeOut(250);
                $(".bugform").delay(249).empty();
            });

        });
    });



















});