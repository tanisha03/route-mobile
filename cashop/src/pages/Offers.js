import React,{useState, useEffect} from 'react';
import OffersTile from '../components/OffersTile';
import axios from 'axios';
import {API_CONSTANTS} from '../config/API';
import {getFormattedDate} from '../config/util'
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
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import DatePicker from '@mui/lab/DatePicker';
import LoadingButton from '@mui/lab/LoadingButton';

const baseURL = `${API_CONSTANTS.endpoint}/offer/${API_CONSTANTS.userID}`;

export default function Offers() {
    const [offersData, setoffersData] = useState([]);
    const [open, setOpen] = useState(false);
    const [newOffer, setNewOffer] = useState({
        offerName: "",
        offerDetails: "",
        validTill: getFormattedDate(new Date()),
        offerType: "PROMOTIONAL",
        merchantUsername: API_CONSTANTS.userID
    });
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        axios.get(baseURL).then((response) => {
            setoffersData(response.data.responseObject);
        });
      }, []);

      const handleClickOpen = () => {
        setOpen(true);
      };
    
      const handleClose = () => {
        setOpen(false);
      };

      const handleChange = (evt) => {
        setNewOffer((prevState)=> {
            return{
                ...prevState,
                [evt.target.id]: evt.target.value
            }
        });
      };

      const handleSubmitOffer = () => {
        setLoading(true);
        axios.post(baseURL, newOffer).then((response) => {
            let offerArr = [response.data.responseObject].concat(offersData);
            setoffersData(offerArr);
            setLoading(false);
            handleClose();
        });
      };

    return (
        <div className='container'>
            <Button variant="contained" onClick={handleClickOpen} style={{float: 'right'}}>New Offer</Button>
            <h2>
                My offers : {offersData.length}
            </h2>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Create an offer</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Give details of the offer to be created. Enter the name and details of the offer.
                    </DialogContentText>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="offerName"
                        label="Offer Name"
                        type="text"
                        fullWidth
                        variant="standard"
                        onChange={handleChange}
                        required
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        id="offerDetails"
                        label="Offer Details"
                        type="text"
                        fullWidth
                        multiline
                        rows={4}
                        variant="standard"
                        onChange={handleChange}
                        required
                    /><br/>
                        <InputLabel id="offer-type">Offer Type</InputLabel>
                        <Select
                            labelId="offer-type"
                            id="offerType"
                            value={newOffer.offerType}
                            label="Offer Type"
                            fullWidth
                            onChange={handleChange}
                            >
                            <MenuItem value={'PROMOTIONAL'}>Promotional</MenuItem>
                        </Select><br/><br/>
                        {/* <InputLabel id="offer-date">Offer Type</InputLabel> */}
                        <LocalizationProvider dateAdapter={AdapterDateFns}>
                            <DatePicker
                                labelId="offer-date"
                                label="Offer Valid till"
                                value={newOffer.validTill}
                                onChange={(newValue) => {
                                    setNewOffer((prevState)=> {
                                        return{
                                            ...prevState,
                                            validTill: getFormattedDate(newValue)
                                        }
                                    });
                                  }}
                                renderInput={(params) => <TextField fullWidth onChange={handleChange} {...params} />}
                            />
                        </LocalizationProvider>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    {
                        loading? 
                        ( <LoadingButton loading variant="outlined">Submit</LoadingButton>) : (
                            <Button variant="outlined" onClick={handleSubmitOffer}>Create</Button>
                        )
                    }
                </DialogActions>
            </Dialog>
            <div>
                {
                    offersData && offersData.map(offer => (
                        <OffersTile offer={offer}/>
                    ))
                }
            </div>
        </div>
    )
}
