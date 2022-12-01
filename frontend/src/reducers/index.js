import { combineReducers } from 'redux';

import authReducer from './auth';
import postReducer from './posts';

export const reducers = combineReducers( { authReducer, postReducer });