import { Navigate } from "react-router-dom";
 
const ProtectedRoute = ({ children }) => {
 
 
  const isAuthenticated = localStorage.getItem('userData')
 
 
  return isAuthenticated ? children : <Navigate to="/Signin" />;
};
 
export default ProtectedRoute;