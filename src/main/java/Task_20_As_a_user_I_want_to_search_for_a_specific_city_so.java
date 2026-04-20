```html
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
    <style>#map { height: 600px; width: 100%; } .search-box { margin-bottom: 10px; }</style>
</head>
<body>
    <div class="search-box">
        <input type="text" id="cityInput" placeholder="Enter city name (e.g. London)">
        <button onclick="searchCity()">Search</button>
    </div>
    <div id="map"></div>

    <script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>
    <script>
        const map = L.map('map').setView([20, 0], 2);
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; OpenStreetMap contributors'
        }).addTo(map);

        async function searchCity() {
            const city = document.getElementById('cityInput').value;
            if (!city) return;
            
            try {
                const response = await fetch(`https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(city)}`);
                const data = await response.json();
                
                if (data && data.length > 0) {
                    const { lat, lon, display_name } = data[0];
                    map.setView([lat, lon], 10);
                    L.marker([lat, lon]).addTo(map).bindPopup(display_name).openPopup();
                } else {
                    alert("City not found.");
                }
            } catch (error) {
                console.error("Search failed:", error);
            }
        }

        document.getElementById('cityInput').addEventListener('keypress', (e) => {
            if (e.key === 'Enter') searchCity();
        });
    </script>
</body>
</html>
```