//
//  GameListTableViewDataSource.swift
//  BaseBall
//
//  Created by Cloud on 2020/05/12.
//  Copyright © 2020 Cloud. All rights reserved.
//

import UIKit

class GameListTableViewDataSource: NSObject {
    
}

extension GameListTableViewDataSource: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 4
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: GameListTableViewCell.identifier) as? GameListTableViewCell else { return UITableViewCell() }
        return cell
    }
}
