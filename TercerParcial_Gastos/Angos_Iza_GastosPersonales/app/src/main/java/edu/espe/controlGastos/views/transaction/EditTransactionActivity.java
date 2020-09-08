package edu.espe.controlGastos.views.transaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.mynameismidori.currencypicker.CurrencyPicker;
import com.mynameismidori.currencypicker.CurrencyPickerListener;

import org.espe.controlGastos.R;
import edu.espe.controlGastos.controller.Controller;
import edu.espe.controlGastos.model.DateRange;
import edu.espe.controlGastos.model.MyCategory;
import edu.espe.controlGastos.model.MyTransaction;
import edu.espe.controlGastos.views.categories.ListCategoryDialog;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

import static edu.espe.controlGastos.utils.Constants.PARAM_CATEGORY_ID;
import static edu.espe.controlGastos.utils.Constants.PARAM_CATEGORY_IS_INCOME;
import static edu.espe.controlGastos.utils.Constants.PARAM_TRANSACTION_ID;
import static edu.espe.controlGastos.utils.Constants.PARAM_TRANSACTION_IS_INCOME;

public class EditTransactionActivity extends AppCompatActivity {
    private static final int REQUEST_CHANGE_CATEGORY = 100;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.value)
    TextInputEditText value;
    @BindView(R.id.currency)
    AppCompatTextView currency;
    @BindView(R.id.date)
    AppCompatTextView date;
    @BindView(R.id.category)
    AppCompatTextView category;
    @BindView(R.id.description)
    TextInputEditText description;
    @BindView(R.id.periodicity)
    AppCompatCheckBox periodicity;
    @BindView(R.id.quantityDateRange)
    EditText quantityDateRange;
    @BindView(R.id.dateRange)
    Spinner dateRange;
    @BindView(R.id.duration)
    AppCompatCheckBox duration;
    @BindView(R.id.endDate)
    AppCompatTextView endDate;
    @BindView(R.id.endDate_line)
    View endDateLine;
    @BindView(R.id.endDate_wrapper)
    TextInputLayout endDateWrapper;
    @BindView(R.id.reminderable)
    AppCompatCheckBox reminderable;
    @BindView(R.id.periodicity_form)
    LinearLayout periodicityForm;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    Controller controller;
    MyTransaction transaction;

    public EditTransactionActivity() {
        controller = Controller.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trasaction);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        boolean isIncome = false;
        if(getIntent() != null && getIntent().getExtras() != null){
            isIncome = getIntent().getExtras().getBoolean(PARAM_TRANSACTION_IS_INCOME, false);
            long id = getIntent().getExtras().getLong(PARAM_TRANSACTION_ID);
            transaction = controller.getTransactionById(id);

            value.setText(transaction.getValue() + "");
            description.setText(transaction.getDescription());
            periodicity.setChecked(transaction.isPeriodic());
            if(transaction.isPeriodic()){
                quantityDateRange.setText(transaction.getQuantity() + "");
                switch (transaction.getRange()){
                    case Daily:
                        dateRange.setSelection(0);
                        break;
                    case Weekly:
                        dateRange.setSelection(1);
                        break;
                    case Monthly:
                        dateRange.setSelection(2);
                        break;
                    case Yearly:
                        dateRange.setSelection(3);
                        break;
                }
            }
            duration.setChecked(transaction.isForever());
            if(!transaction.isForever()){
                Calendar c = Calendar.getInstance();
                c.setTime(transaction.getEndDate());
                endDate.setText(format(c));
            }
            reminderable.setChecked(transaction.isRemindMe());
        }

        if (isIncome) {
            if(transaction == null) {
                transaction = MyTransaction.newIncome();
            }
            category.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add_black_24dp, 0, 0, 0);
            for (Drawable drawable : category.getCompoundDrawables()) {
                if (drawable != null) {
                    drawable.setColorFilter(new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN));
                }
            }
        } else {
            if(transaction == null) {
                transaction = MyTransaction.newExpense();
            }
            category.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_remove_24dp, 0, 0, 0);
            for (Drawable drawable : category.getCompoundDrawables()) {
                if (drawable != null) {
                    drawable.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_IN));
                }
            }
        }

        //Set Default timestamp
        Calendar calendar = Calendar.getInstance();
        if(transaction.getTimeStamp() != null){
            calendar.setTime(transaction.getTimeStamp());
        }else{
            transaction.setTimeStamp(calendar.getTime());
        }
        date.setText(format(calendar));

        //Set default MyCategory
        MyCategory defaultMyCategory;
        if(transaction.getMyCategory() != null){
            defaultMyCategory = transaction.getMyCategory();
        }else{
            if(isIncome){
                defaultMyCategory = controller.getDefaultIncomeCategory();
            }else{
                defaultMyCategory = controller.getDefaultExpenseCategory();
            }

            transaction.setMyCategory(defaultMyCategory);
        }
        category.setText(defaultMyCategory.getTitle());
        category.setCompoundDrawablesWithIntrinsicBounds(defaultMyCategory.getIcon(), 0, R.drawable.ic_arrow_drop_down_24dp, 0);
        for (Drawable drawable : category.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(Color.parseColor(defaultMyCategory.getColor()), PorterDuff.Mode.SRC_IN));
            }
        }

        // Set default Currency
        Currency defaultCurrency;
        if(transaction.getCurrency() != null){
            defaultCurrency = transaction.getCurrency();
        }else{
            defaultCurrency = Currency.getInstance(Locale.getDefault());
            transaction.setCurrency(defaultCurrency);
        }
        currency.setText(defaultCurrency.getCurrencyCode());
    }

    @OnClick(R.id.currency)
    public void onCurrencyClicked() {
        final CurrencyPicker picker = CurrencyPicker.newInstance("Select Currency");  // dialog title
        picker.setListener(new CurrencyPickerListener() {
            @Override
            public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
                currency.setText(code);
                transaction.setCurrency(Currency.getInstance(code));
                picker.dismiss();
            }
        });
        picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");
    }

    @OnClick(R.id.category)
    public void onCategoryClicked() {
        Intent intent = new Intent(getApplicationContext(), ListCategoryDialog.class);
        intent.putExtra(PARAM_CATEGORY_IS_INCOME, true);
        startActivityForResult(intent, REQUEST_CHANGE_CATEGORY);
    }

    @OnClick(R.id.date)
    public void onDateClicked() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.clear();
                calendar.set(year, monthOfYear, dayOfMonth);
                date.setText(format(calendar));
                transaction.setTimeStamp(calendar.getTime());
            }
        };

        DatePickerDialog mdiDialog = new DatePickerDialog(this, listener, year, month, day);
        mdiDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + 1000);
        mdiDialog.show();

    }

    @OnClick(R.id.endDate)
    public void onEndDateClicked() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.clear();
                calendar.set(year, monthOfYear, dayOfMonth);
                endDate.setText(format(calendar));
                transaction.setEndDate(calendar.getTime());
            }
        };

        DatePickerDialog mdiDialog = new DatePickerDialog(this, listener, year, month, day);
        mdiDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        mdiDialog.show();

    }

    @OnClick({R.id.save, R.id.fab})
    public void onSaveClicked() {
        if (transaction.isPeriodic()) {
            switch (dateRange.getSelectedItemPosition()) {
                case 2:
                    transaction.setRange(DateRange.Monthly);
                    break;
                case 1:
                    transaction.setRange(DateRange.Weekly);
                    break;
                case 0:
                default:
                    transaction.setRange(DateRange.Daily);
                    break;
            }
            transaction.setQuantity(Integer.parseInt(quantityDateRange.getText().toString()));
            transaction.setRemindMe(reminderable.isChecked());
        }
        transaction.setValue(Float.parseFloat(value.getText().toString()));
        transaction.setDescription(description.getText().toString());
        if(controller.editTransaction(transaction)){
            finish();
        }

    }

    @OnCheckedChanged(R.id.periodicity)
    public void isPeriodic(CompoundButton button, boolean checked) {
        transaction.setPeriodic(checked);
        if (checked) {
            periodicityForm.setVisibility(View.VISIBLE);
        } else {
            periodicityForm.setVisibility(View.GONE);
        }
    }

    @OnCheckedChanged(R.id.duration)
    public void isForever(CompoundButton button, boolean checked) {
        transaction.setForever(checked);
        if (checked) {
            endDateWrapper.setVisibility(View.GONE);
        } else {
            endDateWrapper.setVisibility(View.VISIBLE);
        }
    }

    private String format(Calendar calendar) {
        Date date = calendar.getTime();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        return dateFormat.format(date);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHANGE_CATEGORY && resultCode == RESULT_OK && data != null) {
            long id = data.getExtras().getLong(PARAM_CATEGORY_ID);
            MyCategory nCategory = controller.getCategoryById(id);
            category.setText(nCategory.getTitle());
            category.setCompoundDrawablesWithIntrinsicBounds(nCategory.getIcon(), 0, R.drawable.ic_arrow_drop_down_24dp, 0);
            for (Drawable drawable : category.getCompoundDrawables()) {
                if (drawable != null) {
                    drawable.setColorFilter(new PorterDuffColorFilter(Color.parseColor(nCategory.getColor()), PorterDuff.Mode.SRC_IN));
                }
            }
            transaction.setMyCategory(nCategory);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
