import React from "react";
import {Route, Routes } from 'react-router-dom';
import CustomerHome from './Customer/CustomerHome';
import ViewGyms from './Customer/ViewGyms';
import CustomerViewTrainingPrograms from "./Customer/CustomerViewTrainingPrograms";
import ViewTrainers from "./Customer/ViewTrainers";
import MakeAppointment from "./Customer/MakeAppointment";
import ViewAppointments from "./Customer/ViewAppointments";

function Customer() {
    return (
        <Routes>
            <Route path="/" element={<CustomerHome/>}>
                <Route path="viewGyms" element={<ViewGyms/>}/>
                <Route path="viewPrograms" element={<CustomerViewTrainingPrograms/>}/>
                <Route path="viewTrainers" element={<ViewTrainers/>}/>
                <Route path="makeAppointment" element={<MakeAppointment/>}/>
                <Route path="viewAppointments" element={<ViewAppointments/>}/>
            </Route>
        </Routes>
    );
}

export default Customer;