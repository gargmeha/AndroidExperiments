package mehagarg.android.asyntaskexample;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by meha on 4/21/16.
 */
public class NonUITaskFragment extends Fragment {

    MyTask  myTask;
    Context context;

    public void startTask(String... url) {
        myTask = new MyTask(context);
        myTask.execute(url[0]);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (myTask != null) {
            myTask.onAttach(context);
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (myTask != null) {
            myTask.onDetach();
        }

    }
}
