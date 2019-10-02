'use strict';

fetch("http://localhost:8080/project-1/app/reimbursements/resolved/all")
    .then((response) => {
        return response.json();
    })
    .then((thisJson) => {
        let Json = thisJson;
        var i;
        for (i = 0; i < Json.length; i++) {
            var tablerow = document.createElement("tr");

            var tabledataID = document.createElement("td");
            tabledataID.innerText = Json[i].id;

            var tabledataUser = document.createElement("td");
            tabledataUser.innerText = Json[i].requestedByName;

            var tabledataAmount = document.createElement("td");
            tabledataAmount.innerText = Json[i].dollarAmount;

            var tabledataDescription = document.createElement("td");
            tabledataDescription.innerText = Json[i].reimbursementSource;

            var tabledataResolved = document.createElement("td");
            if (Json[i].approved === true) {
                tabledataResolved.innerText = "Approved";
            } else {
                tabledataResolved.innerText = "Denied";
            }

            var tabledataManager = document.createElement("td");
            tabledataManager.innerText = Json[i].resolvedByName;

            var tabledataRec = document.createElement("td");
            var recLink = document.createElement("a");
            recLink.setAttribute("class", "nav-link");
            recLink.setAttribute("target", "_blank");
            recLink.setAttribute("href", Json[i].imageURL);

            recLink.innerText = "Receipt";

            if (Json[i].imageURL === "") {
                recLink.setAttribute("class", "disabled");
                recLink.innerText = " ";
            }

            document.querySelectorAll(".disabled").disabled = true;

            tabledataRec.appendChild(recLink);

            tablerow.appendChild(tabledataID);
            tablerow.appendChild(tabledataUser);
            tablerow.appendChild(tabledataAmount);
            tablerow.appendChild(tabledataDescription);
            tablerow.appendChild(tabledataResolved);
            tablerow.appendChild(tabledataManager);
            tablerow.appendChild(tabledataRec);
            document.querySelector(".tablebody").appendChild(tablerow);
        }
    })
    .catch(console.log);