var pos = 0;
var score = 0;
var test, question, choice, choices, chA, chB, chC, submitBtn;
const submit = get("submit");

var survivor = [
    {
        question: "What survivor came with the Chains of Hate chapter?",
        a:"Dwight Fairfield",
        b: "Elodie Rakoto",
        c: "Zarina Kassir"
    },
    {
        question: "Which survivor has the Windows Of Opportunity perk?",
        a:"Nea Karlsson",
        b: "Jill Valentine",
        c: "Kate Denson"
    },
    {
        question: "Which two survivors are brother and sister?",
        a:"Yui Kimura and Yun-Jin Lee",
        b: "Felix Richter and Jeff Johansen",
        c: "Renato Lyra and Thalita Lyra"
    },
    {
        question: "In his backstory, Vittorie is said to have been brought by the entity from which period of time?",
        a:"Present day",
        b: "The 1900's.",
        c: "The 1300's"
    },
    {
        question: "Which killer did Jonah Vasquez come with?",
        a:"The Demogorgon",
        b: "The Knight",
        c: "The Artist"
    },
    {
        question: "Before being taken by the entity, what did Feng Min used to do?",
        a:"Teaching",
        b: "Doctor",
        c: "E-sports competitor"
    },
    {
        question: "Which one is a licensed survivor?",
        a:"Claudette Morell",
        b: "Haddie Kaur",
        c: "Ada Wong"
    },
    {
        question: "What can the survivors bring into the match that the killer cannot?",
        a:"Add-ons",
        b: "Offering",
        c: "Items"
    },
    {
        question: "After Netflix pulled the license for the Stranger Things chapter, the character's perks were renamed. What did Steve's Babysitter perk get changed to?",
        a:"They never changed the name",
        b: "Protector",
        c: "Guardian"
    },
    {
        question: "Sprint Burst is an exhaustion perk. How do you activate it?",
        a:"Vault fast over a window or a pallet",
        b: "Fall from great heights",
        c: "Run"
    },
    {
        question: "With the chapter that brought Mikaela Reed, the game introduced boon perks. What do they do?",
        a:"You cleanse totems faster",
        b: "You cleanse hex totems faster",
        c: "You can bless totems"
    },
    {
        question: "Any Means Necessary help you pick up dropped pallets, rendering them usable again. Whose perk is it?",
        a:"Nea Karlsson",
        b: "Zarina Kassir",
        c: "Yui Kimura"
    },
    {
        question: "When a survivor is downed, they are not able to crawl while recovering. What perk is able to let the survivor crawl while recovering?",
        a:"Flip-Flop",
        b: "Power Struggle",
        c: "Tenacity"
    },
    {
        question: "Jane Romero has a perk which lets you stun the killer after hiding in a locker for a few seconds, given the killer is in the right range. How is it called?",
        a:"Deception",
        b: "Counterforce",
        c: "Head on"
    },
    {
        question: "Zarina Kassir has a perk that allows you to heal another survivor instantly when healthy, at the cost of your own health state. How is it called?",
        a:"Empathy",
        b: "Reassurance",
        c: "For the people"
    },
    {
        question: "What exhaustion perk does Feng Min have?",
        a:"Dead Hard",
        b: "Overcome",
        c: "Lithe"
    },
    {
        question: "There is a perk which allows you to attack the killer and make them drop you while being carried. What is it called?",
        a:"Borrowed Time",
        b: "Up the Ante",
        c: "Decisive Strike"
    },
    {
        question: "What perk allows you to unhook yourself, provided you made a safe hook prior being hooked yourself?",
        a:"Buckle Up",
        b: "Deception",
        c: "Deliverance"
    }
];
var killer = [
    {
        question: "What is the Trickster's real name?",
        a: "Jimin",
        b: "Ji-hok",
        c: "Ji-woon Hak"
    },
    {
        question: "When she was first introduced, what chapter was The Nurse released with?",
        a: "Of Flesh and Mud",
        b: "Curtain Call",
        c: "The Last Breath"
    },
    {
        question: "With the introduction of the Resident Evil chapter, two survivors were added to the game together with the Nemesis. What are their names?",
        a: "Steve Harrington and Nancy Wheeler",
        b: "Rebecca Chambers and Ada Wong",
        c: "Leon S. Kennedy and Jill Valentine"
    },
    {
        question: "There are two killers who are related, but from different packs. Who are they?",
        a: "The Nemesis and The Masterming(Wesker)",
        b: "The Trapper and The Wraith",
        c: "The Spirit and The Oni"
    },
    {
        question: "Which killer is able to kill a survivor with their power without the need of a memento mori or add-on?",
        a:"The Shape(Michael Myers)",
        b: "The Huntress",
        c: "The Onryo(Sadako)"
    },
    {
        question: "What is The Legion's power called?",
        a:"Vile Purge",
        b: "Birds of Torment",
        c: "Feral Frenzy"
    },
    {
        question: "Which killer is one of the three original ones that shipped with the game on release?",
        a:"The Nurse",
        b: "The Hag",
        c: "The Hillbilly"
    },
    {
        question: "The Pig is a licensed killer from what movie franchise?",
        a:"Scream",
        b: "Texas Chainsaw Massacre",
        c: "Saw"
    },
    {
        question: "Totems were first introduced with which killer?",
        a:"The Cenobite(Pinhead)",
        b: "The Artist",
        c: "The Hag"
    },
    {
        question: "Certain killers can use lockers to reload their secondary weapons. Which ones can?",
        a:"The Legion",
        b: "The Dredge",
        c: "The Trickster"
    },
    {
        question: "The Twins were introduced in the Binding of Kin chapter. What does their power do?",
        a:"The Twins can vomit on the survivors to infect them",
        b: "The Twins can teleport to traps they have set on the ground",
        c: "The Twins can release Victor to pounce on survivors and injure or down them."
    },
    {
        question: "What killer has a similar power to The Shape(Michael Myers)?",
        a:"The Onryo",
        b: "The Skull Merchant",
        c: "The Ghost Face"
    },
    {
        question: "In The Legion's backstory, the Entity took the full group of four people and thus, created The Legion. Who is one of these people?",
        a:"David",
        b: "Mel",
        c: "Susie"
    },
    {
        question: "What killers are able to close the hatch?",
        a:"The Dredge",
        b: "The Gunslinger",
        c: "All killers"
    },
    {
        question: "Which killers is able to phase?",
        a:"The Oni",
        b: "The Hag",
        c: "The Spirit"
    },
    {
        question: "Which memento mori offering allows the killers to kill any survivors which has reached the 2nd stage on hook?",
        a:"Cypress Mmemento Mori",
        b: "Ivory Mmemento Mori",
        c: "Ebony Mmemento Mori"
    },
    {
        question: "What does the perk Starstruck do?",
        a:"Survivors see stars",
        b: "After the gates are opened, the obsession gets afflicted by the exposed status",
        c: "Any survivors that come in the range of a carried survivor get afflicted by the exposed status for 30 seconds"
    },
    {
        question: "Whose perk is Iron Grasp?",
        a:"The Trapper",
        b: "The Gunslinger",
        c: "No one's"
    },
    {
        question: "What perk shows the aura of survivors in a certain range after damaging a generator?",
        a:"Hex:Crowd Control",
        b: "Iron Maiden",
        c: "Nowhere to hide"
    },
    {
        question: "Before its rework, what did Hex: Ruin used to affect?",
        a:"It makes the generators regress when not being repaired",
        b: "It makes the survivors repair slower",
        c: "It made every good skill check regress the generator"
    },
    {
        question: "What does Rancor do?",
        a:"It allows you to kill any survivor after the gates are powered",
        b: "It allows you to kill the obsession after you hooked them at least once",
        c: "It allows you to kill the obsession after the gates are powered, no matter the number of hooks they have"
    },
    {
        question: "Whose perk is Dead Man's Switch?",
        a:"The Cannibal(Leatherface)",
        b: "The Clown",
        c: "The Gunslinger"
    },
    {
        question: "One of The Clown's perks is Coulrophobia. What does it do?",
        a:"It makes the survivors scream while in The Clown's terror radius",
        b: "It makes the survivors unable to do any actions while in The Clown's terror radius",
        c: "It makes the healing go slower and skillchecks faster while in the killer's terror radius"
    },
    {
        question: "Which of these perks is The Doctor's perk?",
        a:"Oppression",
        b: "Surveillance",
        c: "Overcharge"
    }
];

var questions=choose();

const randomQuestions = shuffle(questions).slice(0, 10);


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
    test = get("quiz-container");
    test.innerHTML="";
    if(pos >= randomQuestions.length){
        get("quiz_status").innerHTML="<h3> Congratulations! </h3>";
        test.innerHTML = "<h3>You scored "+score+" questions right!</h3>";
        let button = document.createElement("button");
        button.setAttribute("class", "btn-guess");
        button.innerHTML = "Finish"
        button.onclick = () => {
            location.href = "/";
        }
        test.appendChild(button)
    } else{
        get("quiz_status").innerHTML = "Question "+(pos+1)+" of "+randomQuestions.length;

        question = randomQuestions[pos].question;
        chA = randomQuestions[pos].a;
        chB = randomQuestions[pos].b;
        chC= randomQuestions[pos].c;

        let questionDiv= document.createElement("div");
        questionDiv.setAttribute("class", "question-text");
        questionDiv.innerHTML = "<h3>"+question+"</h3>";

        test.appendChild(questionDiv);

        let firstDiv=document.createElement("div");
        firstDiv.setAttribute("class", "questions-container");

        let label1 = document.createElement("label");
        label1.setAttribute("class", "round");

        let input1 = document.createElement("input");
        input1.setAttribute("class", "radio");
        input1.type = "radio";
        input1.name = "choices";
        input1.value = randomQuestions[pos].a;

        let span1 = document.createElement("span");
        span1.setAttribute("class", "checkmark");

        label1.appendChild(input1);
        label1.appendChild(span1);
        label1.innerHTML += chA;

        firstDiv.appendChild(label1);

        let label2 = document.createElement("label");
        label1.setAttribute("class", "round");

        let input2 = document.createElement("input");
        input2.setAttribute("class", "radio");
        input2.type = "radio";
        input2.name = "choices";
        input2.value = randomQuestions[pos].b;

        let span2 = document.createElement("span");
        span1.setAttribute("class", "checkmark");

        label2.appendChild(input2);
        label2.appendChild(span2);
        label2.innerHTML += chB;

        firstDiv.appendChild(label2);

        let secondDiv = document.createElement("div");
        secondDiv.setAttribute("class", "btn-quiz");

        let inputBtn = document.createElement("input");
        inputBtn.setAttribute("id", "submit")
        inputBtn.setAttribute("class", "btn-guess");
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
        secondDiv.appendChild(inputBtn);

        test.appendChild(firstDiv);
        test.appendChild(secondDiv);
    }
}