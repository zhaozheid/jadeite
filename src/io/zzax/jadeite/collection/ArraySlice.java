package io.zzax.jadeite.collection;

public class ArraySlice<T> extends Slice<T> {

    T[] array;

    public static <T> Slice<T> from (T[] array){
        return new ArraySlice<T>(array);
    }

    ArraySlice() {

    }

    public ArraySlice(T[] array) {
        super(array.length);
        this.array = array;
    }

    public Slice<T> copy() {
        ArraySlice<T> slice = new ArraySlice<T>();
        ArraySlice.copyTo(this, slice);
        return slice;
    }

    static <T> void copyTo(ArraySlice<T> source, ArraySlice<T> target) {
        Slice.copyTo(source, target);
        target.array = source.array;
    }

    @Override
    protected int originalSize() {
        return array.length;
    }

    @Override
    public T originalGet(int index) {
        return array[index];
    }
}
