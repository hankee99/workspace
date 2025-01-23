const addBtn = document.querySelector("#add-btn");

addBtn.addEventListener("click", () => {
    todoAdd();
});
input.addEventListener("keyup", (e) => {
    if(e.keyCode === 13){
        todoAdd();
    }
});

function todoAdd(){
    const input = document.querySelector("#input");
    const inputValue = input.value;
    if(inputValue.length === 0){
        return;
    }

    const ul = document.createElement("ul");
    ul.classList.add("todo");
    const likeLi = document.createElement("li");
    const textLi = document.createElement("li");
    const checkLi = document.createElement("li");
    likeLi.classList.add("todo-like");
    textLi.classList.add("todo-text");
    checkLi.classList.add("todo-check");

    const likeSpan = document.createElement("span");
    const doneSpan = document.createElement("span");
    const deleteSpan = document.createElement("span");
    likeSpan.classList.add("material-icons");

    
    doneSpan.classList.add("material-icons");
    deleteSpan.classList.add("material-icons");

    likeSpan.innerText = "favorite_border";
    doneSpan.innerText = "task_alt";
    deleteSpan.innerText = "delete";

    likeSpan.addEventListener("click", () => {
        const currentText = likeSpan.innerText;
        if(currentText === "favorite_border"){
            likeSpan.innerText = "favorite";
        }else{
            likeSpan.innerText = "favorite_border";
        }
    });
    doneSpan.addEventListener("click", () => {
        textLi.style.textDecoration = "line-through";
        doneSpan.remove();
    });
    deleteSpan.addEventListener("click", () => {
        ul.remove();
    });


    likeLi.appendChild(likeSpan);
    textLi.innerText = inputValue;
    checkLi.appendChild(doneSpan);
    checkLi.appendChild(deleteSpan);

    ul.appendChild(likeLi);
    ul.appendChild(textLi);
    ul.appendChild(checkLi);

    const listBox = document.querySelector(".list-box");
    listBox.appendChild(ul);

    input.value = '';

}



