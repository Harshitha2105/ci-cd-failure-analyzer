import React from "react";

const HealthScore = () => {
  const score = 72;

  return (
    <div>
      <h3>Pipeline Health Score</h3>
      <h1>{score}%</h1>
    </div>
  );
};

export default HealthScore;
