import React, {useEffect, useState} from 'react';
import Chart from '../../components/Chart'
import axios from 'axios';
import {API_CONSTANTS} from '../../config/API';

const baseURL = `${API_CONSTANTS.endpoint}/analytics/product-cat-sales/${API_CONSTANTS.userID}`;
//   `${API_CONSTANTS.endpoint}/analytics/sales/${API_CONSTANTS.userID}`,
//   `${API_CONSTANTS.endpoint}/analytics/product-cat-sales/${API_CONSTANTS.userID}`,
//   `${API_CONSTANTS.endpoint}/analytics/nearby-sales-insights/${API_CONSTANTS.userID}`,
//   `${API_CONSTANTS.endpoint}/analytics/sales/${API_CONSTANTS.userID}` 
// ];

const Category = () => {
  const [salesData, setSalesData] = useState({});
  useEffect(() => {
    axios.get(baseURL).then((response) => {
      let data = response.data.responseObject.data;
      let labels=[];
      let values=[];
      Object.keys(data).forEach((key) => {
        labels.push(key);
        values.push(data[key]);
      });
      setSalesData({
        labels: labels,
        values: values,
        header: 'Category Sales Data',
        type: 'pie'
      });
    });
  }, []);

  return(
  <>
    <div style={{width: "25%", height:"25%"}}>
        <Chart data={salesData}/>
    </div>
  </>
)};

export default Category;