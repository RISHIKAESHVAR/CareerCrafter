import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { Button, Confirm } from 'semantic-ui-react';
import './EmployeeDashboard.css';
import { ToastContainer, toast } from 'react-toastify';

const EmployeeDashboard = () => {
    const [postedJobs, setPostedJobs] = useState([]);
    const [errorMessage, setErrorMessage] = useState('');
    const [empId, setEmpId] = useState('');
    const [open, setOpen] = useState(false);
    const [jobIdToDelete, setJobIdToDelete] = useState(null);
    const navigate = useNavigate();
    const token = localStorage.getItem('token');
    const storedEmail = localStorage.getItem('email');

    useEffect(() => {
        if (!token) {
            navigate('/login');
        } else if (storedEmail) {
            fetchEmployeeId(storedEmail);
        }
    }, [token, navigate, storedEmail]);

    const fetchEmployeeId = async (email) => {
        try {
            const response = await axios.get(`http://localhost:8080/api/employer/getallemployees?email=${email}`, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                },
            });

            if (response.data.length > 0) {
                const employee = response.data[0];

                setEmpId(employee.empid); 
                localStorage.setItem('empid', employee.empid);
                fetchPostedJobs(employee.empid);
            } else {
                setErrorMessage('No employee found with the given email.');
            }
        } catch (error) {
            console.error('Error fetching employee ID:', error);
            setErrorMessage('Failed to load employee data.');
        }
    };

    const fetchPostedJobs = async (empId) => {
        if (!empId) return;

        try {
            const response = await axios.get(`http://localhost:8080/api/employer/${empId}/jobListings`, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                },
            });
            setPostedJobs(response.data);
        } catch (error) {
            console.error('Error fetching posted jobs:', error);
            setErrorMessage('Failed to load posted jobs.');
        }
    };

    const handleDelete = async (jobId) => {
        try {
            await axios.delete(`http://localhost:8080/api/employer/removeJobs/${jobId}`, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                },
            });
            setPostedJobs((prevJobs) => prevJobs.filter((job) => job.jobId !== jobId));
            setOpen(false);
            toast.success('Job deleted successfully!');
        } catch (error) {
            console.error('Error deleting job:', error);
            setErrorMessage('Failed to delete job.');
        }
    };

    const showConfirm = (jobId) => {
        setJobIdToDelete(jobId);
        setOpen(true);
    };

    return (
        <div className="container mt-4">
            <h2>Employee Dashboard</h2>
            {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}

            <table className="table">
                <thead>
                    <tr>
                        <th>Job Title</th>
                        <th>Description</th>
                        <th>Location</th>
                        <th>Salary</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {postedJobs.length === 0 ? (
                        <tr>
                            <td colSpan="5">No jobs posted yet.</td>
                        </tr>
                    ) : (
                        postedJobs.map((job) => (
                            <tr key={job.jobId}>
                                <td>{job.jobTitle}</td>
                                <td>{job.jobDescription}</td>
                                <td>{job.location}</td>
                                <td>{job.salaryRange}</td>
                                <td>
                                    <button
                                        className="btn btn-primary mr-2"
                                        onClick={() => navigate(`/update-job/${job.jobId}`)}
                                    >
                                        Update
                                    </button>
                                    <button
                                        className="btn btn-danger"
                                        onClick={() => showConfirm(job.jobId)}
                                    >
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        ))
                    )}
                </tbody>
            </table>

            <Confirm
                open={open}
                onCancel={() => setOpen(false)}
                onConfirm={() => handleDelete(jobIdToDelete)}
                content="Are you sure you want to delete the job?"
                cancelButton="No"
                confirmButton="Yes"
            />
            <ToastContainer />
        </div>
    );
};

export default EmployeeDashboard;
