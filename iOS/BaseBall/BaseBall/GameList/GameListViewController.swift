//
//  GameListViewController.swift
//  BaseBall
//
//  Created by Cloud on 2020/05/12.
//  Copyright © 2020 Cloud. All rights reserved.
//

import UIKit

class GameListViewController: UIViewController {
    
    // MARK: - IBOutlets
    @IBOutlet weak var gameListTableView: UITableView! {
        didSet {
            gameListTableView.dataSource = datasource
        }
    }
    
    // MARK: - Properties
    let datasource = GameListTableViewDataSource()
    
    // MARK: - Lifecycles
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    // MARK: - Methods
}

// MARK: - UITableView
// MARK: Delegate
extension GameListViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        GameListTableViewCell.height
    }
}
