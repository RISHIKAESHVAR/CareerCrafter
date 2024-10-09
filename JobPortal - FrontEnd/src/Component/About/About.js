import React from 'react';
import { Container, Header, Segment, List } from 'semantic-ui-react';
import './About.css';

const About = () => {
    return (
        <Container className="about-us-container">
            <Header as='h1' textAlign='center' className="about-us-header">
                About Us
            </Header>

            <Segment raised className="about-us-segment">

                <Header as='h2' textAlign='center'>Our Mission</Header>
                <p>
                    At Careercrafter, our mission is to connect job seekers with their dream careers. We strive to empower individuals by providing them with the resources and support they need to succeed in the job market.
                </p>
                <p>
                    We focus on innovation and excellence, ensuring that we remain a trusted partner for both job seekers and employers. Our goal is to create meaningful connections that lead to fulfilling careers.
                </p>
            </Segment>

            <Segment raised className="about-us-segment">
                <Header as='h2' >Our History</Header>
                <p>
                    Founded in 2020, Careercrafter has been committed to revolutionizing the job search experience. Our journey began with a small team of passionate individuals dedicated to bridging the gap between talent and opportunity.
                </p>
                <p>
                    Over the years, we have grown our platform, expanded our services, and fostered a community of job seekers and employers. Our history is defined by our unwavering commitment to helping people find meaningful work.
                </p>
            </Segment>

            
            <Segment raised className="about-us-segment">
    <Header as='h2' textAlign='center'>Our Services</Header>
    <p>
        We offer a variety of services tailored to meet the diverse needs of job seekers and employers:
    </p>
    <List className="custom-list" bulleted>
        <List.Item>Job Listings</List.Item>
        <List.Item>Resume Writing Assistance</List.Item>
        <List.Item>Interview Coaching</List.Item>
        <List.Item>Networking Opportunities</List.Item>
        <List.Item>Employer Partnerships</List.Item>
    </List>
    <p>
        Our services are designed to help job seekers leverage their skills and stand out in the competitive job market.
    </p>
</Segment>


            <Segment raised className="about-us-segment">
                <Header as='h2' textAlign='center'>Testimonials</Header>
                <p>
                    Here’s what our clients have to say about their experiences with Careercrafter:
                </p>
                <blockquote>
                    <p>
                        "Careercrafter helped me land my dream job! Their support and resources were invaluable." - <strong>Client A</strong>
                    </p>
                </blockquote>
                <blockquote>
                    <p>
                        "The team at Careercrafter is amazing! They guided me through every step of my job search." - <strong>Client B</strong>
                    </p>
                </blockquote>
            </Segment>
        </Container>
    );
};

export default About;