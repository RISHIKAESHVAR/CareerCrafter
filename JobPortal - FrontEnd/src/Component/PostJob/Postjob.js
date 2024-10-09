import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Postjob.css';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const Postjob = () => {
    const [jobTitle, setJobTitle] = useState('');
    const [company, setCompany] = useState('');
    const [jobDescription, setJobDescription] = useState('');
    const [location, setLocation] = useState('');
    const [requirements, setRequirements] = useState('');
    const [salaryRange, setSalaryRange] = useState('');
    const [empid, setEmpid] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!jobTitle || !company || !jobDescription || !location || !salaryRange || !empid) {
            setErrorMessage('All fields are required.');
            return;
        }

        const jobData = {
            jobTitle,
            company,
            jobDescription,
            location,
            requirements,
            salaryRange: parseFloat(salaryRange),
            postedDate: new Date().toISOString().split('T')[0],
            employer: {
                empid: parseInt(empid),
            },
        };

        const token = localStorage.getItem('token');

        if (!token) {
            setErrorMessage('You are not logged in. Please log in to post a job.');
            return;
        }

        try {
            const response = await axios.post('http://localhost:8080/api/employer/post', jobData, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,
                },
            });

            if (response.status === 201) {
                setJobTitle('');
                setCompany('');
                setJobDescription('');
                setLocation('');
                setRequirements('');
                setSalaryRange('');
                setEmpid('');

                setErrorMessage('');
                toast.success('Job posted successfully!', {
                    position: 'top-right'
                });

            }
        } catch (error) {
            console.error('Error posting job:', error);
            setErrorMessage('Failed to post job. Please try again.');
            toast.error('Failed to post job. Please try again.', {
                position: 'top-right'
            });
        }
    };

    return (
        <div className="post-job-container">
            <ToastContainer />
            <h2 className="post-job-title">Post a Job</h2>
            <form className="post-job-form" onSubmit={handleSubmit}>
                <div className="form-group">
                    <label className="label" htmlFor="jobTitle">Job Title:</label>
                    <input
                        type="text"
                        id="jobTitle"
                        className="input"
                        value={jobTitle}
                        onChange={(e) => setJobTitle(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label className="label" htmlFor="company">Company:</label>
                    <input
                        type="text"
                        id="company"
                        className="input"
                        value={company}
                        onChange={(e) => setCompany(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label className="label" htmlFor="jobDescription">Job Description:</label>
                    <textarea
                        id="jobDescription"
                        className="input textarea"
                        value={jobDescription}
                        onChange={(e) => setJobDescription(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label className="label" htmlFor="location">Location:</label>
                    <input
                        type="text"
                        id="location"
                        className="input"
                        value={location}
                        onChange={(e) => setLocation(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label className="label" htmlFor="requirements">Requirements:</label>
                    <textarea
                        id="requirements"
                        className="input textarea"
                        value={requirements}
                        onChange={(e) => setRequirements(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label className="label" htmlFor="salaryRange">Salary Range:</label>
                    <input
                        type="number"
                        id="salaryRange"
                        className="input"
                        value={salaryRange}
                        onChange={(e) => setSalaryRange(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label className="label" htmlFor="empid">Employer ID:</label>
                    <input
                        type="number"
                        id="empid"
                        className="input"
                        value={empid}
                        onChange={(e) => setEmpid(e.target.value)}
                    />
                </div>
                {errorMessage && <div className="error-message">{errorMessage}</div>}
                <button type="submit" className="submit-button">Post Job</button>
            </form>
        </div>
    );
};

export default Postjob;
