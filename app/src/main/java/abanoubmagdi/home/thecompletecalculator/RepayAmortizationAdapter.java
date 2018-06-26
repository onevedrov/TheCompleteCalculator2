package abanoubmagdi.home.thecompletecalculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


class RepayAmortizationAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<RepayAmortization> amortizationArrayList;

    RepayAmortizationAdapter(Context context, ArrayList<RepayAmortization> amortizationArrayList)
    {
        super(context,R.layout.repay_amortization_item, amortizationArrayList);
        this.context=context;
        this.amortizationArrayList=amortizationArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.repay_amortization_item,null);
        TextView textViewNO=(TextView) view.findViewById(R.id.textViewRepayAmortizationNO);
        TextView textViewPayments=(TextView) view.findViewById(R.id.textViewRepayAmortizationPayments);
        TextView textViewPrincipal=(TextView) view.findViewById(R.id.textViewRepayAmortizationPrincipal);
        TextView textViewInterest=(TextView) view.findViewById(R.id.textViewRepayAmortizationInterest);
        TextView textViewBalance=(TextView) view.findViewById(R.id.textViewRepayAmortizationBalance);

        textViewNO.setText(amortizationArrayList.get(position).no);
        textViewBalance.setText(amortizationArrayList.get(position).balance);
        textViewInterest.setText(amortizationArrayList.get(position).interest);
        textViewPayments.setText(amortizationArrayList.get(position).payments);
        textViewPrincipal.setText(amortizationArrayList.get(position).principal);
        return view;
    }
}
