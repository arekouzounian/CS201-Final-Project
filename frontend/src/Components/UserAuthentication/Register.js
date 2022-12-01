import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { actionRegisterUser } from '../../actions/actions';
import { useDispatch } from "react-redux";
import Select from 'react-select';
import makeAnimated from 'react-select/animated';

const initialState = {email: '', password: '', authenticated: -1, preferences: []};

const Register = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const location = useLocation
    const animatedComponents = makeAnimated();

    const [confirmpass, setConfirmPass] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const [form, setForm] = useState(initialState);

    const options = [
        { value: 'Club activity', label: 'Club activity' },
        { value: 'Sports', label: 'Sports' },
        { value: 'Study group', label: 'Study group' },
        { value: 'Monday', label: 'Monday' },
        { value: 'Tuesday', label: 'Tuesday' },
        { value: 'Wednesday', label: 'Wednesday' },
        { value: 'Thursday', label: 'Thursday' },
        { value: 'Friday', label: 'Friday' },
        { value: 'Saturday', label: 'Saturday' },
        { value: 'Sunday', label: 'Sunday' }
    ]

    const handleChangePref = (e) => {
        const map = e.map(option =>
            option.value
        )
        setForm({...form, preferences: map})
    }

    useEffect(() => {
        console.log(form);
        if(JSON.parse(localStorage.getItem('profile'))?.authenticated === -1) {
            localStorage.removeItem('profile')
            setErrorMessage('User already exists')
        }
        else if(JSON.parse(localStorage.getItem('profile'))?.authenticated > -1) {
            navigate('/dashboard')
        }
        
      }, [location, navigate, form]);

    const handleSubmit = (e) => {
        e.preventDefault();
    
        if(form.email === '' || form.pass === '' || confirmpass === '') {
            setErrorMessage('Please fill all fields');
        }
        else if(form.email.search("@usc.edu") === -1) {
            setErrorMessage('Please enter a USC email');
        }
        else if(form.password !== confirmpass) {
            setErrorMessage('Passwords do not match');
        } //now make the API call, if the registration is success go to else. otherwise, user already exists
        else {
            localStorage.setItem('profile', JSON.stringify({...form}))
            dispatch(actionRegisterUser(form, navigate));
        }
    }
    const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });


    return (
        <div className = "userAuthentication-container">
            <h2> Register </h2>
            {errorMessage && <div className="error-message"> {errorMessage} </div>}
            <form className = "register-form" onSubmit={handleSubmit}>
                <label htmlFor="email">USC Email</label>
                <input value={form.email} onChange={handleChange} type="email" placeholder="Tommytrojan@usc.edu" id="email" name="email" />

                <label htmlFor="password">Password</label>
                <input value={form.pass} onChange={handleChange} type="password" placeholder="Password" id="password" name="password" />
                
                <label htmlFor="confirmpassword">Confirm Password</label>
                <input value={confirmpass} onChange={(e) => setConfirmPass(e.target.value)} type="password" placeholder="Confirm Password" id="confirmpassword" name="confirmpassword" />
                <Select className="register" name ="preferences" onChange={handleChangePref} options={options} closeMenuOnSelect={false} components={animatedComponents} isMulti /> 
                <button>Register</button> 
            </form>
            <Link to="/"> 
                <button className="link-btn">Already have an account? Log-In Here.</button>
            </Link>
        </div>
    );
};

export default Register;