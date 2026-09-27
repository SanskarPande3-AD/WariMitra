import axios from 'axios';

const AUTH_KEY = 'vari-mitra-auth';
const baseURL = '/api';

export const http = axios.create({
  baseURL,
  headers: { Accept: 'application/json' },
  timeout: 12000,
});

export const getStoredAuth = () => {
  const raw = sessionStorage.getItem(AUTH_KEY) || localStorage.getItem(AUTH_KEY);
  if (!raw) return null;

  try {
    return JSON.parse(raw);
  } catch {
    sessionStorage.removeItem(AUTH_KEY);
    localStorage.removeItem(AUTH_KEY);
    return null;
  }
};

http.interceptors.request.use((config) => {
  const auth = getStoredAuth();
  if (auth?.basicAuth && !config.headers.Authorization) {
    config.headers.Authorization = `Basic ${auth.basicAuth}`;
  }
  return config;
});

http.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      window.dispatchEvent(new Event('vari-mitra-session-expired'));
    }
    return Promise.reject(error);
  },
);

export const toApiError = (error, fallback = 'Something went wrong. Please try again.') => {
  if (error.code === 'ECONNABORTED') return 'The server took too long to respond.';
  if (!error.response) return 'Unable to reach the backend. Start Spring Boot and try again.';
  if (error.response.status === 401) return 'Your mobile number or password was not accepted.';
  if (typeof error.response.data === 'string') return error.response.data;
  return error.response.data?.message || fallback;
};
