function haveUsersBeenAdded(){
    if(document.getElementById("currentTaskUsers").children[0] !== null){
        document.getElementById("taskcreation").submit();
        return true;
    }
    alert("Add users to task");
    return false;
}

function navn(){
    //check om inputs er tomme
}
