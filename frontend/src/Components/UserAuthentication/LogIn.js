import React, { useState } from 'react';
import { Link, useNavigate }from 'react-router-dom';


const LogIn = (props) => {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');
    // eslint-disable-next-line
    const [authenticated, setauthenticated] = useState(localStorage.getItem(localStorage.getItem("authenticated")|| false));
    const [errorMessage, setErrorMessage] = useState('');
    const users = [{ email: "tommytrojan@usc.edu", pass: "password" }];

    const handleSubmit = (e) => {
        e.preventDefault()
        if(email === '' || pass === '') {
            setErrorMessage("Please fill all fields");
        }
        else if(email.search("@usc.edu") === -1) {
            console.log(email);
            setErrorMessage('Please enter a USC email');
        }
        else {
            const account = users.find((user) => user.email === email);
            if (account && account.pass === pass) {
                console.log(email)
                console.log(pass)
                setauthenticated(true)
                localStorage.setItem("authenticated", true);
                navigate('dashboard')
            } else {
                console.log("incorrect user or passowrd")
                setErrorMessage('Email or password is incorrect');
            }
        }
      
    };
    return (
        <div className = "userAuthentication-container">
            <h2> Log In </h2>
            {errorMessage && <div className="error-message"> {errorMessage} </div>}
            <form className = "login-form" onSubmit={handleSubmit}>
                <label htmlFor="email">USC Email </label>
                <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" placeholder="tommytrojan@usc.edu" id="email" name="email" />
                <label htmlFor="password">Password </label>
                <input value={pass} onChange={(e) => setPass(e.target.value)} type="password" placeholder="password" id="password" name="password" />
                <button>Log In</button>
            </form>
            <Link to="/register">
                <button className="link-btn">Don't have an account? Register Here.</button>
            </Link>
        </div>
    );
};


export default LogIn;

