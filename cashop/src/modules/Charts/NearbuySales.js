import React, {useEffect, useState} from 'react';
import Chart from '../../components/Chart'
import axios from 'axios';
import {API_CONSTANTS} from '../../config/API';

const baseURL = `${API_CONSTANTS.endpoint}/analytics/nearby-sales-insights/${API_CONSTANTS.userID}`;

const SalesChart = () => {
  const [salesData, setSalesData] = useState([]);
  useEffect(() => {
    axios.get(baseURL).then((response) => {
      let data = response.data.responseObject.data;
      let cumulativeData= [];
      Object.keys(data).forEach((key) => {
        cumulativeData.push({
          labels: ['Others share', 'My share'],
          values: [data[key]['nearby-share'], data[key]['your-share']],
          header: key,
          type: 'pie'
        })
      });
      console.log(cumulativeData);
      setSalesData(cumulativeData);
    });
  }, []);

  return(
    <div>
      <center className='title'>Nearbuy Analytics</center>
      <span id="nearbuy-grid">
          {
            salesData && salesData.map(item => (
              <div style={{width: "35%", height:"35%"}}>
                <Chart data={item}/> 
              </div>
            ))
        }
      </span>
    </div>
)};

export default SalesChart;