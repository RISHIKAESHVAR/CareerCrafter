import React from 'react';
import { useNavigate } from 'react-router-dom';

const Logout = ({ setIsAuthenticated }) => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('userId');

    setIsAuthenticated(false);

    navigate('/');
  };

  return (
    <button onClick={handleLogout} className="logout-button">
      Logout
    </button>
  );
};

export default Logout;
