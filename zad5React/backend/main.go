package main

import (
	"encoding/json"
	"net/http"
)

// Struktura produktu
type Product struct {
	ID int `json:"id"`
	Name string `json:"name"`
	Price float64 `json:"price"`
}

// Middleware dodający nagłówki CORS (5.0)
func enableCORS(next http.HandlerFunc) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		w.Header().Set("Access-Control-Allow-Origin", "*")
		w.Header().Set("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
		w.Header().Set("Access-Control-Allow-Headers", "Content-Type")
		if r.Method == "OPTIONS" {
			w.WriteHeader(http.StatusOK)
			return
		}
		next(w, r)
	}
}

func productsHandler(w http.ResponseWriter, r *http.Request) {
	products :=[]Product{
		{ID: 1, Name: "Krzesło", Price: 120.50},
		{ID: 2, Name: "Dywan", Price: 500.00},
		{ID: 3, Name: "Sofa", Price: 3500.00},
	}
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(products)
}

func cartHandler(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(map[string]string{"status": "Koszyk zapisany na serwerze"})
}

func paymentsHandler(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(map[string]string{"status": "Płatność zrealizowana pomyślnie"})
}

func main() {
	http.HandleFunc("/products", enableCORS(productsHandler))
	http.HandleFunc("/cart", enableCORS(cartHandler))
	http.HandleFunc("/payments", enableCORS(paymentsHandler))

	http.ListenAndServe(":8080", nil)
}