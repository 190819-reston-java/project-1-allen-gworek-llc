'use strict';

fetch("http://localhost:8080/project-1/employees/view/all")
.then((response)=>{
    console.log(response);
    return response.json();
})
.then((thisJson)=>{
    let Json = thisJson;
        var i;
        for(i=0; i<Json.length; i++) {
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
            tableFullAddress.innerText = `${Json[i].address}, ${Json[i].addressTwo}, ${Json[i].city}, ${Json[i].state}  ${Json[i].zip}`;
            
            
            tablerow.appendChild(tabledataID);
            tablerow.appendChild(tabledataFullName);
            tablerow.appendChild(tabledataJobTitle);
            tablerow.appendChild(tabledataEmail);
            tablerow.appendChild(tableFullAddress);

            document.querySelector(".tablebody").appendChild(tablerow);

        }
})
.catch(console.log);