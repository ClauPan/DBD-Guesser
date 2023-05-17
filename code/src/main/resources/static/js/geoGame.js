const guessImage = document.getElementById("guessImage");
const guessWindow = document.getElementById("guessWindow");
const guessButton = document.getElementById("guessButton");
const guessCounter = document.getElementById("guessCounter");
const guessChoice_1 = document.getElementById("guessChoice_1");
const guessChoice_2 = document.getElementById("guessChoice_2");
const guessChoice_3 = document.getElementById("guessChoice_3");

let score = 0;
let level = 0;

draw();

function endGame() {
    guessCounter.innerHTML = "Guess " + level + " out of " + (images.length-1);
    guessWindow.innerHTML = "";

    let h3 = document.createElement("h3");
    h3.innerHTML = "Score: " + score;

    let button = document.createElement("button");
    button.setAttribute("class", "btn-move");
    button.innerHTML = "Finish"
    button.onclick = () => {
        location.href = "/geo/end?score=" + score;
    }

    guessWindow.appendChild(h3);
    guessWindow.appendChild(button);
}

function draw() {
    if (level === images.length-1) {
        endGame()
    }
    else {
        guessImage.src = "/images/playlists/" + path + "/" + images[level];
        guessCounter.innerHTML = "Guess " + (level+1) + " out of " + (images.length-1);
        guessChoice_1.selectedIndex = 0;
        guessChoice_2.selectedIndex = 0;
        guessChoice_3.selectedIndex = 0;
    }
}

guessButton.onclick = () => {
    let choice_1 = guessChoice_1.value;
    let choice_2 = guessChoice_2.value;
    let choice_3 = guessChoice_3.value;

    let answers = images[level].substring(0, images[level].indexOf(".")).split("_");

    if (answers[1] === choice_1) {
        score += 1;
    }
    if (answers[2] === choice_2) {
        score += 1;
    }
    if (answers[3] === choice_3) {
        score += 1;
    }
    level += 1;
    draw();
}

