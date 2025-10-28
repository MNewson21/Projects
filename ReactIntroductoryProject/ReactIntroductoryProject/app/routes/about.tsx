
import { Link } from "react-router";



export default function About() {
  return (
    <div className="min-h-screen flex flex-col items-center justify-center
      bg-gradient-to-r from-cyan-500 via-purple-500 to-blue-500
      bg-size-200 animate-gradient-x text-center p-8">
      <h1 className="text-[100px]">About</h1>
      <p className="text-[80px]">This is the about page.</p>
      <p className="text-[50px]"> Scroll ↓ </p>
      <p className="text-[40px] mt-100"> Scroll ↓ </p>
      <p className="text-[30px] mt-200"> Scroll ↓ </p>
      <p className="text-[20px] mt-300"> Scroll ↓ </p>
      <p className="text-[10px] mt-400"> Scroll ↓ </p>

      <div className="mt-500">
        <Link to="/" className="mt-100  text-center text-blue-600 hover:underline">Back to Home</Link>
      </div>

    
    </div>
  );
}