"use strict"

const xValue = document.getElementById("xValue");
const yValue = document.getElementById("yValue");
const resultInput = document.getElementById("result");
const buttonClick = document.getElementById("myButton")
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
buttonClick.addEventListener('click', function(event) {
    handleKeyPress({code: "Enter"});
});
function handleKeyPress(event){
    if (event.code == "Enter"|| IsClick) {
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
        IsClick = false;

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
