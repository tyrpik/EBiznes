package controllers

import (
	"net/http"
	"shop/database"
	"shop/models"

	"github.com/labstack/echo/v4"
)

// 4.0 Należy dodać model Koszyka oraz dodać odpowiedni endpoint

func CreateCart(c echo.Context) error {
	var cart models.Cart
	if err := c.Bind(&cart); err != nil {
		return c.JSON(http.StatusBadRequest, err.Error())
	}
	database.DB.Create(&cart)
	return c.JSON(http.StatusCreated, cart)
}

func GetCarts(c echo.Context) error {
	var carts []models.Cart
	database.DB.Find(&carts)
	return c.JSON(http.StatusOK, carts)
}