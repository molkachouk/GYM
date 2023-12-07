import React from "react";
import {Route, Routes } from 'react-router-dom';
import TrainerHome from './Trainer/TrainerHome';
import TrainerViewTrainingPrograms from './Trainer/TrainerViewTrainingPrograms';
import TrainerAddTrainingPrograms from "./Trainer/TrainerAddTrainingPrograms";
import TrainerViewGyms from './Trainer/TrainerViewGyms';

function Trainer() {
    return (
        <Routes>
            <Route path="/" element={<TrainerHome/>}>
                <Route path="viewPrograms" element={<TrainerViewTrainingPrograms/>}/>
                <Route path="addProgram" element={<TrainerAddTrainingPrograms/>}/>
                <Route path="viewGyms" element={<TrainerViewGyms/>}/>
            </Route>
        </Routes>
    );
}

export default Trainer;