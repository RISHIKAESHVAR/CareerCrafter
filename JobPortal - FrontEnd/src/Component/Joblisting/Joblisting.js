import React, { useEffect, useState } from "react";
import axios from "axios";
import './joblisting.css';
import { useNavigate } from "react-router-dom";

const Joblisting = () => {
  const [jobListing, setJoblisting] = useState([]);
  const [appliedJobs, setAppliedJobs] = useState(new Set());
  const [location, setLocation] = useState("");
  const [jobTitle, setJobTitle] = useState("");
  const [company, setCompany] = useState("");
  const [filteredJobs, setFilteredJobs] = useState([]);
  const [jobSeekerId, setJobSeekerId] = useState(null);
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    fetchJobSeekerId();
    fetchJobs();
  }, []);

  useEffect(() => {
    setFilteredJobs(jobListing);
  }, [jobListing]);

  const fetchJobSeekerId = async () => {
    const token = localStorage.getItem('token');
    const email = localStorage.getItem('email');

    if (!email) {
      setErrorMessage("Email not found. Please log in.");
      return;
    }

    try {
      const response = await axios.get(`http://localhost:8080/api/jobseeker/getalljobseeker?email=${email}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (response.data.length > 0) {
        const jobSeeker = response.data[0];
        setJobSeekerId(jobSeeker.jobSeekerId);
        localStorage.setItem('jobSeekerId', jobSeeker.jobSeekerId);
        fetchAppliedJobs(jobSeeker.jobSeekerId);
      } else {
        setErrorMessage("No job seeker found with the given email.");
      }
    } catch (error) {
      setErrorMessage("Failed to load job seeker data.");
    }
  };

  // Fetch all jobs
  const fetchJobs = () => {
    const token = localStorage.getItem('token');
    axios.get("http://localhost:8080/api/jobseeker/getJobs", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then(response => setJoblisting(response.data))
    .catch(error => console.error("There was an error fetching the jobs!", error));
  };

  const fetchAppliedJobs = (jobSeekerId) => {
    const token = localStorage.getItem('token');
    axios.get(`http://localhost:8080/api/jobseeker/applications/jobseekers/${jobSeekerId}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then(response => {
      const appliedJobIds = response.data.map(application => application.jobListing.jobId);
      setAppliedJobs(new Set(appliedJobIds));
    })
    .catch(error => console.error("Error fetching applied jobs!", error));
  };

  const handleApplyClick = (jobId) => {

    if (appliedJobs.has(jobId)) {
      alert("You have already applied for this job.");
      return;
    }
    navigate(`/apply/${jobId}`);
  };

  const handleSearch = () => {
    const token = localStorage.getItem('token');
    let searchUrl = `http://localhost:8080/api/jobseeker/SearchJobs?`;

    if (location) searchUrl += `location=${encodeURIComponent(location)}&`;
    if (jobTitle) searchUrl += `jobTitle=${encodeURIComponent(jobTitle)}&`;
    if (company) searchUrl += `company=${encodeURIComponent(company)}`;

    axios.get(searchUrl, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then(response => setFilteredJobs(response.data))
    .catch(error => console.error("Error searching for jobs:", error));
  };

  return (
    <div className="job-listing-container">
      <h1>Job Listings</h1>

      {errorMessage && <p className="error-message">{errorMessage}</p>}

      <div className="search-form">
        <input
          type="text"
          placeholder="Location"
          value={location}
          onChange={e => setLocation(e.target.value)}
        />
        <input
          type="text"
          placeholder="Job Title"
          value={jobTitle}
          onChange={e => setJobTitle(e.target.value)}
        />
        <input
          type="text"
          placeholder="Company"
          value={company}
          onChange={e => setCompany(e.target.value)}
        />
        <button onClick={handleSearch}>Search</button>
      </div>

      {filteredJobs.length === 0 ? (
        <p>No jobs to display.</p>
      ) : (
        <div className="job-cards">
          {filteredJobs.map(job => (
            <div key={job.jobId} className="job-card">
              <h2>{job.jobTitle}</h2>
              <h3>{job.company}</h3>
              <p>{job.jobDescription}</p>
              <p><strong>Location:</strong> {job.location}</p>
              <p><strong>Salary:</strong> {job.salaryRange}</p>
              <div className="job-apply-container">
                <button
                  className="job-apply-button"
                  onClick={() => handleApplyClick(job.jobId)}
                  disabled={appliedJobs.has(job.jobId)}
                >
                  {appliedJobs.has(job.jobId) ? "Applied" : "Apply Now"}
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Joblisting;
