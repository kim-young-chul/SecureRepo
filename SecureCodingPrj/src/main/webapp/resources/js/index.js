$(document).ready(function() {
    let domain = $("#reurl").val();
    let address = domain + "/servlet/user_login";
    window.location.replace(address);
})
