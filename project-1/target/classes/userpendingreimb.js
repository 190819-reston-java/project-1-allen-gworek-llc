'use strict';

fetch("http://localhost:8080/project-1/reimbursements/pending")
    .then((response) => {
        console.log(response);
        return response.json();
    })
    .then((thisJson) => {
        let Json = thisJson;
        var i;
        for (i = 0; i < Json.length; i++) {
            var tablerow = document.createElement("tr");
            var tabledataID = document.createElement("td");
            tabledataID.innerText = Json[i].id;
            var tabledataAmount = document.createElement("td");
            tabledataAmount.innerText = Json[i].dollarAmount;
            var tabledataRec = document.createElement("td");

            var recLink = document.createElement("a");
            recLink.setAttribute("class", "nav-link");
            var imageURLText;
            recLink.setAttribute("href", Json[i].imgURL);
            if (Json[i].imgUrl !== undefined) {
                recLink.innerText = "Receipt";
            } else {
                recLink.setAttribute("disabled", "");
            }
            tabledataRec.appendChild(recLink);

            tablerow.appendChild(tabledataID);
            tablerow.appendChild(tabledataAmount);
            tablerow.appendChild(tabledataRec);

            document.querySelector(".tablebody").appendChild(tablerow);

        }
    })
    .catch(console.log);