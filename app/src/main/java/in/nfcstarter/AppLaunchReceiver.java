package in.nfcstarter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Brekkishhh on 13-08-2016.
 */
public class AppLaunchReceiver extends BroadcastReceiver{

    private static final String TAG = "AppLaunchReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        String phone = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        Log.d(TAG,"phone number called is : "+phone);

        if (phone.equals("*574#")){
            Intent startApp = new Intent(context,LandingActivity.class);
            startApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(startApp);
        }
    }
}
