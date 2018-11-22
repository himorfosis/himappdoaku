package com.himorfosis.doaku;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/**
 * Created by him on 6/14/2018.
 */

public class PlayerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String param = intent.getAction();
        if (param.equals("exit")) {

//            Intent service1 = new Intent(context, PlayerService.class);
//            context.stopService(service1);

            //context.sendBroadcast(new Intent("exit"));
        } else if (param.equals("stop")) {

            context.sendBroadcast(new Intent("stop"));

        } else if (param.equals("start")) {

            context.sendBroadcast(new Intent("start"));

        }
    }

}
