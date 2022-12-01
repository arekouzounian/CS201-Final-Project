import { useEffect } from 'react';
import { Link, useLocation, useNavigate } from "react-router-dom";
import './styles.js'
// import { Container, AppBar, Toolbar } from '@mui/material';
import { StyledAppBar, StyledButton } from "./styles.js";


const NavBar = () => {
  const navigate = useNavigate();
  const location = useLocation();

 
  const logout = (e) => {
    e.preventDefault();
    localStorage.removeItem('profile')
    navigate('/')
  }

  useEffect(() => {
    localStorage.getItem('view');
  }, [location]);

  const handleClick = (e) => {
    e.preventDefault();
    if(localStorage?.getItem('view') === "1") {
      navigate('/dashboard')
    } else {
      navigate('/curateddashboard')
    }
  }

  return (
      <StyledAppBar style={{marginRight: 76, marginTop: 15}} position="fixed" color="inherit"> 
        <Link to="/createpost" >
          <StyledButton variant="contained" color="primary">Create Post</StyledButton>
        </Link>
        <StyledButton onClick={handleClick} variant="contained" color="primary"> { localStorage?.getItem('view') === "1" ? "All Events" : "Curated Events" } </StyledButton>
        <StyledButton onClick={logout}variant="contained" color="primary">Log Out</StyledButton>
      </StyledAppBar>
  );
  
};
export default NavBar;