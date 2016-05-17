package mehagarg.android.todotask;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class TaskListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_task);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment == null) {
            fragment = TaskListFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
        }
    }
}
