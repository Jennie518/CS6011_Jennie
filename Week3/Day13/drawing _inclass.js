"use strict"
// let canvas = document.getElementsByTagName("canvas")[0];
let canvas = document.getElementById("canvasDrawing");
let cWidth = canvas.width;
let cHeight = canvas.height;
let context = canvas.getContext("2d")

let myImg = new Image();
//<img src = ...> in html
myImg.src = "./avator.png";


let myImgMSD = new Image();
myImgMSD.src = "./msd.png";
myImgMSD.xPos = 10;
myImgMSD.yPos = 10;
let movingRight = true;
function animateImg(){
    eraseOld();
    context.drawImage(myImgMSD,myImgMSD.xPos,myImgMSD.yPos,200,200);
    // if(myImgMSD.xPos < cWidth-200)
    if(movingRight){
        myImgMSD.xPos += 3;
    }
    else
    {
        myImgMSD.xPos -= 3;
    }
    if (myImgMSD.xPos > cWidth)
    {
        movingRight =false;
    }
    else if (myImgMSD.xPos < 0){
        movingRight = true;
    }
    
    window.requestAnimationFrame(animateImg);
}
function mainDrawing(){
    context.drawImage(myImg,20,20,100,100)
    context.strokeRect(20,20,100,100)
    //call animate
    window.requestAnimationFrame(animateImg);
}
function handleClick(e){
    //move the box around img
    //erase the old img
    //erase before drawing
    //create a function to erase
    eraseOld();
    myImg.xPos = e.x;
    myImg.yPos = e.y;
    context.drawImage(myImg,e.x,e.y,100,100)
    context.strokeRect(e.x,e.y,100,100)
}

function eraseOld(){
    //add layer on the top of the prev. image
    context.drawImage(0,0,cWidth,cHeight);
}

window.onload = mainDrawing;
window.onmouseover = handleClick;

