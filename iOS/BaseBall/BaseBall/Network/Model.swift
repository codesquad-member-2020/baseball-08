//
//  Model.swift
//  BaseBall
//
//  Created by Cloud on 2020/05/07.
//  Copyright © 2020 Cloud. All rights reserved.
//

import Foundation

protocol Gameable {
    var game: Int { get }
    var away: String { get }
    var home: String { get }
}

struct Game: Decodable, Gameable {
    let game: Int
    let away: String
    let home: String
    let awayUser: String?
    let homeUser: String?
    
    init(_ game: Game) {
        self = game
    }
}

struct Selectable: Decodable {
    let available: Bool
}

struct GameDetail: Decodable {
    let team: String
    let score: [Int]
    let total_score: Int
    let turn: Bool
}

struct GameDetailPlayers: Decodable {
    let team: String
    let user: String
    let players: [Player]
    let total: Total
}

struct Player: Decodable {
    let name: String
    let at_bat: Int
    let hit: Int
    let out: Int
    let average: Double
}

struct Total: Decodable {
    let bat: Int
    let hit: Int
    let out: Int
}

struct Score: Decodable {
    let strike: Int
    let ball: Int
    let out: Int
    let base: Int
}

struct Pitcher: Decodable {
    let name: String
    let pitches: Int
}

struct Hitter: Decodable {
    let name: String
    let at_bat: Int
    let hit: Int
}

struct History: Decodable {
    let nmae: String
    let line_up: Int
    let hit_log: [String]
}

struct GameDetailScreen: Decodable {
    let away: String
    let home: String
    let away_total_score: Int
    let home_total_score: Int
    let user: String
    let inning: Int
    let turn: Bool
    let score: Score
    let pitcher: Pitcher
    let hitter: Hitter
    let history: History
}
