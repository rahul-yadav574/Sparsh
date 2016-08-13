package in.nfcstarter;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import org.json.JSONObject;

/**
 * Created by Brekkishhh on 13-08-2016.
 */
@TargetApi(16)
public class ReadTagAsyncTask extends AsyncTask<String,Void,JSONObject> {

    Tag tag;
    Context context;
    private static final String TAG = "ReadTagAsyncTask";

    public ReadTagAsyncTask(Context context, Tag tag) {
        this.context = context;
        this.tag = tag;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override

    protected JSONObject doInBackground(String... params) {

        String [] techList = tag.getTechList();
        JSONObject jsonObject = new JSONObject();

        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
    }
}
