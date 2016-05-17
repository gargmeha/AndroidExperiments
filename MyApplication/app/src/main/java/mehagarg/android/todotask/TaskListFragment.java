package mehagarg.android.todotask;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mehagarg.android.todotask.model.Task;
import mehagarg.android.todotask.model.TaskList;

/**
 * Created by meha on 5/17/16.
 */
public class TaskListFragment extends Fragment {

    private TaskList tasklist;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;


    public static TaskListFragment newInstance() {

        Bundle args = new Bundle();

        TaskListFragment fragment = new TaskListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);

        tasklist = TaskList.getInstance(getActivity());

        recyclerView = new RecyclerView(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        taskAdapter = new TaskAdapter(tasklist);

        recyclerView.setAdapter(taskAdapter);


        return view;
    }

    private class TaskHolder extends RecyclerView.ViewHolder {

        private TextView titleTV;
        private TextView desciptionTV;

        public TaskHolder(View itemView) {
            super(itemView);
            titleTV = (TextView) itemView.findViewById(R.id.title_tv);
            desciptionTV = (TextView) itemView.findViewById(R.id.desc_tv);
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        TaskList taskList;

        public TaskAdapter(TaskList tasklist) {
            this.taskList = tasklist;
        }

        @Override
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            inflater.inflate(R.layout.fragment_item_layout, parent);

            return null;
        }

        @Override
        public void onBindViewHolder(TaskHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return taskList.getCount();
        }
    }

}
