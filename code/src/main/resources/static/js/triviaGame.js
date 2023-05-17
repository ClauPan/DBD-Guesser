var pos = 0;
var score = 0;
var test, question, choice, choices, chA, chB, chC;

function get(x){
    return document.getElementById(x);
}

var survivor = [
    {
        question: "Choose a color.",
        a: "Green",
        b: "Purple",
        c: "answer"
    },
    {
        question: "What is you greatest strenght?",
        a: "Humility",
        b: "Patience",
        c: "answer"
    },
    {
        question: "What is your favourite accessory?",
        a: "A crown",
        b: "Combat boots",
        c: "answer"
    },
    {
        question: "You're one with the Force. How do you wield this power?",
        a: "Helping those who can't help themselves",
        b: "Teach others its power",
        c: "answer"
    },
    {
        question: "You've been betrayed. How do you react?",
        a:"Walk away",
        b: "Plot revenge",
        c: "answer"
    },
    {
        question: "People hate it when you...",
        a:"Give them orders",
        b: "Get snippy",
        c: "answer"
    }

];

var killer = [
    {
        question: "Choose a color.",
        a: "Green",
        b: "Purple",
        c: "answer"
    },
    {
        question: "What is you greatest strenght?",
        a: "Humility",
        b: "Patience",
        c: "answer"
    },
    {
        question: "What is your favourite accessory?",
        a: "A crown",
        b: "Combat boots",
        c: "answer"
    },
    {
        question: "You're one with the Force. How do you wield this power?",
        a: "Helping those who can't help themselves",
        b: "Teach others its power",
        c: "answer"
    },
    {
        question: "You've been betrayed. How do you react?",
        a:"Walk away",
        b: "Plot revenge",
        c: "answer"
    },
    {
        question: "People hate it when you...",
        a:"Give them orders",
        b: "Get snippy",
        c: "answer"
    }

];

function shuffle(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
}

function choose(){
    if (name === "survivor")
        return survivor
    else
        return killer
}

const randomQuestions = shuffle(choose()).slice(0, 3);

function renderQuestion(){
    test = get("test");

    if(pos >= randomQuestions.length){
        test.innerHTML = "<h3> Congratulations </h3>";
        test.innerHTML = "<h4> You scored </h4>";
        test.innerHTML = "<h3>"+score+" points</h3>";
    } else{
        get("test_status").innerHTML = "Question "+(pos+1)+" of "+randomQuestions.length;

        question = randomQuestions[pos].question;
        chA = randomQuestions[pos].a;
        chB = randomQuestions[pos].b;
        chC = randomQuestions[pos].c;

        test.innerHTML = "<h3>"+question+"</h3>";

        test.innerHTML += "<label class='round'> <input type='radio' name='choices' value=chA><span class='checkmark'></span> "+chA+"</label><br>";
        test.innerHTML += "<label class='round'> <input type='radio' name='choices' value=chA><span class='checkmark'></span> "+chB+"</label><br>";
        test.innerHTML += "<button class='button' onclick='checkAnswer()'>Submit</button>";
    }
}

function checkAnswer(){

    choices = document.getElementsByName("choices");
    for(var i=0; i<choices.length; i++){
        if(choices[i].checked){
            choice = choices[i].value;
        }
    }
    if(choice === chC){
        score++;
    pos++;
    renderQuestion();
}

window.addEventListener("load", renderQuestion);
}