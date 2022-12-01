import React from 'react';
//import { Link, useNavigate }from 'react-router-dom';
import Card from '@mui/material/Card';
import { CardActionArea } from '@mui/material';
import CardContent from '@mui/material/CardContent';




const Post = () => {
    // //populate the form with an API call
    // const posts = useSelector((state) => state.posts);


    // hardcoded just for testing 
    const postData = {eventname: 'Basketball', categories: 'Sports, Gym', description: 'Redekopp broke our ankles last time', startTime: '12:00 PM', endTime: '4:00 PM', location: 'USC Lyon Center'};

    return (
        <Card raised>
            <CardActionArea>
                <CardContent>
                    <h1>{postData.eventname}</h1>
                    <p>Categories: {postData.categories}</p>
                    <p>Description: {postData.description}</p>
                    <p>Start: {postData.startTime}</p>
                    <p>End: {postData.endTime}</p>
                    <p>Location: {postData.location}</p>
                </CardContent>
            </CardActionArea>
        </Card>
    )
}

export default Post;