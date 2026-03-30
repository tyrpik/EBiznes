package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.{Category, CategoryRepository}

@Singleton
class CategoryController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  implicit val categoryFormat: OFormat[Category] = Json.format[Category]

  // GET /categories
  def getAll = Action {
    Ok(Json.toJson(CategoryRepository.categories))
  }

  // GET /categories/:id
  def getById(id: Long) = Action {
    CategoryRepository.categories.find(_.id == id) match {
      case Some(category) => Ok(Json.toJson(category))
      case None => NotFound(Json.obj("message" -> s"Category with id $id not found"))
    }
  }

  // POST /categories
  def add = Action(parse.json) { request =>
    request.body.validate[Category] match {
      case JsSuccess(category, _) =>
        if (CategoryRepository.categories.exists(_.id == category.id)) {
          BadRequest(Json.obj("message" -> s"Category with id ${category.id} already exists"))
        } else {
          CategoryRepository.categories = CategoryRepository.categories :+ category
          Created(Json.toJson(category))
        }

      case JsError(errors) =>
        BadRequest(Json.obj("message" -> "Invalid JSON", "errors" -> JsError.toJson(errors)))
    }
  }

  // PUT /categories/:id
  def update(id: Long) = Action(parse.json) { request =>
    request.body.validate[Category] match {
      case JsSuccess(updatedCategory, _) =>
        CategoryRepository.categories.indexWhere(_.id == id) match {
          case -1 =>
            NotFound(Json.obj("message" -> s"Category with id $id not found"))
          case index =>
            val categoryToSave = updatedCategory.copy(id = id)
            CategoryRepository.categories = CategoryRepository.categories.updated(index, categoryToSave)
            Ok(Json.toJson(categoryToSave))
        }

      case JsError(errors) =>
        BadRequest(Json.obj("message" -> "Invalid JSON", "errors" -> JsError.toJson(errors)))
    }
  }

  // DELETE /categories/:id
  def delete(id: Long) = Action {
    if (CategoryRepository.categories.exists(_.id == id)) {
      CategoryRepository.categories = CategoryRepository.categories.filterNot(_.id == id)
      Ok(Json.obj("message" -> s"Category with id $id deleted"))
    } else {
      NotFound(Json.obj("message" -> s"Category with id $id not found"))
    }
  }
}