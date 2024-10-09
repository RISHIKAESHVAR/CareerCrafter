import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import './Updatejob.css';

const Updatejob = () => {
    const { jobId } = useParams();
    const [jobDetails, setJobDetails] = useState({
        jobTitle: '',
        company: '',
        jobDescription: '',
        location: '',
        requirements: '',
        salaryRange: '',
        empid: '',
    });
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate();
    const token = localStorage.getItem('token');

    useEffect(() => {
        const fetchJobDetails = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/employer/getJobsbyid/${jobId}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    },
                });
                setJobDetails(response.data);
            } catch (error) {
                console.error('Error fetching job details:', error);
                setErrorMessage('Failed to load job details.');
            }
        };

        fetchJobDetails();
    }, [jobId, token]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setJobDetails((prevDetails) => ({
            ...prevDetails,
            [name]: value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log('Job details before submission:', jobDetails);

        if (!jobDetails.jobTitle || !jobDetails.company || !jobDetails.jobDescription ||
            !jobDetails.location || !jobDetails.requirements || !jobDetails.salaryRange) {
            setErrorMessage('All fields are required.');
            return;
        }

        try {
            const response = await axios.put(`http://localhost:8080/api/employer/updatedatabyjobid/${jobId}`, null, {
                params: {
                    jobTitle: jobDetails.jobTitle,
                    company: jobDetails.company,
                    jobDescription: jobDetails.jobDescription,
                    location: jobDetails.location,
                    requirements: jobDetails.requirements,
                    salaryRange: jobDetails.salaryRange,
                },
                headers: {
                    'Authorization': `Bearer ${token}`,
                },
            });
            console.log('Job updated successfully:', response.data);
            navigate('/employee-dashboard');
        } catch (error) {
            console.error('Error updating job:', error.response ? error.response.data : error);
            setErrorMessage('Failed to update job: ' + (error.response?.data?.detail || 'Unknown error.'));
        }
    };

    return (
        <div className="update-job-container">
            <h2>Update Job</h2>
            {errorMessage && <div className="error-message">{errorMessage}</div>}
            <form onSubmit={handleSubmit} className="update-job-form">
                <input
                    type="text"
                    name="jobTitle"
                    value={jobDetails.jobTitle}
                    onChange={handleChange}
                    placeholder="Job Title"
                    required
                />
                <input
                    type="text"
                    name="company"
                    value={jobDetails.company}
                    onChange={handleChange}
                    placeholder="Company"
                    required
                />
                <textarea
                    name="jobDescription"
                    value={jobDetails.jobDescription}
                    onChange={handleChange}
                    placeholder="Job Description"
                    required
                />
                <input
                    type="text"
                    name="location"
                    value={jobDetails.location}
                    onChange={handleChange}
                    placeholder="Location"
                    required
                />
                <input
                    type="text"
                    name="requirements"
                    value={jobDetails.requirements}
                    onChange={handleChange}
                    placeholder="Requirements"
                    required
                />
                <input
                    type="number"
                    name="salaryRange"
                    value={jobDetails.salaryRange}
                    onChange={handleChange}
                    placeholder="Salary Range"
                    required
                />
                <button type="submit">Update Job</button>
            </form>
        </div>
    );
};

export default Updatejob;
