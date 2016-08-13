package in.nfcstarter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Brekkishhh on 11-08-2016.
 */
public class LandingActivity extends Activity {

    private TextView nfcInfo;
    private static final int RC_NFC = 574;
    private NfcManager nfcManager;
    private NfcAdapter nfcAdapter;
    private static final String TAG = "LandingActivity";

    public LandingActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        nfcInfo = (TextView) findViewById(R.id.nfcInfoTextView);
        showNfcStatus();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_NFC){
            showNfcStatus();
        }
    }

    void showNfcStatus(){

        Boolean nfcState = Utility.checkNFCStatus(LandingActivity.this);

        if (nfcState == null){
            nfcInfo.setText("NFC NOT AVAILABLE");
        }

        else if (nfcState){
            nfcInfo.setText("NFC AVAILABLE ... USE ANY ACTIVE OR PASSIVE DEVICE TO USE WITH IT...");
        }
        else {
            showSwitchOnNfcDialog();
        }
    }

    void showSwitchOnNfcDialog(){

        new AlertDialog.Builder(LandingActivity.this)
                .setMessage("NFC Is Not Enabled. In Order To Enjoy Our Services Enable Your Wifi..")
                .setCancelable(false)
                .setPositiveButton("ENABLE NFC", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
                            startActivityForResult(intent,RC_NFC);
                        } else {
                            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                            startActivityForResult(intent,RC_NFC);
                        }
                    }
                })
                .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LandingActivity.this.finish();
                    }
                })
                .create().show();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(getIntent().getAction())){
            processNfcTag(getIntent());
        }
    }

    void processNfcTag(Intent intent){

        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        Log.d(TAG,"Tag id is : "+tag.getId().toString());
    }



}
