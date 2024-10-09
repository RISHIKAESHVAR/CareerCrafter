import React from 'react';
import './Footer.css';

const Footer = () => {
    return (
        <footer className="footer">
            <div className="footer-content">
                <h2 className="footer-title">Careercrafter</h2>
                <p className="footer-text">Connecting job seekers with opportunities.</p>
                <div className="footer-links">
                    <a href="/about" className="footer-link">About Us</a>
                    <a href="/contact" className="footer-link">Contact</a>
                </div>
            </div>
            <div className="footer-bottom">
                <p>© 2024 Careercrafter. All rights reserved.</p>
            </div>
        </footer>
    );
};

export default Footer;
