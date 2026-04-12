import React, { useState } from "react";
import axios from "axios";

const UploadLog = ({ onResult }) => {
  const [log, setLog] = useState("");
  const [pipeline, setPipeline] = useState("");

  const handleFile = (e) => {
    const reader = new FileReader();
    reader.onload = (event) => {
      setLog(event.target.result);
    };
    reader.readAsText(e.target.files[0]);
  };

  const handleSubmit = async () => {
    const res = await axios.post("http://localhost:8080/logs", {
      pipelineName: pipeline,
      logContent: log
    });

    onResult(res.data);
  };

  return (
    <div>
      <h3>Upload Pipeline Log</h3>

      <input
        type="text"
        placeholder="Pipeline Name"
        onChange={(e) => setPipeline(e.target.value)}
      />

      <textarea
        placeholder="Paste logs..."
        onChange={(e) => setLog(e.target.value)}
      />

      <input type="file" onChange={handleFile} />

      <button onClick={handleSubmit}>Analyze</button>
    </div>
  );
};

export default UploadLog;