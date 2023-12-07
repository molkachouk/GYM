import React, { useState, useEffect } from "react";
import {Form, Button, Card, Alert} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../../css/FormStyle.css";

function MakeAppointment() {
    const [admin, setAdmin] = useState(JSON.parse(localStorage.getItem("user")));
    const [appointmentInfo, setAppointmentInfo] = useState({
        name: "",
        date: "",
    });
    const [error, setError] = useState("");

    const navigate = useNavigate();

    // function validateForm() {
    //     return programInfo.name.length > 0 && programInfo.duration > 0 && programInfo.description.length > 0;
    // }

    function handleChange(event) {
        const {name, value} = event.target
        setAppointmentInfo(prevState => {
            return {
                ...prevState,
                [name]: value
            };
        })
        console.log(appointmentInfo);
    }

    // function handleSelect(event) {
    //     const {name, value} = event.target
    //     console.log(value);
    //     setProgramInfo(prevState => {
    //         return {
    //             ...prevState,
    //             category: {
    //                 idCategory: value,
    //                 name: ""
    //             }
    //         };
    //     })
    //     console.log(programInfo);
    // }

    const addAppointment = async() => {
        await axios
            .post("http://localhost:8081/test/customer/makeAppointment", appointmentInfo)
            .then((response) => {
                alert("You added the appointment " + appointmentInfo.name);
                setAppointmentInfo({
                    name: "",
                    date: "",
                })
                console.info(response)
            })
            .catch((error) => {
                setError(error.response.data.message);
                console.error("There was an error!", error.response.data.message)
            });
    }

    const updateState = async() => {
        console.log(admin.username);
        await addAppointment();
        axios
            .get("http://localhost:8081/test/admin/get", {
                params: {
                    adminUsername: admin.username
                }
            })
            .then((response) => {
                localStorage.setItem('user', JSON.stringify(response.data));
                console.log(response.data);
                navigate("/customer/makeAppointment");
            })
    }

    function handleSubmit(event) {
        updateState();
        event.preventDefault();
    }

    return (
        <div className="FormStyle">
            <Card className="CardStyle">
                <Card.Body>Make an Appointment!</Card.Body>
            </Card>

            <br/>
            <br/>

            <div>
                <select id="appointment">
                    <option value="1">Training Program</option>
                    <option value="2">Trainer</option>
                </select>
            </div>

            <br/>
            <br/>

            <Form onSubmit={handleSubmit}>
                <Form.Group size="lg" controlId="appointmentName" className="mb-3">
                    <Form.Label>Program/Trainer Name</Form.Label>
                    <Form.Control autoFocus name="name" type="text" value={appointmentInfo.name} onChange={handleChange} />
                </Form.Group>

                <Form.Group size="lg" controlId="appointmentDate" className="mb-3">
                    <Form.Label>Date</Form.Label>
                    <Form.Control autoFocus name="date" type="text" value={appointmentInfo.date} onChange={handleChange} />
                </Form.Group>

                <br/>
                <text style={{color: 'red', justifyContent: 'center', display: 'flex'}}>
                    {error}
                </text>

                <Button variant="button" block size="lg" type="submit">
                    Create Appointment
                </Button>
            </Form>
        </div>
    );
}

export default MakeAppointment;