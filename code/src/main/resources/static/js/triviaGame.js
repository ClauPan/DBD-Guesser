var pos = 0;
var score = 0;
var test, question, choice, choices, chA, chB, chC, submitBtn;
const submit = get("submit");

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
        c: "Purple"
    },
    {
        question: "What is you greatest strength?",
        a: "Humility",
        b: "Patience",
        c: "Patience"
    },
    {
        question: "What is your favourite accessory?",
        a: "A crown",
        b: "Combat boots",
        c: "Combat boots"
    },
    {
        question: "You're one with the Force. How do you wield this power?",
        a: "Helping those who can't help themselves",
        b: "Teach others its power",
        c: "Teach others its power"
    },
    {
        question: "You've been betrayed. How do you react?",
        a:"Walk away",
        b: "Plot revenge",
        c: "Plot revenge"
    },
    {
        question: "People hate it when you...",
        a:"Give them orders",
        b: "Get snippy",
        c: "Get snippy"
    }

];

var questions=choose();

const randomQuestions = shuffle(questions).slice(0, 3);


renderQuestion();

function get(x){
    return document.getElementById(x);
}
function shuffle(array) {
    for(var i=0; i<array.length-1; i++) {
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
        chC= randomQuestions[pos].c;

        test.innerHTML = "<h3>"+question+"</h3>";

        let label1 = document.createElement("label")
        label1.setAttribute("class", "round");
        label1.innerHTML= chA;
        let input1 =document.createElement("input")
        input1.type="radio";
        input1.name="choices";
        input1.value= randomQuestions[pos].c;

        label1.appendChild(input1);
        test.appendChild(label1);

        let label2 = document.createElement("label")
        label2.setAttribute("class", "round");
        label2.innerHTML=chB;
        let input2 =document.createElement("input")
        input2.type="radio";
        input2.name="choices";
        input2.value= randomQuestions[pos].c;

        label2.appendChild(input2);
        test.appendChild(label2);

        let inputBtn = document.createElement("input");
        inputBtn.setAttribute("id", "submit")
        inputBtn.type="submit";
        inputBtn.value="Submit";
        inputBtn.onclick = () => {
            choices = document.getElementsByName("choices");
            for(var i=0; i<choices.length; i++){
                if(choices[i].checked){
                    choice = choices[i].value;
                }
            }
            if(choice === chC){
                score++;
            }
            pos++;
            renderQuestion();
        }
        test.appendChild(inputBtn);
    }
}