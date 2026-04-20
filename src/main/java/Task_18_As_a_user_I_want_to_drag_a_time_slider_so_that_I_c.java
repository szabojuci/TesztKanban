```html
<!DOCTYPE html>
<html>
<head>
  <style>
    #map-view { height: 300px; background: #87CEEB; display: flex; align-items: center; justify-content: center; font-family: sans-serif; transition: background 0.3s; }
    .slider-container { padding: 20px; background: #f4f4f4; font-family: sans-serif; }
    input[type=range] { width: 100%; }
  </style>
</head>
<body>
  <div id="map-view"><h1>Sunny (Now)</h1></div>
  <div class="slider-container">
    <label>Time Offset: <strong><span id="hour-label">0</span> hours</strong></label>
    <input type="range" id="time-slider" min="0" max="24" value="0">
  </div>

  <script>
    const slider = document.getElementById('time-slider');
    const label = document.getElementById('hour-label');
    const map = document.getElementById('map-view');
    const weatherStates = ['Sunny', 'Cloudy', 'Rainy', 'Showers', 'Clear Night'];

    slider.addEventListener('input', (e) => {
      const h = parseInt(e.target.value);
      label.textContent = h;
      
      // Update UI to simulate weather change
      const state = weatherStates[h % weatherStates.length];
      const isNight = h > 18 || h < 6;
      
      map.innerHTML = `<h1>${state} (+${h}h)</h1>`;
      map.style.background = isNight ? '#2c3e50' : '#87CEEB';
      map.style.color = isNight ? 'white' : 'black';
      
      // Note: In a production app, this would trigger a map.setSource() 
      // or filter for a specific timestamp in the Mapbox/Leaflet layer.
    });
  </script>
</body>
</html>
```