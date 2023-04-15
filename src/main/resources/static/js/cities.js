function goToCity(code) {
    window.location.href = '/city?code=' + code;
}

function goToPage(page) {
    const input = document.getElementById("page_city");
    input.value = (parseInt(page) + 1).toString();
    input.click();
}

const select = document.querySelector('select');
select.addEventListener('change', (event) => {
    goToPage(event.target.value);
});

const th = document.querySelectorAll('th');

th.forEach((element) => {
    const id = element.id;
    element.addEventListener('click', () => {
        const order = document.querySelector('input[name=order]');
        if (order!= null && id === order.value.substring(0, order.value.length - 2)) {
            const orderDirection = order.value.substring(order.value.length - 1);
            if (orderDirection === 'A') {
                window.location.href = '/cities?order=' + id + '_D';
            } else {
                window.location.href = '/cities?order=' + id + '_A';
            }
        } else {
            window.location.href = '/cities?order=' + id + '_A';
        }
    });
});


const delete_message = document.querySelector(".delete-message");

if (delete_message != null) {
    setTimeout(() => {
        delete_message.classList.add("visible");

        setTimeout(() => {
            delete_message.classList.remove("visible");
        }, 3000);
    }, 10);
}