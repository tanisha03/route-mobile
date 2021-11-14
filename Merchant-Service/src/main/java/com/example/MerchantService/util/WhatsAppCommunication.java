package com.example.MerchantService.util;

import com.example.MerchantService.entity.Campaign;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class WhatsAppCommunication {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = "ENTER_YOURS";
    public static final String AUTH_TOKEN = "ENTER_YOURS";

    public boolean sendWhatsappText(List<CampaignData> customerNumbers, Campaign campaign) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        try{
            String campDesc = campaign.getCampaignMessage();

            for(int i=0;i<customerNumbers.size();i++){
                System.out.println(customerNumbers.get(i).getPurchaseCustNum());
                Message message = Message.creator(
                                new com.twilio.type.PhoneNumber("whatsapp:+918384852943"),
                                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                                campDesc)
                        .create();
                System.out.println(message.getSid());
            }
            return true;
        }
        catch(Exception e){
            System.out.println(e.toString());
            return false;
        }
    }


    public boolean sendWhatsappMedia(List<CampaignData> customerNumbers, Campaign campaign) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        try{
            String url = campaign.getCampaignBannerUrl().toString();

            for(int i=0;i<customerNumbers.size();i++){
                System.out.println(customerNumbers.get(i).getPurchaseCustNum());
                Message message = Message.creator(
                                new com.twilio.type.PhoneNumber("whatsapp:+918384852943"),
                                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                                Arrays.asList(URI.create(url)))
                        .create();
                System.out.println(message.getSid());
            }
            return true;
        }
        catch(Exception e){
            System.out.println(e.toString());
            return false;
        }
    }
}
