```javascript
import React, { useState, createContext, useContext } from 'react';

// Context to manage temperature unit globally
const TemperatureContext = createContext();

export const TemperatureProvider = ({ children }) => {
  const [unit, setUnit] = useState('C'); // 'C' for Celsius, 'F' for Fahrenheit

  const toggleUnit = () => setUnit((prev) => (prev === 'C' ? 'F' : 'C'));

  const formatTemp = (celsius) => {
    const value = unit === 'C' ? celsius : (celsius * 9) / 5 + 32;
    return `${value.toFixed(1)}°${unit}`;
  };

  return (
    <TemperatureContext.Provider value={{ unit, toggleUnit, formatTemp }}>
      {children}
    </TemperatureContext.Provider>
  );
};

// UI Component to toggle units
export const UnitToggleButton = () => {
  const { unit, toggleUnit } = useContext(TemperatureContext);
  return (
    <button 
      onClick={toggleUnit} 
      style={{ position: 'absolute', top: '10px', right: '10px', zIndex: 1000, padding: '8px' }}
    >
      Units: °{unit}
    </button>
  );
};

// Component to display formatted temperature
export const TempDisplay = ({ celsius }) => {
  const { formatTemp } = useContext(TemperatureContext);
  return <span>{formatTemp(celsius)}</span>;
};
```