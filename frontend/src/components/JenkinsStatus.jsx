export default function JenkinsStatus() {
  return (
    <div className="card">
      <h3>CI Pipeline Status</h3>
      <ul>
        <li>Checkout : Success</li>
        <li>Build : Success</li>
        <li>Test : Failed</li>
        <li>Docker : Pending</li>
      </ul>
    </div>
  );
}
