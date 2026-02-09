import axios from "axios";

const API_BASE = process.env.REACT_APP_API_URL;

// ---------- LOG ANALYSIS ----------
export const analyzeLog = (data) =>
  axios.post(`${API_BASE}/api/logs/analyze`, data);

// ---------- TICKETS ----------
export const getTickets = async () => {
  const response = await axios.get(`${API_BASE}/api/tickets`);
  return response.data;
};

// ---------- DASHBOARD ----------
export const getHealthScore = async () => {
  const response = await axios.get(`${API_BASE}/api/dashboard/health`);
  return response.data;
};

export const getPrediction = async () => {
  const response = await axios.get(`${API_BASE}/api/dashboard/prediction`);
  return response.data;
};
