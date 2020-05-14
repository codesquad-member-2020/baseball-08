//
//  Endpoint.swift
//  BaseBall
//
//  Created by Cloud on 2020/05/07.
//  Copyright © 2020 Cloud. All rights reserved.
//

import Foundation

protocol RequestProviding {
    var request: URLRequest { get }
}

struct Endpoint: RequestProviding {
    
    enum Path: CustomStringConvertible {
        var description: String {
            switch self {
            case .game:
                return "/game"
            }
        }
        
        case game
    }
    
    // MARK: - Properties
    static let baseURL: String = "4ea8bf16-a9c4-4101-8626-a7c53c0b1e89.mock.pstmn.io"
    static let gamePath: String = "/game"
    let path: Path
    var request: URLRequest {
        var components = URLComponents()
        components.scheme = "https"
        components.host = Endpoint.baseURL
        components.path = path.description
        guard let url = components.url else { preconditionFailure("Invalid URL") }
        
        return URLRequest(url: url)
    }
}


