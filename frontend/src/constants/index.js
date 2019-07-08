export const BACKEND_ROOT_URL = "http://localhost:3000";

export const GITHUB_OAUTH_URL = `${BACKEND_ROOT_URL}/oauth2/authorize/github?redirect_uri=http://localhost:8080/oauth2/redirect`;
export const SIGNIN_ENDPOINT_URL = `${BACKEND_ROOT_URL}/api/signin`;
export const SIGNUP_ENDPOINT_URL = `${BACKEND_ROOT_URL}/api/signup`;
export const USER_DETAILS = `${BACKEND_ROOT_URL}/api/users/me`;
