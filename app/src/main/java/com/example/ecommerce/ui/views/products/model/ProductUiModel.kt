package com.example.ecommerce.ui.views.products.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ecommerce.ui.views.utils.FakeDataUtils
import com.example.shared.data.model.responses.product.ProductsDataItem
import kotlin.random.Random

data class ProductUiModel(
    val id: Int,
    val name : String,
    val price: Double,
    val stock: Int,
    val model: ProductModelUiModel,
    val category: CategoryUiModel,
    val displayPrice: String,
    val rating: Float,
    val ratingCount: Int,
    val imageUrl: ImageVector?=null,
    val isInStock: Boolean,
    val productStatus : String
){
    companion object{
        fun fromProductDataItem(productDataItem : ProductsDataItem) : ProductUiModel{
            return ProductUiModel(
                productDataItem.id,
                name=productDataItem.name,
                model = ProductModelUiModel.fromProductModel(productDataItem.model),
                category = CategoryUiModel.fromCategory(productDataItem.category),
                price = productDataItem.price,
                displayPrice =  productDataItem.price.toString(),
                stock = productDataItem.stock,
                rating = (Random.nextInt(2, 10)) / 2f,
                ratingCount = Random.nextInt(1,50),
                imageUrl = FakeDataUtils.getRandomIconList()[Random.nextInt(1,FakeDataUtils.getRandomIconList().size)],
                isInStock=productDataItem.stock>0,
                productStatus = productDataItem.productStatus
            )
        }
    }
}



