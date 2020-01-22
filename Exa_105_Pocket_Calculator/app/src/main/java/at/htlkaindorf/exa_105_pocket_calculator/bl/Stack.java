package at.htlkaindorf.exa_105_pocket_calculator.bl;

import android.util.Log;

import java.util.Arrays;

import at.htlkaindorf.exa_105_pocket_calculator.MainActivity;
import at.htlkaindorf.exa_105_pocket_calculator.exceptions.NotEnoughOperandsException;
import at.htlkaindorf.exa_105_pocket_calculator.exceptions.RuntimeStackOverflowError;

import static at.htlkaindorf.exa_105_pocket_calculator.MainActivity.TAG;

public class Stack {
    private double[] values = new double[10];
    private int tos = 0;

    public void push(double val) {
        if (this.isFull()) {
            throw new RuntimeStackOverflowError("Stack overflow");
        }
        this.values[this.tos++] = val;
    }

    public double pop() {
        if (this.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return this.values[--this.tos];
    }

    public boolean isEmpty() {
        return tos == 0;
    }

    public boolean isFull() {
        return tos == values.length;
    }

    public void clear() {
        this.tos = 0;
    }

    public int getTos() {
        return this.tos;
    }

    public double performCalculation(Sign currentOperator) {
        if (tos >= 2) {
            double result;
            double first_number;
            double second_number;
            switch (currentOperator) {
                case Plus:
                    result = this.pop() + this.pop();
                    this.push(result);
                    return result;
                case Minus:
                    first_number = this.pop();
                    second_number = this.pop();
                    result = second_number - first_number;
                    this.push(result);
                    return result;
                case Multiply:
                    result = this.pop() * this.pop();
                    this.push(result);

                    return result;
                case Divide:
                    first_number = this.pop();
                    second_number = this.pop();
                    if (first_number == 0) throw new ArithmeticException();
                    result = second_number / first_number;
                    this.push(result);

                    return result;
                default:
                    return 0;
            }
        } else {
            throw new NotEnoughOperandsException("not enough operands");
        }
    }

    public boolean calculationPossible() {
        return this.tos >= 2;
    }
}
