//
//  GameListViewController.swift
//  BaseBall
//
//  Created by Cloud on 2020/05/12.
//  Copyright © 2020 Cloud. All rights reserved.
//

import UIKit
import Combine

final class GameListViewController: UIViewController {
    
    // MARK: - IBOutlets
    @IBOutlet weak var gameListTableView: UITableView! {
        didSet {
            gameListTableView.dataSource = datasource
        }
    }
    
    // MARK: - Properties
    private let datasource = GameListTableViewDataSource()
    private var bindings: Set<AnyCancellable> = Set<AnyCancellable>()
    
    // MARK: - Lifecycles
    override func viewDidLoad() {
        super.viewDidLoad()
        bindViewModelToView()
    }
    
    // MARK: - Methods
    private func bindViewModelToView() {
        let viewModelsValueHandler: ([Gameable]) -> Void = { [weak self] _ in
            guard let self = self else { return }
            self.gameListTableView.reloadData()
        }
        
        datasource.$games
            .receive(on: RunLoop.main)
            .sink(receiveValue: viewModelsValueHandler)
            .store(in: &bindings)
    }
}

// MARK: - UITableView
// MARK: Delegate
extension GameListViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        GameListTableViewCell.height
    }
}
