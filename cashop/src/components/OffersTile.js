import React from 'react';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';

export default function OffersTile({offer}) {
    return (
        <div className="offersTile" id={offer.offerId}>
            <div style={{fontSize: '1.6rem', fontWeight:500, marginBottom:'8px', display: 'flex', alignItems:'center'}}>
                {offer.offerName}
                <span className="tag">{offer.offerType}</span>
            </div> 
            <div style={{display: 'flex', alignItems:'center', color: '#666', marginBottom:'8px', fontSize: '0.9rem'}}>
                <CalendarTodayIcon style={{marginRight: '6px'}}/>
                Valid till {offer.validTill}
            </div>
            <div style={{fontSize: '1.1rem'}}>{offer.offerDetails}</div>
        </div>
    )
}
