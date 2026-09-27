import { createContext, useCallback, useEffect, useMemo, useState } from 'react';
import { http, getStoredAuth, toApiError } from '../api/http';
import { platformApi } from '../api/platform';

const AuthContext = createContext(null);
const AUTH_KEY = 'vari-mitra-auth';

const publicUser = (user) => ({
  id: user.id,
  name: user.name,
  mobile: user.mobile,
  email: user.email,
  role: user.role,
});

export function AuthProvider({ children }) {
  const [auth, setAuth] = useState(() => getStoredAuth());
  const [isAuthenticating, setIsAuthenticating] = useState(false);

  const clearAuth = useCallback(() => {
    sessionStorage.removeItem(AUTH_KEY);
    localStorage.removeItem(AUTH_KEY);
    setAuth(null);
  }, []);

  useEffect(() => {
    window.addEventListener('vari-mitra-session-expired', clearAuth);
    return () => window.removeEventListener('vari-mitra-session-expired', clearAuth);
  }, [clearAuth]);

  const login = useCallback(async ({ mobile, password, rememberMe }) => {
    setIsAuthenticating(true);
    try {
      const basicAuth = window.btoa(`${mobile.trim()}:${password}`);
      const headers = { Authorization: `Basic ${basicAuth}` };

      // The supplied backend has HTTP Basic enabled but no /auth/login endpoint.
      await http.get('/health', { headers });
      const { data: users } = await http.get('/users', { headers });
      const matchingUser = users.find((user) => user.mobile === mobile.trim());

      if (!matchingUser) {
        throw new Error('No profile matches this mobile number.');
      }

      const nextAuth = { user: publicUser(matchingUser), basicAuth };
      const storage = rememberMe ? localStorage : sessionStorage;
      sessionStorage.removeItem(AUTH_KEY);
      localStorage.removeItem(AUTH_KEY);
      storage.setItem(AUTH_KEY, JSON.stringify(nextAuth));
      setAuth(nextAuth);
      return nextAuth.user;
    } catch (error) {
      throw new Error(error.message === 'No profile matches this mobile number.' ? error.message : toApiError(error));
    } finally {
      setIsAuthenticating(false);
    }
  }, []);

  const register = useCallback(async (payload) => {
    await platformApi.createUser(payload);
    return login({ mobile: payload.mobile, password: payload.password, rememberMe: true });
  }, [login]);

  const updateStoredUser = useCallback((user) => {
    setAuth((current) => {
      if (!current) return current;
      const nextAuth = { ...current, user: publicUser(user) };
      const storage = localStorage.getItem(AUTH_KEY) ? localStorage : sessionStorage;
      storage.setItem(AUTH_KEY, JSON.stringify(nextAuth));
      return nextAuth;
    });
  }, []);

  const value = useMemo(() => ({
    user: auth?.user ?? null,
    isAuthenticated: Boolean(auth?.user),
    isAuthenticating,
    login,
    register,
    logout: clearAuth,
    updateStoredUser,
  }), [auth?.user, clearAuth, isAuthenticating, login, register, updateStoredUser]);

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export default AuthContext;
