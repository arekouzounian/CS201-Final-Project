import { useEffect, useState } from "react";
import { useLocation, useNavigate } from 'react-router-dom';
import { Container, Grid } from '@mui/material';
import { useDispatch } from 'react-redux';

import { actionFetchCurated } from "../../actions/actions";
import NavBar from "./NavBar/NavBar";
import AllPosts from "../Posts/AllPosts";


export const CuratedDashboard = () => {
  const [user, setUser] = useState(JSON.parse(localStorage.getItem('profile')))
  const authenticated = user?.authenticated
  const navigate = useNavigate();
  const location = useLocation();
  const dispatch = useDispatch();

  useEffect(() => {
    setUser(JSON.parse(localStorage.getItem('profile')))
    localStorage.setItem('view', 1);
    
    if (authenticated === -1 || authenticated === null) {
      console.log("not auth")
      navigate('/')
    }
    dispatch(actionFetchCurated);    
  }, [location, authenticated, navigate, dispatch]);

  return (
    <div>
       <NavBar style={{justifySelf:"center"}}/>
      <Container maxWidth="xl">
          <div style = {{position:'absolute', top:0, width: 1380}}>
                <h1 style={{marginTop: 75, textAlign: "left"}}>Curated Events</h1>
                <Container style={{marginTop: 50, marginBottom:50}} maxWidth="xl">
                  <Grid container spacing={3}>
                      <AllPosts> </AllPosts>
                  </Grid>
                </Container>
            </div>
      </Container>
    </div>
    );
};
export default CuratedDashboard;