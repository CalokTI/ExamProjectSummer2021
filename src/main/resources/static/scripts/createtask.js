function haveUsersBeenAdded(){
    if(document.getElementById("taskusername") !== null){
        document.getElementById("taskcreation").submit();
        return true;
    }
    alert("Add users to task");
    return false;
}
