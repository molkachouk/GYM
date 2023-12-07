import React, { useState } from "react";
import {Card, Table} from "react-bootstrap";
import "../../css/FormStyle.css";

function ViewCustomers() {
    const [customers, setCustomers] = useState(JSON.parse(localStorage.getItem("customers")));

    return (
        <div className="TableStyle">

            <br/>
            <br/>

            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>Customer First Name</th>
                    <th>Customer Last Name</th>
                    <th>Address</th>
                    <th>Membership Expiration Date</th>
                </tr>
                </thead>
                <tbody>
                {customers.map(customer => {
                    return (
                        <tr key={customer.firstName}>
                            <td key={customer.firstName}>{customer.firstName}</td>
                            <td key={customer.lastName}>{customer.lastName}</td>
                            <td key={customer.address}>{customer.address}</td>
                            <td key={customer.membershipExpiration}>{customer.membershipExpiration}</td>
                        </tr>
                    );
                })}
                </tbody>
            </Table>
        </div>
    );
}

export default ViewCustomers;