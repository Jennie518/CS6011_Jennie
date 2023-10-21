"use strict"

const xVaule = document.getElementById("xValue");
const yVaule = document.getElementById("yValue");
const resultInput = document.getElementById("result");
const buttonClick = document.getElementById("myButton")
const IsClick = false
// xVaule.addEventListener("keypress", handleKeyPress);
// yVaule.addEventListener("keypress", handleKeyPress);

 let ws = new WebSocket("ws://localhost:8080");
 let wsOpen = false;

 ws.onopen = function(){
    wsOpen = true;
}

ws.onmessage = function(event){
    resultInput.value = event.data; // data coming from the socket
}

function handleAJAX(){
    resultInput.value = this.responseText;
}

function handleError(){
    resultInput.value = "Problem connecting to the server";
}

window.addEventListener("keypress",handleKeyPress)
button.addEventListener('click', function(event) {
    
});
function handleKeyPress(event){
    if (event.code == "Enter"|| event.code == "Click") {
        let x = parseFloat(xValue.value);
        let y = parseFloat(yValue.value);
        if (isNaN(x)){
            alert("X should be a number!");
            xVaule.value = "Enter a number";
            xVaule.select();
            return;
        }

        if (isNaN(y)){
            alert("Y should be a number!");
            yVaule.value = "Enter a number";
            yVaule.select();
            return;
        }

        console.log("x value", x);
        console.log("y value", y);
        
        //Naive way
        //resultInput.value = (x+y);

        //Option 1 - Using AJAX
        // let xhr = new XMLHttpRequest();
        // xhr.open("GET", "http://localhost:8080/calculate?x=" + x + "&y=" + y);
        // xhr.onerror = handleError;
        // //xhr.addEventListener("load", "call a method")
        // xhr.onload = handleAJAX;
        // xhr.send();

       

        //Option 2 - Web Sockets    
        if(wsOpen){
            // send x, y
            ws.send(x + " " + y);
        }
        else {
            resultInput.value = "Could not open the websocket!"
        }


        ws.onerror = handleError;
        ws.onclose;
        
    }
}
