package com.example.ecommerce.ui.views.products.model

import com.example.shared.data.model.responses.product.ProductBrand
import com.example.shared.data.model.responses.product.ProductCategory

data class CategoryUiModel(
    val id: Int,
    val name: String,
    val description : String?
){
    companion object{
        fun fromCategory(category: ProductCategory): CategoryUiModel{
            return CategoryUiModel(
                category.id,
                category.name,
                category.description
            )
        }
    }
}


