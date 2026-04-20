```jsx
import React from 'react';
import { MapContainer, TileLayer } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';

/**
 * Implements the zoomable map view. 
 * Leaflet provides native support for zooming via mouse wheel, 
 * double click, and UI buttons.
 */
const WeatherMapView = () => {
  return (
    <div style={{ height: '100vh', width: '100%' }}>
      <MapContainer 
        center={[40, -100]} 
        zoom={4} 
        minZoom={2} 
        maxZoom={18} 
        scrollWheelZoom={true}
        style={{ height: '100%', width: '100%' }}
      >
        <TileLayer
          attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
      </MapContainer>
    </div>
  );
};

export default WeatherMapView;
```