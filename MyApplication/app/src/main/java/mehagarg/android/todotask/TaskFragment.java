package mehagarg.android.todotask;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.UUID;

import mehagarg.android.todotask.model.Task;
import mehagarg.android.todotask.model.TaskList;

/**
 * Created by meha on 5/17/16.
 */
public class TaskFragment extends Fragment {

    private EditText titleET;
    private EditText descriptionET;
    private Task task;

    public static final String INTENT_ACTION = "intent_action";

    public static TaskFragment newInstance(UUID uuid) {

        Bundle args = new Bundle();
        args.putSerializable(INTENT_ACTION, uuid);

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID id = (UUID) getArguments().getSerializable(INTENT_ACTION);
        task = TaskList.getInstance(getActivity()).getTaskDetails(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, null);
        titleET = (EditText) view.findViewById(R.id.et_title);
        descriptionET = (EditText) view.findViewById(R.id.et_description);

        titleET.setText(task.getTitle());
        descriptionET.setText(task.getDescription());

        titleET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                task.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        descriptionET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                task.setDescription(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

}
