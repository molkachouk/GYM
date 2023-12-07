import React, { useState, useEffect } from "react";
import {Card, Table, Button, Modal, ListGroup, ListGroupItem} from "react-bootstrap";
import "../../css/FormStyle.css";
import axios from "axios";
import Dropdown from "bootstrap/js/src/dropdown";
import { CardBody } from "reactstrap";

function ViewAllMenus() {
    const [gyms, setGyms] = useState(JSON.parse(localStorage.getItem("gyms")));
    const [programCategories, setProgramCategories] = useState(JSON.parse(localStorage.getItem("programCategories")));
    const [programs, setPrograms] = useState([]);

    useEffect(() => {
        axios
            .get('http://localhost:8080/test/trainer/viewPrograms')
            .then(response => {
                setPrograms(response.data);
            });
    }, []);

    return (
        <div key="initial" className="TableStyle">
            <br/>
            <div>
                {
                    programCategories.map(category => {
                        return (
                            <div key={category.name}>
                                <Card className="CardStyle">
                                    <Card.Body>{category.name}</Card.Body>
                                </Card>
                                <Table striped bordered hover key={category.name}>
                                    <thead>
                                    <tr>
                                        <th>Program Name</th>
                                        <th>Description</th>
                                        <th>Duration</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {programs.map(program => {
                                        if (program.category.name === category.name) {
                                            return (
                                                <tr key={program.name}>
                                                    <td key={program.name}>{program.name}</td>
                                                    <td key={program.description}>{program.description}</td>
                                                    <td key={program.duration}>{program.duration + " Minutes"}</td>
                                                </tr>
                                            );
                                        }
                                    })}
                                    </tbody>
                                </Table>
                                <br/>
                                <br/>
                            </div>
                        );
                    })
                }
            </div>
        </div>
    );
}

export default ViewAllMenus;