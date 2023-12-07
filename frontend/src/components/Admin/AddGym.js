import React, { useState, useEffect } from "react";
import {Form, Button} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../../css/FormStyle.css";

function AddGym() {
    const [zones, setZones] = useState(JSON.parse(localStorage.getItem("zones")));
    const [addressState, setAddressState] = useState({
        street: "",
        number: "",
        city: "",
        zone: zones[0],
        country: "",
        postalCode: ""
    })
    const [gymInfo, setGymInfo] = useState({
        name: "",
        address: "",
        deliveryZones: [],
        administratorDTO: JSON.parse(localStorage.getItem("user"))
    });

    const navigate = useNavigate();

    function handleChange(event) {
        const {name, value} = event.target
        setGymInfo(prevState => {
            return {
                ...prevState,
                [name]: value
            };
        })
        console.log(gymInfo);
    }

    function handleChangeAddress(event) {
        const {name, value} = event.target
        setAddressState(prevState => {
            return {
                ...prevState,
                [name]: value
            };
        })
        setGymInfo(prevState => {
            return {
                ...prevState,
                address: addressState.toString()
            };
        })
        console.log(gymInfo)
    }

    function handleSelect(event) {
        const {name, value} = event.target
        console.log(value);
        setAddressState(prevState => {
            return {
                ...prevState,
                "zone": {
                    idZone: value,
                    name: ""
                }
            };
        })

        setGymInfo(prevState => {
            return {
                ...prevState,
                address: addressState.toString()
            };
        })
        console.log(addressState);
        console.log(gymInfo);
    }

    const addGym = async() => {
        console.log(gymInfo);
        alert("Gym " + gymInfo.name + " added successfully!");

    }

    const updateGyms = async() => {
        await addGym();

        await axios
            .get("http://localhost:8081/test/admin/get", {
                params: {
                    adminEmail: JSON.parse(localStorage.getItem("user")).email
                }
            })
            .then((response) => {
                localStorage.setItem('user', JSON.stringify(response.data));
            })
            .catch((error) => console.error("There was an error when adding program!", error));

        axios
            .get('http://localhost:8081/test/customer/viewGyms')
            .then(response => {
                localStorage.setItem('gyms', JSON.stringify(response.data));
                navigate("/admin/addProgram");
            });
    }

    function handleSubmit(event) {
        updateGyms();
        event.preventDefault();
    }

    return (
        <div className="FormStyle">
            <Form onSubmit={handleSubmit}>
                <Form.Group size="lg" controlId="formBasicName" className="mb-3">
                    <Form.Label>Gym Name</Form.Label>
                    <Form.Control autoFocus name="name" type="text" value={gymInfo.name} onChange={handleChange} />
                </Form.Group>

                <Form.Text>Address</Form.Text>{' '}

                <Form.Group size="lg" className="mb-3" controlId="formBasicStreet">
                    <Form.Label>Street</Form.Label>
                    <Form.Control name="street" type="text" value={addressState.street} onChange={handleChangeAddress}/>
                </Form.Group>

                <Form.Group size="lg" className="mb-3" controlId="formBasicNumber">
                    <Form.Label>Number</Form.Label>
                    <Form.Control name="number" type="text" value={addressState.number} onChange={handleChangeAddress}/>
                </Form.Group>

                <Form.Group size="lg" className="mb-3" controlId="formBasicCity">
                    <Form.Label>City</Form.Label>
                    <Form.Control name="city" type="text" value={addressState.city} onChange={handleChangeAddress}/>
                </Form.Group>

                <Form.Group size="lg" className="mb-3" controlId="formBasicCountry">
                    <Form.Label>Country</Form.Label>
                    <Form.Control name="country" type="text" value={addressState.country} onChange={handleChangeAddress}/>
                </Form.Group>

                <Form.Group size="lg" className="mb-3" controlId="formBasicPostalCode">
                    <Form.Label>PostalCode</Form.Label>
                    <Form.Control name="postalCode" type="text" value={addressState.postalCode} onChange={handleChangeAddress}/>
                </Form.Group>

                <div>
                    <select value={addressState.zone.idZone} onChange={handleSelect}>
                        {zones.map(zone =>
                            <option value={zone.idZone} key={zone.idZone}>{zone.name}</option>
                        )}
                    </select>
                </div>

                <br/>

                <Button variant="button" block size="lg" type="submit">
                    Add Gym
                </Button>
            </Form>
        </div>
    );
}

export default AddGym;