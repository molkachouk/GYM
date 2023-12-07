import React, { useState } from "react";
import {Card, Table} from "react-bootstrap";
import "../../css/FormStyle.css";

function ViewAppointments() {
    const [appointments, setAppointments] = useState(JSON.parse(localStorage.getItem("appointments")));

    return (
        <div className="TableStyle">

            <br/>
            <br/>

            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>Appointment</th>
                    <th>Date</th>
                </tr>
                </thead>
                <tbody>
                {appointments.map(appointment => {
                    return (
                        <tr key={appointment.name}>
                            <td key={appointment.name}>{appointment.name}</td>
                            <td key={appointment.date}>{appointment.date}</td>
                        </tr>
                    );
                })}
                </tbody>
            </Table>
        </div>
    );
}

export default ViewAppointments;