import React, { useState } from 'react';
import './Signin.css'; 
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const SignIn = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('jobSeeker');

  const navigate = useNavigate();

  const loginUser = (e) => {
    e.preventDefault();

    const loginData = { email, password };

    axios.post('http://localhost:8080/api/auth/login', loginData)
      .then(response => {
        const token = response.data.jwt;
        localStorage.setItem('token', token);
        localStorage.setItem('email', email);
        localStorage.setItem('role', role);

        toast.success('Login successful!', {
          position: "top-right"
        });
        setTimeout(() => {
          navigate('/');
          window.location.reload();
        }, 2000);
      })
      .catch(error => {
        console.error('Login error:', error);
        toast.error('Login failed! Please check your credentials.', {
          position: "top-right"
        });
      });
  };

  return (
    <div className="signin-container">
      <ToastContainer />
      <h1>Login</h1>
      <form onSubmit={loginUser}>
        <div className="input-container">
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="input-container">
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <div className="input-container">
          <label htmlFor="role">I am a:</label>
          <select
            id="role"
            value={role}
            onChange={(e) => setRole(e.target.value)}
            required
          >
            <option value="jobSeeker">Job Seeker</option>
            <option value="employer">Employer</option>
          </select>
        </div>
        <button type="submit" className="signin-button">Login</button>
      </form>
    </div>
  );
};

export default SignIn;
