package models

object ProductRepository {

  private var products = List(
    Product(1, "Jablka", 5.0, 1),
    Product(2, "Banany", 6.0, 1),
    Product(3, "Mleko 1L", 4.5, 2),
    Product(4, "Ser", 12.0, 2),
    Product(5, "Kurczak filet", 18.0, 3),
    Product(6, "Szynka w plasterkach", 15.0, 3),
    Product(7, "Chleb pszenny", 3.5, 4),
    Product(8, "Bulki", 2.0, 4),
    Product(9, "Sok pomaranczowy 1L", 6.0, 5),
    Product(10, "Woda mineralna 1.5L", 3.0, 5),
    Product(11, "Czekolada mleczna", 5.0, 6),
    Product(12, "Ciastka", 7.0, 6),
    Product(13, "Chipsy", 4.0, 7),
    Product(14, "Makaron 500g", 6.0, 8),
    Product(15, "Tunczyk w puszce", 10.0, 9)
  )

  def getAll: List[Product] = products

  def getById(id: Long): Option[Product] =
    products.find(_.id == id)

  def getByCategory(categoryId: Long): List[Product] =
    products.filter(_.categoryId == categoryId)

  def add(product: Product): Either[String, Product] = {
    if (products.exists(_.id == product.id)) {
      Left(s"Product with id ${product.id} already exists")
    } else {
      products = products :+ product
      Right(product)
    }
  }

  def update(id: Long, updatedProduct: Product): Option[Product] = {
    products.indexWhere(_.id == id) match {
      case -1 => None
      case index =>
        val productToSave = updatedProduct.copy(id = id)
        products = products.updated(index, productToSave)
        Some(productToSave)
    }
  }

  def delete(id: Long): Boolean = {
    if (products.exists(_.id == id)) {
      products = products.filterNot(_.id == id)
      true
    } else {
      false
    }
  }
}