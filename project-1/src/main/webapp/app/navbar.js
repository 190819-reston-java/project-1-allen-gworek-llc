'use strict';

fetch("/project-1/app/users/current")
    .then((response) => {
        console.log(response);
        return response.json();
    })
    .then((thisJson) => {
        let Json = thisJson;

        if (Json.managerStatus === true) {
            var navbarone = document.createElement("li");
            navbarone.setAttribute("class", "nav-item");
            var navbaronelink = document.createElement("a");
            navbaronelink.setAttribute("class", "nav-link");
            navbaronelink.setAttribute("href", "manager-pending-reimbursements.html");
            navbaronelink.innerText = "All Pending Reimbursements";
            navbarone.appendChild(navbaronelink);
            document.querySelector(".jsnavbar").appendChild(navbarone);

            var navbartwo = document.createElement("li");
            navbartwo.setAttribute("class", "nav-item");
            var navbartwolink = document.createElement("a");
            navbartwolink.setAttribute("class", "nav-link");
            navbartwolink.setAttribute("href", "manager-all-reimbursements.html");
            navbartwolink.innerText = "All Reimbursements";
            navbartwo.appendChild(navbartwolink);
            document.querySelector(".jsnavbar").appendChild(navbartwo);

            var navbarthree = document.createElement("li");
            navbarthree.setAttribute("class", "nav-item");
            var navbarthreelink = document.createElement("a");
            navbarthreelink.setAttribute("class", "nav-link");
            navbarthreelink.setAttribute("href", "manager-all-users.html");
            navbarthreelink.innerText = "All Users";
            navbarthree.appendChild(navbarthreelink);
            document.querySelector(".jsnavbar").appendChild(navbarthree);
        }
    })
    .catch(console.log);