import {REACT_APP_REST_API_KEY} from '../env'
import { REACT_APP_REDIRECT_URL } from '../env';

const CLIENT_ID = REACT_APP_REST_API_KEY;
const REDIRECT_URI = REACT_APP_REDIRECT_URL;

export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;

