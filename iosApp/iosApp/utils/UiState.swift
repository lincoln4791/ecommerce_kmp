//
//  UiState.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 31/12/25.
//


enum UiState<T> {
    case idle
    case loading
    case success(T)
    case error(String)
}
extension UiState {
    var isLoading: Bool {
        if case .loading = self { return true }
        return false
    }
}
