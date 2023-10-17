let t1Element = document.createElement('h1');
t1Element.textContent = "Good Good Study"
document.body.appendChild(t1Element); 
let t2Element = document.createElement('h2');
t2Element.textContent = "and Day day up"
document.body.appendChild(t2Element); 
let ulElement = document.createElement('ul')
for (let i = 1; i <= 3; i++) {
    let liElement = document.createElement('li');
    liElement.textContent = 'hobby' + i;
    ulElement.appendChild(liElement);
}
document.body.appendChild(ulElement); 
let imgElement = document.createElement('img');
imgElement.src = 'https://raygun.com/blog/images/programming-languages/feature-2023.jpg'
document.body.appendChild(imgElement); 
