'use strict';

fetch("http://localhost:8080/project-1/reimbursements/resolved/all")
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

            var tabledataAmount = document.createElement("td");
            tabledataAmount.innerText = Json[i].dollarAmount;

            var tabledataDescription = document.createElement("td");
            tabledataDescription.innerText = Json[i].reimbursementSource;

            var tabledataResolved = document.createElement("td");
            if (Json[i].approved = true) {
                tabledataResolved.innerText = "Approved";
            } else {
                tabledataResolved.innerText = "Denied";
            }

            var tabledataManager = document.createElement("td");

            var tabledataRec = document.createElement("td");
            var recLink = document.createElement("a");
            recLink.setAttribute("class", "nav-link");
            var imageURLText;
            recLink.setAttribute("href", Json[i].imgURL);
            if (Json[i].imgUrl !== undefined) {
                recLink.innerText = "Receipt";
            } else {
                recLink.setAttribute("class", "disabled");
            }
            document.querySelectorAll(".disabled").disabled = true;

            tabledataRec.appendChild(recLink);

            tablerow.appendChild(tabledataID);

            fetch(`http://localhost:8080/project-1/employees/view/target/${Json[i].requestedBy}`)
                .then((resulttwo) => {
                    return resulttwo.json();
                })
                .then((resulttwoJson) => {
                    let employeeJson = resulttwoJson;
                    tabledataUser.innerText = employeeJson.fullName;
                })
                .catch(console.log);
            tablerow.appendChild(tabledataUser);
            tablerow.appendChild(tabledataAmount);
            tablerow.appendChild(tabledataDescription);
            tablerow.appendChild(tabledataResolved);

            fetch(`http://localhost:8080/project-1/employees/view/target/${Json[i].resolvedBy}`)
                .then((result) => {
                    return result.json();
                })
                .then((resultJson) => {
                    let managerJson = resultJson;
                    tabledataManager.innerText = managerJson.fullName;
                })
                .catch(console.log);

            tablerow.appendChild(tabledataManager);
            tablerow.appendChild(tabledataRec);
            document.querySelector(".tablebody").appendChild(tablerow);
        }
    })
    .catch(console.log);