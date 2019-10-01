'use strict';

fetch("http://localhost:8080/project-1/reimbursements/resolved/all")
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
            var tabledataResolved = document.createElement("td");

            var recLink = document.createElement("a");
            recLink.setAttribute("class", "nav-link");
            var imageURLText;
            recLink.setAttribute("href", Json[i].imgURL);
            if (Json[i].imgUrl !== undefined) {
                recLink.innerText = "Receipt";
            } else {
                //MAKE THIS WORK
                recLink.disabled = true;
            }
            tabledataRec.appendChild(recLink);

            if (Json[i].approved = true) {
                tabledataResolved.innerText = "Approved";
            } else {
                tabledataResolved.innerText = "Denied";
            }

            tablerow.appendChild(tabledataID);
            tablerow.appendChild(tabledataAmount);
            tablerow.appendChild(tabledataResolved);

            // fetch("http://localhost:8080/project-1/reimbursements/resolved/all")


            tablerow.appendChild(tabledataRec);

            document.querySelector(".tablebody").appendChild(tablerow);

        }
    })
    .catch(console.log);