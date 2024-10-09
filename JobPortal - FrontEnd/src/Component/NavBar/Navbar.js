import { Link } from 'react-router-dom';
import { FaUserCircle } from 'react-icons/fa';
import './Navbar.css';
import Logout from '../Auth/Logout';
import React, { useState, useEffect } from 'react';




const Navbar = () => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [role, setRole] = useState('');


    useEffect(() => {
        const token = localStorage.getItem('token');
        const storedRole = localStorage.getItem('role');
        setRole(storedRole);
        if (token) {
            setIsAuthenticated(true);
        }
    }, []);


    return (
        <nav className="navbar">
            <div className="navbar-container">
                <Link to="/" className="navbar-logo">
                    <img src="logo.png" alt="CareerCrafter Logo" className="navbar-logo-image" />
                    <span className="company-name">CareerCrafter</span>
                </Link>
                <ul className="navbar-menu">
                    <li className="navbar-item"><Link to="/">Home</Link></li>
                    {isAuthenticated && role === 'jobSeeker' && (
                        <li className="navbar-item"><Link to="/job-listings">Jobs</Link></li>
                    )}
                    <li className="navbar-item"><Link to="/about">About</Link></li>
                    <li className="navbar-item"><Link to="/contact">Contact</Link></li>
                    {isAuthenticated && (
                        <>
                            {role === 'employer' && (
                                <>
                                    <li className="navbar-item">
                                        <Link to="/post-job" className="post-job-button">Post Job</Link>
                                    </li>
                                    <li className="navbar-item">
                                        <Link to="/employee-dashboard">Employee Dashboard</Link>
                                    </li>
                                    <li className="navbar-item">
                                        <Link to ="/view-applications">Applications</Link>
                                    </li>
                                </>
                            )}
                            <li className="navbar-item">
                                <Link to={`/${role === 'employer' ? 'employer-profile' : 'jobseeker-profile'}`} className="profile-icon">
                                    <FaUserCircle size={24} />
                                </Link>
                            </li>

                            
                        </>
                    )}

                    {isAuthenticated && role === 'jobSeeker' && (
                 
                        <li className="navbar-item"><Link to="/manage-application">Applied Jobs</Link></li>
                
                    )}
                </ul>
                <div className="navbar-buttons">
                    {!isAuthenticated ? (
                        <>
                            <Link to="/signup" className="register-button">Sign Up</Link>
                            <Link to="/signin" className="login-button">Log In</Link>
                        </>
                    ) : (
                        <Logout setIsAuthenticated={setIsAuthenticated} />
                    )}
                </div>
            </div>
        </nav>
    );
};


export default Navbar;
