import SearchBox from "./SearchBox";
import InfoBox from "./InfoBox";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "./auth/AuthContext";
import { useContext } from "react";


export default function MyWeatherApp(){

     const {user,fetchUser } = useContext(AuthContext);
     const navigate = useNavigate();

    const [weatherInfo,setweatherInfo] = useState({
      city: "Delhi",
      feelsLike: 15.07,
      temp: 15.05,
      tempMax: 15.05,
      tempMin: 15.05,
      humidity: 94,
      weather: "mist",    
      isRaining: false,
      windSpeed: 1.54,
    });

    let updateInfo = (newInfo) => {
        setweatherInfo(newInfo);
    }


    const logout = () => {
    localStorage.removeItem("token");
    navigate("/");
    window.location.reload();
  };

    return(
    <div style={{textAlign:"center"}}>
      <h4>Welcome, {user?.name} 👋</h4>
        <h2>Weather App</h2>
        <SearchBox updateInfo={updateInfo}/>
        <InfoBox info={weatherInfo}/>

         <button className="btn btn-primary w-100 auth-btn"
        onClick={logout}> Logout </button>

    </div>
    )
};
