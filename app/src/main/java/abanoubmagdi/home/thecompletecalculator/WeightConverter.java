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
public class WeightConverter extends Fragment {

    private View view;
    private TextView textMilligram, textGram, textKilogram, textTon, textTonUK ,textTonUS,
            textStoneUK, textGrain,textOunce,textPound;
    private EditText editTextWeight;
    private Spinner weightSpinner;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_weight_converter,null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                setListeners();
            }
        }).start();
        return view;
    }

    private void setListeners(){
        textGram=(TextView) view.findViewById(R.id.gram);
        textMilligram=(TextView) view.findViewById(R.id.milligram);
        textKilogram=(TextView) view.findViewById(R.id.kilogram);
        textTon=(TextView) view.findViewById(R.id.ton);
        textTonUK=(TextView) view.findViewById(R.id.ton_uk);
        textTonUS=(TextView) view.findViewById(R.id.ton_us);
        textStoneUK=(TextView) view.findViewById(R.id.stone_uk);
        textGrain=(TextView) view.findViewById(R.id.grain);
        textOunce=(TextView) view.findViewById(R.id.ounce);
        textPound=(TextView) view.findViewById(R.id.pound);

        editTextWeight=(EditText) view.findViewById(R.id.weightEditText);
        weightSpinner=(Spinner) view.findViewById(R.id.weightSpinner);

        editTextWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                        OnItemClickListener(weightSpinner.getSelectedItemPosition());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        weightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        if (!editTextWeight.getText().toString().isEmpty())
        {
            val=Double.parseDouble(editTextWeight.getText().toString());
        }
        if (position==0)
        {
            textMilligram.setText(FormatResult(val));
            textGram.setText(FormatResult(val/1000.0));
            textKilogram.setText(FormatResult(val/1000000.0));
            textTon.setText(FormatResult(val/1000000000.0));
            textTonUS.setText(FormatResult(val*1.1023e-9));
            textTonUK.setText(FormatResult(val*0.000984/1000000));
            textStoneUK.setText(FormatResult(val*1.5747e-7));
            textGrain.setText(FormatResult(val*0.015432));
            textOunce.setText(FormatResult(val*0.000035));
            textPound.setText(FormatResult (val*0.002205/1000));
        }
        else if (position==1)
        {
            textMilligram.setText(FormatResult(val*1000));
            textGram.setText(FormatResult (val));
            textKilogram.setText(FormatResult (val*.001));
            textTon.setText(FormatResult (val*.000001));
            textTonUS.setText(FormatResult (val*1.102311*.000001));
            textTonUK.setText(FormatResult (val*0.984207*.000001));
            textStoneUK.setText(FormatResult (val*157.473044*.000001));
            textGrain.setText(FormatResult (val*15.432358));
            textOunce.setText(FormatResult (val*0.035274));
            textPound.setText(FormatResult (val*0.002205));
        }
        else if (position==2)
        {
            textMilligram.setText(FormatResult (val*1000000.0));
            textGram.setText(FormatResult (val*1000.0));
            textKilogram.setText(FormatResult (val));
            textTon.setText(FormatResult (val*.001));
            textTonUS.setText(FormatResult (val*1.102311*.001));
            textTonUK.setText(FormatResult (val*0.984207*.001));
            textStoneUK.setText(FormatResult (val*.157473044));
            textGrain.setText(FormatResult (val*15432.358353));
            textOunce.setText(FormatResult (val*35.273962));
            textPound.setText(FormatResult (val*2.204623));
        }
        else if (position==3)
        {
            textMilligram.setText(FormatResult (val*1000000000.0));
            textGram.setText(FormatResult (val*1000000.0));
            textKilogram.setText(FormatResult (val*1000));
            textTon.setText(FormatResult (val));
            textTonUS.setText(FormatResult (val*1.102311));
            textTonUK.setText(FormatResult (val*0.984207));
            textStoneUK.setText(FormatResult (val*157.473044));
            textGrain.setText(FormatResult (val*15432358.352941));
            textOunce.setText(FormatResult (val*35273.961950));
            textPound.setText(FormatResult (val*2204.622622));
        }
        else if (position==4){
            textMilligram.setText(FormatResult (val*9.072e+8));
            textGram.setText(FormatResult (val*907185));
            textKilogram.setText(FormatResult (val*907.185));
            textTon.setText(FormatResult (val*.907185));
            textTonUS.setText(FormatResult (val));
            textTonUK.setText(FormatResult (val*0.892857));
            textStoneUK.setText(FormatResult (val*142.857));
            textGrain.setText(FormatResult (val*14000000.0));
            textOunce.setText(FormatResult (val*32000.0));
            textPound.setText(FormatResult (val*2000.0));
        }
        else if (position==5){
            textMilligram.setText(FormatResult (val*1016046908.8));
            textGram.setText(FormatResult (val*1016046.9088));
            textKilogram.setText(FormatResult (val*1016.046909));
            textTon.setText(FormatResult (val*1.016047));
            textTonUS.setText(FormatResult (val*1.12));
            textTonUK.setText(FormatResult (val));
            textStoneUK.setText(FormatResult (val*160.0));
            textGrain.setText(FormatResult (val*15680000.0));
            textOunce.setText(FormatResult (val*35840.0));
            textPound.setText(FormatResult (val*2240.0));
        }
        else if (position==6){
            textMilligram.setText(FormatResult (val*6350293.18));
            textGram.setText(FormatResult (val*6350.29318));
            textKilogram.setText(FormatResult (val*6.35029318));
            textTon.setText(FormatResult (val*.00635029318));
            textTonUS.setText(FormatResult (val*.007));
            textTonUK.setText(FormatResult (val*0.006250));
            textStoneUK.setText(FormatResult (val));
            textGrain.setText(FormatResult (val*98000.0));
            textOunce.setText(FormatResult (val*224.0));
            textPound.setText(FormatResult (val*14.0));
        }
        else if (position==7){
            textMilligram.setText(FormatResult (val*64.798910));
            textGram.setText(FormatResult (val*0.064799));
            textKilogram.setText(FormatResult (val*0.064799/1000));
            textTon.setText(FormatResult (val*0.064799/1000000));
            textTonUS.setText(FormatResult (val*7.142857142857E-8  ));
            textTonUK.setText(FormatResult (val*6.377551020408E-8 ));
            textStoneUK.setText(FormatResult (val*0.00001020408163265 ));
            textGrain.setText(FormatResult (val));
            textOunce.setText(FormatResult (val*0.002285714285714 ));
            textPound.setText(FormatResult (val*0.0001428571428571 ));
        }
        else if (position==8){
            textMilligram.setText(FormatResult (val*28349.523125 ));
            textGram.setText(FormatResult (val*28.349523125 ));
            textKilogram.setText(FormatResult (val*0.028349523125 ));
            textTon.setText(FormatResult (val*0.000028349523125 ));
            textTonUS.setText(FormatResult (val*0.00003125));
            textTonUK.setText(FormatResult (val*0.00002790178571429  ));
            textStoneUK.setText(FormatResult (val*0.004464285714286  ));
            textGrain.setText(FormatResult (val*437.5 ));
            textOunce.setText(FormatResult (val));
            textPound.setText(FormatResult (val*0.0625  ));
        }
        else if (position==9){
            textMilligram.setText(FormatResult (val*453592.37 ));
            textGram.setText(FormatResult (val*453.59237));
            textKilogram.setText(FormatResult (val*.45359237  ));
            textTon.setText(FormatResult (val*0.00045359237 ));
            textTonUS.setText(FormatResult (val*0.0005 ));
            textTonUK.setText(FormatResult (val*0.0004464285714286  ));
            textStoneUK.setText(FormatResult (val*0.07142857142857  ));
            textGrain.setText(FormatResult (val*437.5 ));
            textOunce.setText(FormatResult (val*16));
            textPound.setText(FormatResult (val ));
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
