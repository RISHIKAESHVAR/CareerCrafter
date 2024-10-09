import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './ViewApplication.css';

const ViewApplication = () => {
  const [postedJobs, setPostedJobs] = useState([]);
  const [applications, setApplications] = useState({});
  const [errorMessage, setErrorMessage] = useState("");
  const [isLoading, setIsLoading] = useState(true);
  const navigate = useNavigate();

  const employerId = localStorage.getItem("empid");
  const token = localStorage.getItem("token");

  useEffect(() => {
    fetchPostedJobs();
  }, []);

  const fetchPostedJobs = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/employer/${employerId}/jobListings`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setPostedJobs(response.data);
      response.data.forEach((job) => fetchApplicationsForJob(job.jobId));
      setIsLoading(false);
    } catch (error) {
      setErrorMessage("Failed to load posted jobs.");
      setIsLoading(false);
    }
  };

  const fetchApplicationsForJob = async (jobId) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/employer/applications/jobListing/${jobId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setApplications((prevApplications) => ({
        ...prevApplications,
        [jobId]: response.data,
      }));
    } catch (error) {
      console.error("Error fetching applications for job:", error);
    }
  };

  const downloadResume = async (applicationId) => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/employer/applications/${applicationId}/resume`,
        { headers: { Authorization: `Bearer ${token}` }, responseType: 'blob' }
      );
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", "resume.pdf");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    } catch (error) {
      console.error("Error downloading resume:", error);
    }
  };

  const updateApplicationStatus = async (applicationId, status) => {
    try {
      const response = await axios.put(
        `http://localhost:8080/api/employer/status/${applicationId}`,
        null,
        {
          params: { status },
          headers: { Authorization: `Bearer ${token}` },
        }
      );
      console.log(response.data);
      toast.success("Application status updated successfully!");
      setApplications((prevApplications) =>
        Object.keys(prevApplications).reduce((acc, jobId) => {
          acc[jobId] = prevApplications[jobId].map((app) =>
            app.applicationId === applicationId ? { ...app, status } : app
          );
          return acc;
        }, {})
      );
    } catch (error) {
      console.error("Error updating application status:", error);
      toast.error("Failed to update application status.");
    }
  };

  return (
    <div className="view-application-container">
      <ToastContainer />
      <h1>Applications for Your Posted Jobs</h1>
      {errorMessage && <p className="error-message">{errorMessage}</p>}

      {isLoading ? (
        <p>Loading posted jobs and applications...</p>
      ) : postedJobs.length === 0 ? (
        <p>No jobs posted yet.</p>
      ) : (
        postedJobs.map((job) => (
          <div key={job.jobId} className="job-applications">
            <h2>{job.jobTitle} - Applications</h2>
            {applications[job.jobId] && applications[job.jobId].length > 0 ? (
              <table className="applications-table">
                <thead>
                  <tr>
                    <th>Job Seeker Name</th>
                    <th>Email</th>
                    <th>Applied Date</th>
                    <th>Status</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {applications[job.jobId].map((application) => (
                    <tr key={application.applicationId}>
                      <td>{application.jobSeeker ? `${application.jobSeeker.firstName} ${application.jobSeeker.lastName}` : "N/A"}</td>
                      <td>{application.jobSeeker ? application.jobSeeker.email : "N/A"}</td>
                      <td>{new Date(application.appliedDate).toLocaleDateString()}</td>
                      <td>
                        <select
                          value={application.status || ''}
                          onChange={(e) => updateApplicationStatus(application.applicationId, e.target.value)}
                        >
                          <option value="" disabled>Status</option>
                          <option value="Approved">Approve</option>
                          <option value="Pending">Pending</option>
                          <option value="Rejected">Reject</option>
                        </select>
                      </td>
                      <td>
                        {application.jobSeeker ? (
                          <button onClick={() => downloadResume(application.applicationId)}>
                            Download Resume
                          </button>
                        ) : (
                          <span>Info Missing</span>
                        )}
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            ) : (
              <p>No applications for this job yet.</p>
            )}
          </div>
        ))
      )}
    </div>
  );
};

export default ViewApplication;
