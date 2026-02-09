import { useState } from "react";
import { analyzeLog } from "../api/api";

export default function UploadLog({ onResult }) {
  const [pipelineName, setPipelineName] = useState("");
  const [logContent, setLogContent] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const submit = async () => {
    setError("");
    try {
      setLoading(true);
      const response = await analyzeLog({ pipelineName, logContent });
      onResult(response.data);
    } catch (err) {
      setError("Backend not reachable or error occurred");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="card">
      <h3>Upload CI/CD Log</h3>

      <input
        placeholder="Pipeline Name"
        value={pipelineName}
        onChange={(e) => setPipelineName(e.target.value)}
      />

      <br /><br />

      <textarea
        rows="6"
        placeholder="Paste build log here"
        value={logContent}
        onChange={(e) => setLogContent(e.target.value)}
      />

      <br /><br />

      <button onClick={submit}>Analyze</button>

      {loading && <p className="loading">Analyzing log...</p>}
      {error && <p className="error">{error}</p>}
    </div>
  );
}
