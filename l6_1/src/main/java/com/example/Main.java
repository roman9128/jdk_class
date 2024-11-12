package com.example;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.util.ArithmeticUtils;

public class Main {
    public static void main(String[] args) {

         double[] arr = { 1, 2, 3, 4, 5, 6, 7, 89, 12, 3, 5, 65, 65, 5, 8, 6, 45, 8 };

         DescriptiveStatistics ds = new DescriptiveStatistics(arr);
         System.out.println(ds.getMin());
         System.out.println(ds.getMax());
         System.out.println(ds.getSum());
         System.out.println(ds.getMean());

         System.out.println(ArithmeticUtils.factorial(5));
         System.out.println(ArithmeticUtils.lcm(14, 20));
         System.out.println(ArithmeticUtils.gcd(14, 13));
         System.out.println(ArithmeticUtils.isPowerOfTwo(128));

    }
}