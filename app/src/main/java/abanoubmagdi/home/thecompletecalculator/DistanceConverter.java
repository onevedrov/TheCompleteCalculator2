package abanoubmagdi.home.thecompletecalculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;


/**
 * A simple {@link Fragment} subclass.
 */
public class DistanceConverter extends Fragment {

    private View view;
    private TextView textMilli, textCenti, textDeci, textMeter, textHecto,textKilo,
            textInch, textFoot,textYard,textMile,textNauticalMile;
    private EditText editTextDistance;
    private Spinner distanceSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_distance_converter, container, false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                setListeners();
            }
        }).start();
        return view;
    }

    private void setListeners(){
        textMilli=(TextView) view.findViewById(R.id.millimeter);
        textCenti=(TextView) view.findViewById(R.id.centimeter);
        textDeci=(TextView) view.findViewById(R.id.decimeter);
        textMeter=(TextView) view.findViewById(R.id.meter);
        textHecto=(TextView) view.findViewById(R.id.hectometer);
        textKilo=(TextView) view.findViewById(R.id.kilometer);
        textInch=(TextView) view.findViewById(R.id.inch);
        textFoot=(TextView) view.findViewById(R.id.foot);
        textYard=(TextView) view.findViewById(R.id.yard);
        textMile=(TextView) view.findViewById(R.id.mile);
        textNauticalMile=(TextView) view.findViewById(R.id.nautical_mile);

        editTextDistance=(EditText) view.findViewById(R.id.distanceEditText);
        distanceSpinner=(Spinner) view.findViewById(R.id.distanceSpinner);
        editTextDistance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                        OnItemClickListener(distanceSpinner.getSelectedItemPosition());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        distanceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        OnItemClickListener(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                        OnItemClickListener(0);
            }
        });
    }
    
    private void OnItemClickListener(int position){
        double val=1;
        if (!editTextDistance.getText().toString().isEmpty())
        {
            val=Double.parseDouble(editTextDistance.getText().toString());
        }
        if (position>=0 && position<=3)
        {
            textMilli.setText(FormatResult (val*Math.pow(10,position)));
            textCenti.setText(FormatResult (val*Math.pow(10,position)/10.0));
            textDeci.setText(FormatResult (val*Math.pow(10,position)/100.0));
            textMeter.setText(FormatResult (val*Math.pow(10,position)/1000.0));
            textHecto.setText(FormatResult (val*Math.pow(10,position)/100000.0));
            textKilo.setText(FormatResult (val*Math.pow(10,position)/1000000.0));
            textInch.setText(FormatResult (val*Math.pow(10,position)*0.0393701));
            textFoot.setText(FormatResult (val*Math.pow(10,position)*0.00328084));
            textYard.setText(FormatResult (val*Math.pow(10,position)*0.00109361));
            textMile.setText(FormatResult (val*Math.pow(10,position)*6.2137e-7));
            textNauticalMile.setText(FormatResult (val*Math.pow(10,position)*5.3996e-7));
        }
        else if (position>=4 && position<=5)
        {
            textMilli.setText(FormatResult (val*Math.pow(10,position+1)));
            textCenti.setText(FormatResult (val*Math.pow(10,position+1)/10.0));
            textDeci.setText(FormatResult (val*Math.pow(10,position+1)/100.0));
            textMeter.setText(FormatResult (val*Math.pow(10,position+1)/1000.0));
            textHecto.setText(FormatResult (val*Math.pow(10,position+1)/100000.0));
            textKilo.setText(FormatResult (val*Math.pow(10,position+1)/1000000.0));
            textInch.setText(FormatResult (val*Math.pow(10,position+1)*0.0393701));
            textFoot.setText(FormatResult (val*Math.pow(10,position+1)*0.00328084));
            textYard.setText(FormatResult (val*Math.pow(10,position+1)*0.00109361));
            textMile.setText(FormatResult (val*Math.pow(10,position+1)*6.2137e-7));
            textNauticalMile.setText(FormatResult (val*Math.pow(10,position+1)*5.3996e-7));
        }
        else if (position==6){
            textMilli.setText(FormatResult (val*25.4));
            textCenti.setText(FormatResult (val*25.4/10.0));
            textDeci.setText(FormatResult (val*25.4/100.0));
            textMeter.setText(FormatResult (val*25.4/1000.0));
            textHecto.setText(FormatResult (val*25.4/100000.0));
            textKilo.setText(FormatResult (val*25.4/1000000.0));
            textInch.setText(FormatResult (val));
            textFoot.setText(FormatResult (val*0.0833333));
            textYard.setText(FormatResult (val*0.0277778));
            textMile.setText(FormatResult (val*1.5783e-5));
            textNauticalMile.setText(FormatResult (val*1.3715e-5));
        }
        else if (position==7){
            textMilli.setText(FormatResult (val*304.8));
            textCenti.setText(FormatResult (val*304.8/10.0));
            textDeci.setText(FormatResult (val*304.8/100.0));
            textMeter.setText(FormatResult (val*304.8/1000.0));
            textHecto.setText(FormatResult (val*304.8/100000.0));
            textKilo.setText(FormatResult (val*304.8/1000000.0));
            textInch.setText(FormatResult (val*12));
            textFoot.setText(FormatResult (val));
            textYard.setText(FormatResult (val*0.333333));
            textMile.setText(FormatResult (val*0.000189394));
            textNauticalMile.setText(FormatResult (val*0.000164579));
        }
        else if (position==8){
            textMilli.setText(FormatResult (val*914.4));
            textCenti.setText(FormatResult (val*914.4/10.0));
            textDeci.setText(FormatResult (val*914.4/100.0));
            textMeter.setText(FormatResult (val*914.4/1000.0));
            textHecto.setText(FormatResult (val*914.4/100000.0));
            textKilo.setText(FormatResult (val*914.4/1000000.0));
            textInch.setText(FormatResult (val*36));
            textFoot.setText(FormatResult (val*3));
            textYard.setText(FormatResult (val));
            textMile.setText(FormatResult (val*0.000568182));
            textNauticalMile.setText(FormatResult (val*0.000493737));
        }
        else if (position==9){
            textMilli.setText(FormatResult (val*1.609e+6));
            textCenti.setText(FormatResult (val*1.609e+6/10.0));
            textDeci.setText(FormatResult (val*1.609e+6/100.0));
            textMeter.setText(FormatResult (val*1.609e+6/1000.0));
            textHecto.setText(FormatResult (val*1.609e+6/100000.0));
            textKilo.setText(FormatResult (val*1.609e+6/1000000.0));
            textInch.setText(FormatResult (val*63360));
            textFoot.setText(FormatResult (val*5280));
            textYard.setText(FormatResult (val*1760));
            textMile.setText(FormatResult (val));
            textNauticalMile.setText(FormatResult (val*0.000493737));
        }
        else if (position==10){
            textMilli.setText(FormatResult (val*1.852e+6));
            textCenti.setText(FormatResult (val*1.852e+6/10.0));
            textDeci.setText(FormatResult (val*1.852e+6/100.0));
            textMeter.setText(FormatResult (val*1.852e+6/1000.0));
            textHecto.setText(FormatResult (val*1.852e+6/100000.0));
            textKilo.setText(FormatResult (val*1.852e+6/1000000.0));
            textInch.setText(FormatResult (val*72913.4));
            textFoot.setText(FormatResult (val*6076.12));
            textYard.setText(FormatResult (val*2025.37));
            textMile.setText(FormatResult (val*1.15078));
            textNauticalMile.setText(FormatResult (val));
        }
    }

    private String FormatResult(double result){
        result= BigDecimal.valueOf(result)
                .setScale(9,BigDecimal.ROUND_HALF_UP).doubleValue();

        if (result%1==0)
            return ((String.valueOf((int)result)));
        else {
            return (String.valueOf(result));
        }
    }
}
