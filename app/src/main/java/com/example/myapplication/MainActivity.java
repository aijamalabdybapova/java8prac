package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView Formula, EndResult;
    private Button One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Zero;
    private Button Plus, Minus, Division, Multiple, Result, Koren, Kvadrat, Prosent, Clear;
    private double valueFirst = Double.NaN;
    private double valueSecond;
    private char Action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupView();
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                if (Formula.getText().toString().equals("Error")) {
                    Formula.setText("");
                }
                Formula.setText(Formula.getText().toString() + button.getText().toString());
            }
        };
        One.setOnClickListener(numberClickListener);
        Two.setOnClickListener(numberClickListener);
        Three.setOnClickListener(numberClickListener);
        Four.setOnClickListener(numberClickListener);
        Five.setOnClickListener(numberClickListener);
        Six.setOnClickListener(numberClickListener);
        Seven.setOnClickListener(numberClickListener);
        Eight.setOnClickListener(numberClickListener);
        Nine.setOnClickListener(numberClickListener);
        Zero.setOnClickListener(numberClickListener);

        View.OnClickListener actionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                if (Formula.getText().toString().equals("Error")) {
                    Formula.setText("");
                }
                calculate();
                Action = button.getText().charAt(0);
                Formula.setText(String.valueOf(valueFirst) + Action);
                EndResult.setText(null);
            }
        };
        Plus.setOnClickListener(actionClickListener);
        Minus.setOnClickListener(actionClickListener);
        Division.setOnClickListener(actionClickListener);
        Multiple.setOnClickListener(actionClickListener);
        Koren.setOnClickListener(actionClickListener);
        Kvadrat.setOnClickListener(actionClickListener);
        Prosent.setOnClickListener(actionClickListener);

        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                Action = '=';
                EndResult.setText(String.valueOf(valueFirst));
                Formula.setText(null);
            }
        });

        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Formula.setText("");
                EndResult.setText("");
                valueFirst = Double.NaN;
                valueSecond = 0;
                Action = ' ';
            }
        });
    }

    private void setupView() {
        One = (Button) findViewById(R.id.One);
        Two = (Button) findViewById(R.id.Two);
        Three = (Button) findViewById(R.id.Three);
        Four = (Button) findViewById(R.id.Four);
        Five = (Button) findViewById(R.id.Five);
        Six = (Button) findViewById(R.id.Six);
        Seven = (Button) findViewById(R.id.Seven);
        Eight = (Button) findViewById(R.id.Eight);
        Nine = (Button) findViewById(R.id.Nine);
        Zero = (Button) findViewById(R.id.Zero);
        Plus = (Button) findViewById(R.id.Plus);
        Minus = (Button) findViewById(R.id.Minus);
        Division = (Button) findViewById(R.id.Division);
        Multiple = (Button) findViewById(R.id.Multiple);
        Koren = (Button) findViewById(R.id.Koren);
        Kvadrat = (Button) findViewById(R.id.Kvadrat);
        Prosent = (Button) findViewById(R.id.Prosent);
        Result = (Button) findViewById(R.id.Result);
        Clear = (Button) findViewById(R.id.Clear);
        Formula = (TextView) findViewById(R.id.Formula);
        EndResult = (TextView) findViewById(R.id.EndResult);
    }

    private void calculate() {
        if (!Double.isNaN(valueFirst)) {
            String textFormula = Formula.getText().toString();
            int indexAction = textFormula.indexOf(Action);
            if (indexAction!= -1) {
                String number = textFormula.substring(indexAction + 1);
                try {
                    valueSecond = Double.parseDouble(number);
                } catch (NumberFormatException e) {
                    Formula.setText("Error");
                    return;
                }

                switch (Action) {
                    case '+':
                        valueFirst += valueSecond;
                        break;
                    case '-':
                        valueFirst -= valueSecond;
                        break;
                    case '*':
                        valueFirst *= valueSecond;
                        break;
                    case '/':
                        if (valueSecond == 0) {
                            Formula.setText("Error");
                            return;
                        } else {
                            valueFirst /= valueSecond;
                            break;
                        }
                    case '√':
                        valueFirst = Math.sqrt(valueFirst);
                        break;
                    case '^':
                        valueFirst = Math.pow(valueFirst, valueSecond);
                        break;
                    case '%':
                        valueFirst = valueFirst * valueSecond / 100;
                        break;
                    case '=':
                        valueFirst = valueSecond;
                        break;
                }
            }

        } else {
            try {
                valueFirst = Double.parseDouble(Formula.getText().toString());
            } catch (NumberFormatException e) {
                Formula.setText("Error");
                return;
            }
        }
        EndResult.setText(String.valueOf(valueFirst));
        Formula.setText("");
    }
}