import { useState } from "react";
import {useNavigate, Link} from "react-router-dom";
import "./Auth.css";
import api from "../api";

function Signup(){
    const navigate = useNavigate();
    const [user, setUser] = useState({
        name: "",
        email: "",
        password: ""
    });

    const handleChange = (e) => {
        setUser({...user, [e.target.name] : e.target.value});
    };

    const handleSignup = async () => {
    if (!user.name || !user.email || !user.password) {
      alert("Fill all fields");
      return;
    }

    try {
      await api.post("/addUser", user);
      alert("Signup Successful");
      navigate("/");
    } catch (error) {
      alert(error.response?.data || "Email already Exist");
    }
  };

    return(
        <div className="auth-bg vh-100 d-flex justify-content-center align-items-center">
            <div className="card auth-card p-4 shadow" style={{width: "360px"}}>

            <h2 className="text-center auth-title mb-3">Signup</h2>

            <input className="form-control mb-3" name="name" placeholder="Name"
            onChange={handleChange} />
            <br/>

            <input className="form-control mb-3" type="email" name="email" placeholder="Email"
            onChange={handleChange} />
            <br/>

            <input className="form-control mb-3" type="password" name="password" placeholder="Password"
            onChange={handleChange} />
            <br/>

            <button className="btn-btn-sucess w-100 auth-btn" onClick={handleSignup}>Signup</button>

            <p className="text-center mt-3">
                Already have an account? <Link to="/">Login</Link>
            </p>
         </div>
        </div>
    );
}

export default Signup;