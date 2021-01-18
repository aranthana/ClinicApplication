import React from "react";
import { Router } from "@reach/router";
import Patients from "./patients/Patients";
import Profile from "./patients/Profile"
import Physicians from "./physicians/Physicians";
import PhysicianProfile from "./physicians/PhysicianProfile";
import Visit from "./visits/Visits";
import  VisitProfile from "./visits/VisitProfile";
function Application(){

    return(

        <Router>
            <Patients path="/"/>
            <Profile path="profile"/>
            <Physicians path ="physicians"/>
            <PhysicianProfile path ="physician_profile"/>
            <Visit path ="visits"/>
            <VisitProfile path ="visit_profile"/>


        </Router>
    );
}
export default Application;