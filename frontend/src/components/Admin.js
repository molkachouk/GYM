import React from "react";
import {Route, Routes } from 'react-router-dom';
import AdminHome from './Admin/AdminHome';
import ViewTrainingPrograms from './Admin/ViewTrainingPrograms';
import AddTrainingProgram from "./Admin/AddTrainingProgram";
import AddGym from "./Admin/AddGym";
import ViewCustomers from "./Admin/ViewCustomers";
// import

function Admin() {
    return (
        <Routes>
            <Route path="/" element={<AdminHome/>}>
                <Route path="viewPrograms" element={<ViewTrainingPrograms/>}/>
                <Route path="addProgram" element={<AddTrainingProgram/>}/>
                <Route path="addGym" element={<AddGym/>}/>
                <Route path="viewCustomers" element={<ViewCustomers/>}/>
            </Route>
        </Routes>
    );
}

export default Admin;