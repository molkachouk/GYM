import React from 'react';
import '../css/Home.css';
import { Container, Nav, Navbar, Card, NavLink } from 'react-bootstrap';
import {Outlet} from 'react-router-dom';
import axios from "axios";

export default function Home () {

    axios
        .get('http://localhost:8081/test/admin/addGym')
        .then(response => {
            localStorage.setItem('zones', JSON.stringify(response.data));
        });

    return (
        <div>
            <Navbar bg="dark" variant={"dark"}  defaultActiveKey={"/login"}>
                <Container>
                <Navbar.Brand href="/"> <img className='hamburger' src='./images/logo.png' alt='hamburger' /></Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link href="/customer/register"><h5>Register</h5></Nav.Link>
                        <Nav.Link href="/login"><h5>Log In</h5></Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
            <Card className="Home App bg-cover"

style={{
    backgroundImage: 'url("./images/bg3.jpg")', height: '800px',
    backgroundSize: 'cover',  // Optional: Adjust background size as needed
    backgroundRepeat: 'no-repeat',
}}>

<Card.Body ><br /><br /><br /><br />
    <p className="text-3xl text-yellow-200"><h1></h1>
        <h1  style={{ color: '#fff' }} >BIENVENUE À WMA GYM</h1><br />
        <img src='./images/logo.png' className="w-100 h-100" alt="Logo" />
        <span><h1 style={{ color: '#fff' }} >Salle de sport et de Fitness</h1></span>
    </p>

    <br /><br /></Card.Body>
</Card>



<Card className="Home App bg-cover"

style={{
    backgroundImage: 'url("./images/bg4.jpg")', height: '200px',
    backgroundSize: 'cover',  // Optional: Adjust background size as needed
    backgroundRepeat: 'no-repeat',
}}>


<Card.Body >  <br /><br /><br /><br /><br />
    <div class="container">
        <p class="h2"  style={{ color: '#fff' }} >
            Nos offres sont bien étudiées
        </p>
        <button class="btn btn-warning mt-4 px-4 py-2 rounded-pill font-weight-bold">NOS TARIFS</button>
    </div>

    <br /><br /><br /><br /></Card.Body>
</Card>


<Card className="Home App bg-cover"

style={{
    backgroundImage: 'url("./images/sal2.jpg")', height: '500px',
    backgroundSize: 'cover',  // Optional: Adjust background size as needed
    backgroundRepeat: 'no-repeat',
}}>

<Card.Body >
    <br /><br /><br /><br />
    <div className="card w-50 mx-auto my-auto">
        <div className="card-body d-flex">
            <p className="card-text"> Profitez d'un cadre agréable, des espaces aérés et climatisés,
                des équipements de pointe et des coachs qualifiés pour vous encadrer,
                vous motiver et vous soutenir. Galaxy Gym est une salle de sport et de fitness
                qui vous propose des cours variés dans un espace de Fitness destiné aux adultes,
                ados et enfants. Vous trouverez tout ce qu'il vous faut dans nos espaces de
                musculation et Cardio Training avec un matériel de qualité.</p>
            <img src="./images/sal1.jpg" alt="Description de l'image" className="ml-1" style={{ maxHeight: '200px', maxWidth: '200px' }} />
        </div>
    </div>




</Card.Body>
</Card>



<Card className="Home App bg-cover"
style={{
background: '#000',  // Utilisez le code hexadécimal pour la couleur noire
color: '#fff',       // Utilisez le code hexadécimal pour la couleur du texte (ici blanc)
padding: '8rem 0',   // Ajustez la marge interne comme nécessaire
}}>


<Card.Body>
<div>


<h2 class="h1-responsive font-weight-bold text-center my-4">Contact us</h2>

<p class="text-center w-responsive mx-auto mb-5">Do you have any questions? Please do not hesitate to contact us directly. Our team will come back to you within
a matter of hours to help you.</p>

<div class="row">


<div class="col-md-9 mb-md-0 mb-5">
<form id="contact-form" name="contact-form" action="mail.php" method="POST">


<div class="row">


    <div class="col-md-6">
        <div class="md-form mb-0">
            <input type="text" id="name" name="name" class="form-control"/>
            <label for="name" class="">Your name</label>
        </div>
    </div>
   
    <div class="col-md-6">
        <div class="md-form mb-0">
            <input type="text" id="email" name="email" class="form-control"/>
            <label for="email" class="">Your email</label>
        </div>
    </div>
   

</div>

<div class="row">
    <div class="col-md-12">
        <div class="md-form mb-0">
            <input type="text" id="subject" name="subject" class="form-control"/>
            <label for="subject" class="">Subject</label>
        </div>
    </div>
</div>

<div class="row">

 
    <div class="col-md-12">

        <div class="md-form">
            <textarea type="text" id="message" name="message" rows="2" class="form-control md-textarea"></textarea>
            <label for="message">Your message</label>
        </div>

    </div>
</div>


</form>

<div class="text-center text-md-left">
<a class="btn btn-warning" onclick="document.getElementById('contact-form').submit();">Send</a>
</div>
<div class="status"></div>
</div>

<div class="col-md-3 text-center">

</div>


</div>
</div>
</Card.Body>
</Card>
            <Outlet />
            
        </div>
    );
}