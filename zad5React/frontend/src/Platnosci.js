import React, { useContext } from 'react';
import axios from 'axios';
import { CartContext } from './CartContext';
import { useNavigate } from 'react-router-dom';

function Platnosci() {
  const { cart, clearCart } = useContext(CartContext);
  const navigate = useNavigate();

  const total = cart.reduce((sum, item) => sum + item.price, 0);

  const processPayment = () => {
    axios
      .post('http://localhost:8080/payments', { amount: total })
      .then((response) => {
        alert('Status płatności: ' + response.data.status);
        clearCart();
        navigate('/');
      })
      .catch((error) => console.error('Błąd:', error));
  };

  return (
    <div className="row justify-content-center">
      <div className="col-md-6">
        <div className="card border-primary shadow-sm">
          <div className="card-header bg-primary text-white">
            <h3 className="mb-0">Podsumowanie zamówienia</h3>
          </div>

          <div className="card-body">
            {cart.length === 0 ? (
              <div className="alert alert-warning">Twój koszyk jest pusty.</div>
            ) : (
              <>
                <ul className="list-group mb-4">
                  {cart.map((item, index) => (
                    <li
                      key={index}
                      className="list-group-item d-flex justify-content-between align-items-center"
                    >
                      <span>{item.name}</span>
                      <span className="badge bg-secondary rounded-pill">
                        {item.price} PLN
                      </span>
                    </li>
                  ))}
                </ul>

                <div className="d-flex justify-content-between align-items-center mb-4">
                  <span className="h5 mb-0">Do zapłaty:</span>
                  <span className="h4 text-primary mb-0">{total} PLN</span>
                </div>

                <div className="d-grid gap-2">
                  <button
                    onClick={processPayment}
                    className="btn btn-success btn-lg"
                    type="button"
                  >
                    Zrealizuj płatność
                  </button>

                  <button
                    onClick={() => navigate('/')}
                    className="btn btn-outline-secondary"
                    type="button"
                  >
                    Wróć do produktów
                  </button>
                </div>
              </>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Platnosci;
