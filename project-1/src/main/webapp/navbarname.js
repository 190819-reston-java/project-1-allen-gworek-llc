'use strict';

fetch("http://localhost:8080/project-1/users/current")
    .then((response) => {
        return response.json();
    })
    .then((thisJson) => {
        let userJson = thisJson;
        var hFiveName = document.createElement("h5");
        hFiveName.setAttribute("style", "color:white");
        hFiveName.innerText = `\xa0\xa0\xa0\xa0Welcome ${userJson.fullName}`;
        document.querySelector(".middlenavbar").appendChild(hFiveName);
    })