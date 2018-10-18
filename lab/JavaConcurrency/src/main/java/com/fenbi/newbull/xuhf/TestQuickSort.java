/**
 * @(#)TestQuickSort.java, Jul 22, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.xuhf;


import com.sun.tools.javac.util.Pair;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author xuhongfeng
 */
public class TestQuickSort {

    public static void main(String[] args) throws InterruptedException {

        int size = 1000000;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Random().nextInt(size);
        }

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        SortTask task = new SortTask(array, 0, size - 1);
        pool.submit(task);
        task.join();

        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }

    }

    private static class SortTask extends RecursiveAction {

        private final int[] array;
        private final int startInclusive;
        private final int endInclusive;

        public SortTask(int[] array, int startInclusive, int endInclusive) {
            this.array = array;
            this.startInclusive = startInclusive;
            this.endInclusive = endInclusive;
        }

        @Override
        protected void compute() {
            if (length() <= 10) {
                selectSort();
                return;
            }

            Pair<SortTask, SortTask> sub = divide();
            sub.fst.fork();
            sub.snd.compute();
            sub.fst.join();
        }

        private Pair<SortTask, SortTask> divide() {

            int p = array[startInclusive];

            int i = startInclusive + 1;
            int j = endInclusive;

            while (i < j) {
                while (i < j && array[i] <= p) {
                    i++;
                }
                while (i < j && array[j] > p) {
                    j--;
                }
                if (i < j) {
                    swap(i, j);
                    i++;
                    j--;
                }
            }
            swap(i, startInclusive);

            SortTask left = new SortTask(array, startInclusive, i - 1);
            SortTask right = new SortTask(array, i + 1, endInclusive);
            return new Pair<>(left, right);
        }

        private void swap(int i, int j) {
            int t = array[i];
            array[i] = array[j];
            array[j] = t;
        }

        private void selectSort() {
            for (int i = startInclusive; i < endInclusive; i++) {
                int min = i;
                for (int j = i + 1; j <= endInclusive; j++) {
                    if (array[j] < array[min]) {
                        min = j;
                    }
                }
                swap(i, min);
            }
        }

        private int length() {
            return endInclusive - startInclusive + 1;
        }
    }
}