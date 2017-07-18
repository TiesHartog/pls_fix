$("#snackbar").hide();

$("#login").click(function (event) {

    var data = $("#loginform").serialize();

    $.post("restservices/authentication", data, function (response) {

        window.sessionStorage.setItem("sessionToken", response);
        window.location.href = "Overview.html";

        }).fail(function (jqXHR, textStatus, errorThrown) {
            $(".form").effect("shake", {times: 4}, 1000);
            $(".form, .textinput").css("background-color", "#FF4040");
            console.log(textStatus);
            console.log(errorThrown);
            $(".form").animate({backgroundColor: '#FFFFFF'}, 1000);
            $(".textinput ").animate({backgroundColor: '#D4D4D4'}, 1000);
            $("#snackbar").fadeIn(400).delay(1000).fadeOut(500);
        });
    $.post("restservices/UserResource", data, function (stuff) {
        stuff.forEach(function (obj) {
            window.sessionStorage.setItem("ID", obj.id);
            console.log(obj.id);
        })
    });
});