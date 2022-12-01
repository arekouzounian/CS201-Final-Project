import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useLocation }from 'react-router-dom';
import { useDispatch } from "react-redux";

import { actionLogIn } from '../../actions/actions';

const initialState = {email: '', password: '', authenticated: -1, preferences: []};

const LogIn = (props) => {
   
    const [errorMessage, setErrorMessage] = useState('');
   
    const [form, setForm] = useState(initialState);

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {
        if(JSON.parse(localStorage.getItem('profile'))?.authenticated === -1) {
            localStorage.removeItem('profile')
            setErrorMessage('Invalid Credentials')
        }
        else if(JSON.parse(localStorage.getItem('profile'))?.authenticated > 1) {
            navigate('/dashboard')
        }
        
      }, [location, navigate]);

    const handleSubmit = (e) => {
        e.preventDefault();

        if(form.email === '' || form.password === '') {
            setErrorMessage("Please fill all fields");
        }
        else if(form.email.search("@usc.edu") === -1) {

            setErrorMessage('Please enter a USC email');
        }
        else {
            localStorage.setItem('profile', JSON.stringify({...form, authenticated: JSON.parse(localStorage.getItem('profile'))?.authenticated}))
            dispatch(actionLogIn(form, navigate));
        }
    };
    const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

    return (
     
        <div className = "userAuthentication-container">
            <h2> Log In </h2>
            {errorMessage && <div className="error-message"> {errorMessage} </div>}
            <form className = "login-form" onSubmit={handleSubmit}>
                <label htmlFor="email">USC Email </label>
                <input onChange={handleChange} type="email" placeholder="tommytrojan@usc.edu" id="email" name="email" />
                <label htmlFor="password">Password </label>
                <input onChange={handleChange} type="password" placeholder="password" id="password" name="password" />
                <button>Log In</button>
            </form>
            <Link to="/register">
                <button className="link-btn">Don't have an account? Register Here.</button>
            </Link>
        </div>
    );
};

export default LogIn;

