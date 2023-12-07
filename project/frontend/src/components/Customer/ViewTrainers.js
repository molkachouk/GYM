import React, { useState } from "react";
import {Card, Table} from "react-bootstrap";
import "../../css/FormStyle.css";

function ViewTrainers() {
    const [trainers, setCustomers] = useState(JSON.parse(localStorage.getItem("trainers")));

    return (
        <div className="TableStyle">

            <br/>
            <br/>

            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>Trainer First Name</th>
                    <th>Trainer Last Name</th>
                </tr>
                </thead>
                <tbody>
                {trainers.map(trainer => {
                    return (
                        <tr key={trainer.firstName}>
                            <td key={trainer.firstName}>{trainer.firstName}</td>
                            <td key={trainer.lastName}>{trainer.lastName}</td>
                        </tr>
                    );
                })}
                </tbody>
            </Table>
        </div>
    );
}

export default ViewTrainers;