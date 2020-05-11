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
            return URLSession.shared
                .dataTaskPublisher(for: requestProviding.request)
                .map { $0.data }
                .decode(type: T.self, decoder: JSONDecoder())
                .eraseToAnyPublisher()
    }
}

protocol GameProviding {
    var apiSession: APISessionProviding { get }
    
    func getGame() -> AnyPublisher<[Game], Error>
}

struct GameProvider: GameProviding {
    
    // MARK: - Properties
    var apiSession: APISessionProviding
    
    // MARK: - Methods
    func getGame() -> AnyPublisher<[Game], Error> {
        apiSession
            .execute(Endpoint(path: "/game"))
            .eraseToAnyPublisher()
    }
}
