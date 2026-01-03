//
//  AppRoute.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 18/12/25.
//


enum AppRoute: Hashable {
    case login
    case register
    case profile
    
    case home
    
    case products
    case productDetail(Int)
    case ordewrProduct(Int)
    
    case orders
    case orderDetails(Int)
    
    case saleReport
}
