import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './EmployeeProfile.css';
import { FaUser, FaSave, FaCheckCircle } from 'react-icons/fa';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const EmployeeProfile = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    companyName: '',
    companyDescription: '',
    email: '',
    contactNumber: '',
    address: '',
    website: '',
  });
  const [isProfileCreated, setIsProfileCreated] = useState(false);
  const [isUpdating, setIsUpdating] = useState(false);

  const token = localStorage.getItem('token');
  const email = localStorage.getItem('email');
  console.log(email);

  useEffect(() => {
    if (email) {
      axios
        .get(`http://localhost:8080/api/employer/getEmployeebyemail/${email}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((response) => {
          if (response.data) {
            setFormData(response.data);
            setIsUpdating(true);
          }
        })
        .catch((error) => {
          console.error('Error fetching employee profile:', error);
        });
    }
  }, [email, token]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (isUpdating) {
      axios
        .put(`http://localhost:8080/api/employer/updateprofilebyemail/${email}`, null, {
          params: {
            companyName: formData.companyName,
            companyDescription: formData.companyDescription,
            contactNumber: formData.contactNumber,
            address: formData.address,
            website: formData.website,
            empid: formData.empid,
          },
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((response) => {
          console.log('Profile updated successfully', response.data);
          toast.success('Profile updated successfully!', {
            position: 'top-right'
          });
          setIsProfileCreated(true);
          setTimeout(() => {
            navigate('/');
          }, 2000);
        })
        .catch((error) => {
          console.error('Error updating profile:', error);
          toast.error('Error updating profile!', {
            position: 'top-right'
          });
        });
    } else {
      axios
        .post('http://localhost:8080/api/employer/addEmployeeProfile', formData, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((response) => {
          console.log('Profile created successfully', response.data);

          const createdJobSeekerId = response.data.jobSeekerId;
          localStorage.setItem('jobSeekerId', createdJobSeekerId);

          toast.success('Profile created successfully!', {
            position: 'top-center'
          });

          setIsProfileCreated(true);
          setTimeout(() => {
            navigate('/');
          }, 2000);
        })
        .catch((error) => {
          console.error('Error creating profile:', error);
          toast.error('Error creating profile!', {
            position: 'top-center'
          });
        });
    }
  };

  return (
    <div className="profile-form-container">
      <ToastContainer />
      <h2>
        <FaUser /> {isUpdating ? 'Update your Profile' : 'Create your Profile'}
      </h2>
      <form onSubmit={handleSubmit}>
        <input
          className="profile-input"
          type="text"
          name="companyName"
          placeholder="Company Name"
          value={formData.companyName}
          onChange={handleChange}
          required
        />
        <input
          className="profile-input"
          type="text"
          name="companyDescription"
          placeholder="Company Description"
          value={formData.companyDescription}
          onChange={handleChange}
          required
        />
        <input
          className="profile-input"
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
          required
        />
        <input
          className="profile-input"
          type="text"
          name="contactNumber"
          placeholder="Contact Number"
          value={formData.contactNumber}
          onChange={handleChange}
          required
        />
        <input
          className="profile-input"
          type="text"
          name="address"
          placeholder="Address"
          value={formData.address}
          onChange={handleChange}
          required
        />
        <input
          className="profile-input"
          type="text"
          name="website"
          placeholder="Company Website"
          value={formData.website}
          onChange={handleChange}
          required
        />
        <button type="submit" className="profile-button">
          <FaSave /> {isUpdating ? 'Update Profile' : 'Create Profile'}
        </button>
      </form>
      {isProfileCreated && (
        <div className="tick-mark">
          <FaCheckCircle size={30} color="green" />
          <span>{isUpdating ? 'Profile Updated Successfully!' : 'Profile Created Successfully!'}</span>
        </div>
      )}
    </div>
  );
};

export default EmployeeProfile;
