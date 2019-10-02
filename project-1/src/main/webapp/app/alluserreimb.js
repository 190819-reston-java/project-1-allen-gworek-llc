'use strict';

function getReimbursements(id) {

    fetch("http://localhost:8080/project-1/app/reimbursements/view/target/" + id)
        .then((response) => {
            return response.json();
        })
        .then((responseJson) => {
            var userJson = responseJson;

            var resetpagechild = document.querySelector(".resetpagechild");
            resetpagechild.parentNode.removeChild(resetpagechild);

            var tableboxchild = document.querySelector(".tableboxchild");
            tableboxchild.parentNode.removeChild(tableboxchild);

            var tablebodychild = document.querySelector(".tablebodychild");
            tablebodychild.parentNode.removeChild(tablebodychild);


            var newtitle = document.createElement("h1");
            newtitle.setAttribute("class", "h2");
            
            var template = [ ];
            if(userJson === template){
                newtitle.innerHTML = "No Reimbursements On File";
            } else {
                newtitle.innerHTML = `${userJson[0].requestedByName}'s Reimbursements`;
            }

            document.querySelector(".resetpage").appendChild(newtitle);


            var resetButton = document.createElement("a");
            resetButton.setAttribute("class", "btn btn-dark");
            resetButton.setAttribute("href", "manager-all-users.html");
            resetButton.innerText = "Reset Page";
            document.querySelector(".resetpage").appendChild(resetButton);

            var tablehead = document.createElement("thead");
            var tableheadRow = document.createElement("tr");
            var tableHId = document.createElement("th");
            tableHId.innerText = "ID";
            var tableHDollar = document.createElement("th");
            tableHDollar.innerText = "Amount";
            var tableHDesc = document.createElement("th");
            tableHDesc.innerText = "Description";
            var tableHApproval = document.createElement("th");
            tableHApproval.innerText = "Approval Status";
            var tableHApprovedBy = document.createElement("th");
            tableHApprovedBy.innerText = "Reviewer";
            var tableHReceipt = document.createElement("th");
            tableHReceipt.innerText = "Receipt Image";
            tableheadRow.appendChild(tableHId);
            tableheadRow.appendChild(tableHDollar);
            tableheadRow.appendChild(tableHDesc);
            tableheadRow.appendChild(tableHApproval);
            tableheadRow.appendChild(tableHApprovedBy);
            tableheadRow.appendChild(tableHReceipt);
            tablehead.appendChild(tableheadRow);
            document.querySelector(".tablebox").appendChild(tablehead);

            var tablebody = document.createElement("tbody");
            tablebody.setAttribute("class", "usertablebody");
            document.querySelector(".tablebox").appendChild(tablebody);

            var i;
            for (i = 0; i < userJson.length; i++) {

                var tablerow = document.createElement("tr");


                var tabledataID = document.createElement("td");
                tabledataID.innerText = userJson[i].id;

                var tabledataAmount = document.createElement("td");
                tabledataAmount.innerText = userJson[i].dollarAmount;

                var tabledataDescription = document.createElement("td");
                tabledataDescription.innerText = userJson[i].reimbursementSource;

                var tabledataApproval = document.createElement("td");

                if (userJson[i].approvedOrDenied === false) {
                    tabledataApproval.innerText = "Pending Review";
                } else {
                    if (userJson[i].approved === true) {
                        tabledataApproval.innerText = "Approved";
                    } else {
                        tabledataApproval.innerText = "Denied";
                    }
                }

                var tabledataManager = document.createElement("td");
                if (userJson[i].approvedOrDenied === false) {
                    tabledataManager.innerText = "Pending Review";
                } else {
                    tabledataManager.innerText = userJson[i].resolvedByName;
                }
                var tabledataRec = document.createElement("td");
                var recLink = document.createElement("a");

                recLink.setAttribute("class", "nav-link");
                recLink.setAttribute("target", "_blank");
                recLink.setAttribute("href", userJson[i].imageURL);
                recLink.innerText = "Receipt";

                if (userJson[i].imageURL === "") {
                    recLink.setAttribute("class", "disabled");
                    recLink.innerText = " ";
                }

                document.querySelectorAll(".disabled").disabled = true;
                tabledataRec.appendChild(recLink);

                tablerow.appendChild(tabledataID);
                tablerow.appendChild(tabledataAmount);
                tablerow.appendChild(tabledataDescription);
                tablerow.appendChild(tabledataApproval);
                tablerow.appendChild(tabledataManager);
                tablerow.appendChild(tabledataRec);

                document.querySelector(".usertablebody").appendChild(tablerow);
            }
        })
        .catch(console.log);
};