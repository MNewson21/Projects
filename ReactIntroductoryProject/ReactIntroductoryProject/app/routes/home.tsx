import type { Route } from "./+types/home";
import { Link } from "react-router";
import { useState, useEffect } from "react";


export function meta({}: Route.MetaArgs) {
  return [
    { title: "New React Router App" },
    { name: "description", content: "Welcome to React Router!" },
  ];
}

export default function Home() {
  return <Helper />;
}

export function Helper() {
  const [count, setCount] = useState(0);
  const [mousePos, setMousePos] = useState({ x: 0, y: 0 });

  useEffect(() => {
    const handleMouseMove = (e: MouseEvent) => {
      setMousePos({ x: e.clientX, y: e.clientY });
    };
    window.addEventListener("mousemove", handleMouseMove);
    return () => window.removeEventListener("mousemove", handleMouseMove);
  }, []);

  return (
    <div className="relative min-h-screen overflow-hidden bg-gradient-to-r from-cyan-500 via-purple-500 to-blue-500 bg-size-200 animate-gradient-x">
      {/* Glow effect */}
      <div
        className="absolute pointer-events-none z-0"
        style={{
          top: mousePos.y - 150,
          left: mousePos.x - 150,
          width: 300,
          height: 300,
          borderRadius: "50%",
          background: "radial-gradient(circle, rgba(255,255,255,0.3) 0%, transparent 70%)",
          transition: "top 0.1s, left 0.1s",
        }}
      />

      
      <div className="relative z-10 flex flex-row items-center justify-center min-h-screen px-4">
        {/* Left bar */}
        <div className="hidden md:flex flex-col items-center justify-center mr-4">
          <div className="w-1 h-64 bg-black rounded-full" />
        </div>

        {/* Center*/}
        <div className="flex flex-col items-center justify-center text-center p-8  bg-opacity-80 rounded-lg shadow-lg">
          <h1 className="text-[50px] font-sans font-bold text-blue-600 mb-6">
            React Page 1
          </h1>
          <p>This project is just for me to help get familiar with react and tailwind.</p>
          <p>I am trying to react it for educational purposes and its uses for making modern websites / frontends</p>

          <Link to="/about" className="text-blue-600 hover:underline mb-8 mt-4">
            Go to next page
          </Link>

          <button
            onClick={() => setCount(count + 1)}
            className="hover:bg-cyan-500 scale-110 p-4 rounded text-blue-600 "
          >
            Times Pressed: {count}
          </button>
        </div>

        {/* Right bar*/}
        <div className="hidden md:flex flex-col items-center justify-center ml-4">
          <div className="w-1 h-64 bg-black rounded-full" />

        </div>
      </div>
    </div>
  );
}