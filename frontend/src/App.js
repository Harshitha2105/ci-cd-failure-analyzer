import React from "react";
import Dashboard from "./pages/Dashboard";
import "./styles/dashboard.css";
import PredictionCard from "./PredictionCard";

function App() {
  return (
    <div>
      <Dashboard />

      <PredictionCard />
    </div>
  );
}

export default App;