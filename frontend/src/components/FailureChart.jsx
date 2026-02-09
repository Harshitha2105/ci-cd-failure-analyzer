import React from "react";
import {
  PieChart,
  Pie,
  Cell,
  Tooltip,
  Legend,
  ResponsiveContainer
} from "recharts";

const COLORS = ["#ff4d4f", "#ffa940", "#40a9ff"];

const FailureChart = ({ failureType }) => {
  const data = [
    { name: "Build", value: failureType === "BUILD" ? 1 : 0 },
    { name: "Test", value: failureType === "TEST" ? 1 : 0 },
    { name: "Deploy", value: failureType === "DEPLOY" ? 1 : 0 }
  ];

  return (
    <ResponsiveContainer width="100%" height={300}>
      <PieChart>
        <Pie
          data={data}
          cx="50%"
          cy="50%"
          innerRadius={60}
          outerRadius={100}
          paddingAngle={5}
          dataKey="value"
          label
        >
          {data.map((entry, index) => (
            <Cell key={`cell-${index}`} fill={COLORS[index]} />
          ))}
        </Pie>

        <Tooltip />
        <Legend />
      </PieChart>
    </ResponsiveContainer>
  );
};

export default FailureChart;
