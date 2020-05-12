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
    
    // MARK: - Properties
    static let baseURL: String = "4ea8bf16-a9c4-4101-8626-a7c53c0b1e89.mock.pstmn.io"
    let path: String
    var request: URLRequest {
        var components = URLComponents()
        components.scheme = "https"
        components.host = Endpoint.baseURL
        components.path = path
        guard let url = components.url else { preconditionFailure("Invalid URL") }
        
        return URLRequest(url: url)
    }
}


