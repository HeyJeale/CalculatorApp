package edu.zjut.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    float memRegister;
    TextView resultDisplay;
    List<ImageButtonWithText> numberButtons;
    ImageButtonWithText clearButton, addButton, subtractButton, multiplyButton,
            divideButton, equalButton, percentageButton, memAdd, memSub, memClear, memRetrieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);

        resultDisplay = findViewById(R.id.ResultDisplay);
        resultDisplay.setTypeface(Typeface.createFromAsset(getAssets(),"s.ttf"));

        initializeNumberButtons();
        initializeOperatorButtons();
        initializeMemoryOperatorButtons();
    }

    void initializeMemoryOperatorButtons(){
        memAdd=findViewById(R.id.MemoryPlus);
        memSub=findViewById(R.id.MemoryMinus);
        memClear=findViewById(R.id.MemoryClear);
        memRetrieve=findViewById(R.id.MemoryRetrieve);
        memRegister = 0;

        MemoryOperationListeners memoryOperationListeners = new MemoryOperationListeners();
        memAdd.setOnClickListener(memoryOperationListeners.new memoryAdditionListener());
        memSub.setOnClickListener(memoryOperationListeners.new memorySubtractionListener());
        memClear.setOnClickListener(memoryOperationListeners.new memoryClearListener());
        memRetrieve.setOnClickListener(memoryOperationListeners.new memoryRetrieveListener());
    }

    void initializeOperatorButtons(){
        clearButton=findViewById(R.id.ClearButton);
        addButton=findViewById(R.id.AdditionButton);
        subtractButton=findViewById(R.id.SubtractionButton);
        multiplyButton=findViewById(R.id.MultiplicationButton);
        divideButton=findViewById(R.id.DivisionButton);
        equalButton=findViewById(R.id.EqualsButton);
        percentageButton=findViewById(R.id.PercentageButton);

        OperatorListeners operatorListeners = new OperatorListeners();
        clearButton.setOnClickListener(operatorListeners.new clearListener());
        addButton.setOnClickListener(operatorListeners.new addListener());
        subtractButton.setOnClickListener(operatorListeners.new subtractListener());
        multiplyButton.setOnClickListener(operatorListeners.new multiplyListener());
        divideButton.setOnClickListener(operatorListeners.new divideListener());
        equalButton.setOnClickListener(operatorListeners.new equalListener());
        percentageButton.setOnClickListener(operatorListeners.new percentageListener());
    }

    void initializeNumberButtons(){
        numberButtons = new ArrayList<>();
        numberButtons.add(findViewById(R.id.ZeroButton_0));
        numberButtons.add(findViewById(R.id.OneButton_1));
        numberButtons.add(findViewById(R.id.TwoButton_2));
        numberButtons.add(findViewById(R.id.ThreeButton_3));
        numberButtons.add(findViewById(R.id.FourButton_4));
        numberButtons.add(findViewById(R.id.FiveButton_5));
        numberButtons.add(findViewById(R.id.SixButton_6));
        numberButtons.add(findViewById(R.id.SevenButton_7));
        numberButtons.add(findViewById(R.id.EightButton_8));
        numberButtons.add(findViewById(R.id.NineButton_9));
        numberButtons.add(findViewById(R.id.PointButton));

        NumberListeners numberListeners =new NumberListeners();
        numberButtons.get(0).setOnClickListener(numberListeners.new Listener0());
        numberButtons.get(1).setOnClickListener(numberListeners.new Listener1());
        numberButtons.get(2).setOnClickListener(numberListeners.new Listener2());
        numberButtons.get(3).setOnClickListener(numberListeners.new Listener3());
        numberButtons.get(4).setOnClickListener(numberListeners.new Listener4());
        numberButtons.get(5).setOnClickListener(numberListeners.new Listener5());
        numberButtons.get(6).setOnClickListener(numberListeners.new Listener6());
        numberButtons.get(7).setOnClickListener(numberListeners.new Listener7());
        numberButtons.get(8).setOnClickListener(numberListeners.new Listener8());
        numberButtons.get(9).setOnClickListener(numberListeners.new Listener9());
        numberButtons.get(10).setOnClickListener(numberListeners.new PointListener());
    }

    boolean isOperator(char c){
        char[] operators={'+','-',getString(R.string.multiply).charAt(0),getString(R.string.divide).charAt(0)};
        for (char a :
                operators) {
            if (a==c){
                return true;
            }
        }
        return false;
    }

    final class NumberListeners {

        String getLastNumber(){
            
        }

        boolean isTailedWithOperator(){
            String expression=resultDisplay.getText().toString();
            return expression.endsWith("+") || expression.endsWith("-") ||
                    expression.endsWith(getString(R.string.multiply)) ||
                    expression.endsWith(getString(R.string.divide));
        }

        boolean isLastDotted(){
            return resultDisplay.getText().toString().contains(".");
        }

        boolean isInitial(){
            return resultDisplay.getText().toString().equals(getString(R.string.result))
                    || resultDisplay.getText().toString().isEmpty();
        }


        void concat(String appendix){
            String newDisplay=resultDisplay.getText().toString().concat(appendix);
            resultDisplay.setText(newDisplay);
        }

        void NumberButtonClickedHandler(String num){
            VibrateProvider.vibrateOnClick(MainActivity.this);
            if (isInitial()){
                resultDisplay.setText(num);
            }else{
                concat(num);
            }
        }

        class Listener0 implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                NumberButtonClickedHandler("0");
            }
        }

        class Listener1 implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                NumberButtonClickedHandler("1");
            }
        }

        class Listener2 implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                NumberButtonClickedHandler("2");
            }
        }

        class Listener3 implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                NumberButtonClickedHandler("3");
            }
        }

        class Listener4 implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                NumberButtonClickedHandler("4");
            }
        }

        class Listener5 implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                NumberButtonClickedHandler("5");
            }
        }

        class Listener6 implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                NumberButtonClickedHandler("6");
            }
        }

        class Listener7 implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                NumberButtonClickedHandler("7");
            }
        }

        class Listener8 implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                NumberButtonClickedHandler("8");
            }
        }

        class Listener9 implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                NumberButtonClickedHandler("9");
            }
        }

        class PointListener implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                VibrateProvider.vibrateOnClick(MainActivity.this);
                if(!isLastDotted()&&!isTailedWithOperator()){
                    NumberButtonClickedHandler(".");
                }
            }
        }

    }

    final class OperatorListeners{
        boolean isInitial(){
            return resultDisplay.getText().toString().equals(getString(R.string.result))
                    || resultDisplay.getText().toString().isEmpty();
        }


        void concat(String appendix){
            String newDisplay=resultDisplay.getText().toString().concat(appendix);
            resultDisplay.setText(newDisplay);
        }

        void replaceTail(String appendix){
            String newDisplay = resultDisplay.getText().toString();
            newDisplay=newDisplay.subSequence(0,newDisplay.length()-1).toString();
            resultDisplay.setText(newDisplay.concat(appendix));
        }

        boolean isTailedWithOperator(){
            String expression=resultDisplay.getText().toString();
            return expression.endsWith("+") || expression.endsWith("-") ||
                    expression.endsWith(getString(R.string.multiply)) ||
                    expression.endsWith(getString(R.string.divide));
        }

        void NumberButtonClickedHandler(String operator){
            VibrateProvider.vibrateOnClick(MainActivity.this);
            if (!isInitial() && !isTailedWithOperator()){
                concat(operator);
            }

            if (isTailedWithOperator()){
                replaceTail(operator);
            }
        }

        class addListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                NumberButtonClickedHandler(getString(R.string.addition));
            }
        }

        class subtractListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                NumberButtonClickedHandler(getString(R.string.subtract));
            }
        }

        class multiplyListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                NumberButtonClickedHandler(getString(R.string.multiply));
            }
        }

        class divideListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                NumberButtonClickedHandler(getString(R.string.divide));
            }
        }

        class equalListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                //TODO
            }
        }

        class percentageListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                //TODO
            }
        }

        class clearListener implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                resultDisplay.setText("");
            }
        }
    }

    final class MemoryOperationListeners{
        class memoryClearListener implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                memRegister=0;
            }
        }

        class memoryRetrieveListener implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                resultDisplay.setText(Float.toString(memRegister));
            }
        }

        class memoryAdditionListener implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                //TODO:Error avoidance
                memRegister+=Float.parseFloat(resultDisplay.getText().toString());
            }
        }

        class memorySubtractionListener implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                memRegister-=Float.parseFloat(resultDisplay.getText().toString());
            }
        }
    }
}