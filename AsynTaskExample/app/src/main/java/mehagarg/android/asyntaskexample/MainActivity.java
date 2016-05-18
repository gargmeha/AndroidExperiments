package mehagarg.android.asyntaskexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView mainList;
    private ProgressBar progressBar;

    MyTask myTask;

    private String[] texts = {
            "Lorem", "ipsum", "doror", "sit", "amet", "consernke",
            "Lorem", "ipsum", "doror", "sit", "amet", "consernke",
            "Lorem", "ipsum", "doror", "sit", "amet", "consernke",
            "Lorem", "ipsum", "doror", "sit", "amet", "consernke",
            "Lorem", "ipsum", "doror", "sit", "amet", "consernke",
            "Lorem", "ipsum", "doror", "sit", "amet", "consernke",
            "Lorem", "ipsum", "doror", "sit", "amet", "consernke",
            "Lorem", "ipsum", "doror", "sit", "amet", "consernke",
            "Lorem", "ipsum", "doror", "sit", "amet", "consernke",
            "Lorem", "ipsum", "doror", "sit", "amet", "consernke",
            "Lorem", "ipsum", "doror", "sit", "amet", "consernke",
            "Lorem", "ipsum", "doror", "sit", "amet", "consernke",
            "Lorem", "ipsum", "doror", "sit", "amet", "consernke",
            "Lorem", "ipsum", "doror", "sit", "amet", "consernke"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mainList = (ListView) findViewById(R.id.listView);
        mainList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        myTask = new MyTask();
        myTask.execute(texts);
    }

    class MyTask extends AsyncTask<String[], String, Void>{

        private ArrayAdapter<String> adapter;
        private int count = 0;

        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) mainList.getAdapter();
            progressBar.setVisibility(View.VISIBLE);
        }


        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p/>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Void doInBackground(String[]... params) {
            for(String item : params[0]){
                publishProgress(item);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            count++;
            progressBar.setProgress((int) ((double) (count / texts.length)) * 10000);
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "successfully added", Toast.LENGTH_LONG).show();
        }

    }
}
