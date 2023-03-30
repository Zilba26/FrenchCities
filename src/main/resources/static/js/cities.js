function goToCity(code) {
    window.location.href = '/city?code=' + code;
}

function goToPage(page) {
    const input = document.querySelector('input[type=submit]');
    input.value = (parseInt(page) + 1).toString();
    input.click();
}

const select = document.querySelector('select');
select.addEventListener('change', (event) => {
    goToPage(event.target.value);
});

const th = document.querySelectorAll('th');

th.forEach((element) => {
    element.addEventListener('click', (event) => {
        const id = event.target.id;
        window.location.href = '/cities?order=' + id;
    });
})