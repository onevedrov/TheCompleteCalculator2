package abanoubmagdi.home.thecompletecalculator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class CalculatorPagerAdapter extends FragmentPagerAdapter {

    CalculatorPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new CalculatorBasic();
        else if (position == 1)
            return new CalculatorSci();

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}