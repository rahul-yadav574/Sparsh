package in.nfcstarter.Fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import in.nfcstarter.Constants;
import in.nfcstarter.DescriptionActivity;
import in.nfcstarter.R;
import in.nfcstarter.Utility;

/**
 * Created by Brekkishhh on 19-08-2016.
 */
public class About extends Fragment {

    private TextView querySummary;
    private TextView queryRef;
    private TextView queryInfo;
    private static final String TAG = "AboutFragment";
    private String summaryForSpeech;
    private TextToSpeech textToSpeech;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about,container,false);

        queryInfo = (TextView) view.findViewById(R.id.queryExtra);
        querySummary = (TextView) view.findViewById(R.id.querySummary);
        queryRef = (TextView) view.findViewById(R.id.queryref);

        return view;
    }

    public void onPause(){
        if(textToSpeech !=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();

        startGettingInfoFromServer();

    }

    private void startGettingInfoFromServer(){

      //  how(dialog.s);

        String apiUrl = Constants.SUMMARY_API_URL+ DescriptionActivity.actualQuery;
        new GetSummary(apiUrl).execute();

    }


    protected class GetSummary extends AsyncTask<String,Void,Boolean>{

        final ProgressDialog dialog = new ProgressDialog(getActivity());


        String url;
        public GetSummary(String url) {
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog.setMessage("Retrieving Your Info...");
            dialog.setIndeterminate(true);
                dialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {

            JSONObject jsonObject1 = Utility.getJSONFromUrl(url);
            Log.d(TAG,""+jsonObject1);

            try {

                JSONObject jsonObject = jsonObject1.getJSONArray("documents").getJSONObject(0);
                final String summary = jsonObject.getString("summary");
                summaryForSpeech = summary;
                final String ref = jsonObject.getString("reference");

                JSONArray jsonArray = jsonObject.getJSONArray("wikipedia_category");
                String res = "";



                for (int i=0;i<jsonArray.length();i++){
                    res+=jsonArray.getString(i)+"\n";
                }

                final String finalRes = res;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        querySummary.setText(summary);
                        queryInfo.setText(finalRes);
                        queryRef.setText(ref);
                    }
                });



            }catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            super.onPostExecute(aVoid);

            dialog.cancel();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_about_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.convertTextToSpeech){
            convertSummaryToSpeech();
        }
        return super.onOptionsItemSelected(item);
    }

    private void convertSummaryToSpeech(){

      //  textToSpeech.speak(summaryForSpeech, TextToSpeech.QUEUE_FLUSH, null);

        textToSpeech  = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                    textToSpeech.speak(summaryForSpeech,TextToSpeech.QUEUE_FLUSH,null,"574");
                }
            }
        });

}
}
