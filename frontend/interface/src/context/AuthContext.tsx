
import React, { createContext, useContext, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "@/components/ui/sonner";
import { authApi, employeeApi } from "@/utils/api";
import jwtDecode from "jwt-decode";

interface User {
  id: number;
  email: string;
  role: "admin" | "employee";
  name: string;
}

interface JwtPayload {
  sub: string; // email
  exp: number;
}

interface AuthContextType {
  user: User | null;
  login: (email: string, password: string) => Promise<void>;
  logout: () => void;
  isLoading: boolean;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [user, setUser] = useState<User | null>(null);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const navigate = useNavigate();

  // Check for existing session on component mount
  useEffect(() => {
    const checkAuth = async () => {
      const token = localStorage.getItem("token");
      if (token) {
        try {
          // Verify token is valid and not expired
          const decoded = jwtDecode<JwtPayload>(token);
          const currentTime = Date.now() / 1000;

          if (decoded.exp < currentTime) {
            // Token expired
            localStorage.removeItem("token");
            localStorage.removeItem("user");
            setIsLoading(false);
            return;
          }

          // Token is valid, get user info
          const storedUser = localStorage.getItem("user");
          if (storedUser) {
            setUser(JSON.parse(storedUser));
          } else {
            // If we have a token but no user, fetch user info
            try {
              const email = decoded.sub;
              const response = await employeeApi.getByEmail(email);
              const userData = response.data;

              // Determine role based on position
              // This is a simplification - you might need to adjust based on your actual role determination logic
              const role = userData.positionId === 1 ? "admin" : "employee";

              const user: User = {
                id: userData.id || 0,
                email: userData.email,
                name: `${userData.name} ${userData.lastName}`,
                role: role
              };

              localStorage.setItem("user", JSON.stringify(user));
              setUser(user);
            } catch (error) {
              console.error("Error fetching user data:", error);
              localStorage.removeItem("token");
            }
          }
        } catch (error) {
          console.error("Invalid token:", error);
          localStorage.removeItem("token");
          localStorage.removeItem("user");
        }
      }
      setIsLoading(false);
    };

    checkAuth();
  }, []);

  const login = async (email: string, password: string) => {
    setIsLoading(true);
    try {
      if (!email || !password) {
        throw new Error("Email and password are required");
      }

      // Call the login API
      const response = await authApi.login({ email, password });
      const { token } = response.data;

      // Store the token
      localStorage.setItem("token", token);

      // Decode token to get user email
      const decoded = jwtDecode<JwtPayload>(token);
      const userEmail = decoded.sub;

      // Get user details
      const userResponse = await employeeApi.getByEmail(userEmail);
      const userData = userResponse.data;

      // Determine role based on position
      // This is a simplification - you might need to adjust based on your actual role determination logic
      const role = userData.positionId === 1 ? "admin" : "employee";

      const user: User = {
        id: userData.id || 0,
        email: userData.email,
        name: `${userData.name} ${userData.lastName}`,
        role: role
      };

      localStorage.setItem("user", JSON.stringify(user));
      setUser(user);

      // Redirect based on user role
      if (user.role === "admin") {
        navigate("/admin/dashboard");
      } else {
        navigate("/employee/profile");
      }

      toast.success("Login successful", {
        description: `Welcome back, ${user.name}!`
      });
    } catch (error) {
      toast.error("Login failed", {
        description: error instanceof Error ? error.message : "Invalid credentials or server error"
      });
      console.error("Login error:", error);
    } finally {
      setIsLoading(false);
    }
  };

  const logout = () => {
    localStorage.removeItem("user");
    localStorage.removeItem("token");
    setUser(null);
    navigate("/");
    toast.success("Logged out successfully");
  };

  return (
    <AuthContext.Provider value={{ user, login, logout, isLoading }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};
