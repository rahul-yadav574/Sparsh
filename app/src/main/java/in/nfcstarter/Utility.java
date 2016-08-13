package in.nfcstarter;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Brekkishhh on 11-08-2016.
 */
public class Utility {



    @Nullable
    public static Boolean checkNFCStatus(Context context){
        NfcManager nfcManager = (NfcManager) context.getSystemService(Context.NFC_SERVICE);
        NfcAdapter nfcAdapter = nfcManager.getDefaultAdapter();

        if (nfcAdapter == null){
            return null;
        }

        return nfcAdapter.isEnabled();

    }

    public static void toastL(Context context,String string){
        Toast.makeText(context,string, Toast.LENGTH_LONG).show();
    }
    public static void toastS(Context context,String string){
        Toast.makeText(context,string, Toast.LENGTH_SHORT).show();
    }
}
