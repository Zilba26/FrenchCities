function goToCity(code) {
    window.location.href = '/city?code=' + code;
}

function goToPage(page) {
    const input = document.querySelector('input');
    input.value = page;
    input.click();
}

const select = document.querySelector('select');
select.addEventListener('change', (event) => {
    goToPage(event.target.value);
});