import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import "./InfoBox.css";


export default function InfoBox({info}){
  const NORMAL_URL = "https://images.unsplash.com/photo-1758521959341-398692c140a7?w=1000&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTR8fG5vcm1hbCUyMHdlYXRoZXJ8ZW58MHx8MHx8fDA%3D";

  const HOT_URL="https://images.unsplash.com/photo-1504370805625-d32c54b16100?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aG90JTIwd2VhdGhlcnxlbnwwfHwwfHx8MA%3D%3D";

  const COLD_URL="https://images.unsplash.com/photo-1612208695882-02f2322b7fee?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8Y29sZCUyMHdlYXRoZXJ8ZW58MHx8MHx8fDA%3D";

  const RAIN_URL="https://media.istockphoto.com/id/1257951336/photo/transparent-umbrella-under-rain-against-water-drops-splash-background-rainy-weather-concept.webp?a=1&b=1&s=612x612&w=0&k=20&c=sw_CRZcGopaGHDWqtT1M8y64k5uCcq-nro55Bw3YzyQ=";
  
    return(
    <div className="InfoBox">
        <div className='cardContainer'>
        <Card sx={{ maxWidth: 345 }}>
      <CardMedia sx={{ height: 140 }} image={info.isRaining ? RAIN_URL : info.temp < 15 ? COLD_URL : info.temp < 30 ? NORMAL_URL : HOT_URL} title="green iguana"/>
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {info.city}
        </Typography>
        <Typography variant="body2"  color='text.secondary'component={"span"}>
         <p>Temperature = {info.temp}&deg;C</p>
         <p>Humidity = {info.humidity}</p>
         <p>Min Temp = {info.tempMin}&deg;C</p>
         <p>Max Temp = {info.tempMax}&deg;C</p>
         <p>The weather can be described as <b><i><u>{info.weather}</u></i></b> and feels like {info.feelsLike}&deg;C</p>
         <p>Wind Speed = {info.windSpeed} km/h</p>
         <p>{info.isRaining ? "Rain is happening - Carry an umbrella" : "No rain - Enjoy the weather"}</p>
         {/* <p>{info.timeOfDay === "Day" ? "Day Time - clear visibility" : "Night Time - drive carefully"}</p> */}
        </Typography>
      </CardContent>
    </Card>
    </div>
    </div>
   )
}