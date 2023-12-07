import React, {useState, useEffect} from 'react';
import { Container, Nav, Navbar } from 'react-bootstrap';
import {Outlet} from 'react-router-dom';
import axios from "axios";
 
export default function AdminHome() {

    axios
        .get('http://localhost:8080/test/admin/addProgram')
        .then(response => {
            localStorage.setItem('programCategories', JSON.stringify(response.data));
        });
    axios
        .get('http://localhost:8080/test/admin/viewCustomers')
        .then(response => {
            localStorage.setItem('customers', JSON.stringify(response.data));
        });

    return (
        <div>
            <Navbar bg="dark" variant="dark" defaultActiveKey={"/login"}>
                <Container>
                    <Navbar.Brand href="/admin/addProgram">Admin</Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link disabled={JSON.parse(localStorage.getItem("user")).gym == null ? "disabled" : ""} href="/admin/addProgram">Add Training Program</Nav.Link>
                        <Nav.Link disabled={JSON.parse(localStorage.getItem("user")).gym == null ? "disabled" : ""} href="/admin/viewPrograms">View Training Programs</Nav.Link>
                        <Nav.Link disabled={JSON.parse(localStorage.getItem("user")).gym == null ? "disabled" : ""} href="/admin/addGym">Add Gym</Nav.Link>
                        <Nav.Link href="/admin/viewCustomers">View Customers</Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
            <Outlet />
        </div>
    );
}