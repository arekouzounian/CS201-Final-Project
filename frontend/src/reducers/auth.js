import { LOGIN, REGISTER } from '../constants/actionTypes';

const authReducer = (state = null, action) => {
    switch(action.type) {
        case LOGIN:
            const form = JSON.parse(localStorage.getItem('profile'))
            localStorage.setItem('profile', JSON.stringify({...form, displayName: action.data }))
            return {...state};
        case REGISTER:
            const user = JSON.parse(localStorage.getItem('profile'))
            localStorage.setItem('profile', JSON.stringify({...user, displayName: action.data}))
            return {...state};
        default:
            return state;
    }
    
}

export default authReducer;