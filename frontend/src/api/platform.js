import { http } from './http';

export const platformApi = {
  health: () => http.get('/health').then(({ data }) => data),
  weather: () => http.get('/weather').then(({ data }) => data),
  contact: () => http.get('/contact').then(({ data }) => data),

  routes: () => http.get('/routes').then(({ data }) => data),
  route: (id) => http.get(`/routes/${id}`).then(({ data }) => data),

  waterPoints: () => http.get('/waterpoints').then(({ data }) => data),
  waterPointsByStop: (stopId) =>
    http.get(`/waterpoints/stop/${stopId}`).then(({ data }) => data),

  events: () => http.get('/events').then(({ data }) => data),

  user: (id) => http.get(`/users/${id}`).then(({ data }) => data),
  users: () => http.get('/users').then(({ data }) => data),
  createUser: (user) => http.post('/users', user).then(({ data }) => data),
  updateUser: (id, user) =>
    http.put(`/users/${id}`, user).then(({ data }) => data),
};