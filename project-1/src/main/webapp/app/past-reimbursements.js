'use strict';

fetch("/project-1/app/reimbursements/resolved")
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
            var tabledataDescription = document.createElement("td");
            tabledataDescription.innerText = Json[i].reimbursementSource;
            var tabledataRec = document.createElement("td");
            var tabledataResolved = document.createElement("td");

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

            if (Json[i].approved === true) {
                tabledataResolved.innerText = "Approved";
            } else {
                tabledataResolved.innerText = "Denied";
            }

            tablerow.appendChild(tabledataID);
            tablerow.appendChild(tabledataAmount);
            tablerow.appendChild(tabledataDescription);
            tablerow.appendChild(tabledataResolved);
            tablerow.appendChild(tabledataRec);

            document.querySelector(".tablebody").appendChild(tablerow);

        }
    })
    .catch(console.log);