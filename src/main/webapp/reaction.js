function LoadReactions(rid) {
    var request = $.get({
        url: "restservices/reactions/get",
        data: {id: rid},
        beforeSend: function (xhr) {
            var token = window.sessionStorage.getItem("sessionToken");
            xhr.setRequestHeader('Authorization', 'Bearer ' + token);
        }
    });
    request.done(function (data) {
        $(".reactielist").empty();
        data.forEach(function (obj) {
            // Create elements
            var div = document.createElement('div');
            var body = document.createElement('p');
            var username = document.createElement('a');

            // Set Attributes
            div.setAttribute('class', 'reactie');
            div.setAttribute('id', obj.id);
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

}
function DeleteReaction(reID){

    var request = $.ajax({
        type:"DELETE" ,
        url: "restservices/reactions/delete" + reID,
        beforeSend: function (xhr) {
            var token = window.sessionStorage.getItem("sessionToken");
            xhr.setRequestHeader('Authorization', 'Bearer ' + token);
        }
    });
    request.done(function (data) {
        $("#snackbar").empty().append("Comment deleted");
        $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
        LoadReactions(window.sessionStorage.getItem("oldrid"));
    });
    request.fail(function (jqXHR, textStatus, errorThrown) {
        $("#snackbar").empty().append(textStatus + " " + errorThrown);
        $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
        console.log(textStatus);
        console.log(errorThrown);

    });
}

$(".reportlist").ready(function () {
    var x = 0;
    $(".reportlist").on("click", ".report", function () {
        var rid = $(this).attr("id");
        if (x === 1){
            var oldrid = window.sessionStorage.getItem("oldrid");
            $('#' + oldrid).animate({backgroundColor: "#cccccc", width: "-=20"}, "fast");
            $('#' + rid).animate({backgroundColor: "white", width: "+=20"}, "fast");

        }
        else {
            $('#' + rid).animate({backgroundColor: "white", width: "+=20"}, "fast");
            x+=1;
      }
        LoadReactions(rid);
        window.sessionStorage.setItem("oldrid", rid);
    });
});

$(".reactielist").ready(function () {
    $(".reactielist").on("dblclick", ".reactie", function () {
        var reID = $(this).attr("id");
        $("#snackbar").empty().append('Are you sure?' , $("<input type='button' id='YesDelRe' value='Yes'/><input type='button' id='NoDelRe' value='No'>" ));
        $("#snackbar").fadeIn(400);
        $("#snackbar").on("click", "#YesDelRe", function (){
            DeleteReaction(reID);
            LoadReactions(window.sessionStorage.getItem("oldrid"));
            $("#snackbar").fadeOut(500);
        });
        $("#snackbar").on("click", "#NoDelRe", function (){
            $("#snackbar").fadeOut(500);
        });
    });
});



$(".reactielist").ready(function () {
    $(".reactielis t").on("keyup", "#newreaction", function (e) {
        if (e.which === 13) {
            e.preventDefault();
            var request = $.ajax({
                url: "restservices/reactions/create" + window.sessionStorage.getItem("ID"),
                type: "put",
                beforeSend: function (xhr) {
                    var token = window.sessionStorage.getItem("sessionToken");
                    xhr.setRequestHeader('Authorization', 'Bearer ' + token);
                },
                data: $(".reactform").serialize(),
                success: function (response) {
                    $("#snackbar").empty().append("Comment saved");
                    $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
                    LoadReactions(window.sessionStorage.getItem("oldrid"));
                },
                error: function (response) {
                    $("#snackbar").empty().append("Something went wrong" + response);
                    console.log(response);
                    $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
                }
            });

        }
    });
});