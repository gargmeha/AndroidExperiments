package meha.garg.com.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by meha on 4/28/16.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return CrimeListFragment.newInstance();
    }
}
