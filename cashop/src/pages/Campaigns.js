import React,{useState, useEffect} from 'react';
import CampaignsTile from '../components/CampaignsTile';
import axios from 'axios';
import {API_CONSTANTS} from '../config/API';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import InputLabel from '@mui/material/InputLabel';
import LoadingButton from '@mui/lab/LoadingButton';

const baseURL = `${API_CONSTANTS.endpoint}/campaign/${API_CONSTANTS.userID}`;

export default function Campaigns() {
    const [campaignsData, setcampaignsData] = useState([]);
    const [open, setOpen] = useState(false);
    const [newcampaign, setNewcampaign] = useState({
        campaignName: "",
        campaignMessage: "",
        campaignBannerUrl: "https://businessfirstfamily.com/wp-content/uploads/2017/04/sale-banners-tips-business-owners.jpg",
        targetCategory: "HOMEUSE",
    });
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        axios.get(baseURL).then((response) => {
            setcampaignsData(response.data.responseObject);
        });
      }, []);

      const handleClickOpen = () => {
        setOpen(true);
      };
    
      const handleClose = () => {
        setOpen(false);
      };

      const handleChange = (evt) => {
        setNewcampaign((prevState)=> {
            return{
                ...prevState,
                [evt.target.id]: evt.target.value
            }
        });
      };

      const handleSubmitcampaign = () => {
        setLoading(true);
        axios.post(baseURL, newcampaign).then((response) => {
            let campaignArr = [response.data.responseObject].concat(campaignsData);
            setcampaignsData(campaignArr);
            setLoading(false);
            handleClose();
        });
      };

    return (
        <div className='container'>
            <Button variant="contained" onClick={handleClickOpen} style={{float: 'right'}}>New campaign</Button>
            <h2>
                My campaigns : {campaignsData.length}
            </h2>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Create an campaign</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Give details of the campaign to be created. Enter the name and details of the campaign.
                    </DialogContentText>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="campaignName"
                        label="Campaign Name"
                        type="text"
                        fullWidth
                        variant="standard"
                        onChange={handleChange}
                        required
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        id="campaignMessage"
                        label="Campaign Message"
                        type="text"
                        fullWidth
                        multiline
                        rows={4}
                        variant="standard"
                        onChange={handleChange}
                        required
                    /><br/>
                        <InputLabel id="campaign-type">Campaign Category</InputLabel>
                        <Select
                            labelId="campaign-type"
                            id="campaignType"
                            value={newcampaign.targetCategory}
                            label="campaign Type"
                            fullWidth
                            onChange={handleChange}
                            >
                            <MenuItem value={'HOMEUSE'}>Home Use</MenuItem>
                            <MenuItem value={'GROCERY'}>Grocery</MenuItem>
                            <MenuItem value={'UTILITY'}>Utility</MenuItem>
                            <MenuItem value={'FOOD'}>Food</MenuItem>
                        </Select><br/><br/>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    {
                        loading? 
                        ( <LoadingButton loading variant="outlined">Submit</LoadingButton>) : (
                            <Button variant="outlined" onClick={handleSubmitcampaign}>Create</Button>
                        )
                    }
                </DialogActions>
            </Dialog>
            <div>
                {
                    campaignsData && campaignsData.map(campaign => (
                        <CampaignsTile campaign={campaign}/>
                    ))
                }
            </div>
        </div>
    )
}
