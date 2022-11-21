import React, { useState} from 'react';

const CreatePost = () => {
    const [postData, setPostData] = useState({eventname: '', categories: '', description: '', date: '',  startTime: '', endTime: ' ', location:''});

    const handleSubmit = async (e) => {
        e.preventDefault();
        
      };
    return (
        <div className="createpost-form">
            <h1> Create Event </h1>
            <form className = "createpost-form" onSubmit={handleSubmit}>
                <label htmlFor="eventName">Event Name </label>
                <input value={postData.eventname} onChange={(e) => setPostData({ ...postData, eventname: e.target.value })} type="eventname" placeholder="Pick-Up Basketball" id="eventname" name="eventname" />
                <label htmlFor="Categories"> Categories </label>
                <input value={postData.categories} onChange={(e) => setPostData({ ...postData, categories: e.target.value })} type="categories" placeholder="Basketball" id="categories" name="categories" />
                <label htmlFor="Description"> Description </label>
                <input value={postData.description} onChange={(e) => setPostData({ ...postData, description: e.target.value })} type="description" placeholder="we lowk suck, so go easy" id="description" name="description" />
                <label htmlFor="Date">Date </label>
                <input value = {postData.date} onChange={(e) => setPostData({ ...postData, date: e.target.value })} type ="date" id="date" name="date" />
                <label htmlFor="Start Time">Start Time </label>
                <input value = {postData.startTime} onChange={(e) => setPostData({ ...postData, startTime: e.target.value })} type ="time" id="starttime" name="starttime" />
                <label htmlFor="End Time">End Time </label>
                <input value = {postData.endTime} onChange={(e) => setPostData({ ...postData, endTime: e.target.value })} type ="time" id="endtime" name="endtime" />
                <label htmlFor="Location">Location </label>
                <input value={postData.location} onChange={(e) => setPostData({ ...postData, location: e.target.value })} type="location" placeholder="USC Lyon Center" id="location" name="location" />
                {/* onChange={(e) => setPostData({ ...postData, title: e.target.value })}  */}
            </form>
            <button type="submit" className="button">Create Post</button>
        </div>
    )
}

export default CreatePost;