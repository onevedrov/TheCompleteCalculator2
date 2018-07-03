package bqds.constructioncalculator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import static android.content.Context.MODE_PRIVATE;

public class Calculator extends Fragment {

    private View view;
    private static TextView resultTxtView;
    private static EditText equationEdtView;
    private static SpannableStringBuilder equationSpan;
    private static boolean equationHasOperatorOrFunction;

    public Calculator(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_calculator, container, false);

        equationSpan=new SpannableStringBuilder("");
        equationHasOperatorOrFunction=false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                setListeners();
            }
        }).start();

        try {
            if (savedInstanceState!=null) {
                if (savedInstanceState.getSerializable("equation") != null) {
                    Equation equation = (Equation) savedInstanceState.getSerializable("equation");
                    equationSpan=equation.getEquation();
                    equationEdtView.setText(equationSpan);
//                    equationEdtView.setSelection(equation.getPosition());
                    resultTxtView.setText(equation.getResult());
                    equationHasOperatorOrFunction=equation.isEquationHasOperatorOrFunction();
                }
            }
        }catch (Exception ex){}

        SharedPreferences prefs = getActivity().getPreferences(MODE_PRIVATE);
        if (prefs.getBoolean("Show Dialog", true))
        {
            View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
            final CheckBox dialogCheckBox=(CheckBox)dialogView.findViewById(R.id.checkBox);
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setCancelable(true);
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if (dialogCheckBox.isChecked()) {
                        SharedPreferences.Editor editor = getActivity().getPreferences(MODE_PRIVATE).edit();
                        editor.putBoolean("Show Dialog", false);
                        editor.apply();
                    } else {
                        SharedPreferences.Editor editor = getActivity().getPreferences(MODE_PRIVATE).edit();
                        editor.putBoolean("Show Dialog", true);
                        editor.apply();
                    }
                }
            });
            AlertDialog alertDialog = builder.create();

            alertDialog.setView(dialogView);
            alertDialog.setMessage("Swipe left for more functions");
            alertDialog.show();
        }

        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("lastFragment", R.id.nav_calculator);
        editor.apply();

//        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
//                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        getActivity().setTitle(R.string.Calculator);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("equation",
                new Equation(equationSpan,equationEdtView.getSelectionStart()
                        , equationHasOperatorOrFunction,resultTxtView.getText().toString()));
        super.onSaveInstanceState(outState);
    }

    private void setListeners(){

        resultTxtView=(TextView) view.findViewById(R.id.result);
        equationEdtView=(EditText) view.findViewById(R.id.equation);

        if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE) {

            OnLandscape();

        }else if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
                    CalculatorPagerAdapter pagerAdapter = new CalculatorPagerAdapter(getChildFragmentManager());
                    viewPager.setAdapter(pagerAdapter);
                    viewPager.setCurrentItem(0);
                }
            });
        }
        equationEdtView.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
        equationEdtView.setLongClickable(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            equationEdtView.setShowSoftInputOnFocus(false);
        } else {
            try {
                final Method method = EditText.class.getMethod(
                        "setShowSoftInputOnFocus"
                        , new Class[]{boolean.class});
                method.setAccessible(true);
                method.invoke(equationEdtView, false);
            } catch (Exception e) {}
        }
    }

    public static void BtnNumClick(View view)
    {
        equationSpan=new SpannableStringBuilder(equationEdtView.getText());
        int position=equationEdtView.getSelectionStart();
        if (position>0) {
            if (CheckInsertion(position)) {
                CharSequence txt= ((Button) view).getText();
                equationSpan.insert(position, txt);
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position + 1);

                if (equationHasOperatorOrFunction)
                    Equal(false,null);
            }
        }else {
            CharSequence txt= ((Button) view).getText();
            equationSpan.insert(position, txt);
            equationEdtView.setText(equationSpan);
            equationEdtView.setSelection(position + 1);

            if (equationHasOperatorOrFunction)
                Equal(false,null);
        }
    }

    public static void BtnOperatorClick(View view)
    {
        equationSpan=new SpannableStringBuilder(equationEdtView.getText());
        int position=equationEdtView.getSelectionStart();
        if (position>0) {
            if (CheckInsertion(position)) {
                CharSequence txt= ((Button) view).getText();
                equationHasOperatorOrFunction=true;
                equationSpan.insert(position, txt);
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position + 1);
            }
        }else {
            CharSequence txt= ((Button) view).getText();
            equationHasOperatorOrFunction=true;
            equationSpan.insert(position, txt);
            equationEdtView.setText(equationSpan);
            equationEdtView.setSelection(position + 1);
        }
    }

    public static void BtnPowClick(View view)
    {
        equationSpan=new SpannableStringBuilder(equationEdtView.getText());
        int position=equationEdtView.getSelectionStart();
        if (position>0) {
            if (CheckInsertion(position)) {
                equationHasOperatorOrFunction=true;
                equationSpan.insert(position, "^");
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position + 1);
            }
        }else {
            equationHasOperatorOrFunction=true;
            equationSpan.insert(position, "^");
            equationEdtView.setText(equationSpan);
            equationEdtView.setSelection(position + 1);
        }
    }

    public static void Btn10PowClick(View view)
    {
        equationSpan=new SpannableStringBuilder(equationEdtView.getText());
        int position=equationEdtView.getSelectionStart();
        if (position>0) {
            if (CheckInsertion(position)) {
                equationHasOperatorOrFunction=true;
                equationSpan.insert(position, "10^");
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position + 3);
            }
        }else {
            equationHasOperatorOrFunction=true;
            equationSpan.insert(position, "10^");
            equationEdtView.setText(equationSpan);
            equationEdtView.setSelection(position + 3);
        }
    }

    public static void BtnFunctClick(View view){
        equationSpan=new SpannableStringBuilder(equationEdtView.getText());
        int position=equationEdtView.getSelectionStart();
        if (position>0) {
            if (CheckInsertion(position)) {
                CharSequence txt= ((Button) view).getText();
                equationHasOperatorOrFunction=true;
                equationSpan.insert(position,txt+"(");
                equationEdtView.setText(equationSpan);
                if (txt.equals("ln")) equationEdtView.setSelection(position+3);
                else equationEdtView.setSelection(position+4);
            }
        }else {
            CharSequence txt= ((Button) view).getText();
            equationHasOperatorOrFunction=true;
            equationSpan.insert(position,txt+"(");
            equationEdtView.setText(equationSpan);
            if (txt.equals("ln")) equationEdtView.setSelection(position+3);
            else equationEdtView.setSelection(position+4);
        }
    }

    public static void BtnInvFunctionClick(View view)
    {
        equationSpan=new SpannableStringBuilder(equationEdtView.getText());
        int position=equationEdtView.getSelectionStart();
        if (position>0) {
            if (CheckInsertion(position)) {
                CharSequence txt=((Button) view).getText();
                equationHasOperatorOrFunction=true;

                equationSpan.insert(position,txt+"(");

                equationSpan.setSpan(new SuperscriptSpan(),
                        position+3,position+5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                equationSpan.setSpan(new RelativeSizeSpan(.75f),
                        position+3,position+5,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position+6);
            }
        }else {
            CharSequence txt=((Button) view).getText();
            equationHasOperatorOrFunction=true;

            equationSpan.insert(position,txt+"(");

            equationSpan.setSpan(new SuperscriptSpan(),
                    position+3,position+5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            equationSpan.setSpan(new RelativeSizeSpan(.75f),
                    position+3,position+5,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            equationEdtView.setText(equationSpan);
            equationEdtView.setSelection(position+6);
        }
    }

    public static void BtnSqrtClick(View view)
    {
        equationSpan=new SpannableStringBuilder(equationEdtView.getText());
        int position=equationEdtView.getSelectionStart();
        if (position>0) {
            if (CheckInsertion(position)) {
                CharSequence txt= ((Button) view).getText();
                equationHasOperatorOrFunction=true;
                equationSpan.insert(position, txt);
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position + 1);
            }
        }else {
            CharSequence txt= ((Button) view).getText();
            equationHasOperatorOrFunction=true;
            equationSpan.insert(position, txt);
            equationEdtView.setText(equationSpan);
            equationEdtView.setSelection(position + 1);
        }
    }

    public static void BtnParenthesesClick(View view)
    {
        equationSpan=new SpannableStringBuilder(equationEdtView.getText());
        int position=equationEdtView.getSelectionStart();
        if (position>0) {
            if (CheckInsertion(position)) {
                CharSequence txt= ((Button) view).getText();
                equationHasOperatorOrFunction=true;
                equationSpan.insert(position, txt);
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position + 1);
            }
        }else {
            CharSequence txt= ((Button) view).getText();
            equationHasOperatorOrFunction=true;
            equationSpan.insert(position, txt);
            equationEdtView.setText(equationSpan);
            equationEdtView.setSelection(position + 1);
        }
    }

    public static void BtnBackspace()
    {
        String equationEdit=Equation.BackspaceFunFormationBefore(new SpannableStringBuilder(equationEdtView.getText()));
        int position=equationEdtView.getSelectionStart()-1;

        if (!equationEdit.isEmpty() && position>=0){

            StringBuilder equationBuilder=new StringBuilder(equationEdit);

            if (equationBuilder.charAt(position)=='A' || equationBuilder.charAt(position)=='B'
                    || equationBuilder.charAt(position)=='C' || equationBuilder.charAt(position)=='H'
                    || equationBuilder.charAt(position)=='L')
            {

                equationBuilder.delete(position,position+4);
                equationSpan= Equation.BackspaceFunFormationAfter(equationBuilder.toString());
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position);
            }
            else if (equationBuilder.charAt(position)=='D' || equationBuilder.charAt(position)=='E'
                    || equationBuilder.charAt(position)=='G')
            {

                equationBuilder.delete(position,position+6);
                equationSpan= Equation.BackspaceFunFormationAfter(equationBuilder.toString());
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position);
            }
            else if (equationBuilder.charAt(position)=='J')
            {

                equationBuilder.delete(position,position+3);
                equationSpan= Equation.BackspaceFunFormationAfter(equationBuilder.toString());
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position);
            }
            else if (position >=1 && (equationBuilder.substring(position-1,position+1).equals("JF")))
            {
                equationBuilder.delete(position-1,position+2);
                equationSpan= Equation.BackspaceFunFormationAfter(equationBuilder.toString());
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position-1);
            }
            else if (position >=2 && (equationBuilder.substring(position-2,position+1).equals("JFF")))
            {
                equationBuilder.delete(position-2,position+1);
                equationSpan= Equation.BackspaceFunFormationAfter(equationBuilder.toString());
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position-2);
            }
            else if (position >=1 && (equationBuilder.substring(position-1,position+1).equals("AF")
                    || equationBuilder.substring(position-1,position+1).equals("BF")
                    || equationBuilder.substring(position-1,position+1).equals("CF")
                    || equationBuilder.substring(position-1,position+1).equals("HF")
                    || equationBuilder.substring(position-1,position+1).equals("LF")))
            {
                equationBuilder.delete(position-1,position+3);
                equationSpan=Equation.BackspaceFunFormationAfter(equationBuilder.toString());
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position-1);
            }
            else if (position>=2 && (equationBuilder.substring(position-2,position+1).equals("AFF")
                    || equationBuilder.substring(position-2,position+1).equals("BFF")
                    || equationBuilder.substring(position-2,position+1).equals("CFF")
                    || equationBuilder.substring(position-2,position+1).equals("HFF")
                    || equationBuilder.substring(position-2,position+1).equals("LFF")) )
            {
                equationBuilder.delete(position-2,position+2);
                equationSpan=Equation.BackspaceFunFormationAfter(equationBuilder.toString());
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position-2);
            }
            else if (position>=3 && (equationBuilder.substring(position-3,position+1).equals("AFFF")
                    || equationBuilder.substring(position-3,position+1).equals("BFFF")
                    || equationBuilder.substring(position-3,position+1).equals("CFFF")
                    || equationBuilder.substring(position-3,position+1).equals("HFFF")
                    || equationBuilder.substring(position-3,position+1).equals("LFFF")) )
            {
                equationBuilder.delete(position-3,position+1);
                equationSpan=Equation.BackspaceFunFormationAfter(equationBuilder.toString());
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position-3);
            }
            else if (position >=1 && (equationBuilder.substring(position-1,position+1).equals("DF")
                    || equationBuilder.substring(position-1,position+1).equals("EF")
                    || equationBuilder.substring(position-1,position+1).equals("GF")))
            {
                equationBuilder.delete(position-1,position+5);
                equationSpan=Equation.BackspaceFunFormationAfter(equationBuilder.toString());
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position-1);
            }
            else if (position >=2 && (equationBuilder.substring(position-2,position+1).equals("DFF")
                    || equationBuilder.substring(position-2,position+1).equals("EFF")
                    || equationBuilder.substring(position-2,position+1).equals("GFF")))
            {
                equationBuilder.delete(position-2,position+4);
                equationSpan=Equation.BackspaceFunFormationAfter(equationBuilder.toString());
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position-2);
            }
            else if (position >=3 && (equationBuilder.substring(position-3,position+1).equals("DFFF")
                    || equationBuilder.substring(position-3,position+1).equals("EFFF")
                    || equationBuilder.substring(position-3,position+1).equals("GFFF")))
            {
                equationBuilder.delete(position-3,position+3);
                equationSpan=Equation.BackspaceFunFormationAfter(equationBuilder.toString());
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position-3);
            }
            else if (position >=4 && (equationBuilder.substring(position-4,position+1).equals("DFFFF")
                    || equationBuilder.substring(position-4,position+1).equals("EFFFF")
                    || equationBuilder.substring(position-4,position+1).equals("GFFFF")))
            {
                equationBuilder.delete(position-4,position+2);
                equationSpan=Equation.BackspaceFunFormationAfter(equationBuilder.toString());
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position-4);
            }
            else if (position >=5 && (equationBuilder.substring(position-5,position+1).equals("DFFFFF")
                    || equationBuilder.substring(position-5,position+1).equals("EFFFFF")
                    || equationBuilder.substring(position-5,position+1).equals("GFFFFF")))
            {
                equationBuilder.delete(position-5,position+1);
                equationSpan=Equation.BackspaceFunFormationAfter(equationBuilder.toString());
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position-5);
            }
            else{
                equationBuilder.delete(position,position+1);
                equationSpan=Equation.BackspaceFunFormationAfter(equationBuilder.toString());
                equationEdtView.setText(equationSpan);
                equationEdtView.setSelection(position);
            }

        }

        if (equationHasOperatorOrFunction)
            Equal(false,null);

    }

    public static void BtnClearAll(View view){
        equationEdtView.setText("");
        resultTxtView.setText("");
        equationSpan=new SpannableStringBuilder("");
        equationHasOperatorOrFunction=false;
    }

    public static void BtnEqualClick(View view, Context context)
    {
        Equal(true, context);
    }

    private static void Equal(boolean toast, Context context)
    {
        String string=equationSpan.toString().replace("%","÷(100)");
        string=string.replace("sin-1","asin");
        string=string.replace("cos-1","acos");
        string=string.replace("tan-1","atan");
        string=Equation.equationFormation(string).replace("π", String.valueOf(Math.PI));
        string=string.replace("e", String.valueOf(Math.E));
        string=string.replace("√","sqrt");
        try {
            double result= BigDecimal.valueOf(Equation.eval(string))
                    .setScale(10,BigDecimal.ROUND_HALF_UP).doubleValue();

//            BigDecimal result=Equation.eval2(string);
//            resultTxtView.setText(result.setScale(10,BigDecimal.ROUND_HALF_UP).toPlainString());
//            Apfloat apfloat=Equation.eval2(string);
//            resultTxtView.setText(apfloat.toString(true));
            if (result%1==0)
                resultTxtView.setText((String.valueOf((int)result)));
            else {
                resultTxtView.setText(String.valueOf(result));
            }
        }
        catch (Exception ex){
            if (toast)
                Toast.makeText(context, "Syntax error",Toast.LENGTH_SHORT).show();
            else resultTxtView.setText("");
        }

    }

    private static boolean CheckInsertion(int position){
        String s=Equation.BackspaceFunFormationBefore(equationSpan);
        if (s.length()==position)
            return true;
        else if (s.charAt(position)=='F')
            return false;
        else return true;
    }

    private void OnLandscape(){

        Button btnAsin=(Button) view.findViewById(R.id.button_sin_1);
        Button btnAcos=(Button) view.findViewById(R.id.button_cos_1);
        Button btnAtan=(Button) view.findViewById(R.id.button_tan_1);
        Button btn10Pow=(Button) view.findViewById(R.id.button_10pow);
        Button btnPow=(Button) view.findViewById(R.id.button_pow);
        Button btn0=(Button) view.findViewById(R.id.button_0);
        Button btn1=(Button) view.findViewById(R.id.button_1);
        Button btn2=(Button) view.findViewById(R.id.button_2);
        Button btn3=(Button) view.findViewById(R.id.button_3);
        Button btn4=(Button) view.findViewById(R.id.button_4);
        Button btn5=(Button) view.findViewById(R.id.button_5);
        Button btn6=(Button) view.findViewById(R.id.button_6);
        Button btn7=(Button) view.findViewById(R.id.button_7);
        Button btn8=(Button) view.findViewById(R.id.button_8);
        Button btn9=(Button) view.findViewById(R.id.button_9);
        Button btnPoint=(Button) view.findViewById(R.id.button_point);
        Button btnEqual=(Button) view.findViewById(R.id.button_equal);
        Button btnPlus=(Button) view.findViewById(R.id.button_plus);
        Button btnMinus=(Button) view.findViewById(R.id.button_minus);
        Button btnMult=(Button) view.findViewById(R.id.button_mult);
        Button btnDiv=(Button) view.findViewById(R.id.button_divide);
        Button btnE=(Button) view.findViewById(R.id.button_e);
        Button btnPi=(Button) view.findViewById(R.id.button_pi);
        Button btnTan=(Button) view.findViewById(R.id.button_tan);
        Button btnSin=(Button) view.findViewById(R.id.button_sin);
        Button btnCos=(Button) view.findViewById(R.id.button_cos);
        Button btnLog=(Button) view.findViewById(R.id.button_log);
        Button btnLn=(Button) view.findViewById(R.id.button_ln);
        Button btnLeftParentheses=(Button) view.findViewById(R.id.button_leftparentheses);
        Button btnRightParentheses=(Button) view.findViewById(R.id.button_rightparentheses);
        Button btnSqrt=(Button) view.findViewById(R.id.button_square_root);
        Button btnClearAll=(Button) view.findViewById(R.id.button_clear_all);
        Button btnBackspace=(Button) view.findViewById(R.id.button_backspace);
        Button btnPercent=(Button) view.findViewById(R.id.button_percent);
        Button btnAbs=(Button) view.findViewById(R.id.button_abs);

        btnAcos.setText(Html.fromHtml("sin<sup>-1</sup>"));
        btnAsin.setText(Html.fromHtml("cos<sup>-1</sup>"));
        btnAtan.setText(Html.fromHtml("tan<sup>-1</sup>"));
        btn10Pow.setText(Html.fromHtml("10<sup>x</sup>"));
        btnPow.setText(Html.fromHtml("x<sup>y</sup>"));

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
                Calculator.BtnEqualClick(v, getContext());
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

        btnTan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnFunctClick(v);
            }
        });


        btnSin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnFunctClick(v);
            }
        });

        btnCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnFunctClick(v);
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

        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnNumClick(v);
            }
        });

        btnPow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator.BtnPowClick(v);
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
