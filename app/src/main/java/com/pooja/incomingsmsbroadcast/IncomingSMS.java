package com.pooja.incomingsmsbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Pooja on 2/11/2017.
 */

public class IncomingSMS extends BroadcastReceiver {
    final SmsManager sms = SmsManager.getDefault();
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage smsmessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String senderphoneNumber = smsmessage.getDisplayOriginatingAddress();
                    String message = smsmessage.getDisplayMessageBody();
                    Toast.makeText(context.getApplicationContext(),"From:"+senderphoneNumber+"Message"+message,Toast.LENGTH_LONG).show();
                }
            }

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }
    }

}


