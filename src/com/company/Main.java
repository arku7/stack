package com.company;
import java.util.Scanner;
import java.lang.Math;

class Stack<T>{
    private int size;
    private element top;

    {
        size = 0;
        top = null;
    }

    Stack(T[] add) {
        for(T i : add)
            AddElement(i);
    }

    Stack() {}

    Stack(Stack<T> clone) { copy(clone.top); }

    private void copy(element clone) {
        element cur = new element(clone);
        if(cur.next != null) copy(cur.next);
        AddElement(cur.data);
    }

    public void Clear() {
        top = null;
        size = 0;
    }

    public Boolean Contains(T x) {
        element cur = new element(top);
        while (cur != null) {
            if(cur.data == x) return true;
            if(cur.next != null) cur = new element(cur.next);
            else cur = null;
        }
        return false;
    }

    public Boolean IsEmpty() { return top == null; }

    public Boolean Remove(T x) { //простите...
        if(top.data == x)
        {
            PopElement();
            return true;
        }
        if(top.next.data == x)
        {
            top.next = top.next.next;
            return true;
        }
        element cur = new element(top);

        while (cur.next.next != null){
            if(cur.next.next.data == x){
                size--;
                cur.next.next = cur.next.next.next;
                return true;
            }

            if(cur.next != null) cur = new element(cur.next);
            else break;
        }
        return false;
    }

    public void AddElement(T x) {
        size++;
        if(top == null) {
            top = new element(x);
            return;
        }

        element pr = top;
        top = new element(x);
        top.next = pr;
    }

    public int GetSize() { return size; }

    public void PrintElements(){
        element cur = new element(top);
        while (cur != null) {
            System.out.print(cur.data + " ");
            if(cur.next != null) cur = new element(cur.next);
            else cur = null;
        }
        System.out.print("\n");
    }

    public T PopElement(){
        if(IsEmpty())
        {
            System.out.println("Stack is empty!");
            return null;
        }
        size--;
        T res = top.data;
        top = top.next;
        return res;
    }

    public void Connect(Stack<T> add) { copy(add.top); }


    private class element{
        private T data;
        private element next;
        {
            data = null;
            next = null;
        }
        element(T x) { data = x; }
        element(element nw) {
            data = nw.data;
            next = nw.next;
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);

        Stack<Integer> a = new Stack<Integer>();
        for (int i = 0; i < 5; i++)
            a.AddElement((int) (Math.random()*11));

        Stack<Integer> b = new Stack<Integer>();
        for (int i = 0; i < 5; i++)
            b.AddElement((int) (Math.random()*11));

        Stack<Integer> c = new Stack<Integer>();
        c.Connect(a); c.Connect(b);

        System.out.println(c.GetSize());
        c.PrintElements();

        int k = 0;
        while (k != 3)
        {
            int del = (int)(Math.random()*11);
            boolean delted = c.Remove(del);
            System.out.println("Delete " + (del) + " : " + (delted));
            if(delted) k++;
        }

        System.out.println(c.GetSize());
        c.PrintElements();

    }
}
