package in.nfcstarter;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class BookActivity extends AppCompatActivity {


    TextView imageBook;
    private static final String TAG = "BooksActivity";
    private String summaryForSpeech;
    private TextToSpeech  textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        getSupportActionBar().setTitle("Read Book");

        imageBook = (TextView) findViewById(R.id.imageText);



        new GetSummary("https://api.havenondemand.com/1/api/sync/ocrdocument/v1?url=https%3A%2F%2Flh5.googleusercontent.com%2FxadwVV2rkjAkNjZp3Onq0n4mb4iIzCT1v5Uo87c1-4jzpSpdGyXIUtgH5H8ZvrQDccvdJPrGRahvXdXNOItWNkmqR6Zcn7edL3qSoYfve8D3Moq8j00a5x9wnAtouvIezzGXUHo&apikey=439a27da-a17c-410c-9201-b8e12f6ddade").execute();

    }

    protected class GetSummary extends AsyncTask<String,Void,Boolean> {

        final ProgressDialog dialog = new ProgressDialog(BookActivity.this);


        String url;
        public GetSummary(String url) {
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog.setMessage("Retrieving Book Info...");
            dialog.setIndeterminate(true);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {

            JSONObject jsonObject1 = Utility.getJSONFromUrl(url);
            Log.d(TAG,""+jsonObject1);

            try {

                JSONObject jsonObject = jsonObject1.getJSONArray("text_block").getJSONObject(0);
                final String summary = jsonObject.getString("text");
                summaryForSpeech = summary;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageBook.setText(summary);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_about_menu,menu);

        return super.onCreateOptionsMenu(menu);
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

        textToSpeech  = new TextToSpeech(BookActivity.this, new TextToSpeech.OnInitListener() {
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
