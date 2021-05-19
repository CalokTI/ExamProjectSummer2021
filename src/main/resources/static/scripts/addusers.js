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
    input.id    = "taskusername";
    input.value = username;
    user.appendChild(input);
    user.appendChild(document.createTextNode(username + " " + firstname + " " + lastname + " " + role));
    list.appendChild(user);
});