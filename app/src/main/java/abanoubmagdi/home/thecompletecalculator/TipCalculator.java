package abanoubmagdi.home.thecompletecalculator;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipCalculator extends Fragment {

    private View view;
    private EditText billAmount,tipPercent,taxAmount,numPeople;
    private TextView textViewTipBasis, textViewTipAmount, textViewTax, textViewTotalPay
            , textViewBillPerPerson, textViewTipPerPerson, textViewTotalPerPerson;

    public TipCalculator(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_tip_calculator, container, false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                setListeners();
            }
        }).start();
        SharedPreferences prefs=getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("lastFragment", R.id.nav_tip_calculator);
        editor.apply();

        getActivity().setTitle(R.string.Tip_Calculator);
        return view;
    }

    private void setListeners(){

        billAmount=(EditText) view.findViewById(R.id.billAmount);
        tipPercent=(EditText) view.findViewById(R.id.tip);
        taxAmount=(EditText) view.findViewById(R.id.taxAmount);
        numPeople=(EditText) view.findViewById(R.id.numPeople);

        textViewTipBasis=(TextView) view.findViewById(R.id.tipBasis);
        textViewTipAmount=(TextView) view.findViewById(R.id.tipAmount);
        textViewTax=(TextView) view.findViewById(R.id.tax);
        textViewTotalPay=(TextView) view.findViewById(R.id.totalPay);
        textViewBillPerPerson=(TextView) view.findViewById(R.id.billPerPerson);
        textViewTipPerPerson=(TextView) view.findViewById(R.id.tipPerPerson);
        textViewTotalPerPerson=(TextView) view.findViewById(R.id.totalPerPerson);

        Button btnIncTip=(Button) view.findViewById(R.id.tipIncrease);
        Button btnDecTip=(Button) view.findViewById(R.id.tipDecrease);
        Button btnIncPeople=(Button) view.findViewById(R.id.numPeopleIncrease);
        Button btnDecPeople=(Button) view.findViewById(R.id.numPeopleDecrease);

        btnIncTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double tip;
                if (tipPercent.getText().toString().isEmpty()) tip=0;
                else
                    tip=Double.parseDouble( tipPercent.getText().toString());

                tip=tip+1;
                tipPercent.setText(String.valueOf(tip));

            }
        });

        btnDecTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double tip;
                if (tipPercent.getText().toString().isEmpty()) tip=0;
                else
                    tip=Double.parseDouble( tipPercent.getText().toString());
                if (tip>0){
                    tip=tip-1;
                    tipPercent.setText(String.valueOf(tip));
                }

            }
        });

        btnIncPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num;
                if (numPeople.getText().toString().isEmpty()) num=1;
                else
                    num=Integer.parseInt( numPeople.getText().toString());

                num=num+1;
                numPeople.setText(String.valueOf(num));

            }
        });

        btnDecPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num;
                if (numPeople.getText().toString().isEmpty()) num=1;
                else
                    num=Integer.parseInt( numPeople.getText().toString());
                if (num>1) {
                    num = num - 1;
                    numPeople.setText(String.valueOf(num));
                }
            }
        });

        billAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                DisplayResults();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tipPercent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                DisplayResults();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        taxAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                DisplayResults();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        numPeople.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                DisplayResults();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    void DisplayResults(){
        double bill, tip, tax, tipBasis, tipAmount, totalPay
                ,tipPerperson, totalPerPerson, billPerPerson;
        int people;
        if (billAmount.getText().toString().isEmpty()) bill=0;
        else
            bill= Double.parseDouble( billAmount.getText().toString());
        if (tipPercent.getText().toString().isEmpty()) tip=0;
        else
            tip= Double.parseDouble( tipPercent.getText().toString())/100;
        if (taxAmount.getText().toString().isEmpty()) tax=0;
        else
            tax= Double.parseDouble( taxAmount.getText().toString());
        if (numPeople.getText().toString().isEmpty()) people=1;
        else
            people= Integer.parseInt( numPeople.getText().toString());

        tipBasis=bill-tax;
        tipAmount=tip*(bill-tax);
        totalPay=tip*(bill-tax)+bill;
        tipPerperson=tip*(bill-tax)/people;
        totalPerPerson=totalPay/people;
        billPerPerson=bill/people;
        int temp=0;

        if (tipBasis%1==0){
            temp=(int)tipBasis;
            textViewTipBasis.setText(String.valueOf( temp));
        }else {
            textViewTipBasis.setText(BigDecimal.valueOf(tipBasis)
                    .setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString());
        }

        if (tipAmount%1==0){
            temp=(int) tipAmount;
            textViewTipAmount.setText(String.valueOf( temp));
        } else textViewTipAmount.setText(BigDecimal.valueOf(tipAmount).
                setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString());

        if (totalPay%1==0){
            temp=(int) totalPay;
            textViewTotalPay.setText(String.valueOf( temp));
        } else textViewTotalPay.setText(BigDecimal.valueOf(totalPay).
                setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString());

        if (tipPerperson%1==0){
            temp=(int) tipPerperson;
            textViewTipPerPerson.setText(String.valueOf( temp));
        } else textViewTipPerPerson.setText(BigDecimal.valueOf(tipPerperson).
                setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString());

        if (totalPerPerson%1==0){
            temp=(int) totalPerPerson;
            textViewTotalPerPerson.setText(String.valueOf( temp));
        } else textViewTotalPerPerson.setText(BigDecimal.valueOf(totalPerPerson).
                setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString());

        if (billPerPerson%1==0){
            temp=(int) billPerPerson;
            textViewBillPerPerson.setText(String.valueOf( temp));
        } else textViewBillPerPerson.setText(BigDecimal.valueOf(billPerPerson).
                setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        if (tax%1==0){
            temp=(int) tax;
            textViewTax.setText(String.valueOf( temp));
        } else textViewTax.setText(String.valueOf(tax));

    }
}
