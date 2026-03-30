package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.{Cart, CartRepository}

@Singleton
class CartController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  implicit val cartFormat: OFormat[Cart] = Json.format[Cart]

  // GET /carts
  def getAll = Action {
    Ok(Json.toJson(CartRepository.carts))
  }

  // GET /carts/:id
  def getById(id: Long) = Action {
    CartRepository.carts.find(_.id == id) match {
      case Some(cart) => Ok(Json.toJson(cart))
      case None => NotFound(Json.obj("message" -> s"Cart with id $id not found"))
    }
  }

  // POST /carts
  def add = Action(parse.json) { request =>
    request.body.validate[Cart] match {
      case JsSuccess(cart, _) =>
        if (CartRepository.carts.exists(_.id == cart.id)) {
          BadRequest(Json.obj("message" -> s"Cart with id ${cart.id} already exists"))
        } else {
          CartRepository.carts = CartRepository.carts :+ cart
          Created(Json.toJson(cart))
        }

      case JsError(errors) =>
        BadRequest(Json.obj("message" -> "Invalid JSON", "errors" -> JsError.toJson(errors)))
    }
  }

  // PUT /carts/:id
  def update(id: Long) = Action(parse.json) { request =>
    request.body.validate[Cart] match {
      case JsSuccess(updatedCart, _) =>
        CartRepository.carts.indexWhere(_.id == id) match {
          case -1 =>
            NotFound(Json.obj("message" -> s"Cart with id $id not found"))
          case index =>
            val cartToSave = updatedCart.copy(id = id)
            CartRepository.carts = CartRepository.carts.updated(index, cartToSave)
            Ok(Json.toJson(cartToSave))
        }

      case JsError(errors) =>
        BadRequest(Json.obj("message" -> "Invalid JSON", "errors" -> JsError.toJson(errors)))
    }
  }

  // DELETE /carts/:id
  def delete(id: Long) = Action {
    if (CartRepository.carts.exists(_.id == id)) {
      CartRepository.carts = CartRepository.carts.filterNot(_.id == id)
      Ok(Json.obj("message" -> s"Cart with id $id deleted"))
    } else {
      NotFound(Json.obj("message" -> s"Cart with id $id not found"))
    }
  }
}