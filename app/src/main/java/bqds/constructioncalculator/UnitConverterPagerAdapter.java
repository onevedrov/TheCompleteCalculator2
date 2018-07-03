package bqds.constructioncalculator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class UnitConverterPagerAdapter extends FragmentPagerAdapter {

    UnitConverterPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0)
            return new DistanceConverter();
        else if (position==1)
            return new WeightConverter();
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Distance";
            case 1:
                return "Weight";
            default:
                return "Second Tab";
        }
    }
}
