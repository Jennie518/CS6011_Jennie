
// find the location of the smallest value in the array starting from the 'start' position
function findMinLocation(arr, start) {
    let minIndex = start;
    for(let i = start + 1; i < arr.length; i++) {
        if(arr[i] < arr[minIndex]) {
            minIndex = i;
        }
    }
    return minIndex;
}

// Basic implementation of the selection sort algorithm
function selectionSort(arr) {
    for(let i = 0; i < arr.length - 1; i++) {
        let minIndex = findMinLocation(arr, i);
        [arr[i], arr[minIndex]] = [arr[minIndex], arr[i]];// Swap elements
    }
}

function compareNumbers(a, b) {
    return a < b;
}


let testArray1 = [3, 1, 4, 1, 5, 9];//todo 加一个comprator 
selectionSort(testArray1);
console.log(testArray1); // 输出: [1, 1, 3, 4, 5, 9]

let testArray2 = ["apple", "Orange", "banana"];
selectionSort(testArray2);
console.log(testArray2);  // 注意：此函数将首字母大写的字符串排在首字母小写的字符串之前，因为大写字母的ASCII值小于小写字母。


function selectionSort(arr, comparator) {
    if(!comparator) {
        comparator = (a, b) => a < b;
    }

    for(let i = 0; i < arr.length - 1; i++) {
        let minIndex = i;
        for(let j = i + 1; j < arr.length; j++) {
            if(comparator(arr[j], arr[minIndex])) {
                minIndex = j;
            }
        }
        [arr[i], arr[minIndex]] = [arr[minIndex], arr[i]];
    }
}

// Test arrays
let people = [
    {first: "John", last: "Doe"},
    {first: "Jane", last: "Smith"},
    {first: "John", last: "Smith"},
];


function compareByLastName(a, b) {
    if(a.last === b.last) {
        return a.first < b.first;
    }
    return a.last < b.last;
}

function compareByFirstName(a, b) {
    if(a.first === b.first) {
        return a.last < b.last;
    }
    return a.first < b.first;
}



selectionSort(people, compareByLastName);
console.log(people);

selectionSort(people, compareByFirstName);
console.log(people);
