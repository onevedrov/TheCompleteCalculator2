package bqds.constructioncalculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorSci extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(abanoubmagdi.home.thecompletecalculator.R.layout.fragment_calculator_sci, container, false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                setListeners();
            }
        }).start();
        return view;
    }

    private void setListeners(){
        Button btnAsin=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_sin_1);
        Button btnAcos=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_cos_1);
        Button btnAtan=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_tan_1);
        Button btn10Pow=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_10pow);
        Button btn0=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_0);
        Button btn1=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_1);
        Button btn2=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_2);
        Button btn3=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_3);
        Button btn4=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_4);
        Button btn5=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_5);
        Button btn6=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_6);
        Button btn7=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_7);
        Button btn8=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_8);
        Button btn9=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_9);
        Button btnPoint=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_point);
        Button btnEqual=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_equal);
        Button btnPlus=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_plus);
        Button btnMinus=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_minus);
        Button btnMult=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_mult);
        Button btnDiv=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_divide);
        Button btnE=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_e);
        Button btnPi=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_pi);
        Button btnLog=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_log);
        Button btnLn=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_ln);
        Button btnLeftParentheses=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_leftparentheses);
        Button btnRightParentheses=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_rightparentheses);
        Button btnSqrt=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_square_root);
        Button btnClearAll=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_clear_all);
        Button btnBackspace=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_backspace);
        Button btnAbs=(Button) view.findViewById(abanoubmagdi.home.thecompletecalculator.R.id.button_abs);

        btnAcos.setText(Html.fromHtml("sin<sup>-1</sup>"));
        btnAsin.setText(Html.fromHtml("cos<sup>-1</sup>"));
        btnAtan.setText(Html.fromHtml("tan<sup>-1</sup>"));
        btn10Pow.setText(Html.fromHtml("10<sup>x</sup>"));

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnNumClick(v);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnNumClick(v);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnNumClick(v);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnNumClick(v);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnNumClick(v);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnNumClick(v);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnNumClick(v);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnNumClick(v);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnNumClick(v);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnNumClick(v);
            }
        });
        btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnNumClick(v);
            }
        });
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnEqualClick(v,getContext());
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnOperatorClick(v);
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnOperatorClick(v);
            }
        });

        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnOperatorClick(v);
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnOperatorClick(v);
            }
        });

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnNumClick(v);
            }
        });
        btnPi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnNumClick(v);
            }
        });

        btnAtan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnInvFunctionClick(v);
            }
        });


        btnAsin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnInvFunctionClick(v);
            }
        });

        btnAcos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnInvFunctionClick(v);
            }
        });
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnFunctClick(v);
            }
        });
        btnLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnFunctClick(v);
            }
        });

        btnLeftParentheses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnParenthesesClick(v);
            }
        });

        btnRightParentheses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnParenthesesClick(v);
            }
        });

        btnSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnSqrtClick(v);
            }
        });

        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnClearAll(v);
            }
        });

        btnBackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnBackspace();
            }
        });

        btnAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnFunctClick(v);
            }
        });

        btn10Pow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.Btn10PowClick(v);
            }
        });
    }
}
