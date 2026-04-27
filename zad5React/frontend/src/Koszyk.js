import React, { useContext } from 'react';
import axios from 'axios';
import { CartContext } from './CartContext';
import { useNavigate } from 'react-router-dom';

function Koszyk() {
  const { cart } = useContext(CartContext);
  const navigate = useNavigate();

  const total = cart.reduce((sum, item) => sum + item.price, 0);

  const saveCartOnServer = () => {
    axios
      .post('http://localhost:8080/cart', { items: cart })
      .then((response) => {
        alert(response.data.status);
        navigate('/platnosci');
      })
      .catch((error) => console.error('Błąd:', error));
  };

  return (
    <div>
      <h2 className="mb-4">Twój koszyk</h2>

      {cart.length === 0 ? (
        <div className="alert alert-warning">Koszyk jest pusty</div>
      ) : (
        <div className="card shadow-sm">
          <div className="card-body">
            <ul className="list-group mb-3">
              {cart.map((item, index) => (
                <li
                  key={index}
                  className="list-group-item d-flex justify-content-between align-items-center"
                >
                  <span>{item.name}</span>
                  <span className="badge bg-primary rounded-pill">
                    {item.price} PLN
                  </span>
                </li>
              ))}
            </ul>

            <h4 className="mb-3">Suma: {total} PLN</h4>

            <button className="btn btn-primary" onClick={saveCartOnServer}>
              Wyślij koszyk i przejdź do płatności
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default Koszyk;
