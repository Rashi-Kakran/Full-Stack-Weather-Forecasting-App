
//import './App.css';
// import SearchBox from "./SearchBox";
// import InfoBox from "./InfoBox";

import {Routes, Route} from "react-router-dom";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import MyWeatherApp from "./MyWeatherApp"
import ProtectedRoute from "./auth/ProtectedRoute";
import { AuthProvider } from "./auth/AuthContext";
import OAuthSuccess from "./pages/OAuthSuccess";

function App() {
  return (
    <AuthProvider>
    <Routes> 
      <Route path="/" element={<Login />} />
      <Route path="/signup" element={<Signup/ >} />
      <Route path="/oauth-success" element={<OAuthSuccess />} />

      <Route path="/weather" element={ <ProtectedRoute> <MyWeatherApp />  </ProtectedRoute>} />
    </Routes>
    </AuthProvider>
  );
}

export default App;
