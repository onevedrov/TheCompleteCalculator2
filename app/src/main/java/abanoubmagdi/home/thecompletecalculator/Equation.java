package abanoubmagdi.home.thecompletecalculator;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;

import java.io.Serializable;

class Equation implements Serializable {

    private static final String SIN_ALIAS="AFFF";
    private static final String COS_ALIAS="BFFF";
    private static final String TAN_ALIAS="CFFF";
    private static final String ASIN_ALIAS="DFFFFF";
    private static final String ACOS_ALIAS="EFFFFF";
    private static final String ATAN_ALIAS="GFFFFF";
    private static final String ABS_ALIAS="LFFF";
    private static final String LOG_ALIAS="HFFF";
    private static final String LN_ALIAS="JFF";

    private transient SpannableStringBuilder equation;
    private String result;
    private int position;
    private boolean equationHasOperatorOrFunction;

    Equation(SpannableStringBuilder equation, int position, boolean equationHasOperatorOrFunction, String result) {
        this.equation = equation;
        this.position = position;
        this.equationHasOperatorOrFunction = equationHasOperatorOrFunction;
        this.result = result;
    }

    SpannableStringBuilder getEquation() {
        return equation;
    }

    int getPosition() {
        return position;
    }

    boolean isEquationHasOperatorOrFunction() {
        return equationHasOperatorOrFunction;
    }

    String getResult() {
        return result;
    }

    static String BackspaceFunFormationBefore(SpannableStringBuilder ssb)
    {
        String equation=(ssb).toString();

        equation=equation.replace("sin(",SIN_ALIAS);
        equation=equation.replace("cos(",COS_ALIAS);
        equation=equation.replace("tan(",TAN_ALIAS);
        equation=equation.replace("sin-1(",ASIN_ALIAS);
        equation=equation.replace("cos-1(",ACOS_ALIAS);
        equation=equation.replace("tan-1(",ATAN_ALIAS);
        equation=equation.replace("abs(",ABS_ALIAS);
        equation=equation.replace("log(",LOG_ALIAS);
        equation=equation.replace("ln(",LN_ALIAS);
        return equation;
    }

    static SpannableStringBuilder BackspaceFunFormationAfter(String equation)
    {
        SpannableStringBuilder ssb=new SpannableStringBuilder(equation);
        if (equation.contains(SIN_ALIAS))
        {
            equation=equation.replace(SIN_ALIAS,"sin(");
            ssb=new SpannableStringBuilder(equation);
        }
        if (equation.contains(COS_ALIAS))
        {
            equation=equation.replace(COS_ALIAS,"cos(");
            ssb=new SpannableStringBuilder(equation);
        }
        if (equation.contains(TAN_ALIAS))
        {
            equation=equation.replace(TAN_ALIAS,"tan(");
            ssb=new SpannableStringBuilder(equation);
        }
        if (equation.contains(ABS_ALIAS))
        {
            equation=equation.replace(ABS_ALIAS,"abs(");
            ssb=new SpannableStringBuilder(equation);
        }
        if (equation.contains(LOG_ALIAS))
        {
            equation=equation.replace(LOG_ALIAS,"log(");
            ssb=new SpannableStringBuilder(equation);
        }
        if (equation.contains(LN_ALIAS))
        {
            equation=equation.replace(LN_ALIAS,"ln(");
            ssb=new SpannableStringBuilder(equation);
        }
        if (equation.contains(ASIN_ALIAS))
        {
            equation=equation.replace(ASIN_ALIAS,"sin-1(");
            ssb=ReplaceSpan(ssb,ASIN_ALIAS);
        }
        if (equation.contains(ACOS_ALIAS))
        {
//            equation=equation.replace(ACOS_ALIAS,"cos-1(");
            ssb=ReplaceSpan(ssb,ACOS_ALIAS);
        }
        if (equation.contains(ATAN_ALIAS))
        {
//            equation=equation.replace(ATAN_ALIAS,"tan-1(");
            ssb=ReplaceSpan(ssb,ATAN_ALIAS);
        }
        return ssb;
    }
    private static SpannableStringBuilder ReplaceSpan(SpannableStringBuilder ssb, String replacement)
    {
        String string="";
        if (replacement.equals(ACOS_ALIAS))string="cos-1(";
        else if (replacement.equals(ASIN_ALIAS))string="sin-1(";
        else if (replacement.equals(ATAN_ALIAS))string="tan-1(";
        SpannableStringBuilder ssbIncert=new SpannableStringBuilder(string);
        ssbIncert.setSpan(new SuperscriptSpan(),
                3, + 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssbIncert.setSpan(new RelativeSizeSpan(.75f),
                3, + 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        String equation=ssb.toString();
        int len=ssb.length();
        try{
            for (int i=0;i<len;i++){
                int index= equation.indexOf(replacement,i);
                if (index!=-1) {
                    ssb.delete(index,index+6);
                    ssb.insert(index,ssbIncert);
                } else if(index==-1) break;
                i = index + 5;
            }
        }
        catch (Exception ex){

        }
        return ssb;
    }
    static String equationFormation(String equation){
        int length=equation.length();
        if (length>0){
            for (int i=1; i<length;i++){
                if (equation.charAt(i)=='c' || equation.charAt(i)=='t'
                        || equation.charAt(i)=='π' || equation.charAt(i)=='e'
                        || equation.charAt(i)=='(' || equation.charAt(i)=='√'
                        || equation.charAt(i)=='s' || equation.charAt(i)=='l'
                        || equation.charAt(i)=='a')
                {
                    if ((equation.charAt(i-1)>='0' && equation.charAt(i-1) <='9')
                            || equation.charAt(i-1)==')' || equation.charAt(i-1)=='e'
                            || equation.charAt(i-1)=='π')
                    {
                        equation=new StringBuilder(equation).insert(i,"X").toString();
                        length=equation.length();
                    }
                }
                else if (equation.charAt(i)>='0' && equation.charAt(i) <='9'){
                    if (equation.charAt(i-1)==')' || equation.charAt(i-1)=='e'
                            || equation.charAt(i-1)=='π'){
                        equation=new StringBuilder(equation).insert(i,"X").toString();
                        length=equation.length();
                    }
                }
            }
        }

        return equation;
    }

    static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('X')) x *= parseFactor();
                    else if (eat('÷')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else if (func.equals("asin")) x = Math.toDegrees(Math.asin(x));
                    else if (func.equals("acos")) x = Math.toDegrees(Math.acos(x));
                    else if (func.equals("atan")) x = Math.toDegrees(Math.atan(x));
                    else if (func.equals("abs")) x = Math.abs(x);
                    else if (func.equals("log")) x = Math.log(x)/Math.log(10);
                    else if (func.equals("ln")) x = Math.log(x);
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);

                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

}
