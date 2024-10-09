import React from 'react';
import { FaPhone, FaEnvelope, FaMapMarkerAlt } from 'react-icons/fa';
import './Contact.css';

const Contact = () => {
  return (
    <div className="contact-container">
      <div className="contact-wrapper">
        <div className="contact-info">
          <h1>Contact for any Query</h1>
          <div className="info-card">
            <div className="icon"><FaMapMarkerAlt /></div>
            <h3>Address</h3>
            <p>1234 Street Name, City, State, ZIP</p>
          </div>
          <div className="info-card">
            <div className="icon"><FaEnvelope /></div>
            <h3>Email</h3>
            <p>info@example.com</p>
          </div>
          <div className="info-card">
            <div className="icon"><FaPhone /></div>
            <h3>Phone</h3>
            <p>+1 234 567 890</p>
          </div>
        </div>

        <div className="contact-form">
          <h2>Send Us a Message</h2>
          {/* Update the action URL with your actual email address */}
          <form action="https://formsubmit.co/rishikaesh14@gmail.com" method="POST">
            <div className="form-group">
              <input type="text" name="name" placeholder="Your Name" required />
            </div>
            <div className="form-group">
              <input type="email" name="email" placeholder="Your Email" required />
            </div>
            <div className="form-group">
              <textarea name="message" placeholder="Your Message" required></textarea>
            </div>
            <button type="submit" className="submit-button">Submit</button>

            
          </form>
        </div>

        <div className="map">
          <h2>Our Location</h2>
          <iframe
            title="Google Map"
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3151.8354345092923!2d144.95373531531812!3d-37.81720997975135!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x6ad642af0f0d6f35%3A0x5026aa6c1b8960bc!2sYour%20Location%20Here!5e0!3m2!1sen!2sus!4v1614081551357!5m2!1sen!2sus"
            width="600"
            height="295"
            style={{ border: 0 }}
            allowFullScreen=""
            loading="lazy"
          ></iframe>
        </div>
      </div>
    </div>
  );
};

export default Contact;