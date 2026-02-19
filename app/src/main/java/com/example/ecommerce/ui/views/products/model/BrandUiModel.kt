package com.example.ecommerce.ui.views.products.model

import com.example.shared.data.model.responses.product.ProductBrand

data class BrandUiModel(
    val id: Int,
    val name: String,
    val description : String?
){
    companion object{
        fun fromBrand(brand: ProductBrand): BrandUiModel{
            return BrandUiModel(
                brand.id,
                brand.name,
                brand.description
            )
        }

        fun getDemoData() : BrandUiModel {
            return BrandUiModel(
                id = 1,
                name = "Apple",
                description = "this is demo desc"
            )
        }
    }
}


