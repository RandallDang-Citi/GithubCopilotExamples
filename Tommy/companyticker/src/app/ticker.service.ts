import { Injectable } from '@angular/core';
import { shuffle } from 'lodash';
export interface ButtonStatus {
  key: string;
  selected: boolean;
  isCorrect: boolean;
}


@Injectable({
  providedIn: 'root'
})
export class TickerService {


  //create a map to store the answer
  //key is the answer, value is the answer
  answerMap = new Map<string, string>([
    ['Google', 'GOOG'],
    ['Microsoft', 'MSFT'],
    ['APPLE', 'AAPL'],
    ['GOOG', 'Google'],
    ['MSFT', 'Microsoft'],
    ['AAPL', 'APPLE']
  ]);
  // init button list with key and seleced and isCorrect


  buttonlist = shuffle([{ key: 'Google', selected: false, isCorrect: false },
  { key: 'GOOG', selected: false, isCorrect: false },
  { key: 'Microsoft', selected: false, isCorrect: false },
  { key: 'MSFT', selected: false, isCorrect: false },
  { key: 'APPLE', selected: false, isCorrect: false },
  { key: 'AAPL', selected: false, isCorrect: false }
  ])
  constructor() { }


  //set selectedanwser method
  setSelectedAnswer(answer: ButtonStatus) {
    //search match answer from buttonlist and set selected to true
    this.buttonlist.forEach((button) => {
      if (button.key === answer.key) {
        button.selected = true;
      }
    }
    );

  }

  //check if there's a match answer from answermap
  //if there is a match, return true
  //if there's no match, return false
  checkAnswer(answer: string): void {
    //get match answer from answermap and check if it exists in selected button

    const selecedAnswer = this.buttonlist.filter(button => button.selected);
    const correctAnswer = this.answerMap.get(answer);
    let isAnswerCorrect = false;
    if (correctAnswer !== undefined) {
      selecedAnswer.filter(button => button.key === correctAnswer).forEach((button) => {
        button.isCorrect = true;
        isAnswerCorrect = true;
      });
    }
    if (isAnswerCorrect) {
      // find current answer from buttonlist and set isCorrect to true
      if (this.buttonlist.filter(button => button.key === answer).length > 0) {
        this.buttonlist.filter(button => button.key === answer).forEach((button) => {
          button.isCorrect = true;
        });
      }
    }



    //remove correct answer from buttonlist


    this.buttonlist = this.buttonlist.filter(button => !button.isCorrect);
    

  }

}