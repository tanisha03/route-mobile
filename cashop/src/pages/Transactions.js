import React, {useState, useEffect} from 'react';
import Table from '../components/Table';
import Box from '@mui/material/Box';
import Tab from '@mui/material/Tab';
import TabContext from '@mui/lab/TabContext';
import TabList from '@mui/lab/TabList';
import TabPanel from '@mui/lab/TabPanel';
import {API_CONSTANTS} from '../config/API';
import axios from 'axios';

const purchasesBaseURL = `${API_CONSTANTS.endpoint}/purchase/${API_CONSTANTS.userID}`;
const expensesBaseURL = `${API_CONSTANTS.endpoint}/expense/${API_CONSTANTS.userID}`;


export default function Transactions() {
    const [value, setValue] = useState('1');
    const [expenseData, setExpenseData] = useState([]);
    const [purchaseData, setPurchaseData] = useState([]);

    const handleChange = (event, newValue) => {
      setValue(newValue);
    };

    useEffect(() => {
        axios.get(purchasesBaseURL).then((response) => {
            setPurchaseData({
                fields: ['purchaseCustNum', 'purchaseCategory', 'purchaseAmount', 'createdAt'],
                data:response.data.responseObject
            });
        });
        axios.get(expensesBaseURL).then((response) => {
            setExpenseData({
                fields: ['expenseName', 'expenseDescription', 'expenseCategory', 'expenseAmount', 'createdAt'],
                data:response.data.responseObject
            });
        });
      }, []);

    return (
        <div className='container'>
            <Box sx={{ width: '100%', typography: 'body1' }}>
            <TabContext value={value}>
                <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                <TabList onChange={handleChange} aria-label="lab API tabs example">
                    <Tab label="Expenses" value="1" />
                    <Tab label="Purchases" value="2" />
                </TabList>
                </Box>
                <TabPanel value="1"><Table tableData={expenseData}/></TabPanel>
                <TabPanel value="2"><Table tableData={purchaseData}/></TabPanel>
            </TabContext>
            </Box>
        </div>
    )
}
