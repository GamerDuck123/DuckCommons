package com.gamerduck.commons.general;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class ExpiringList<T> {
    private final DelayQueue<DelayedItem<T>> queue = new DelayQueue<>();

    public void add(T item, long lifespan, TimeUnit unit) {
        queue.put(new DelayedItem<>(item, lifespan, unit));
    }

    public T take() throws InterruptedException {
        return queue.take().getItem();
    }

    private static class DelayedItem<T> implements Delayed {
        private final T item;
        private final long expiryTime;

        public DelayedItem(T item, long lifespan, TimeUnit unit) {
            this.item = item;
            this.expiryTime = System.currentTimeMillis() + unit.toMillis(lifespan);
        }

        public T getItem() {
            return item;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = expiryTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(expiryTime, ((DelayedItem<?>) o).expiryTime);
        }
    }
}
