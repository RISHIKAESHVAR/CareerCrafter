import { Route, Routes } from "react-router-dom";
import HomePage from "./Component/HomePage/HomePage"; 
import About from "./Component/About/About";
import Joblisting from "./Component/Joblisting/Joblisting";
import Contact from "./Component/Contact/Contact";
import SignUp from "./Component/Auth/SignUp";
import SignIn from "./Component/Auth/SignIn";
import Applyforjob from "./Component/Applyforjob/Applyforjob";
import Profile from "./Component/Profile/Profile";
import Postjob from "./Component/PostJob/Postjob";
import EmployeeDashboard from "./Component/EmployeeDashboard/EmployeeDashboard";
import UpdateJob from "./Component/Updatejob/Updatejob";
import EmployeeProfile from "./Component/EmployeeProfile/EmployeeProfile";
import ManageApplication from "./Component/ManageApplication/ManageApplication";
import ViewApplication from "./Component/ViewApplication/ViewApplication";

const JRoutes = () => {
    const role = localStorage.getItem('role');

    return (
        <Routes>
        <Route path="/" element={<HomePage />} />
        {role === 'jobSeeker' && (
            <Route path="/job-listings" element={<Joblisting />} />
        )}
        <Route path="/apply/:jobId" element={<Applyforjob />} />
        <Route path="/manage-application" element={<ManageApplication/>}/>
        <Route path="/view-applications" element={<ViewApplication/>}/>
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
        <Route path="/signin" element={<SignIn />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/post-job" element={<Postjob />} />
        <Route path="/employee-dashboard" element={<EmployeeDashboard />} />
        <Route path="/update-job/:jobId" element={<UpdateJob />} />
        {role === 'employer' ? (
            <Route path="/employer-profile" element={<EmployeeProfile />} />
        ) : (
            <Route path="/jobseeker-profile" element={<Profile />} />
        )}
    </Routes>
    );
};

export default JRoutes;
