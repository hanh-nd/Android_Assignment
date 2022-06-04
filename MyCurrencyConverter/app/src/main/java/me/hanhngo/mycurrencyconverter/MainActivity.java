package me.hanhngo.mycurrencyconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import me.hanhngo.mycurrencyconverter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding = null;
    private String fromAmount = "";
    private int afterDecimalPointIndex = 0;
    private final List<Currency> currencies = new ArrayList<>();
    private CurrencyName fromCurrency;
    private CurrencyName toCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        setupSpinner();
        setupFunctionalButton();
    }

    private void setupSpinner() {
        ArrayAdapter<Currency> adapter = new ArrayAdapter<>(this,
                R.layout.color_spinner_layout,
                getCurrencies());
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        binding.fromSpn.setAdapter(adapter);
        binding.fromSpn.setSelection(0);
        fromCurrency = CurrencyName.values()[0];
        binding.toSpn.setAdapter(adapter);
        binding.toSpn.setSelection(1);
        toCurrency = CurrencyName.values()[1];

        binding.fromSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fromCurrency = ((Currency) adapterView.getAdapter().getItem(i)).getCurrencyName();
                String rateText = "1 " +
                        fromCurrency.toString() +
                        " = " +
                        getCurrency(fromCurrency).getRates().get(toCurrency) +
                        " " +
                        toCurrency.toString();
                binding.rateTv.setText(rateText);
                updateFromTextView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        binding.toSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                toCurrency = ((Currency) adapterView.getAdapter().getItem(i)).getCurrencyName();
                String rateText = "1 " +
                        fromCurrency.toString() +
                        " = " +
                        getCurrency(fromCurrency).getRates().get(toCurrency) +
                        " " +
                        toCurrency.toString();
                binding.rateTv.setText(rateText);
                updateFromTextView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        String rateText = "1 " +
                fromCurrency.toString() +
                " = " +
                getCurrency(fromCurrency).getRates().get(toCurrency) +
                " " +
                toCurrency.toString();
        binding.rateTv.setText(rateText);
    }

    private Currency getCurrency(CurrencyName name) {
        return getCurrencies().stream().filter(currency ->
                currency.getCurrencyName() == name
        ).findFirst().orElse(null);
    }

    private List<Currency> getCurrencies() {
        if (currencies.size() > 0) {
            return currencies;
        }

        for (CurrencyName name : CurrencyName.values()) {
            currencies.add(new Currency(name));
        }
        return currencies;
    }

    private void setupFunctionalButton() {
        binding.btn0.setOnClickListener(view -> {
            if (fromAmount.equals("")) {
                return;
            }
            buttonOperator(0);
        });

        binding.btn1.setOnClickListener(view -> buttonOperator(1));

        binding.btn2.setOnClickListener(view -> buttonOperator(2));

        binding.btn3.setOnClickListener(view -> buttonOperator(3));

        binding.btn4.setOnClickListener(view -> buttonOperator(4));

        binding.btn5.setOnClickListener(view -> buttonOperator(5));

        binding.btn6.setOnClickListener(view -> buttonOperator(6));

        binding.btn7.setOnClickListener(view -> buttonOperator(7));

        binding.btn8.setOnClickListener(view -> buttonOperator(8));

        binding.btn9.setOnClickListener(view -> buttonOperator(9));

        binding.btnDot.setOnClickListener(view -> {
            if (!fromAmount.contains(".")) {
                fromAmount += ".00";
                afterDecimalPointIndex = 1;
                updateFromTextView();
            }
        });

        binding.CEBtn.setOnClickListener(view -> {
            fromAmount = "";
            afterDecimalPointIndex = 0;
            updateFromTextView();
        });

        binding.deleteBtn.setOnClickListener(view -> {
            if (fromAmount.equals("")) {
                return;
            }
            if (afterDecimalPointIndex == 0) {
                fromAmount = fromAmount.substring(0, fromAmount.length() - 1);
            } else if (afterDecimalPointIndex == 1) {
                fromAmount = fromAmount.substring(0, fromAmount.length() - 3);
                afterDecimalPointIndex--;
            } else {
                int index = fromAmount.indexOf(".") + afterDecimalPointIndex - 1;
                fromAmount = replaceCharAt(index, fromAmount, '0');
                afterDecimalPointIndex--;
            }
            updateFromTextView();
        });

    }

    private void updateFromTextView() {
        binding.fromTv.setText(fromAmount);
        try {
            double rate = 0d;
            HashMap<CurrencyName, Double> map = getCurrency(fromCurrency).getRates();
            if (map.containsKey(toCurrency)) {
                rate = map.get(toCurrency);
            }
            String toAmount = String.format(Locale.ROOT, "%.2f", Double.parseDouble(fromAmount) * rate);
            binding.toTv.setText(toAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buttonOperator(int number) {
        if (afterDecimalPointIndex == 0) {
            fromAmount += String.valueOf(number);
        } else if (afterDecimalPointIndex <= 2) {
            int index = fromAmount.indexOf(".") + afterDecimalPointIndex;
            fromAmount = replaceCharAt(index, fromAmount, Character.forDigit(number, 10));
            afterDecimalPointIndex++;
        }
        updateFromTextView();
    }

    private String replaceCharAt(int index, String input, char replaceChar) {
        StringBuilder newString = new StringBuilder(input);
        newString.setCharAt(index, replaceChar);
        return newString.toString();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}