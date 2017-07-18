$(".dropdown-content").hide();

$(".dropbtn").mouseenter(
    function () {
        $(".dropdown-content").fadeIn();
    });
$(".dropdown-content").mouseleave(function () {
    $(".dropdown-content").fadeOut();

});

$(".search").keypress( function (e) {
    if (e.which == 13) {
        e.preventDefault();
        $("#snackbar").empty().append("This function is still WIP");
        $("#snackbar").fadeIn(400).delay(1500).fadeOut(500);
    }
});