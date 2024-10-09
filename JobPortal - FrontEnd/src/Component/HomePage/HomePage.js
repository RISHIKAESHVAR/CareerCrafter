import React, { useState, useEffect } from 'react';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import './Homepage.css';
import Footer from '../../Footer';
import axios from 'axios';
import { Loader } from 'semantic-ui-react';
import { useNavigate } from 'react-router-dom';

const HomePage = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [featuredJobs, setFeaturedJobs] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const token = localStorage.getItem('token');
    const navigate = useNavigate();

    const companyLogos = [
        { src: "hexaware.png", alt: "Hexaware Technologies", name: "Hexaware Technologies", vision: "Our vision is to drive business success through innovative technology solutions and continuous improvement." },
        { src: "tcs.png", alt: "TCS", name: "Tata Consultancy Service", vision: "Our vision is to achieve sustainable growth by embedding green practices in all operations and helping customers do the same." },
        { src: "cts.png", alt: "Cognizant", name: "Cognizant", vision: "Every choice we make aligns to our vision: to become the pre-eminent technology services partner to the world’s top companies." },
        { src: "apple.png", alt: "apple", name: "Apple", vision: "To bring the best user experience to customers through innovative hardware, software, and services." },
        { src: "amazon.png", alt: "Amazon", name: "Amazon", vision: "Our vision is to be Earth's most customer-centric company, offering everything online at the lowest prices." },
        { src: "google.png", alt: "Google", name: "Google", vision: "Our vision is to provide access to the world's information in one click." },
        { src: "intel.png", alt: "Intel", name: "Intel", vision: "Our vision is to shape the future of technology to create a better world for all." },
        { src: "hp.png", alt: "HP", name: "HP", vision: "Our vision is to create technology that makes life better for everyone, everywhere." }
    ];

    const reviews = [
        { text: "A premier destination for companies seeking the best talent.", author: "Elon Musk", img: "musk.jpeg" },
        { text: "A great platform for hiring the best talent.", author: "Ratan Tata", img: "Tata.jpeg" },
        { text: "Thanks to Careercrafter, we found the perfect candidate for our team!", author: "Sundar Pichai", img: "pichai.jpeg" },
        { text: "Exceptional service! The candidates we found were top-notch and fit our needs.", author: "Mark Zuckerberg", img: "mark.jpeg" },
        { text: "Careercrafter has simplified our hiring process significantly. Highly satisfied!", author: "Sridhar Vembu", img: "sridar.jpeg" }
    ];

    const companySettings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 4,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 2000,
    };

    const jobSettings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 2,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 3000,
    };

    const reviewSettings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 4000,
    };

    

    const handleSearch = (event) => {
        event.preventDefault();
        console.log("Search Query:", searchQuery);
    };

    useEffect(() => {
        const fetchFeaturedJobs = async () => {
            if (token) {
                setLoading(true);
                try {
                    const response = await axios.get('http://localhost:8080/api/jobseeker/getJobs', {
                        headers: {
                            'Authorization': `Bearer ${token}`,
                        },
                    });
                    setFeaturedJobs(response.data);
                } catch (error) {
                    setError('Failed to fetch jobs');
                    console.error(error);
                } finally {
                    setLoading(false);
                }
            }
        };

        fetchFeaturedJobs();
    }, [token]);

    return (
        <div className="homepage-container">
            <header className="home-hero-section">
                <h1>Find Your Dream Job</h1>
                <h3>Explore thousands of job opportunities at top companies</h3>
            </header>

            <main>
                <section className="vision">
                    <h1 className="centered-heading">Our Vision</h1>
                    <h3>To connect job seekers with their dream jobs and help companies find the right talent.</h3>
                </section>

                <section className="carousel-section">
                    <h1 className="centered-heading">Featured Jobs</h1>
                    {loading ? (
                        <div className="loading">
                            <Loader active inline="centered" />
                        </div>
                    ) : error ? (
                        <div className="error">{error}</div>
                    ) : !token ? (
                        <div className="home-login-prompt">
                            <h2>To show the featured jobs please login.</h2>
                            <button onClick={() => navigate('/signin')} className="home-login-button">Login</button>
                        </div>
                    ) : (
                        <Slider {...jobSettings}>
                            {featuredJobs.length > 0 ? (
                                featuredJobs.map((job) => (
                                    <div key={job.jobId} className="home-job-card">
                                        <h3 className="home-job-title">{job.jobTitle}</h3>
                                        <p className="home-company-name">{job.company}</p>
                                        <p className="home-job-description">{job.jobDescription}</p>
                                        <p className="home-job-location">{job.location}</p>
                                    </div>
                                ))
                            ) : (
                                <p>No featured jobs available.</p>
                            )}
                        </Slider>
                    )}
                </section>

                <section className="companies">
                    <h1 className="centered-heading">Companies they post job in this application</h1>
                    <Slider {...companySettings}>
                        {companyLogos.map((logo, index) => (
                            <div key={index} className="company-logo-container">
                                <img src={logo.src} alt={logo.alt} className="company-logo" />
                                <div className="company-info"> 
                                    <h3>{logo.name}</h3>  
                                    <p>{logo.vision}</p>
                                </div>
                            </div>
                        ))}
                    </Slider>
                </section>

                <section className="reviews">
                    <h1 className="centered-heading">What Our Users Say</h1>
                    <Slider {...reviewSettings}>
                        {reviews.map((review, index) => (
                            <div key={index} className="review-card">
                                <div className="review">
                                    <div className="review-img-container">
                                        <img src={review.img} alt={review.author} className="review-img" />
                                    </div>
                                </div>
                                <p className="review-text">"{review.text}"</p>
                                <p className="review-author">- {review.author}</p>
                            </div>
                        ))}
                    </Slider>
                </section>
            </main>

            <footer>
                <Footer className="footer fade-in" />
            </footer>
        </div>
    );
};

export default HomePage;
