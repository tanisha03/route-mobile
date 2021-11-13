import React from 'react'
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';

export default function CampaignsTile({campaign}) {
    return (
        <div className="offersTile" id={campaign.campaignId}>
            <img src={campaign.campaignBannerUrl} alt="" style={{width: '100%'}}/>
            <div style={{fontSize: '1.6rem', fontWeight:500, marginBottom:'8px', display: 'flex', alignItems:'center'}}>
                {campaign.campaignName}
                <span className="tag">{campaign.targetCategory}</span>
            </div> 
            <div style={{display: 'flex', alignItems:'center', color: '#666', marginBottom:'8px', fontSize: '0.9rem'}}>
                <CalendarTodayIcon style={{marginRight: '6px'}}/>
                Created on {campaign.campaignCreatedAt}
            </div>
            <div style={{fontSize: '1.1rem'}}>{campaign.campaignMessage}</div>
        </div>
    )
}
