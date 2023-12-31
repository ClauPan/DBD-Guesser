const imgDiv = document.getElementById("images");

const prevPageButton = document.getElementById("prevPage");
const nextPageButton = document.getElementById("nextPage");

let displayPage = 0;
let displayLimit = 3;
let displayLength = getMax();
let basePath = "/images/playlists/temp/" + id + "_" + username + "/";

drawImages();

function drawImages() {
    imgDiv.innerHTML = "";
    for(let i = displayPage; i < displayLength; i++) {
        let mainDiv = document.createElement("div");
        mainDiv.setAttribute("class", "img-wrap");

        let img = document.createElement("img");
        img.src = basePath + images[i];
        img.style.width = "200px";
        img.style.height = "200px";

        let div = document.createElement("div");
        div.setAttribute("class", "btn-edit-wrap")

        let formEdit = document.createElement("form");
        formEdit.method = "get";
        formEdit.action = "/playlist/create/edit";

        let formEdit_input = document.createElement("input");
        formEdit_input.setAttribute("class", "btn");
        formEdit_input.type = "hidden";
        formEdit_input.name = "index";
        formEdit_input.value = images[i].substring(0, images[i].indexOf('_'));

        let formEdit_button = document.createElement("button");
        formEdit_button.setAttribute("class", "btn-edit-delete");
        formEdit_button.type = "submit";
        formEdit_button.innerHTML = "Edit";

        formEdit.appendChild(formEdit_input);
        formEdit.appendChild(formEdit_button);

        let formDelete = document.createElement("form");
        formDelete.method = "post";
        formDelete.action = "/playlist/create/delete";

        let formDelete_input = document.createElement("input");
        formDelete_input.setAttribute("class", "btn");
        formDelete_input.type = "hidden";
        formDelete_input.name = "index";
        formDelete_input.value = images[i].substring(0, images[i].indexOf('_'));


        let formDelete_button = document.createElement("button");
        formDelete_button.setAttribute("class", "btn-edit-delete");
        formDelete_button.type = "submit";
        formDelete_button.innerHTML = "Delete";

        formDelete.appendChild(formDelete_input);
        formDelete.appendChild(formDelete_button);

        div.appendChild(formEdit);
        div.appendChild(formDelete);

        mainDiv.appendChild(img);
        mainDiv.appendChild(div);

        imgDiv.appendChild(mainDiv);
    }
}

function getMax() {
    return Math.min(displayPage + displayLimit, images.length);
}

prevPageButton.onclick = () => {
    if (displayPage - displayLimit >= 0) {
        displayPage -= displayLimit;
    }
    displayLength = getMax();
    drawImages();
}

nextPageButton.onclick = () => {
    if (images.length > displayPage + displayLimit) {
        displayPage += displayLimit;
    }
    displayLength = getMax();
    drawImages();
}

