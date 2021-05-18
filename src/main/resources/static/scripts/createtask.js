function filterSearch() {
    // Declare variables
    var input, filter, ul, li, a, i, txtValue;
    input = document.getElementById('myInput');
    filter = input.value.toUpperCase();
    ul = document.getElementById("myUL");
    li = ul.getElementsByTagName('li');

    // Loop through all list items, and hide those who don't match the search query
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByTagName("a")[0]; //element is <a> here
        txtValue = a.innerText || a.textContent;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
}

//adds users to a hidden input field
document.getElementById("myUL").addEventListener("click", function (event) {
    var list = document.getElementById('currentTaskUsers');
    var parentNode = document.adoptNode(event.target.parentNode.parentNode);

    var username    = parentNode.children[0].children[0].textContent;
    var firstname   = parentNode.children[0].children[1].textContent;
    var lastname    = parentNode.children[0].children[2].textContent;
    var role        = parentNode.children[0].children[3].textContent;

    var user    = document.createElement("li");
    var input   = document.createElement("input");
    input.type  = "hidden";
    input.name  = "username";
    input.id    = "username";
    input.value = username;
    user.appendChild(input);
    user.appendChild(document.createTextNode(username + " " + firstname + " " + lastname + " " + role));
    list.appendChild(user);

});

function haveUsersBeenAdded(){
    if(document.getElementById("username") !== null){
        return true;
    }
    alert("Add users to task");
    return false;
}
