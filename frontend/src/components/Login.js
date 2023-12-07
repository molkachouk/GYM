import React, {useState} from "react";
import {Form, Button} from "react-bootstrap";
import {useNavigate} from "react-router-dom";
import "../css/FormStyle.css";
import axios from "axios";


function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const navigate = useNavigate();

    function validateForm() {
        return username.length > 0 && password.length > 0;
    }

    const loginUser = async (credentials) => {
        console.log("asd");
        await axios
            .post("http://localhost:8081/test/login", credentials)
            .then((response) => {
                console.log(response.data);
                localStorage.setItem('user', JSON.stringify(response.data));

                if (response.data.hasOwnProperty('experience')) {
                    navigate('/trainer')
                } else if(response.data.hasOwnProperty('gym')){
                    navigate('/admin')
                } else {
                    navigate("/customer");
                }
            })
    }

    const doLogin = async () => {
        await loginUser({username, password});
    }

    function handleSubmit(event) {
        doLogin();
        event.preventDefault();
    }

    return (
        <div style={{
            backgroundImage: 'url("./images/sal2.jpg")', height: '900px',
            backgroundSize: 'cover',  // Optional: Adjust background size as needed
            backgroundRepeat: 'no-repeat',
        }}>

            <br /><br />
        <div className="FormStyle" h-75>
        <br /><br /><br /><br /><br />
        <div className="text-center">
                    <h2>Sign into your account</h2>
                </div>

            <Form onSubmit={handleSubmit}>
                <Form.Group size="lg" controlId="formBasicUsername" className="mb-3">
                    <Form.Label>Username</Form.Label>
                    <Form.Control
                        autoFocus
                        type="username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                </Form.Group>
                <Form.Group size="lg" className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </Form.Group>
                <br/>
                <text style={{color: 'red', justifyContent: 'center', display: 'flex'}}>
                    {error}
                </text>

                <Button variant="warning" block size="sm" type="submit" disabled={!validateForm()}>
                    Login
                </Button>
            </Form>
        </div>
        </div>
    );
}

export default Login;