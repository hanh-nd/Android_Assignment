package me.hanhngo.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import me.hanhngo.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    int result = 0;
    int firstNumber = 0;
    String secondNumber = "";
    String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupButtonFunction();
    }

    private void setupButtonFunction() {
        setupButtonCE();
        setupButtonC();
        setupButtonBS();
        setupButtonPlus();
        setupButtonMinus();
        setupButtonMultiply();
        setupButtonDivide();
        setupButtonEqual();
        setupButtonDot();
        setupButtonPositiveNegative();
        setupButton0();
        setupButton1();
        setupButton2();
        setupButton3();
        setupButton4();
        setupButton5();
        setupButton6();
        setupButton7();
        setupButton8();
        setupButton9();
    }

    private void setupButtonCE() {
        binding.btnCe.setOnClickListener(view -> {
            result = 0;
            firstNumber = 0;
            secondNumber = "";
            operator = "";
            updateHistoryDisplay();
        });
    }

    private void setupButtonC() {
        binding.btnC.setOnClickListener(view -> {
            result = 0;
            firstNumber = 0;
            secondNumber = "";
            operator = "";
            updateHistoryDisplay();
        });
    }

    private void setupButtonBS() {
        binding.btnBs.setOnClickListener(view -> System.out.println("Unknown function: BS."));
    }

    private void setupButtonPlus() {
        binding.btnPlus.setOnClickListener(view -> setupMathOperator("+"));
    }

    private void setupButtonMinus() {
        binding.btnMinus.setOnClickListener(view -> setupMathOperator("-"));
    }

    private void setupButtonMultiply() {
        binding.btnMultiply.setOnClickListener(view -> setupMathOperator("X"));
    }

    private void setupButtonDivide() {
        binding.btnDivide.setOnClickListener(view -> setupMathOperator("/"));
    }

    private void setupButtonEqual() {
        binding.btnEqual.setOnClickListener(view -> {
            if (!operator.equals("")) {
                switch (operator) {
                    case "+":
                        result = firstNumber + parseString(secondNumber);
                        break;
                    case "-":
                        result = firstNumber - parseString(secondNumber);
                        break;
                    case "X":
                        result = firstNumber * parseString(secondNumber);
                        break;
                    default:
                        result = firstNumber / parseString(secondNumber);
                        break;
                }
            } else {
                result = parseString(secondNumber);
            }
            updateResultDisplay();
        });
    }

    private void setupButtonDot() {
        binding.btnDot.setOnClickListener(view -> System.out.println("Unknown function: Dot."));
    }

    private void setupButtonPositiveNegative() {
        binding.btnPositiveNegative.setOnClickListener(view -> System.out.println("Unknown function: Positive-Negative."));
    }


    private void setupButton0() {
        binding.btn0.setOnClickListener(view -> {
            if (!secondNumber.equals("")) {
                secondNumber += "0";
            }
            updateHistoryDisplay();
        });
    }

    private void setupButton1() {
        binding.btn1.setOnClickListener(view -> {
            secondNumber += "1";
            updateHistoryDisplay();
        });

    }

    private void setupButton2() {
        binding.btn2.setOnClickListener(view -> {
            secondNumber += "2";
            updateHistoryDisplay();
        });
    }

    private void setupButton3() {
        binding.btn3.setOnClickListener(view -> {
            secondNumber += "3";
            updateHistoryDisplay();
        });
    }

    private void setupButton4() {
        binding.btn4.setOnClickListener(view -> {
            secondNumber += "4";
            updateHistoryDisplay();
        });
    }

    private void setupButton5() {
        binding.btn5.setOnClickListener(view -> {
            secondNumber += "5";
            updateHistoryDisplay();
        });
    }

    private void setupButton6() {
        binding.btn6.setOnClickListener(view -> {
            secondNumber += "6";
            updateHistoryDisplay();
        });
    }

    private void setupButton7() {
        binding.btn7.setOnClickListener(view -> {
            secondNumber += "7";
            updateHistoryDisplay();
        });
    }

    private void setupButton8() {
        binding.btn8.setOnClickListener(view -> {
            secondNumber += "8";
            updateHistoryDisplay();
        });
    }

    private void setupButton9() {
        binding.btn9.setOnClickListener(view -> {
            secondNumber += "9";
            updateHistoryDisplay();
        });
    }

    private void setupMathOperator(String type) {
        if (operator.equals("")) {
            firstNumber = parseString(secondNumber);
            secondNumber = "";
            operator = type;
            updateHistoryDisplay();
            return;
        }

        if (!secondNumber.equals("")) {
            switch (operator) {
                case "+":
                    firstNumber += parseString(secondNumber);
                    break;
                case "-":
                    firstNumber -= parseString(secondNumber);
                    break;
                case "X":
                    firstNumber *= parseString(secondNumber);
                    break;
                default:
                    firstNumber /= parseString(secondNumber);
                    break;
            }
            secondNumber = "";
            operator = type;
            updateHistoryDisplay();
            return;
        }

        operator = type;
        updateHistoryDisplay();
    }

    private int parseString(String string) {
        try {
            return Integer.parseInt(string);
        } catch (Exception e) {
            return 0;
        }
    }

    private void updateHistoryDisplay() {
        String result;
        if (firstNumber != 0) {
            result = firstNumber + " " + operator + " " + secondNumber;
        } else {
            result = secondNumber;
        }
        binding.historyTv.setText(result);

    }

    private void updateResultDisplay() {
        binding.resultTv.setText(String.valueOf(result));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
