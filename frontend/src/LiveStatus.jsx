import { useEffect, useState } from "react";
import SockJS from "sockjs-client";
import Stomp from "stompjs";

const LiveStatus = () => {
  const [status, setStatus] = useState([]);

  useEffect(() => {
    const socket = new SockJS("http://localhost:8080/ws");
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, () => {
      stompClient.subscribe("/topic/pipeline", (message) => {
        const data = JSON.parse(message.body);
        setStatus(prev => [...prev, data]);
      });
    });
  }, []);

  return (
    <div>
      <h3>Live Pipeline Status</h3>
      {status.map((s, i) => (
        <p key={i}>{s.pipeline} - {s.status}</p>
      ))}
    </div>
  );
};

export default LiveStatus;