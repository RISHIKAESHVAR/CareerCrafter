import React, { useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import { useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './applyforjobs.css';


const Applyforjob = () => {
  const { jobId } = useParams();
  const [resume, setResume] = useState(null); 
  const jobSeekerId = localStorage.getItem("jobSeekerId");
  const token = localStorage.getItem("token");
  const navigate = useNavigate();

  const handleFileChange = (e) => {
    setResume(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!resume) {
      toast.error("Please upload your resume.", {
        position: "top-right"
      });
      return;
    }

    const formData = new FormData();
    formData.append("resume", resume);
    formData.append("jobSeekerId", jobSeekerId);
    formData.append("jobId", jobId);
    console.log([...formData]);

    try {
      const response = await axios.post("http://localhost:8080/api/jobseeker/applications/upload", formData, {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "multipart/form-data",
        },
      });

      toast.success('Applied Successful', {
        position: "top-right"
      });
      setTimeout(() => {
        navigate('/job-listings');
        window.location.reload();
      }, 5000);
      
    } catch (error) {
      console.error("Error submitting application:", error);
      toast.error("You have already applied to this job. You can't apply anymore.", {
        position: "top-right"
      });
    }
  };

  return (
    <div className="apply-job-container">
      <ToastContainer />
      <h1>Apply for Job</h1>
      <form onSubmit={handleSubmit} className="apply-form">
        <div className="form-group">
          <label htmlFor="resume">Upload Resume:</label>
          <input
            type="file"
            id="resume"
            onChange={handleFileChange}
            accept=".pdf,.doc,.docx"
          />
        </div>
        <button type="submit" className="submit-button">Submit Application</button>
      </form>
    </div>
  );
};

export default Applyforjob;
