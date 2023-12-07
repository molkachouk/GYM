import React, {useState, useEffect} from 'react';
import { Container, Nav, Navbar } from 'react-bootstrap';
import {Outlet} from 'react-router-dom';
import axios from "axios";

export default function CustomerHome() {

    axios
        .get('http://localhost:8081/test/customer/viewGyms')
        .then(response => {
            localStorage.setItem('gyms', JSON.stringify(response.data));
        });
    axios
        .get('http://localhost:8081/test/customer/viewPrograms')
        .then(response => {
            localStorage.setItem('allPrograms', JSON.stringify(response.data));
        })
    axios
        .get('http://localhost:8081/test/customer/viewTrainers')
        .then(response => {
            localStorage.setItem('trainers', JSON.stringify(response.data));
        })
    axios
        .get('http://localhost:8081/test/customer/viewAppointments')
        .then(response => {
            localStorage.setItem('appointments', JSON.stringify(response.data));
        })

    return (
        <div>
            <Navbar bg="dark" variant="dark">
                <Container>
                <Navbar.Brand href="/customer/">Customer</Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link href="/customer/viewGyms">View Gyms</Nav.Link>
                        <Nav.Link href="/customer/viewPrograms">View Training Programs</Nav.Link>
                        <Nav.Link href="/customer/viewTrainers">View Trainers</Nav.Link>
                        <Nav.Link href="/customer/makeAppointment">Make Appointment</Nav.Link>
                        <Nav.Link href="/customer/viewAppointments">View Appointments</Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
            <Outlet />
        </div>
    );
}