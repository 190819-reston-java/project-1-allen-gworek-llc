'use strict';

var previousPage = document.referrer;

window.onload = checkEmail();

function checkEmail() {
    if (previousPage === "http://ec2-52-90-209-187.compute-1.amazonaws.com:8080/project-1/login/signuppage.html") {
        var oopsemail = document.createElement("h6");
        oopsemail.setAttribute("style", "color:red");
        oopsemail.innerText = "*That email is already on file"
        
        document.querySelector(".emailoops").appendChild(oopsemail);
    }
}