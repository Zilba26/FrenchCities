const update_message = document.querySelector(".update-message");

if (update_message != null) {
    setTimeout(() => {
        update_message.classList.add("visible");

        setTimeout(() => {
            update_message.classList.remove("visible");
        }, 3000);
    }, 10);
}
