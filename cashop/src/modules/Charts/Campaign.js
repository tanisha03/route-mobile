import React, {useEffect, useState} from 'react';
import Chart from '../../components/Chart'
import axios from 'axios';
import {API_CONSTANTS} from '../../config/API';

const baseURL = `${API_CONSTANTS.endpoint}/analytics/campaign-success-rate/${API_CONSTANTS.userID}`;
//   `${API_CONSTANTS.endpoint}/analytics/sales/${API_CONSTANTS.userID}`,
//   `${API_CONSTANTS.endpoint}/analytics/product-cat-sales/${API_CONSTANTS.userID}`,
//   `${API_CONSTANTS.endpoint}/analytics/nearby-sales-insights/${API_CONSTANTS.userID}`,
//   `${API_CONSTANTS.endpoint}/analytics/sales/${API_CONSTANTS.userID}` 
// ];

const SalesChart = () => {
  const [salesData, setSalesData] = useState({});
  useEffect(() => {
    axios.get(baseURL).then((response) => {
      let data = response.data.responseObject.data;
      let labels=[];
      let values=[];
      data.forEach((item) => {
        labels.push(item.campaignName);
        values.push(item.campaignSuccessRate);
      });
      setSalesData({
        labels: labels,
        values: values,
        header: 'Campaign Sales Data',
        type: 'bar'
      });
    });
  }, []);

  return(
  <>
    <div style={{width: "80%", height:"80%"}}>
        <Chart data={salesData}/>
    </div>
  </>
)};

export default SalesChart;