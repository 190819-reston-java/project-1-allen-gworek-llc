'use strict';

fetch("http://localhost:8080/project-1/reimbursements/pending/all")
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

            var tabledataUser = document.createElement("td");

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

            if (Json[i].approved = true) {
                tabledataResolved.innerText = "Approved";
            } else {
                tabledataResolved.innerText = "Denied";
            }


            var tabledataDecision = document.createElement("td");

            var tabledataDecisionForm = document.createElement("form");
            tabledataDecisionForm.setAttribute("class", "form-inline");
            tabledataDecisionForm.setAttribute("action", "/project-1/reimbursements/resolve");
            tabledataDecisionForm.setAttribute("method", "POST");

            var tabledataDecisionSelect = document.createElement("select");
            tabledataDecisionSelect.setAttribute("name", "updatereimbursement");
            tabledataDecisionSelect.setAttribute("class", "custom-select my-1 mr-sm-2");
            tabledataDecisionSelect.setAttribute("id", "inlineFormCustomSelectPref");

            var tabledataDecisionValueTrue = document.createElement("option");
            tabledataDecisionValueTrue.setAttribute("value", `${Json[i].id}+True`);
            tabledataDecisionValueTrue.innerText = "Approve";

            var tabledataDecisionValueFalse = document.createElement("option");
            tabledataDecisionValueFalse.setAttribute("value", `${Json[i].id}+False`);
            tabledataDecisionValueFalse.innerText = "Deny";

            var tabledataDecisionBtn = document.createElement("button");
            tabledataDecisionBtn.setAttribute("type", "submit");
            tabledataDecisionBtn.setAttribute("class", "btn btn-dark");
            tabledataDecisionBtn.innerText = "Submit";

            tabledataDecisionSelect.appendChild(tabledataDecisionValueTrue);
            tabledataDecisionSelect.appendChild(tabledataDecisionValueFalse);
            tabledataDecisionForm.appendChild(tabledataDecisionSelect);
            tabledataDecisionForm.appendChild(tabledataDecisionBtn);
            tabledataDecision.appendChild(tabledataDecisionForm);

            fetch(`http://localhost:8080/project-1/employees/view/target/${Json[i].requestedBy}`)
                .then((result) => {
                    return result.json();
                })
                .then((resultJson) => {
                    let employeeJson = resultJson;
                    tabledataUser.innerText = employeeJson.fullName;
                })
                .catch(console.log);

            tablerow.appendChild(tabledataID);
            tablerow.appendChild(tabledataUser);
            tablerow.appendChild(tabledataAmount);
            tablerow.appendChild(tabledataDescription);
            tablerow.appendChild(tabledataDecision);
            tablerow.appendChild(tabledataRec);

            document.querySelector(".tablebody").appendChild(tablerow);

        }
    })
    .catch(console.log);