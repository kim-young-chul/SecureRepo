/**
 * 
 */
$(document).ready(function() {
    $("#userLogin").click(function() {
        let crypt = new Crypt({
            aesStandard: 'AES-CBC',
            aesKeySize: 128,
            aesIvSize: 16,
            rsaStandard: 'RSA-OAEP'
        });
        let encrypted = crypt.encrypt($("#base64PublicKey").val(), $("#userpw").val());
        console.log(encrypted);
        let base64encrypted = btoa(encrypted);
        $("#userpw").val(base64encrypted);
        document.loginForm.submit();
    })
})

function json_log(encrypted) {
    let json = JSON.parse(encrypted);
    console.log("v ... " + json.v);
    console.log("iv ... " + json.iv);
    console.log("json.keys ... " + json.keys);
    console.log("keys ... " + Object.keys(json.keys));
    console.log("value ... " + Object.values(json.keys));
    console.log("cipher ... " + json.cipher);
}
