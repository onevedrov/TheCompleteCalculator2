package bqds.constructioncalculator;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnitConverter extends Fragment {

    View view;

    public UnitConverter() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(abanoubmagdi.home.thecompletecalculator.R.layout.fragment_unit_converter, container, false);

        SharedPreferences prefs=getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("lastFragment", abanoubmagdi.home.thecompletecalculator.R.id.nav_unit_converter);
        editor.apply();

        getActivity().setTitle(abanoubmagdi.home.thecompletecalculator.R.string.Unit_Converter);
        TabLayout tabLayout = (TabLayout) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.tabLayout);

        ViewPager viewPager=(ViewPager) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.unitConverterViewPager);
        UnitConverterPagerAdapter tabsFragmentAdapter=new UnitConverterPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(tabsFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
        return view;
    }

}
