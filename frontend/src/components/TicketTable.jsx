import React, { useEffect, useState } from "react";
import { getTickets } from "../api/api";

const TicketTable = () => {
  const [tickets, setTickets] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchTickets = async () => {
      try {
        const data = await getTickets();
        setTickets(data);
      } catch (err) {
        setError("Backend not reachable");
      } finally {
        setLoading(false);
      }
    };

    fetchTickets();
  }, []);

{tickets.length === 0 && (
  <tr><td colSpan="5"> No failures detected</td></tr>
)}


  if (loading) return <p>Loading tickets...</p>;
  if (error) return <p style={{ color: "red" }}>{error}</p>;

  return (
    <table border="1" width="100%">
      <thead>
        <tr>
          <th>ID</th>
          <th>Pipeline</th>
          <th>Failure Type</th>
          <th>Root Cause</th>
          <th>Status</th>
        </tr>
      </thead>
      <tbody>
        {tickets.map(ticket => (
          <tr key={ticket.id}>
            <td>{ticket.id}</td>
            <td>{ticket.pipelineName}</td>
            <td>{ticket.failureType}</td>
            <td>{ticket.rootCause}</td>
            <td>{ticket.status}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default TicketTable;
