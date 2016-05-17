package mehagarg.android.todotask;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

import mehagarg.android.todotask.model.Task;

public class TaskActivity extends AppCompatActivity {

    public static final String INTENT_ACTION = "Intent_Action";

    public static Intent newIntent(Context context, UUID id) {
        Intent intent = new Intent(context, TaskActivity.class);
        intent.putExtra(INTENT_ACTION, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_task);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment == null) {

            UUID id = (UUID) getIntent().getSerializableExtra(INTENT_ACTION);
            fragment = TaskFragment.newInstance(id);
            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();

        }
    }
}
