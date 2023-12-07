import React, { useState, useEffect } from "react";
import {Form, Button, Card, Alert} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../../css/FormStyle.css";

function AddTrainingProgram() {
    const [programCategories, setProgramCategories] = useState(JSON.parse(localStorage.getItem("programCategories")));
    const [admin, setAdmin] = useState(JSON.parse(localStorage.getItem("user")));
    const [programInfo, setProgramInfo] = useState({
        name: "",
        duration: 0,
        description: "",
        category: programCategories[0],
        gymDTO: JSON.parse(localStorage.getItem("user")).gym
    });
    const [error, setError] = useState("");

    const navigate = useNavigate();

    function validateForm() {
        return programInfo.name.length > 0 && programInfo.duration > 0 && programInfo.description.length > 0;
    }

    function handleChange(event) {
        const {name, value} = event.target
        setProgramInfo(prevState => {
            return {
                ...prevState,
                [name]: value
            };
        })
        console.log(programInfo);
    }

    function handleSelect(event) {
        const {name, value} = event.target
        console.log(value);
        setProgramInfo(prevState => {
            return {
                ...prevState,
                category: {
                    idCategory: value,
                    name: ""
                }
            };
        })
        console.log(programInfo);
    }

    const addProgram = async() => {
        await axios
            .post("http://localhost:8081/test/admin/addProgram", programInfo)
            .then((response) => {
                alert("You added the program " + programInfo.name);
                setProgramInfo({
                    name: "",
                    duration: 0,
                    description: "",
                    category: {},
                    gymDTO: JSON.parse(localStorage.getItem("user")).gym
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
        await addProgram();
        axios
            .get("http://localhost:8081/test/admin/get", {
                params: {
                    adminUsername: admin.username
                }
            })
            .then((response) => {
                localStorage.setItem('user', JSON.stringify(response.data));
                console.log(response.data);
                navigate("/admin/addProgram");
            })
    }

    function handleSubmit(event) {
        updateState();
        event.preventDefault();
    }

    return (
        <div className="FormStyle">
            <Card className="CardStyle">
                <Card.Body>Select a Program!</Card.Body>
            </Card>

            <br/>
            <br/>

            <div>
                <select value={programInfo.category.idCategory} onChange={handleSelect}>
                    {programCategories.map(category =>
                        <option value={category.idCategory} key={category.idCategory}>{category.name}</option>
                    )}
                </select>
            </div>

            <br/>
            <br/>

            <Form onSubmit={handleSubmit}>
                <Form.Group size="lg" controlId="programName" className="mb-3">
                    <Form.Label>Training Program Name</Form.Label>
                    <Form.Control autoFocus name="name" type="text" value={programInfo.name} onChange={handleChange} />
                </Form.Group>

                <Form.Group size="lg" controlId="programDuration" className="mb-3">
                    <Form.Label>Duration</Form.Label>
                    <Form.Control autoFocus name="duration" type="number" value={programInfo.duration} onChange={handleChange} />
                </Form.Group>

                <Form.Group size="lg" controlId="programDescription" className="mb-3">
                    <Form.Label>Description</Form.Label>
                    <Form.Control autoFocus name="description" type="text" value={programInfo.description} onChange={handleChange} />
                </Form.Group>

                <br/>
                <text style={{color: 'red', justifyContent: 'center', display: 'flex'}}>
                    {error}
                </text>

                <Button variant="button" block size="lg" type="submit" disabled={!validateForm()}>
                    Add Training Program
                </Button>
            </Form>
        </div>
    );
}

export default AddTrainingProgram;