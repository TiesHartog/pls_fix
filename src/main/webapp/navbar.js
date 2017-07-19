$(".dropdown-content").hide();

$(".dropbtn").mouseenter(
    function () {
        $(".dropdown-content").fadeIn("fast");
    });
$(".dropdown-content").mouseleave(function () {
    $(".dropdown-content").fadeOut();

});

$(".search").keypress(function (e) {
    if (e.which == 13) {
        e.preventDefault();
        $("#snackbar").empty().append("This function is still WIP");
        $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
    }
});

$("#logout").click(function () {
    sessionStorage.clear();
    $("#snackbar").empty().append("Succesfully logged out");
    $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
    window.location.replace("index.html")
    }
);
