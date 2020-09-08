package edu.espe.controlGastos.views.balance;

import android.content.Context;
import android.content.Intent;

import org.jetbrains.annotations.NotNull;
import org.espe.controlGastos.R;
import edu.espe.controlGastos.utils.Constants;
import edu.espe.controlGastos.views.transaction.NewTransactionActivity;

import uk.co.markormesher.android_fab.SpeedDialMenuAdapter;
import uk.co.markormesher.android_fab.SpeedDialMenuItem;

public class AddTransactionSpeedDialAdapter extends SpeedDialMenuAdapter {
    private Context context;

    public AddTransactionSpeedDialAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @NotNull
    @Override
    public SpeedDialMenuItem getMenuItem(Context context, int position) {
        switch (position) {
            case 0:
                return new SpeedDialMenuItem(context, R.drawable.ic_income_24dp, "Ingreso");
            case 1:
            default:
                return new SpeedDialMenuItem(context, R.drawable.ic_expense_24dp, "Gasto");
        }
    }

    @Override
    public boolean onMenuItemClick(int position) {
        Intent intent;
        switch (position){
            case 0:
                intent = new Intent(context, NewTransactionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Constants.PARAM_TRANSACTION_IS_INCOME, true);
                context.startActivity(intent);
                return true;
            case 1:
                intent = new Intent(context, NewTransactionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Constants.PARAM_TRANSACTION_IS_INCOME, false);
                context.startActivity(intent);
                return true;
        }
        return false;
    }
}
