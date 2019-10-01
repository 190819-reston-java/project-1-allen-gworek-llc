'use strict';

fetch("http://localhost:8080/project-1/users/current")
    .then((response) => {
        console.log(response);
        return response.json();
    })
    .then((thisJson) => {
        let Json = thisJson;
        var nameline = document.createElement("h3")
        var namenode = document.createTextNode("\xa0\xa0" + Json.fullName);
        nameline.appendChild(namenode);
        var nametitle = document.querySelector(".name");
        nametitle.appendChild(nameline);

        var jobline = document.createElement("h3")
        var jobnode = document.createTextNode("\xa0\xa0" + Json.jobTitle);
        jobline.appendChild(jobnode);
        var jobtitle = document.querySelector(".jobtitle");
        jobtitle.appendChild(jobline);

        var addressline = document.createElement("h3")
        var addressnode = document.createTextNode(`\xa0\xa0${Json.address}, ${Json.addressTwo}, ${Json.city}, ${Json.state}\xa0\xa0${Json.zip}`);
        addressline.appendChild(addressnode);
        var addresstitle = document.querySelector(".address");
        addresstitle.appendChild(addressline);

        var emailline = document.createElement("h3")
        var emailnode = document.createTextNode("\xa0\xa0" + Json.email);
        emailline.appendChild(emailnode);
        var emailtitle = document.querySelector(".email");
        emailtitle.appendChild(emailline);


        console.log(Json.fullName);
    })
    .catch(console.log);