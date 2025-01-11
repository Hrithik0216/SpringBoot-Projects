import collections.PriorityQueue.Employee;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Sample {
    static class SizeLimitedQueue<E> extends LinkedList<E> {
        int size;

        public SizeLimitedQueue(int size) {
            this.size = size;
        }

        @Override
        public boolean add(E e) {
            if (this.size() > size) {
                try {
                    throw new IllegalAccessException("Queue size reached");
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
            return super.add(e);
        }
    }

    public static void main(String[] args) {
        Queue<Integer> q = new SizeLimitedQueue<>(3);
        for (int i = 0; i < 10; i++) {
            q.add(i);
        }
        Stack<Integer> s = new Stack<>();
    }
}
