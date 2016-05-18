package mehagarg.android.asyntaskexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivityForImageDownload extends AppCompatActivity {
    private ListView mainList;
    private ProgressBar progressBar;
    private TextView textView;
    private Button imageView;
    private NonUITaskFragment fragment;


    private String[] texts = {
            "http://media2.giphy.com/media/FiGiRei2ICzzG/giphy_s.gif",
            "http://media0.giphy.com/media/feqkVgjJpYtjy/giphy_s.gif",
            "http://media0.giphy.com/media/feqkVgjJpYtjy/giphy_s.gif",
            "http://media1.giphy.com/media/7rzbxdu0ZEXLy/giphy_s.gif",
            "http://media2.giphy.com/media/wWAIKcFASEFz2/giphy_s.gif",
            "http://media0.giphy.com/media/op7uqYWBm3R04/giphy_s.gif",
            "http://media2.giphy.com/media/sj0sbNi9cv2dG/giphy_s.gif"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main_image_download);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textViewOfURL);
        imageView = (Button) findViewById(R.id.imageView);


        mainList = (ListView) findViewById(R.id.listView);
        mainList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, texts));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView.getText().toString() != null &&
                        !textView.getText().toString().isEmpty()) {
                    fragment.startTask(textView.getText().toString());
                }
            }
        });

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(texts[position]);
            }
        });

        if (savedInstanceState == null) {
            if (fragment == null) {
                fragment = new NonUITaskFragment();
                getFragmentManager().beginTransaction()
                        .add(fragment, "TaskFragment")
                        .commit();
            }
        } else {
            fragment = (NonUITaskFragment) getFragmentManager().findFragmentByTag("TaskFragment");
        }
    }

    public void showHideProgressBarBeforeDownloading() {
        if (fragment.myTask != null) {
            if (progressBar.getVisibility() != View.VISIBLE
                    && progressBar.getProgress() != progressBar.getMax()) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    public void updateProgress(int progress){
        progressBar.setProgress(progress);

    }


}


