import React, { useState } from "react";
import UploadLog from "../components/UploadLog";
import FailureChart from "../components/FailureChart";
import HealthScore from "../components/HealthScore";
import PredictionCard from "../components/PredictionCard";
import TicketTable from "../components/TicketTable";

const Dashboard = () => {
  const [result, setResult] = useState(null);

  return (
    <div>
      <h1>CI/CD Failure Analyzer Dashboard</h1>
mvn spring-boot:run

      <UploadLog onResult={setResult} />

      {result && (
        <>
          <h3>Analysis Result</h3>
          <p>Failure Type: {result.failureType}</p>
          <p>Root Cause: {result.rootCause}</p>
          <p>Recommendation: {result.recommendation}</p>
          <FailureChart failureType={result.failureType} />
        </>
      )}

      <HealthScore />
      <PredictionCard />
      <TicketTable />
    </div>
  );
};

export default Dashboard;
