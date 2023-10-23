"use strict";

const canvas = document.getElementById("canvas");
const context = canvas.getContext("2d");
const canvasWidth = window.innerWidth * 0.8;  // 设置为屏幕宽度的80%
const canvasHeight = window.innerHeight * 0.8;  // 设置为屏幕高度的80%
canvas.width = canvasWidth;
canvas.height = canvasHeight;
const imgSize = 50;

let cursorX = 0;
let cursorY = 0;

function generateRandomChaser() {
    const chaser = {
        xPos: canvas.width * Math.random(),
        yPos: canvas.height * Math.random(),
        speed: Math.random() * 2 + 1,
        image: new Image(imgSize, imgSize),
        isHeadingRight: true,
        isHeadingUp: true,
    };
    chaser.image.src = "img/canvas.svg";
    return chaser;
}

function getChaseNums() {
    return Math.max(Math.floor(Math.random() * 10), 1) + 5;
}

const chasers = Array(getChaseNums()).fill(null).map(generateRandomChaser);

function animateImg() {

    context.clearRect(0, 0, canvas.width, canvas.height);
    chasers.forEach(chaser => {
        const dx = cursorX - chaser.xPos;
        const dy = cursorY - chaser.yPos;
        const distance = Math.sqrt(dx * dx + dy * dy);

        if (distance > 1) {
            chaser.xPos += (dx / distance) * chaser.speed;
            chaser.yPos += (dy / distance) * chaser.speed;
        }
        if(distance<imgSize/ 2){
            endTheGame()
        }
        context.drawImage(chaser.image, chaser.xPos, chaser.yPos, imgSize, imgSize);
    });

        window.requestAnimationFrame(animateImg);
 
}

function generateEndingDiv() {
    const ending = document.createElement("div");
    ending.style.textAlign = "center";
    ending.style.display = "flex";
    ending.style.flexDirection = "column";
    ending.style.alignItems = "center";
    ending.style.marginTop = `${canvas.height / 4}px`;
    return ending;
}
function showEnding() {
    const ending = generateEndingDiv();
    const oops = document.createElement("h1");
    oops.textContent = "🧟 Game OVER!";
    const refreshTip = document.createElement("h1");
    refreshTip.textContent = "🐙 Refresh the page to start again!";
    ending.appendChild(oops);
    ending.appendChild(refreshTip);
    document.body.appendChild(ending);
}
const endTheGame = () => {
    canvas.remove()
    showEnding()
}

function handleMouseMove(event) {
    cursorX = event.clientX - canvas.offsetLeft;
    cursorY = event.clientY - canvas.offsetTop;
}

window.onload = animateImg;
window.addEventListener('mousemove', handleMouseMove);
