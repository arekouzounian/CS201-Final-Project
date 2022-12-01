import { CREATE, FETCH_CURATED,FETCH_ALL } from '../constants/actionTypes';


const postReducer = (posts = [], action) => {
    switch(action.type) {
        case CREATE:
            return [...posts, action.payload];
        case FETCH_ALL: 
            return action.payload;
        case FETCH_CURATED:
            return action.payload;
        default:
            return posts; 
    }
}

export default postReducer; 