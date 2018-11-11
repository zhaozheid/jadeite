package io.zzax.jadeite.collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class Slice<T> implements Iterable<T> {

    private Range range;

    Slice() {

    }

    Slice(int originalSize) {
        range = new Range().to(originalSize);
    }

    //  MARK - Quick Initialization - Functions

    public static <T> Slice<T> from(List<T> list) {
        return ListSlice.from(list);
    }

    public static <T> Slice<T> from(T[] array) {
        return ArraySlice.from(array);
    }

    public static Slice<Character> from(String string) {
        return StringSlice.from(string);
    }

    //  MARK - Queries - Functions

    public T get(int index) {
        checkInsideRange(index);
        return originalGet(indexToOriginalIndex(index));
    }

    public T getFirst() {
        checkHasElement();
        return originalGet(indexToOriginalIndex(0));
    }

    public T getLast() {
        checkHasElement();
        return originalGet(indexToOriginalIndex(range.length() - 1));
    }

    public Slice<T> slice(Range range) {
        Slice<T> slice = this.copy();
        slice.range = slice.range.cut(range);
        return slice;
    }

    private int indexToOriginalIndex(int index) {
        return range.getFromValue() + index;
    }

    private void checkInsideRange(int index) {
        if (index < 0 || index >= range.length()) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkHasElement() {
        if (range.length() < 1) {
            throw new ElementNotExistException();
        }
    }

    //  MARK - Iterator - Functions

    public Iterator<T> iterator() {
        return new SliceIterator();
    }

    private class SliceIterator implements Iterator<T> {
        private Iterator<Integer> rangeIterator = range.iterator();

        public boolean hasNext() {
            return rangeIterator.hasNext();
        }

        public T next() {
            int index = rangeIterator.next();
            return originalGet(index);
        }

        public void remove() {

        }
    }


    //  MARK - Object - Functions

    public abstract Slice<T> copy();

    static <T> void copyTo(Slice<T> source, Slice<T> target) {
        target.range = source.range;
    }

    //  MARK

    protected abstract T originalGet(int index);

    protected abstract int originalSize();

    protected Range originalRange() {
        return new Range().to(originalSize());
    }


    public static class ElementNotExistException extends RuntimeException {

    }
}
