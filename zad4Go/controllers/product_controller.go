package controllers

import (
	"net/http"
	"shop/database"
	"shop/models"

	"github.com/labstack/echo/v4"
)

func CreateProduct(c echo.Context) error {
	var product models.Product
	if err := c.Bind(&product); err != nil {
		return c.JSON(http.StatusBadRequest, err.Error())
	}
	// 3.5 ...wykorzystać model do obsługi produktów (CRUD) w kontrolerze (zamiast	listy)
	database.DB.Create(&product)
	return c.JSON(http.StatusCreated, product)
}

func GetProducts(c echo.Context) error {
	var products []models.Product
	
	// 5.0 pogrupować zapytania w gorm’owe scope'y (użycie zapytań)
	database.DB.Scopes(models.PriceLowerThan(4), models.WithCategory).Find(&products)
	
	return c.JSON(http.StatusOK, products)
}

func GetProduct(c echo.Context) error {
	id := c.Param("id")
	var product models.Product
	result := database.DB.First(&product, id)
	if result.Error != nil {
		return c.JSON(http.StatusNotFound, "Nie znaleziono produktu")
	}
	return c.JSON(http.StatusOK, product)
}

func UpdateProduct(c echo.Context) error {
	id := c.Param("id")
	var product models.Product
	if err := database.DB.First(&product, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, "Nie znaleziono produktu")
	}

	if err := c.Bind(&product); err != nil {
		return c.JSON(http.StatusBadRequest, err.Error())
	}

	database.DB.Save(&product)
	return c.JSON(http.StatusOK, product)
}

func DeleteProduct(c echo.Context) error {
	id := c.Param("id")
	database.DB.Delete(&models.Product{}, id)
	return c.NoContent(http.StatusNoContent)
}