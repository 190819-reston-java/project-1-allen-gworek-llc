'use strict';

window.onload = checkEmail();

function checkEmail() {
    if (document.referrer === "signuppage.html") {
        var oopsemail = document.createElement("h5");
        oopsemail.setAttribute("color", "red");
        oopsemail.innerText = "That email is already on file"
        
        document.querySelector(".emailoops").appendChild(oopsemail);
    }
}