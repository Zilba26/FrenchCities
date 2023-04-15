const toggleButton = document.getElementById('toggle-button');
const listContainer = document.getElementById('list-container');
const uninhibitedCitiesButton = document.getElementById('uninhibited_cities_button');
const createCityButton = document.getElementById('create_city_button');

toggleButton.addEventListener('click', () => {
    if (listContainer.style.display === 'none' || listContainer.style.display === '') {
        listContainer.style.display = 'block';
    } else {
        listContainer.style.display = 'none';
    }
});

document.addEventListener('click', (event) => {
    if (!listContainer.contains(event.target) && event.target !== toggleButton) {
        listContainer.style.display = 'none';
    }
});

createCityButton.addEventListener('click', () => {
    document.getElementById("form_submit_hidden").click();
});

uninhibitedCitiesButton.addEventListener('click', () => {
    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/uninhibited', true);

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            if (window.location.pathname === '/cities' || window.location.pathname === '/cities-distance') {
                window.location.reload();
            }
        }
    };

    xhr.send();

});