package mehagarg.android.handlerexample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;

/**
 * Created by meha on 5/15/16.
 */
public class MainActivityAsyncTaskBleedOnBack extends Activity {

    ProgressBar progressBar;
    TextView textView;
    PerformBackGroundTask task;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.pb);
        textView = (TextView) findViewById(R.id.textViewFib);

        task = new PerformBackGroundTask();
        task.execute("https://api.github.com");

    }

    private class PerformBackGroundTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            String jsonResponse = null;
            String line;
            BufferedInputStream bufferedInputStream = null;

            connection = 

            while ((line = bufferedInputStream.toString()) != null && !Thread.interrupted()) {
                jsonResponse += line;
            }




            return jsonResponse;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }

        @Override
        protected void onPostExecute(String s) {
            textView.setText(s.toString());
        }
    }
}
