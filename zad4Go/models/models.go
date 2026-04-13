package models

import "gorm.io/gorm"

// 3.5 Należy stworzyć model Produktów wykorzystując gorm oraz wykorzystać model do obsługi produktów (CRUD) w kontrolerze (zamiast listy)
type Product struct {
	gorm.Model
	Name string `json:"name"`
	Price float64 `json:"price"`
	CategoryID uint `json:"category_id"`
	Category Category `json:"category"`
}

// 4.0 Należy dodać model Koszyka oraz dodać odpowiedni endpoint
type Cart struct {
	gorm.Model
	Status string `json:"status"`
}

//4.5 Należy stworzyć model kategorii i dodać relację między kategorią, a produktem)
type Category struct {
	gorm.Model
	Name string `json:"name"`
	Products []Product `json:"products,omitempty"`
}

// 5.0 pogrupować zapytania w gorm’owe scope'y
// Scope: Pobiera produkty tańsze niż podana cena
func PriceLowerThan(minPrice float64) func(db *gorm.DB) *gorm.DB {
	return func(db *gorm.DB) *gorm.DB {
		return db.Where("price < ?", minPrice)
	}
}
// Scope: Dołącza dane o kategorii (Preload) do zapytania o produkt
func WithCategory(db *gorm.DB) *gorm.DB {
	return db.Preload("Category")
}