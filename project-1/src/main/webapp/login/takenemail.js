'use strict';

var previousPage = document.referrer;

window.onload = checkEmail();

function checkEmail() {
    if (previousPage === "http://localhost:8080/project-1/login/signuppage.html") {
        var oopsemail = document.createElement("h5");
        oopsemail.setAttribute("color", "red");
        oopsemail.innerText = "*That email is already on file"
        
        document.querySelector(".emailoops").appendChild(oopsemail);
    }
}