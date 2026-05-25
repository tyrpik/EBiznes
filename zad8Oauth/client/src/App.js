import { useState } from "react";
import axios from "axios";
import "./App.css";

function App() {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const register = async () => {
    const res = await axios.post(
      "http://localhost:5000/register",
      {
        username,
        password
      },
      {
        withCredentials: true
      }
    );

    alert(res.data.message);
  };

  const login = async () => {
    const res = await axios.post(
      "http://localhost:5000/login",
      {
        username,
        password
      },
      {
        withCredentials: true
      }
    );

    alert(res.data.message);
  };

  const me = async () => {
    try {
      const res = await axios.get(
        "http://localhost:5000/me",
        {
          withCredentials: true
        }
      );

      alert("User id: " + res.data.userId);

    } catch {
      alert("Not logged");
    }
  };

  const logout = async () => {
    const res = await axios.post(
      "http://localhost:5000/logout",
      {},
      {
        withCredentials: true
      }
    );

    alert(res.data.message);
  };

  return (
    <div style={{ padding: 30 }}>
      <h1>Login App</h1>

    <div className="container">
      <input
        placeholder="username"
        onChange={(e) => setUsername(e.target.value)}
      />

      <br /><br />

      <input
        type="password"
        placeholder="password"
        onChange={(e) => setPassword(e.target.value)}
      />

      <br /><br />

      <div className="buttons">
      <button onClick={register}>
        Register
      </button>

      <button onClick={login}>
        Login
      </button>

      <button onClick={me}>
        My Account
      </button>

      <button onClick={logout}>
        Logout
      </button>
      </div>
    </div>
    </div>
  );
}

export default App;