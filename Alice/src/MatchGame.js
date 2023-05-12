import react from 'react';

// init a new react component named MatchGame
const MatchGame = () => {
    // init a array to store the button list
    const BUTTONLIST = ["AAPL", "GOOG", "MSFT", "Apple", "Google", "Microsoft"];


    // create a method to randomize the button list
    const randomizeButtonList = (buttonList) => {
        // init a new array to store the randomized button list
        const randomizedButtonList = [];
        // init a new array to store the index of the randomized button list
        const randomizedIndexList = [];
        // init a new variable to store the length of the button list
        const buttonListLength = buttonList.length;
        // init a new variable to store the index of the randomized button list
        let randomizedIndex = 0;
        // init a new variable to store the randomized button list
        let randomizedButton = "";

        // create a loop to randomize the button list
        for (let i = 0; i < buttonListLength; i++) {
            // create a loop to get a random index
            while (true) {
                // get a random index
                randomizedIndex = Math.floor(Math.random() * buttonListLength);
                // check if the random index is not in the randomized index list
                if (randomizedIndexList.indexOf(randomizedIndex) === -1) {
                    // add the random index to the randomized index list
                    randomizedIndexList.push(randomizedIndex);
                    // get the randomized button
                    randomizedButton = buttonList[randomizedIndex];
                    // add the randomized button to the randomized button list
                    randomizedButtonList.push(randomizedButton);
                    // break the loop
                    break;
                }
            }
        }

        // return the randomized button list
        return randomizedButtonList;
    };

    // init a new array to store the randomized button list
    const [buttonList, setButtonList] = react.useState(randomizeButtonList(BUTTONLIST));

    // init a new array to store the selected button list
    const [selectedButtonList, setSelectedButtonList] = react.useState([]);
 

    // create a method to handle the button click
    const handleButtonClick = (button) => {
        // remove the button from the selected button list if exists
        if (selectedButtonList.indexOf(button) !== -1) {
            // init a new array to store the new selected button list
            const newSelectedButtonList = selectedButtonList.filter((selectedButton) => selectedButton !== button);
            // update the selected button list
            setSelectedButtonList(newSelectedButtonList);
        }
        // add the button to the selected button list if not exists and no more than two buttons are selected
        else if (selectedButtonList.length < 2) {
            // init a new array to store the new selected button list
            let newSelectedButtonList = [...selectedButtonList, button];
            // check selected button is matched
            if (newSelectedButtonList.length === 2 && isMatch(newSelectedButtonList[0], newSelectedButtonList[1])) {
                // remove the matched buttons from the button list
                const newButtonList = buttonList.filter((button) => button !== newSelectedButtonList[0] && button !== newSelectedButtonList[1]);
                // update the button list
                setButtonList(newButtonList);
                // clear selected button list
                newSelectedButtonList = [];
            }
            // update the selected button list
            setSelectedButtonList(newSelectedButtonList);
        }
        // if two buttons are selected, clear the selected button list and add the button to the selected button list
        else {
            // clear selected button list
            setSelectedButtonList([button]);
        }
        
    }

    // create a method to get the button status
    const getButtonStatus = (button) => {
        // check if the button is selected and only one button is selected
        if (selectedButtonList.indexOf(button) !== -1 && selectedButtonList.length === 1) {
            // return the button status
            return "SELECTED";
        }
        // check if the button is selected and two buttons are selected
        else if (selectedButtonList.indexOf(button) !== -1 && selectedButtonList.length === 2) {
            return "UNMATCHED";
        }
        // check if the button is not selected
        else {
            return "UNSELECTED";
        }
    }


    return (
        <div>
        {
            buttonList.map((button) => (
            <Button key={button} text={button} onClick={handleButtonClick} status={getButtonStatus(button)}/> 
        ))}
        {buttonList.length === 0 && <h1>Congratulations</h1>}
        </div>
    );
}

// create a json object named TickerCompanyMap
const TickerCompanyMap = {
    "AAPL": "Apple",
    "GOOG": "Google",
    "MSFT": "Microsoft",
};

// create a method to check if two text are paired
const isMatch = (text1, text2) => {
    return TickerCompanyMap[text1] === text2 || TickerCompanyMap[text2] === text1;
};

// init a new react component named Button
const Button = ({text, status, onClick}) => {
    // init a json object named ButtonStatus
    const ButtonStatus = {
        SELECTED: "#0000ff",
        UNMATCHED: "#ff0000",
        UNSELECTED: "",
    }; 

    // create a function to get button background based on the status
    const getButtonBackground = (status) => {
        return ButtonStatus[status];
    };

    return (
        <button style={{backgroundColor: getButtonBackground(status)}} onClick={() => onClick(text)}>
        {text}
        </button>
    );
};

export default MatchGame;
