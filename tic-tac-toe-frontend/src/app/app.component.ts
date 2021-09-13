import { Component } from '@angular/core';
import {GameService} from './game.service';
import { constants } from './constants';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [GameService]
})
export class AppComponent {
  numbers = Array.from(Array(constants.DEFAULT_BOARD_WIDTH)).map((x, i) => i );

  isBombsChecked: boolean;
  isObstaclesChecked: boolean;
  difficultyLevel: number;
  constructor(public gameService: GameService){}

  makeMove(row, column) {
    this.gameService.makeMove(row, column);
  }

  checkObstacles() {
    this.gameService.enableObstacles(this.isObstaclesChecked)
  }

  checkBombs() {
    this.gameService.enableBombs(this.isBombsChecked)
  }

  onLevelSelect() {
    this.gameService.setDifficultyLevel(this.difficultyLevel)
  }

}
