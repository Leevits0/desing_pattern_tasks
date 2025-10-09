package iterator.fibonacci;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        // Finite: first 10 numbers
        FibonacciSequence firstTen = new FibonacciSequence(10);

        System.out.println("First 10 Fibonacci numbers:");
        Iterator<Integer> it = firstTen.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // // Unbounded example (client controls stopping condition)
        // FibonacciSequence unbounded = new FibonacciSequence();
        // Iterator<Integer> it2 = unbounded.iterator();
        // int count = 0;
        // System.out.println("First 20 numbers from unbounded sequence:");
        // while (it2.hasNext() && count < 20) {
        //     System.out.print(it2.next() + " ");
        //     count++;
        // }
        // System.out.println();
    }
}
