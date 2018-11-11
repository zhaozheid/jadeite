package io.zzax.jadeite.collection;

import java.util.List;

public class ListSlice<T> extends Slice<T> {

    List<T> list;

    public static <T> Slice<T> from (List<T> list){
        return new ListSlice<T>(list);
    }

    ListSlice() {

    }

    public ListSlice(List<T> list) {
        super(list.size());
        this.list = list;
    }

    public Slice<T> copy() {
        ListSlice<T> slice = new ListSlice<T>();
        ListSlice.copyTo(this, slice);
        return slice;
    }

    static <T> void copyTo(ListSlice<T> source, ListSlice<T> target) {
        Slice.copyTo(source, target);
        target.list = source.list;
    }

    @Override
    protected int originalSize() {
        return list.size();
    }

    @Override
    public T originalGet(int index) {
        return list.get(index);
    }

}
