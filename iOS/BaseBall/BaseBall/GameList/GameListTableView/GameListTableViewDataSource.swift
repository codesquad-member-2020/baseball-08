//
//  GameListTableViewDataSource.swift
//  BaseBall
//
//  Created by Cloud on 2020/05/12.
//  Copyright © 2020 Cloud. All rights reserved.
//

import UIKit
import Combine

final class GameListTableViewDataSource: NSObject {
    
    // MARK: - Propeties
    @Published var games: [Game] = []
    private var bindings: Set<AnyCancellable> = Set<AnyCancellable>()
    
    // MARK: - Lifecycles
    override init() {
        super.init()
        fetchGame()
    }
    
    // MARK: - Methods
    private func fetchGame() {
        let provider = Provider()
        let fetchGameCompletionHandler: (Subscribers.Completion<Error>) -> Void = { _ in }
        let fetchGameValueHandler: ([Game]) -> Void = { [weak self] games in
            guard let self = self else { return }
            self.games = games.map { Game($0) }
        }
        
        provider
            .get(Game.self, path: Endpoint.Path.game)
            .sink(receiveCompletion: fetchGameCompletionHandler,
                  receiveValue: fetchGameValueHandler)
            .store(in: &bindings)
    }
}

// MARK: - UITableView
// MARK: DataSource
extension GameListTableViewDataSource: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        games.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell =
            tableView
                .dequeueReusableCell(withIdentifier: GameListTableViewCell.identifier)
                as? GameListTableViewCell
            else { return UITableViewCell() }
        let game = games[indexPath.row]
        cell.configure(game)
        
        return cell
    }
}
