import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import { CartProvider } from './CartContext';
import Produkty from './Produkty';
import Koszyk from './Koszyk';
import Platnosci from './Platnosci';

function App() {
  return (
    <CartProvider>
      <Router>
        <div>
          <nav
            className="navbar navbar-expand-lg bg-primary"
            data-bs-theme="dark"
          >
            <div className="container">
              <Link className="navbar-brand" to="/">
                E-Sklep
              </Link>

              <div className="collapse navbar-collapse show">
                <ul className="navbar-nav me-auto">
                  <li className="nav-item">
                    <Link className="nav-link" to="/">
                      Produkty
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link className="nav-link" to="/koszyk">
                      Koszyk
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link className="nav-link" to="/platnosci">
                      Płatności
                    </Link>
                  </li>
                </ul>
              </div>
            </div>
          </nav>

          <div className="container mt-5">
            <Routes>
              <Route path="/" element={<Produkty />} />
              <Route path="/koszyk" element={<Koszyk />} />
              <Route path="/platnosci" element={<Platnosci />} />
            </Routes>
          </div>
        </div>
      </Router>
    </CartProvider>
  );
}

export default App;
