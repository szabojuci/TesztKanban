```tsx
// Frontend: pages/game.tsx
import { useEffect, useState } from 'react';
import { io } from 'socket.io-client';

const socket = io('https://your-socket-server.com'); // Replace with your real-time server URL

export default function GameScreen() {
  const [remotePlayers, setRemotePlayers] = useState<Record<string, any>>({});
  const [myPos, setMyPos] = useState(50);

  useEffect(() => {
    const room = new URLSearchParams(window.location.search).get('room') || 'LOBBY';
    const avatar = localStorage.getItem('playerAvatar') || '🐱';
    const color = localStorage.getItem('playerColor') || '#FF5757';

    socket.emit('join-room', { room, avatar, color });

    socket.on('update-players', (players) => {
      const others = { ...players };
      delete others[socket.id];
      setRemotePlayers(others);
    });

    const handleMouseMove = (e: MouseEvent) => {
      const xPercent = (e.clientX / window.innerWidth) * 100;
      setMyPos(xPercent);
      socket.emit('move', { room, x: xPercent });
    };

    window.addEventListener('mousemove', handleMouseMove);
    return () => {
      window.removeEventListener('mousemove', handleMouseMove);
      socket.disconnect();
    };
  }, []);

  return (
    <div style={{ width: '100vw', height: '100vh', background: '#121212', overflow: 'hidden', position: 'relative', cursor: 'none' }}>
      {/* Background Grid */}
      <div style={{ position: 'absolute', width: '100%', height: '100%', opacity: 0.1, backgroundImage: 'radial-gradient(#fff 1px, transparent 1px)', backgroundSize: '40px 40px' }} />

      {/* Remote Players (Other Kids) */}
      {Object.entries(remotePlayers).map(([id, p]) => (
        <div key={id} style={{ 
          position: 'absolute', left: `${p.x}%`, bottom: '60px', 
          transform: 'translateX(-50%)', transition: 'left 0.1s linear', textAlign: 'center' 
        }}>
          <span style={{ fontSize: '2rem', display: 'block', marginBottom: '5px' }}>{p.avatar}</span>
          <div style={{ width: '80px', height: '12px', background: p.color, borderRadius: '10px', opacity: 0.7 }} />
        </div>
      ))}

      {/* Local Player (Me) */}
      <div style={{ 
        position: 'absolute', left: `${myPos}%`, bottom: '60px', 
        transform: 'translateX(-50%)', textAlign: 'center' 
      }}>
        <span style={{ fontSize: '3rem', display: 'block', marginBottom: '5px' }}>{localStorage.getItem('playerAvatar') || '🐱'}</span>
        <div style={{ 
          width: '100px', height: '15px', background: localStorage.getItem('playerColor')