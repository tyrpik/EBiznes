import React, { useEffect, useState, useContext } from 'react';
import axios from 'axios';
import { CartContext } from './CartContext';

function Produkty() {
  const [products, setProducts] = useState([]);
  const { addToCart } = useContext(CartContext);

  useEffect(() => {
    axios
      .get('http://localhost:8080/products')
      .then((response) => setProducts(response.data))
      .catch((error) => console.error('Błąd pobierania:', error));
  }, []);

  return (
    <div>
      <h2 className="mb-4">Lista produktów</h2>

      <div className="row">
        {products.map((p) => (
          <div className="col-md-4 mb-4" key={p.id}>
            <div className="card h-100 shadow-sm">
              <div className="card-body d-flex flex-column">
                <h5 className="card-title">{p.name}</h5>
                <p className="card-text fs-5 text-primary">{p.price} PLN</p>

                <button
                  className="btn btn-primary mt-auto"
                  onClick={() => addToCart(p)}
                >
                  Dodaj do koszyka
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Produkty;
