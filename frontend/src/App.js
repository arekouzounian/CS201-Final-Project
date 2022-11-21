import React from "react";
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';

import LogIn from "./Components/UserAuthentication/LogIn";
import Register from "./Components/UserAuthentication/Register";
import CreatePost from "./Components/CreatePost/CreatePost";
import Dashboard from "./Components/Dashboard/Dashboard";


const App = () => {
    // const [currentID, setCurrentID] = useState('LogIn');

    // const toggleForm =(formName) => {
    //     setCurrentID(formName);
    // }

    return (
        // <div className="App">
        //     {currentID === "LogIn" ? <LogIn onFormSwitch={toggleForm} /> : <Register onFormSwitch={toggleForm} />}
        // </div>  
        <div className="App">
            <BrowserRouter>
                <Routes>
                    <Route path="/" exact element={<LogIn/ >} />
                    <Route path="/register" exact element={<Register/ >} />
                    <Route path="/createpost" exact element={<CreatePost/ >} />
                    <Route path="/dashboard" exact element={<Dashboard/ >} />
                </Routes>
            </BrowserRouter>
        </div>
    )
}

export default App;


