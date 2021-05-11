import { Component } from '@angular/core';
import {GameService} from './game.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [GameService]
})
export class AppComponent {
  numbers = Array.from(Array(10)).map((x, i) => i );

  isObstaclesChecked: boolean;
  difficultyLevel: number;
  constructor(public gameService: GameService){}

  makeMove(row, column) {
    this.gameService.makeMove(row, column);
  }

  checkObstacles() {
    this.gameService.enableObstacles(this.isObstaclesChecked)
  }

  onLevelSelect() {
    this.gameService.setDifficultyLevel(this.difficultyLevel)
  }

}
