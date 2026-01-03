//
//  HomeCardView.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 22/12/25.
//
import SwiftUI
struct HomeCardView : View{
    
    let title: String
    let image: String
    
    var body: some View{
        
        VStack(spacing: 12){
            Image(systemName: image)
                .resizable()
                .scaledToFit()
                .frame(height: 40)
                .foregroundColor(.blue)
            
            Text(title)
                .font(.body)
                .multilineTextAlignment(.center)
            
         
            
        }.padding(12)
            .frame(maxWidth: .infinity)
            .background(Color.white)
            .cornerRadius(12)
            .shadow(radius: 2)
    }
    
    
}
