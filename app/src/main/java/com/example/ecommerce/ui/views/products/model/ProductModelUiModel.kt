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

        fun getDemoData() : ProductModelUiModel{
            return ProductModelUiModel(
                id = 101,
                name = "iPhone 15 Pro Max",
                description = "Apple’s flagship smartphone with A17 Pro chip and titanium design.",
                brand = BrandUiModel.getDemoData()
            )
        }
    }
}

