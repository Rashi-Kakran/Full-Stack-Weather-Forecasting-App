import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const OAuthSuccess = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    const token = params.get("token");

    if (token) {
      localStorage.setItem("token", token);

      // small delay ensures AuthContext sees token
      setTimeout(() => {
        navigate("/weather", { replace: true });
      }, 300);
    } else {
      navigate("/");
    }
  }, []);

  return <p>Signing in with Google...</p>;
};

export default OAuthSuccess;
