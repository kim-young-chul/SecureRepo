$(document).ready(function() {
    let target = $.find("#reurl");
    window.location.replace($(target).val() + "/servlet/user_login");
})
