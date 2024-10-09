import React, { useState } from 'react';
import './Signup.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const SignUp = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [role, setRole] = useState('jobseeker');
  const navigate = useNavigate();

  const signupUser = async (e) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      toast.error('Passwords do not match!');
      return;
    }

    const signupData = {
      email: email,
      password: password,
      name: username,
      userRole: role === 'jobseeker' ? 1 : 0
    };

    try {
      const response = await axios.post('http://localhost:8080/api/auth/signup', signupData);
      toast.success('Signup successful!', {
        onClose: () => navigate('/signin')
      });
    } catch (error) {
      console.error('There was an error signing up!', error);
      toast.error('Signup failed! Please try again.');
    }
  };

  return (
    <div className="signup-container">
      <h1 className="centered-heading">Register</h1>
      <form onSubmit={signupUser}>
        <div className="signup-input-container">
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="signup-input-container">
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="signup-input-container">
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <div className="signup-input-container">
          <input
            type="password"
            placeholder="Confirm Password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            required
          />
        </div>
        <div className="signup-input-container">
          <select value={role} onChange={(e) => setRole(e.target.value)} required>
            <option value="jobseeker">Job Seeker</option>
            <option value="employer">Employer</option>
          </select>
        </div>
        <button type="submit" className="signup-button">Register</button>
      </form>
      <ToastContainer position="top-right" autoClose={3000} hideProgressBar={false} newestOnTop closeOnClick rtl={false} pauseOnFocusLoss draggable pauseOnHover />
    </div>
  );
};

export default SignUp;
