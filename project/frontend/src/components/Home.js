import React from 'react';
import '../css/Home.css';
import { Container, Nav, Navbar, Card, NavLink } from 'react-bootstrap';
import {Outlet} from 'react-router-dom';
import axios from "axios";

export default function Home () {

    axios
        .get('http://localhost:8080/test/admin/addGym')
        .then(response => {
            localStorage.setItem('zones', JSON.stringify(response.data));
        });

    return (
        <div>
            <Navbar bg="dark" variant={"dark"}  defaultActiveKey={"/login"}>
                <Container>
                <img className='hamburger' src='https://img.icons8.com/external-justicon-lineal-color-justicon/344/external-dumbbell-fitness-gym-justicon-lineal-color-justicon-1.png' alt='hamburger'/>
                    <Navbar.Brand href="/"><h1 className='pth1'>City Gym</h1></Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link href="/customer/register"><h5>Register</h5></Nav.Link>
                        <Nav.Link href="/login"><h5>Log In</h5></Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
            <Card className="Home App ">
            
                <Card.Body ><b>Welcome to City Gym!</b></Card.Body>
            </Card>
            <Outlet />
            
        </div>
    );
}