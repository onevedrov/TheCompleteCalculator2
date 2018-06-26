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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoanCalculator extends Fragment {

    private Spinner spinnerPayMethod, spinnerLoanPeriodTime, spinnerInterestPeriodTime;
    private EditText editTextLoanPrincipal, editTextLoanPeriod
            , editTextInterestRate, editTextInterestOnlyPeriod;
    private TextView textViewMonthlyPay, textViewTotalPay, textViewMonthlyInterest, textViewTotalInterest;
    private ListView listViewRepayAmortization;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_loan_calculator, container, false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                setListeners();
            }
        }).start();

        SharedPreferences prefs=getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("lastFragment", R.id.nav_loan_calculator);
        editor.apply();

        getActivity().setTitle(R.string.Loan_Calculator);
        return view;
    }

    private void setListeners(){

        spinnerInterestPeriodTime=(Spinner) view.findViewById(R.id.spinnerInterestPeriodTime);
        spinnerLoanPeriodTime=(Spinner) view.findViewById(R.id.spinnerLoanPeriodTime);
        spinnerPayMethod=(Spinner) view.findViewById(R.id.spinnerPayMethod);

        spinnerPayMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            calculateResult();
                        }
                    }).start();

                }catch (Exception ex){}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerLoanPeriodTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            calculateResult();
                        }
                    }).start();

                }catch (Exception ex){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerInterestPeriodTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            calculateResult();
                        }
                    }).start();

                }catch (Exception ex){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        editTextInterestOnlyPeriod=(EditText) view.findViewById(R.id.editInterestPeriod);
        editTextInterestRate=(EditText) view.findViewById(R.id.editInterestRate);
        editTextLoanPeriod=(EditText) view.findViewById(R.id.editLoanPeriod);
        editTextLoanPrincipal=(EditText) view.findViewById(R.id.editLoanPrincipal);

        textViewMonthlyInterest=(TextView) view.findViewById(R.id.textViewMonthlyInterest);
        textViewMonthlyPay=(TextView) view.findViewById(R.id.textViewMonthlyPayment);
        textViewTotalPay=(TextView) view.findViewById(R.id.textViewTotalPayment);
        textViewTotalInterest=(TextView) view.findViewById(R.id.textViewTotalInterest);

        listViewRepayAmortization=(ListView) view.findViewById(R.id.listViewRepayAmortization);

        editTextInterestRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            calculateResult();
                        }
                    }).start();

                }catch (Exception ex){

                }
            }
        });

        editTextLoanPrincipal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            calculateResult();
                        }
                    }).start();

                }catch (Exception ex){

                }
            }
        });

        editTextLoanPeriod.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            calculateResult();
                        }
                    }).start();

                }catch (Exception ex){

                }
            }
        });

        editTextInterestOnlyPeriod.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            calculateResult();
                        }
                    }).start();

                }catch (Exception ex){

                }
            }
        });
    }

    private void calculateResult(){
        if (editTextLoanPeriod.length()>0 && editTextLoanPrincipal.length()>0
                && editTextInterestRate.length()>0)
        {
            double interestRate=Double.parseDouble(editTextInterestRate.getText().toString())/100/12;
            int i = spinnerPayMethod.getSelectedItemPosition();
            if (i == 0) {
                calculateLevel(interestRate);
            } else if (i == 1) {
                calculateFixedPrincipal(interestRate);
            } else if (i == 2) {
                calculateBalloon(interestRate);
            }
        }
    }

    private void calculateFixedPrincipal(double interestRate){
        int loanPeriod=Integer.parseInt(editTextLoanPeriod.getText().toString());
        int interestOnlyPeriod=0;
        double loanPrincipal=Double.parseDouble(editTextLoanPrincipal.getText().toString());
        ArrayList<RepayAmortization> repayAmortizations=new ArrayList<>();
        if (spinnerLoanPeriodTime.getSelectedItemPosition()==1)
            loanPeriod*=12;
        if (editTextInterestOnlyPeriod.length()>0)
            interestOnlyPeriod=Integer.parseInt(editTextInterestOnlyPeriod.getText().toString());
        if (spinnerInterestPeriodTime.getSelectedItemPosition()==1)
            interestOnlyPeriod*=12;


        if (interestOnlyPeriod>0 && interestOnlyPeriod<loanPeriod){
            double principal=loanPrincipal/(loanPeriod- interestOnlyPeriod);
            double totalInterest=0;
            double totalPay=0;
            for (int i=0;i<interestOnlyPeriod;i++){
                RepayAmortization amortization=new RepayAmortization();
                amortization.no=String.valueOf(i+1);
                amortization.interest=FormatResult( loanPrincipal*interestRate);
                amortization.principal=FormatResult(0);
                amortization.balance=FormatResult(loanPrincipal);
                amortization.payments=FormatResult(loanPrincipal*interestRate);
                repayAmortizations.add(amortization);

                totalInterest+=loanPrincipal*interestRate;
                totalPay+=loanPrincipal*interestRate;
            }

            for (int i=interestOnlyPeriod;i<loanPeriod;i++){
                double payment=principal+loanPrincipal * interestRate;
                RepayAmortization amortization=new RepayAmortization();
                amortization.no=String.valueOf(i+1);
                amortization.interest=FormatResult( loanPrincipal*interestRate);
                amortization.principal = FormatResult(principal);
                amortization.balance = FormatResult(loanPrincipal - principal);
                amortization.payments = FormatResult(payment);
                repayAmortizations.add(amortization);
                totalInterest+=(loanPrincipal*interestRate);
                totalPay+=payment;
                loanPrincipal = loanPrincipal - principal;
            }

            final double finalTotalInterest=totalInterest;
            final double finalTotalPay=totalPay;
            final double finalLoadPeriod=loanPeriod;

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textViewTotalInterest.setText(FormatResult(finalTotalInterest));
                    textViewTotalPay.setText(FormatResult(finalTotalPay));
                    textViewMonthlyPay.setText(FormatResult(finalTotalPay/finalLoadPeriod));
                    textViewMonthlyInterest.setText(FormatResult(finalTotalInterest/finalLoadPeriod));
                }
            });

        }
        else if (interestOnlyPeriod==0){
            double principal=loanPrincipal/loanPeriod;
            double totalInterest=0;
            double totalPay=0;
            for (int i=0;i<loanPeriod;i++) {
                double payment=principal+loanPrincipal * interestRate;
                RepayAmortization amortization = new RepayAmortization();
                amortization.no = String.valueOf(i + 1);
                amortization.interest = FormatResult(loanPrincipal * interestRate);
                amortization.principal = FormatResult(principal);
                amortization.balance = FormatResult(loanPrincipal - principal);
                amortization.payments = FormatResult(payment);
                repayAmortizations.add(amortization);
                totalInterest+=(loanPrincipal*interestRate);
                totalPay+=payment;
                loanPrincipal = loanPrincipal - principal;

            }
            final double finalTotalInterest=totalInterest;
            final double finalTotalPay=totalPay;
            final double finalLoadPeriod=loanPeriod;

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textViewTotalInterest.setText(FormatResult(finalTotalInterest));
                    textViewTotalPay.setText(FormatResult(finalTotalPay));
                    textViewMonthlyPay.setText(FormatResult(finalTotalPay/finalLoadPeriod));
                    textViewMonthlyInterest.setText(FormatResult(finalTotalInterest/finalLoadPeriod));
                }
            });

        }
        else if (interestOnlyPeriod>=loanPeriod){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(),"Interest only period can't be bigger than or equal to loan period",Toast.LENGTH_SHORT).show();
                }
            });

        }

        final RepayAmortizationAdapter amortizationAdapter=new RepayAmortizationAdapter(getActivity(),repayAmortizations);
        listViewRepayAmortization.post(new Runnable() {
            @Override
            public void run() {
                listViewRepayAmortization.setAdapter(amortizationAdapter);
            }
        });

    }
    private void calculateLevel(double interestRate){
        int loanPeriod=Integer.parseInt(editTextLoanPeriod.getText().toString());
        int interestOnlyPeriod=0;
        double loanPrincipal=Double.parseDouble(editTextLoanPrincipal.getText().toString());
        double paymentPerPeriod;
        ArrayList<RepayAmortization> repayAmortizations=new ArrayList<>();
        if (spinnerLoanPeriodTime.getSelectedItemPosition()==1)
            loanPeriod*=12;
        if (editTextInterestOnlyPeriod.length()>0)
            interestOnlyPeriod=Integer.parseInt(editTextInterestOnlyPeriod.getText().toString());
        if (spinnerInterestPeriodTime.getSelectedItemPosition()==1)
            interestOnlyPeriod*=12;


        if (interestOnlyPeriod>0 && interestOnlyPeriod<loanPeriod){
            paymentPerPeriod=(interestRate/(1-Math.pow(1+interestRate,-(loanPeriod-interestOnlyPeriod))))*loanPrincipal;
            double totalInterest=0;
            double totalPay=0;
            for (int i=0;i<interestOnlyPeriod;i++){
                RepayAmortization amortization=new RepayAmortization();
                amortization.no=String.valueOf(i+1);
                amortization.interest=FormatResult( loanPrincipal*interestRate);
                amortization.principal=FormatResult(0);
                amortization.balance=FormatResult(loanPrincipal);
                amortization.payments=FormatResult(loanPrincipal*interestRate);
                repayAmortizations.add(amortization);

                totalInterest+=loanPrincipal*interestRate;
                totalPay+=loanPrincipal*interestRate;
            }

            for (int i=interestOnlyPeriod;i<loanPeriod;i++){
                RepayAmortization amortization=new RepayAmortization();
                amortization.no=String.valueOf(i+1);
                amortization.interest=FormatResult( loanPrincipal*interestRate);
                amortization.principal=FormatResult(paymentPerPeriod-loanPrincipal*interestRate);
                amortization.balance=FormatResult(loanPrincipal-(paymentPerPeriod-loanPrincipal*interestRate));
                amortization.payments=FormatResult(paymentPerPeriod);
                repayAmortizations.add(amortization);
                totalInterest+=loanPrincipal*interestRate;
                totalPay+=paymentPerPeriod;
                loanPrincipal=loanPrincipal-(paymentPerPeriod-loanPrincipal*interestRate);
            }
            final double finalTotalInterest=totalInterest;
            final double finalTotalPay=totalPay;
            final double finalLoadPeriod=loanPeriod;

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textViewTotalInterest.setText(FormatResult(finalTotalInterest));
                    textViewTotalPay.setText(FormatResult(finalTotalPay));
                    textViewMonthlyPay.setText(FormatResult(finalTotalPay/finalLoadPeriod));
                    textViewMonthlyInterest.setText(FormatResult(finalTotalInterest/finalLoadPeriod));
                }
            });

        }
        else if (interestOnlyPeriod==0){
            paymentPerPeriod=(interestRate/(1-Math.pow(1+interestRate,-loanPeriod)))*loanPrincipal;
            double totalInterest=0;
            double totalPay=0;
            for (int i=0;i<loanPeriod;i++) {
                RepayAmortization amortization = new RepayAmortization();
                amortization.no = String.valueOf(i + 1);
                amortization.interest = FormatResult(loanPrincipal * interestRate);
                amortization.principal = FormatResult(paymentPerPeriod - loanPrincipal * interestRate);
                amortization.balance = FormatResult(loanPrincipal - (paymentPerPeriod - loanPrincipal * interestRate));
                amortization.payments = FormatResult(paymentPerPeriod);
                repayAmortizations.add(amortization);
                totalInterest+=(loanPrincipal*interestRate);
                totalPay+=paymentPerPeriod;
                loanPrincipal = loanPrincipal - (paymentPerPeriod - loanPrincipal * interestRate);

            }
            final double finalTotalInterest=totalInterest;
            final double finalTotalPay=totalPay;
            final double finalLoadPeriod=loanPeriod;

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textViewTotalInterest.setText(FormatResult(finalTotalInterest));
                    textViewTotalPay.setText(FormatResult(finalTotalPay));
                    textViewMonthlyPay.setText(FormatResult(finalTotalPay/finalLoadPeriod));
                    textViewMonthlyInterest.setText(FormatResult(finalTotalInterest/finalLoadPeriod));
                }
            });

        }
        else if (interestOnlyPeriod>=loanPeriod){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(),"Interest only period can't be bigger than or equal to loan period",Toast.LENGTH_SHORT).show();
                }
            });

        }

        final RepayAmortizationAdapter amortizationAdapter=new RepayAmortizationAdapter(getActivity(),repayAmortizations);
        listViewRepayAmortization.post(new Runnable() {
            @Override
            public void run() {
                listViewRepayAmortization.setAdapter(amortizationAdapter);
            }
        });
    }

    private void calculateBalloon(final double interestRate){
        int loanPeriod=Integer.parseInt(editTextLoanPeriod.getText().toString());
        final double loanPrincipal=Double.parseDouble(editTextLoanPrincipal.getText().toString());
        ArrayList<RepayAmortization> repayAmortizations=new ArrayList<>();
        if (spinnerLoanPeriodTime.getSelectedItemPosition()==1)
            loanPeriod*=12;

        double totalInterest=0;
        double totalPay=0;
        for (int i=0;i<loanPeriod-1;i++){
            RepayAmortization amortization=new RepayAmortization();
            amortization.no=String.valueOf(i+1);
            amortization.interest=FormatResult( loanPrincipal*interestRate);
            amortization.principal=FormatResult(0);
            amortization.balance=FormatResult(loanPrincipal);
            amortization.payments=FormatResult(loanPrincipal*interestRate);
            repayAmortizations.add(amortization);

            totalInterest+=loanPrincipal*interestRate;
            totalPay+=loanPrincipal*interestRate;
        }

        for (int i=loanPeriod-1;i<loanPeriod;i++){
            RepayAmortization amortization=new RepayAmortization();
            amortization.no=String.valueOf(i+1);
            amortization.interest=FormatResult( loanPrincipal*interestRate);
            amortization.principal=FormatResult(loanPrincipal);
            amortization.balance=FormatResult(0);
            amortization.payments=FormatResult(loanPrincipal*interestRate+loanPrincipal);
            repayAmortizations.add(amortization);

            totalInterest+=loanPrincipal*interestRate;
            totalPay+=loanPrincipal*interestRate+loanPrincipal;
        }
        final double finalTotalInterest=totalInterest;
        final double finalTotalPay=totalPay;
        final double finalLoadPeriod=loanPeriod;

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewTotalInterest.setText(FormatResult(finalTotalInterest));
                textViewTotalPay.setText(FormatResult(finalTotalPay));
                textViewMonthlyPay.setText(FormatResult(finalTotalPay/finalLoadPeriod));
                textViewMonthlyInterest.setText(FormatResult(loanPrincipal*interestRate));
            }
        });

        final RepayAmortizationAdapter amortizationAdapter=new RepayAmortizationAdapter(getActivity(),repayAmortizations);
        listViewRepayAmortization.post(new Runnable() {
            @Override
            public void run() {
                listViewRepayAmortization.setAdapter(amortizationAdapter);
            }
        });
    }

    private String FormatResult(double result){
        result= BigDecimal.valueOf(result)
                .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

        return ("$"+String.valueOf(result));
    }
}
