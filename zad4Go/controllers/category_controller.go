package controllers

import (
	"net/http"
	"shop/database"
	"shop/models"

	"github.com/labstack/echo/v4"
)

func CreateCategory(c echo.Context) error {
	var category models.Category
	if err := c.Bind(&category); err != nil {
		return c.JSON(http.StatusBadRequest, err.Error())
	}
	database.DB.Create(&category)
	return c.JSON(http.StatusCreated, category)
}

