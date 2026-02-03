import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import "./SearchBox.css";
import { useState } from 'react';
import api from "./api";

export default function SearchBox({updateInfo}){
     let [city, setCity] = useState("");
     let [error, setError] = useState(false);

const handleSubmit = async (e) => {
    e.preventDefault();

    if (!city.trim()) return;

    try{
    const res = await api.get(`/api/weather/${city}`);
      updateInfo(res.data);
      setCity("");
      setError("");
    }catch (err) {
    if (err.response?.status === 404) {
      setError("City not found");
    } else if (err.response?.status === 401) {
      setError("Please login again");
    } else {
      setError("Server error");
    }
  }
};

    return(
    <div className="SearchBox">
        <form onSubmit={handleSubmit}>
            <TextField id="city" label="City Name" variant="outlined" required value={city} onChange={(e) => setCity(e.target.value)} />
            <br></br>
            <br />
             <Button variant="contained" type="submit"> Search </Button>
             {error && <p style={{color:"red"}}>No such place exist!</p>}
        </form>
    </div>
    );
}