import { Component, Input } from '@angular/core';
import { ButtonStatus, TickerService } from '../ticker.service';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class isCorrectButtonComponent {
  @Input() value!:ButtonStatus;

  //inject ticker service
  constructor(public tickerService: TickerService) {
  }
  get isWrong(): boolean {
    return this.value.selected && !this.value.isCorrect && this.tickerService.buttonlist.filter(button => button.selected).length === 2;
  }
  onClick() {
    
  //reset all button selected to false if selected button is 3  
  if (this.tickerService.buttonlist.filter(button => button.selected).length === 2) {
    this.tickerService.buttonlist.forEach((button) => {
      button.selected = false;
    });
  }
      this.value.selected = true;

  
    this.tickerService.checkAnswer(this.value.key);

  
    
  }
}
