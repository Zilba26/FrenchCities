const temp_message = document.getElementsByClassName("temp-message");

for (let i = 0; i < temp_message.length; i++) {
    setTimeout(() => {
        temp_message[i].classList.add("visible");

        setTimeout(() => {
            temp_message[i].classList.remove("visible");
        }, 3000);
    }, 10);
}