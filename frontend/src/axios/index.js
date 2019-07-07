import axios from "axios";
import {BACKEND_ROOT_URL} from "../constants";

export default axios.create({
  baseURL: BACKEND_ROOT_URL + '/api',
  headers: {Authorization: `Bearer ${localStorage.getItem('accessToken')}`}
});
