import axios from "axios";
import React, { useState, useEffect } from "react";
import {Card, Table} from "react-bootstrap";
import "../../css/FormStyle.css";

function ViewTrainingPrograms() {
    const [programs, setPrograms] = useState([]);
    const [programCategories, setProgramCategories] = useState(JSON.parse(localStorage.getItem("programCategories")));

    useEffect(() => {
        console.log("asd1")
        axios
        .get('http://localhost:8081/test/admin/viewPrograms', {
            params: {
                gymName: JSON.parse(localStorage.getItem("user")).gym.name
            }
        })
        .then(response => {
            console.log("asd2:"+response.data + localStorage.getItem("user").gymName);
            setPrograms(response.data);
        });
    }, []);
    {/*//todo: ViewTrainingPrograms Fa fain interfata*/}

    return (
        <div className="TableStyle">
            <div>
            {
                // /
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

export default ViewTrainingPrograms;