```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Weather Map</title>
    <link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
    <style>
        body { margin: 0; }
        #map { height: 100vh; width: 100%; }
    </style>
</head>
<body>
    <div id="map"></div>
    <script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>
    <script>
        const map = L.map('map').setView([20, 0], 2);

        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '© OpenStreetMap contributors'
        }).addTo(map);

        // Mock weather data points
        const weatherData = [
            { coords: [40.71, -74.00], info: "New York: 22°C, Sunny" },
            { coords: [51.50, -0.12], info: "London: 15°C, Rainy" },
            { coords: [35.68, 139.65], info: "Tokyo: 28°C, Cloudy" },
            { coords: [-33.86, 151.20], info: "Sydney: 19°C, Clear" }
        ];

        weatherData.forEach(data => {
            L.marker(data.coords)
                .addTo(map)
                .bindPopup(data.info);
        });

        // Interactive click to show coordinates
        map.on('click', e => {
            L.popup()
                .setLatLng(e.latlng)
                .setContent(`Selected Region: ${e.latlng.toString()}`)
                .openOn(map);
        });
    </script>
</body>
</html>
```