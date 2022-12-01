import axios from 'axios';

const API =  axios.create({ baseURL: 'https://csci-201-project-368421.uw.r.appspot.com' });

export const APILogIn = (formData) =>  API.get(`/api/authenticate/${formData.email}/${formData.password}`);
export const APIRegisterUser = (formData) =>  API.post(`/api/registerUser`, formData);
export const createPost = (newPost) => API.post('/posts', newPost);
// export const APIFetchPosts = () => API.get(`/posts?page=${page}`);
// export const APIFetchCuratedPosts = (id) => API.get(`/posts/${id}`);


  