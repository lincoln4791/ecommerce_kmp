package com.example.ecommerce.ui.views.products.model

import com.example.shared.data.model.responses.product.ProductModel

data class ProductModelUiModel(
    val id: Int,
    val name: String,
    val description : String?,
    val brand : BrandUiModel
){
    companion object{
        fun fromProductModel(productModel: ProductModel): ProductModelUiModel{
            return ProductModelUiModel(
                productModel.id,
                productModel.name,
                productModel.description,
                BrandUiModel.fromBrand(productModel.brand)
            )
        }
    }
}

