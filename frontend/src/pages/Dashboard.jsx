import React, { useState } from "react";
import UploadLog from "../components/UploadLog";
import FailureChart from "../components/FailureChart";
import HealthScore from "../components/HealthScore";
import PredictionCard from "../components/PredictionCard";
import TicketTable from "../components/TicketTable";
import LiveStatus from "../LiveStatus";

const Dashboard = () => {
  const [result, setResult] = useState(null);

  // 🔥 NEW: Pipeline Simulation Function
  const simulatePipeline = async () => {
    const steps = [
      "Build started...",
      "Installing dependencies...",
      "Running tests...",
      "Test failed: NullPointerException"
    ];

    for (let i = 0; i < steps.length; i++) {
      await new Promise((res) => setTimeout(res, 2000));

      const response = await fetch("http://localhost:8080/api/logs/analyze", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          pipelineName: "Demo-Pipeline",
          logContent: steps[i]
        })
      });

      const data = await response.json();
      setResult(data);
    }
  };

  return (
    <div style={{ padding: "20px", fontFamily: "Arial" }}>

      {/* 🔷 HEADER */}
      <h1 style={{ textAlign: "center", marginBottom: "20px" }}>
        CI/CD Failure Analyzer Dashboard
      </h1>

      {/* 🔷 UPLOAD SECTION */}
      <div style={cardStyle}>
        <h2>Upload Pipeline Log</h2>
        <UploadLog onResult={setResult} />

        {/* 🔥 NEW BUTTON */}
        <button
          onClick={simulatePipeline}
          style={{ marginTop: "10px", padding: "10px", cursor: "pointer" }}
        >
          ▶ Start Pipeline Simulation
        </button>
      </div>

      {/* 🔥 LIVE LOGS (REAL-TIME TERMINAL) */}
      <div style={cardStyle}>
        <LiveStatus />
      </div>

      {/* 🔷 ANALYSIS RESULT */}
      {result && (
        <div style={cardStyle}>
          <h2>Analysis Result</h2>

          <p><strong>Failure Type:</strong> {result.failureType}</p>
          <p><strong>Root Cause:</strong> {result.rootCause}</p>
          <p><strong>Recommendation:</strong> {result.recommendation}</p>

          <FailureChart failureType={result.failureType} />
        </div>
      )}

      {/* 🔷 METRICS SECTION */}
      <div style={rowStyle}>
        <div style={smallCard}>
          <HealthScore />
        </div>

        <div style={smallCard}>
          <PredictionCard />
        </div>
      </div>

      {/* 🔷 TICKETS */}
      <div style={cardStyle}>
        <h2>Failure Tickets</h2>
        <TicketTable />
      </div>
    </div>
  );
};

/* 🔥 STYLES */

const cardStyle = {
  border: "1px solid #ddd",
  borderRadius: "10px",
  padding: "20px",
  marginBottom: "20px",
  boxShadow: "0 2px 5px rgba(0,0,0,0.1)",
  backgroundColor: "#fff"
};

const rowStyle = {
  display: "flex",
  gap: "20px",
  marginBottom: "20px"
};

const smallCard = {
  flex: 1,
  border: "1px solid #ddd",
  borderRadius: "10px",
  padding: "20px",
  boxShadow: "0 2px 5px rgba(0,0,0,0.1)",
  backgroundColor: "#fff"
};

export default Dashboard;