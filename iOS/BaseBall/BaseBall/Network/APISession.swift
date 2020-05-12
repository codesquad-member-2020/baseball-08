//
//  APISession.swift
//  BaseBall
//
//  Created by Cloud on 2020/05/07.
//  Copyright © 2020 Cloud. All rights reserved.
//

import Foundation
import Combine

protocol APISessionProviding {
    func execute<T>(_ requestProviding: RequestProviding) -> AnyPublisher<T, Error>
        where T: Decodable
}

struct APISession: APISessionProviding {
    
    // MARK: - Methods
    func execute<T>(_ requestProviding: RequestProviding) -> AnyPublisher<T, Error>
        where T : Decodable {
            URLSession.shared
                .dataTaskPublisher(for: requestProviding.request)
                .map { $0.data }
                .decode(type: T.self, decoder: JSONDecoder())
                .eraseToAnyPublisher()
    }
}

protocol Providing {
    var apiSession: APISessionProviding { get }
    
    func get<T>(_ type: T.Type,
                path: String) -> AnyPublisher<[T], Error>
        where T: Decodable
}

struct Provider: Providing {
    
    // MARK: - Properties
    var apiSession: APISessionProviding
    
    // MARK: - Methods
    func get<T>(_ type: T.Type,
                path: String) -> AnyPublisher<[T], Error>
        where T : Decodable {
            apiSession
                .execute(Endpoint(path: path))
                .eraseToAnyPublisher()
    }
}

