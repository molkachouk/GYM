import React, { useState } from "react";
import {Card, Table} from "react-bootstrap";
import "../../css/FormStyle.css";

function TrainerViewGyms() {
    const [gyms, setGyms] = useState(JSON.parse(localStorage.getItem("gyms")));

    return (
        <div className="TableStyle">

            <br/>
            <br/>

            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>Gym Name</th>
                    <th>Location</th>
                </tr>
                </thead>
                <tbody>
                {gyms.map(gym => {
                    return (
                        <tr key={gym.name}>
                            <td key={gym.name}>{gym.name}</td>
                            <td key={gym.deliveryZones}>{" - "}
                                {gym.deliveryZones.map(zone => { return(
                                    zone.name + " - ");
                                })}
                            </td>
                        </tr>
                    );
                })}
                </tbody>
            </Table>
        </div>
    );
}

export default TrainerViewGyms;