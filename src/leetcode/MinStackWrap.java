/*
155. Min Stack
Medium

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.

You must implement a solution with O(1) time complexity for each function.
*/

package leetcode;

import java.util.ArrayList;
import java.util.List;

class MinStack {
    private int size;
    private int min;
    private final List<Integer> data;
    private final List<Integer> minData;

    public MinStack() {
        size = 0;
        data = new ArrayList<>();
        minData = new ArrayList<>();
    }

    public void push(int val) {
        data.add(val);
        if (minData.isEmpty() || min > val) {
            min = val;
        }
        minData.add(min);
        size++;
    }

    public void pop() {
        size--;
        data.remove(size);
        minData.remove(size);
        if (size == 0) {
            min = -1;
        } else {
            min = minData.get(size - 1);
        }
    }

    public int top() {
        return data.get(size - 1);
    }

    public int getMin() {
        return min;
    }
}