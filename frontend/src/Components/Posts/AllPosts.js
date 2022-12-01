import React from 'react';
import { useSelector } from 'react-redux';
import { Grid } from '@mui/material';
import { styled } from '@mui/material/styles';

import Post from './Post/Post';

const GridMainContainer = styled(Grid)(({ theme }) => ({
  borderRadius: 15,
  flexDirection: "row",
}));

const AllPosts = () => {
  const allPosts = useSelector((state)=> state.posts)
  return (
    <GridMainContainer container spacing={2} columns={4}>
          <Grid item lg={1}> <Post/> </Grid>
          <Grid item lg={1}> <Post/> </Grid>
          <Grid item lg={1}> <Post/> </Grid>
          <Grid item lg={1}> <Post/> </Grid>
          <Grid item lg={1}> <Post/> </Grid>
          <Grid item lg={1}> <Post/> </Grid>
          <Grid item lg={1}> <Post/> </Grid>
          <Grid item lg={1}> <Post/> </Grid>
          <Grid item lg={1}> <Post/> </Grid>
          <Grid item lg={1}> <Post/> </Grid>
          <Grid item lg={1}> <Post/> </Grid>
          <Grid item lg={1}> <Post/> </Grid>
          <Grid item lg={1}> <Post/> </Grid>
         
      </GridMainContainer>
  );
};

export default AllPosts;