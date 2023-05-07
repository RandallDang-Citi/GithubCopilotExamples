import React from 'react';

import CompanyTickerSampleData from '../company-ticker';
import "./CompanyTickerComponent.css";

const CompanyTickerComponent = () => {


    let selectedCompanyTicker = null;
    let blueTarget = null;
    let redTarget = null;

    const handleChange = (event) => {
       var selectedItem = {type: event.target.getAttribute("comType"), value: event.target.innerHTML};
       if (!selectedCompanyTicker) {
            selectedCompanyTicker = selectedItem
            event.target.style.backgroundColor = "blue";
            blueTarget = event.target;
            return;
        }
        
        let selectedResult = false
        if (selectedItem.type == selectedCompanyTicker.type) {
                selectedResult = false;
            } else if (selectedItem.type === "name") {
                selectedResult = checkCompanyTicker(selectedItem.value, selectedCompanyTicker.value);
        } else if (selectedItem.type === "ticker") {
                selectedResult = checkCompanyTicker(selectedCompanyTicker.value, selectedItem.value);
        }

        redTarget && (redTarget.style.backgroundColor = "gray");
        if (selectedResult) {
                event.target.style.backgroundColor = "green";
                blueTarget && (blueTarget.style.backgroundColor = "green");
                selectedCompanyTicker = null;
        } else {
                event.target.style.backgroundColor = "red";
                redTarget = event.target;
        }
    }

    //check the select company name and ticker are one pair
    const checkCompanyTicker = (companyName, companyTicker) => {
        let result = false;
        for (let index = 0; index < CompanyTickerSampleData.length; index++) {
            const element = CompanyTickerSampleData[index];
            if (element.companyName === companyName && element.companyTicker === companyTicker) {
                result = true;
                break;
            }
        }
        return result;
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        alert(`Company Ticker submitted: `);
    }

    //Get one list with separated company name and ticker
    let result = [];
    for (let index = 0; index < CompanyTickerSampleData.length; index++) {
        const element = CompanyTickerSampleData[index];
        result.push({type: 'name', value: element.companyName});
        result.push({type: 'ticker', value: element.companyTicker});
    }

    //Generate a new list with random sequence of company tickers
    const companyTicker = result.sort(() => Math.random() - 0.5);

    const buttonGroup = companyTicker.map((companyTicker, index) => {
        return (
            <div key={index} class="box" onClick={handleChange} comType={companyTicker.type}>
               {companyTicker.value}
            </div>
        )
    });

    return (
        <div>
            <h1>Company Ticker Mapper</h1>
            <div class="container">{buttonGroup}</div>
        </div>
    );
}
    
export default CompanyTickerComponent;