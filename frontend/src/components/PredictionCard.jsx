import React, { useEffect, useState } from "react";
import axios from "axios";

const PredictionCard = () => {
  const [data, setData] = useState(null);

  useEffect(() => {
    fetchPrediction();
    const interval = setInterval(fetchPrediction, 5000); // refresh every 5s
    return () => clearInterval(interval);
  }, []);

  const fetchPrediction = async () => {
    const res = await axios.get("http://localhost:8080/predict");
    setData(res.data);
  };

  if (!data) return <p>Loading...</p>;

  return (
    <div>
      <h3>Failure Prediction</h3>

      <p>Probability: {(data.probability * 100).toFixed(2)}%</p>

      <p style={{
        color:
          data.risk === "HIGH"
            ? "red"
            : data.risk === "MEDIUM"
            ? "orange"
            : "green"
      }}>
        Risk: {data.risk}
      </p>

      <p>Likely Failure: {data.predictedFailure}</p>
    </div>
  );
};

export default PredictionCard;