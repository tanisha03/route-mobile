import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import { Link } from 'react-router-dom';

export default function Header() {
  // const [auth, setAuth] = React.useState(true);

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            <Link to='/' style={{color: 'white'}}>Cashop</Link>
          </Typography>
          {/* {auth && ( */}
            <ul id="nav-links">
              <Link to='/offers'>Offers</Link>
              <Link to='/campaigns'>Campaigns</Link>
              <Link to='/transactions'>Transactions</Link>
            </ul>
           {/* )}  */}
        </Toolbar>
      </AppBar>
    </Box>
  );
}
