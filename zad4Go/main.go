package main

import (
	"shop/controllers"
	"shop/database"
	"shop/models"

	"github.com/labstack/echo/v4"
)

func main() {
	// Połączenie z bazą
	database.Connect()

	// Automatyczna migracja wszystkich modeli
	database.DB.AutoMigrate(&models.Category{}, &models.Product{}, &models.Cart{})

	// 3.0 Należy stworzyć aplikację we frameworki echo w j. Go, która będzie miała kontroler Produktów zgodny z CRUD
	e := echo.New()

	// Trasy dla Kategorii
	e.POST("/categories", controllers.CreateCategory)

	// Trasy dla Produktów
	e.POST("/products", controllers.CreateProduct)
	e.GET("/products", controllers.GetProducts)
	e.GET("/products/:id", controllers.GetProduct)
	e.PUT("/products/:id", controllers.UpdateProduct)
	e.DELETE("/products/:id", controllers.DeleteProduct)

	// Trasy dla Koszyka
	e.POST("/carts", controllers.CreateCart)
	e.GET("/carts", controllers.GetCarts)

	// Uruchomienie serwera
	e.Logger.Fatal(e.Start(":8080"))
}