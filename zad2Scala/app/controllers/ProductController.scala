package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.{Product, ProductRepository}

@Singleton
class ProductController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  implicit val productFormat: OFormat[Product] = Json.format[Product]

  // GET /products
  def getAll = Action {
    Ok(Json.toJson(ProductRepository.getAll))
  }

  // GET /products/:id
  def getById(id: Long) = Action {
    ProductRepository.getById(id) match {
      case Some(product) => Ok(Json.toJson(product))
      case None => NotFound(Json.obj("message" -> s"Product with id $id not found"))
    }
  }

  // GET /products/:categoryId
  def getByCategory(categoryId: Long) = Action {
    Ok(Json.toJson(ProductRepository.getByCategory(categoryId)))
  }

  // POST /products
  def add = Action(parse.json) { request =>
    request.body.validate[Product] match {
      case JsSuccess(product, _) =>
        ProductRepository.add(product) match {
          case Right(addedProduct) =>
            Created(Json.toJson(addedProduct))
          case Left(errorMessage) =>
            BadRequest(Json.obj("message" -> errorMessage))
        }

      case JsError(errors) =>
        BadRequest(Json.obj("message" -> "Invalid JSON", "errors" -> JsError.toJson(errors)))
    }
  }

  // PUT /products/:id
  def update(id: Long) = Action(parse.json) { request =>
    request.body.validate[Product] match {
      case JsSuccess(updatedProduct, _) =>
        ProductRepository.update(id, updatedProduct) match {
          case Some(product) => Ok(Json.toJson(product))
          case None => NotFound(Json.obj("message" -> s"Product with id $id not found"))
        }

      case JsError(errors) =>
        BadRequest(Json.obj("message" -> "Invalid JSON", "errors" -> JsError.toJson(errors)))
    }
  }

  // DELETE /products/:id
  def delete(id: Long) = Action {
    if (ProductRepository.delete(id)) {
      Ok(Json.obj("message" -> s"Product with id $id deleted"))
    } else {
      NotFound(Json.obj("message" -> s"Product with id $id not found"))
    }
  }
}