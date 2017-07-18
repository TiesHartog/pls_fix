$(".dropdown-content").hide();

$(".dropbtn").mouseenter(
    function() {
        $(".dropdown-content").fadeIn();
    });
$(".dropdown-content").mouseleave(function(){
    $(".dropdown-content").fadeOut();
});