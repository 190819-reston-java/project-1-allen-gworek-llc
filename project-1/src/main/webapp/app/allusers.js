'use strict';

fetch("/project-1/app/employees/view/all")
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
            var tabledataFullName = document.createElement("td");
            tabledataFullName.innerText = Json[i].fullName;
            var tabledataJobTitle = document.createElement("td");
            tabledataJobTitle.innerText = Json[i].jobTitle;
            var tabledataEmail = document.createElement("td");
            tabledataEmail.innerText = Json[i].email;
            var tableFullAddress = document.createElement("td");
            var space;
            if (Json[i].addressTwo == "") {
                space = "";
            } else {
                space = ", ";
            }
            tableFullAddress.innerText = `${Json[i].address}${space}${Json[i].addressTwo}, ${Json[i].city}, ${Json[i].state}  ${Json[i].zip}`;

            var tableButton = document.createElement("button");
            tableButton.setAttribute("onclick", `getReimbursements(${Json[i].id})`);
            tableButton.setAttribute("class", "btn btn-dark");
            tableButton.innerText = "Select";

            tablerow.appendChild(tabledataID);
            tablerow.appendChild(tabledataFullName);
            tablerow.appendChild(tabledataJobTitle);
            tablerow.appendChild(tabledataEmail);
            tablerow.appendChild(tableFullAddress);
            tablerow.appendChild(tableButton);

            document.querySelector(".tablebodychild").appendChild(tablerow);

        }
    })
    .catch(console.log);