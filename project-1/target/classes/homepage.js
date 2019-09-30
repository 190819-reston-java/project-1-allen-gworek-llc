'use strict';

fetch("http://localhost:8080/project-1/users/current")
.then((response)=>{
    console.log(response);
    return response.json();
})
.then((thisJson)=>{
    let Json = thisJson;

    if(thisJson.managerStatus === true) {
        var imageone = document.createElement("div");
        imageone.setAttribute("class", "col-xl-2 col-md-3 col-sm-5 col-12 offset-xl-0 offset-md-0 offset-sm-0");
        var imageonelink = document.createElement("a");
        imageonelink.setAttribute("href", "manager-pending-reimbursements.html")
        var imageoneimage = document.createElement("img");
        imageoneimage.setAttribute("class", "menuimage");
        imageoneimage.setAttribute("src", "logoallpendreimb.png");
        imageoneimage.setAttribute("alt", "All Pending Reimbursements");
        imageonelink.appendChild(imageoneimage);
        imageone.appendChild(imageonelink);
        document.querySelector(".homepictures").appendChild(imageone);

        var imagetwo = document.createElement("div");
        imagetwo.setAttribute("class", "col-xl-2 col-md-3 col-sm-5 col-12 offset-xl-0 offset-md-0 offset-sm-0");
        var imagetwolink = document.createElement("a");
        imagetwolink.setAttribute("href", "manager-all-reimbursements.html")
        var imagetwoimage = document.createElement("img");
        imagetwoimage.setAttribute("class", "menuimage");
        imagetwoimage.setAttribute("src", "logoallreimb.png");
        imagetwoimage.setAttribute("alt", "All Reimbursements");
        imagetwolink.appendChild(imagetwoimage);
        imagetwo.appendChild(imagetwolink);
        document.querySelector(".homepictures").appendChild(imagetwo);

        var imagethree = document.createElement("div");
        imagethree.setAttribute("class", "col-xl-2 col-md-3 col-sm-5 col-12 offset-xl-0 offset-md-0 offset-sm-0");
        var imagethreelink = document.createElement("a");
        imagethreelink.setAttribute("href", "manager-all-users.html")
        var imagethreeimage = document.createElement("img");
        imagethreeimage.setAttribute("class", "menuimage");
        imagethreeimage.setAttribute("src", "logoallusers.png");
        imagethreeimage.setAttribute("alt", "All Users");
        imagethreelink.appendChild(imagethreeimage);
        imagethree.appendChild(imagethreelink);
        document.querySelector(".homepictures").appendChild(imagethree);


    }
})
.catch(console.log);