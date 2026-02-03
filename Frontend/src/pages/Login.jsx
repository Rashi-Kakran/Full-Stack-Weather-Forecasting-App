import { useState, useContext } from "react";
import {useNavigate, Link} from "react-router-dom";
import "./Auth.css";
import api from "../api";
import { AuthContext } from "../auth/AuthContext";

function Login() {
    const navigate = useNavigate();
    const { fetchUser } = useContext(AuthContext);
     
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
       
    const handleLogin = async () => {
      if(!email || !password){
        alert("Enter email and password");
        return;
      }

    try {
    const response = await api.post("/loginUser", {
      email,
      password,
    });

    //Save JWT token
    localStorage.setItem("token", response.data.token);
    await fetchUser();    
    alert("Login Successful");   
    navigate("/weather");
  } catch (error) {
    alert(error.response?.data ||"Invalid email or password");
  }
  };

  const googleLogin = () => {
    window.location.href = `${import.meta.env.VITE_API_URL}/oauth2/authorization/google`;
  };

    return(
        <div className="auth-bg vh-100 d-flex justify-content-center align-items-center">
            <div className="card auth-card p-4 shadow" style={{width: "360px"}}>

            <h2 className="text-center auth-title mb-3">Login <div>Welcome Back</div></h2>

            <input className="form-control mb-3" placeholder="Email"
            onChange={(e) => setEmail(e.target.value)}/>
            <br />

            <input className="form-control mb-3" type="password" placeholder="Password"
            onChange={(e) => setPassword(e.target.value)}/>
            <br/>

            <button className="btn-btn-primary w-100 auth-btn" onClick={handleLogin}>Login</button>

            <hr />

            <button className="btn-btn-primary w-100 auth-btn" onClick={googleLogin}>Login with Google</button>

            <p className="text-center mt-3">
                New user? <Link to="/signup">Signup</Link>
            </p>
        </div>
        </div>
    );
}

export default Login;