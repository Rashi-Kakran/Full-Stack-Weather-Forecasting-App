import { createContext, useEffect, useState } from "react";
import api from "../api";

export const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  const fetchUser = async () => {
    try {
      const res = await api.get("/api/user/me");
      setUser(res.data);
    } catch {
      localStorage.removeItem("token");
      setUser(null);
    }finally{
      setLoading(false);
    }
  };

 useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      fetchUser();
    } else {
      setLoading(false);
    }
  }, []);

  return (
    <AuthContext.Provider value={{ user, fetchUser, loading }}>
      {children}
    </AuthContext.Provider>
  );
}
