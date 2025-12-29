//
//  Extentions.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 29/12/25.
//
import SwiftUI
extension Double {
    var takaFormatted: String {
        let formatter = NumberFormatter()
        formatter.numberStyle = .currency
        formatter.currencySymbol = "৳ "
        formatter.minimumFractionDigits = 2
        formatter.maximumFractionDigits = 2
        return formatter.string(from: NSNumber(value: self)) ?? "৳ 0.00"
    }
}
