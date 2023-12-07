import React, {useState, useEffect} from 'react';
import { Container, Nav, Navbar } from 'react-bootstrap';
import {Outlet} from 'react-router-dom';
import axios from "axios";

export default function TrainerHome() {

    axios
        .get('http://localhost:8081/test/trainer/addProgram')
        .then(response => {
            localStorage.setItem('programCategories', JSON.stringify(response.data));
        });
    axios
        .get('http://localhost:8081/test/trainer/viewPrograms')
        .then(response => {
            localStorage.setItem('allPrograms', JSON.stringify(response.data));
        })

    return (
        <div>
            <Navbar bg="dark" variant="dark" defaultActiveKey={"/login"}>
                <Container>
                    <Navbar.Brand href="/trainer/addProgram">Trainer</Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link disabled={JSON.parse(localStorage.getItem("user")) == null ? "disabled" : ""} href="/trainer/addProgram">Add Training Program</Nav.Link>
                        <Nav.Link href="/trainer/viewPrograms">View Training Programs</Nav.Link>
                        <Nav.Link disabled={JSON.parse(localStorage.getItem("user")) == null ? "disabled" : ""} href="/trainer/viewGyms">View Gyms</Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
            <Outlet />
        </div>
    );
}