function titleCheck() {

    let input = document.getElementById("title").value;
    console.log(input);

    if(list.includes(input)){
        document.querySelector(".btn-grid-confirm").disabled = true;
    }else{
        document.querySelector(".btn-grid-confirm").disabled = false;
    }
}