document.writeln("Hello World");
console.log("Hello World");
let myarray = [3.14,true,"hello",{key:"value"}];
console.log("print my array: " + myarray);
myarray[1] = false;
console.log("change array boolean to false: "+ myarray);

console.log(f(2,3));// can be Hoisting
function f(a,b){
    return a + b;
}

let myfunction = function(a,b){
    return a + b;
}
