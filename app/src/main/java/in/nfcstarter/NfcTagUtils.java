package in.nfcstarter;

import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by Brekkishhh on 13-08-2016.
 */
public class NfcTagUtils {

    private static final String TAG = "NfcTagUtils";


    public static JSONObject readNfcTag(Tag tag){

        JSONObject tagComponents = new JSONObject();
        String [] techList = tag.getTechList();

        return tagComponents;
    }
}

