package mehagarg.android.todotask;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_task);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment == null) {
            fragment = TaskFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
        }
    }
}
