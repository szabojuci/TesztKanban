```html
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
    <style>#map { height: 500px; } .controls { padding: 10px; }</style>
</head>
<body>
    <div id="map"></div>
    <div class="controls">
        <input type="range" id="timeSlider" min="0" max="2" value="0" step="1" style="width: 100%;">
        <span id="timeLabel">Time: 0h</span>
    </div>

    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
    <script>
        const map = L.map('map').setView([40.7128, -74.0060], 10);
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png').addTo(map);

        const forecastData = [
            { hour: 0, markers: [{ pos: [40.71, -74.00], info: "20°C - Clear" }, { pos: [40.73, -73.93], info: "19°C" }] },
            { hour: 1, markers: [{ pos: [40.71, -74.00], info: "18°C - Cloudy" }, { pos: [40.73, -73.93], info: "17°C" }] },
            { hour: 2, markers: [{ pos: [40.71, -74.00], info: "15°C - Rain" }, { pos: [40.73, -73.93], info: "14°C" }] }
        ];

        const markerGroup = L.layerGroup().addTo(map);

        function updateDisplay(index) {
            markerGroup.clearLayers();
            const data = forecastData[index];
            document.getElementById('timeLabel').innerText = `Time: +${data.hour}h`;
            
            data.markers.forEach(m => {
                L.marker(m.pos).addTo(markerGroup).bindPopup(m.info);
            });
        }

        document.getElementById('timeSlider').addEventListener('input', (e) => {
            updateDisplay(e.target.value);
        });

        updateDisplay(0); // Initial load
    </script>
</body>
</html>
```