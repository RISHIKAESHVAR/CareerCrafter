import './manageapplication.css';
import React, { useEffect, useState } from "react";
import axios from "axios";

const ManageApplication = () => {
  const [applications, setApplications] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");
  const token = localStorage.getItem("token");
  const email = localStorage.getItem("email");

  useEffect(() => {
    const fetchJobSeekerId = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/jobseeker/getalljobseeker?email=${email}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        const jobSeeker = response.data[0];
        const jobSeekerId = jobSeeker ? jobSeeker.jobSeekerId : null;

        if (jobSeekerId) {
          localStorage.setItem("jobSeekerId", jobSeekerId);
          fetchApplications(jobSeekerId);
        } else {
          setErrorMessage("Job seeker ID is not found in the response.");
        }
      } catch (error) {
        console.error("Error fetching job seeker ID:", error);
        setErrorMessage("There was an error fetching your job seeker details.");
      }
    };

    const fetchApplications = async (jobSeekerId) => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/jobseeker/applications/jobseekers/${jobSeekerId}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setApplications(response.data);
      } catch (error) {
        console.error("Error fetching applications:", error);
        setErrorMessage("There was an error fetching your applications.");
      }
    };

    fetchJobSeekerId();
  }, [email, token]);

  const getStatusClassName = (status) => {
    switch (status) {
      case "Approved":
        return "status-approved";
      case "Pending":
        return "status-pending";
      case "Rejected":
        return "status-rejected";
      default:
        return "";
    }
  };

  return (
    <div className="manage-application-container">
      <h1>Your Applications</h1>
      {errorMessage && <p className="error-message">{errorMessage}</p>}
      {applications.length === 0 ? (
        <p>No applications found.</p>
      ) : (
        <table className="application-table">
          <thead>
            <tr>
              <th>Job ID</th>
              <th>Job Title</th>
              <th>Company</th>
              <th>Status</th>
              <th>Applied Date</th>
            </tr>
          </thead>
          <tbody>
            {applications.map((application) => (
              <tr key={application.applicationId}>
                <td>{application.jobListing.jobId}</td>
                <td>{application.jobListing.jobTitle}</td>
                <td>{application.jobListing.company}</td>
                <td className={getStatusClassName(application.status)}>{application.status}</td>
                <td>{application.appliedDate}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default ManageApplication;
