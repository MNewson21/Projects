import type { Route } from "./+types/home";
import { Welcome } from "../welcome/welcome";
import { Link } from "react-router";
import { useState } from "react";



export function meta({}: Route.MetaArgs) {
  return [
    { title: "New React Router App" },
    { name: "description", content: "Welcome to React Router!" },
  ];
}

export default function Home() {
  return (
    <>
      <Helper />
    </>
  );
}



export function Helper(){
  const [count, setCount] = useState(0);
  const handleClick = () => setCount(count + 1);

  return (
    <div className="min-h-screen
        items-center justify-center bg-gradient-to-r from-cyan-500 via-purple-500 to-blue-500 bg-[length:200%_200%] animate-gradient-x text-center p-8">
      <div className=""> 
        <h1 className="h1 text-center text-[50px] text-blue-600"> This is the start of my react journey </h1>
        <Link to="/about" className="ab text-center text-blue-600 hover:underline"> go to page</Link>
      </div>

      <div>
        <button onClick={handleClick} className="hover:bg-fuchsia-500 scale-150  mt-100"> I am a button, Times Pressed : {count}  </button>
      </div>
    </div>

  )
}





/*
export function Helper(){
  const [count, setCount] = useState(0);
  const handleClick = () => setCount(count + 1);

  return (
    <div className="min-h-screen h-14 bg-linear-to-r from-cyan-500 to-blue-500 bg-cover bg-center bg-scroll text-center">
      <div className=""> 
        <h1 className="h1 text-center text-[50px] text-blue-600"> This is the start of my react journey </h1>
        <Link to="/about" className="ab text-center text-blue-600 hover:underline"> go to page</Link>
      </div>

      <div>
        <button onClick={handleClick} className="hover:bg-fuchsia-500 scale-150  mt-100"> I am a button, Times Pressed : {count}  </button>
      </div>
    </div>

  )
}







/*
export function Helper(){
  return (
    <div className="min-h-screen bg-[url(bck1.jpg)] bg-cover bg-center text-center">
      <div className=""> 
        <h1 className="h1 text-center text-[50px] text-blue-600"> This is the start of my react journey </h1>
        <Link to="/about" className="ab text-center text-blue-600 hover:underline"> go to page</Link>
      </div>


    </div>

  )
}
  */



/*

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
    <div className="relative min-h-screen overflow-hidden">
      {/*For the glow effect}
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


      <div className="min-h-screen flex flex-col items-center justify-center
        bg-gradient-to-r from-cyan-500 via-purple-500 to-blue-500
        bg-size-200 animate-gradient-x text-center p-8"
      >
        <h1 className="hidden md:flex text-[50px] font-sans font-bold text-blue-600 mb-6">
          React Page 1
        </h1>

        <Link to="/about" className="text-blue-600 hover:underline mb-8">
          Go to page
        </Link>

        <button
          onClick={() => setCount(count + 1)}
          className="hover:bg-cyan-500 scale-110 p-4 rounded text-blue-600 bg-blue"
        >
          Times Pressed: {count}
        </button>

      </div>
    </div>
  );
}


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
      {/* Glow effect }
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

      {/* Three-column layout }
      <div className="relative z-10 flex flex-row items-center justify-center min-h-screen px-4">
        {/* Left column }
        <div className="hidden md:flex flex-col items-center justify-center mr-4">
          <div className="w-1 h-64 bg-black rounded-full" />
        </div>

        {/* Center content }
        <div className="flex flex-col items-center justify-center text-center p-8  bg-opacity-80 rounded-lg shadow-lg">
          <h1 className="text-[50px] font-sans font-bold text-blue-600 mb-6">
            React Page 1
          </h1>

          <Link to="/about" className="text-blue-600 hover:underline mb-8">
            Go to page
          </Link>

          <button
            onClick={() => setCount(count + 1)}
            className="hover:bg-cyan-500 scale-110 p-4 rounded text-blue-600 "
          >
            Times Pressed: {count}
          </button>
        </div>

        {/* Right column }
        <div className="hidden md:flex flex-col items-center justify-center ml-4">
          <div className="w-1 h-64 bg-black rounded-full" />
        </div>
      </div>
    </div>
  );
}
*/


