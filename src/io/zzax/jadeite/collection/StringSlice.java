package io.zzax.jadeite.collection;

public class StringSlice extends Slice<Character> {

    String string;

    public static Slice<Character> from(String string) {
        return new StringSlice(string);
    }

    StringSlice() {

    }

    public StringSlice(String string) {
        super(string.length());
        this.string = string;
    }

    public Slice<Character> copy() {
        StringSlice slice = new StringSlice();
        StringSlice.copyTo(this, slice);
        return slice;
    }

    static void copyTo(StringSlice source, StringSlice target) {
        Slice.copyTo(source, target);
        target.string = source.string;
    }

    @Override
    protected int originalSize() {
        return string.length();
    }

    @Override
    public Character originalGet(int index) {
        return string.charAt(index);
    }
}
