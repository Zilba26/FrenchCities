const selects = document.querySelectorAll("select");
const cities = document.querySelectorAll(".city");

for (let i = 0; i < selects.length; i++) {
    selects[i].addEventListener("change", (event) => {
        const { latitude: lat1, longitude: long1 } = getLatLongFromString(selects[0].value);
        const { latitude: lat2, longitude: long2 } = getLatLongFromString(selects[1].value);
        const distance = getDistance(lat1, long1, lat2, long2);
        document.querySelector(".distance-value").innerText = Math.ceil(distance) + " km";
        cities[i].innerText = getNomCommuneFromString(selects[i].value);
    });
}

function getLatLongFromString(str) {
    const latStartIndex = str.indexOf("latitude=") + 9;
    const latEndIndex = str.indexOf(",", latStartIndex);
    const longStartIndex = str.indexOf("longitude=") + 10;
    const longEndIndex = str.indexOf(",", longStartIndex) !== -1 ? str.indexOf(",", longStartIndex) : str.indexOf("]", longStartIndex);
    const latitude = parseFloat(str.substring(latStartIndex, latEndIndex));
    const longitude = parseFloat(str.substring(longStartIndex, longEndIndex));
    return { latitude, longitude };
}

function getNomCommuneFromString(str) {
    const nomStartIndex = str.indexOf("nomCommune=") + 11;
    const nomEndIndex = str.indexOf(",", nomStartIndex);
    return str.substring(nomStartIndex, nomEndIndex);
}

function getDistance(lat1, lon1, lat2, lon2) {
    const R = 6371; // rayon de la Terre en km
    const dLat = toRad(lat2 - lat1);
    const dLon = toRad(lon2 - lon1);
    const a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(toRad(lat1)) *
        Math.cos(toRad(lat2)) *
        Math.sin(dLon / 2) *
        Math.sin(dLon / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return R * c;
}

function toRad(degrees) {
    return (degrees * Math.PI) / 180;
}


selects.forEach((select) => {
    select.selectedIndex = 0;
    select.dispatchEvent(new Event("change"));
});

const container = document.querySelector(".container");
container.style.visibility = "visible";

const loader = document.querySelector(".loading");
loader.style.display = "none";