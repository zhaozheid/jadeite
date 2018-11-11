package io.zzax.jadeite.collection;

import java.util.Iterator;

public class Range implements Iterable<Integer> {


    //  MARK - Constructors

    public Range(){
        from = new Edge(0, true);
    }

    public static Range f(int from) {
        return new Range().from(from);
    }

    public static Range ft(int from, int to) {
        return new Range().from(from).to(to);
    }

    public static Range t(int to) {
        return new Range().to(to);
    }

    //  MARK - Basic - Properties

    private Edge from;
    private Edge to;

    //  MARK - Basic - Builders

    private Range from(int value, boolean include){
        this.from = new Edge(value, include);
        return this;
    }

    public Range includeFrom(int value) {
        return from(value, true);
    }

    public Range excludeFrom(int value) {
        return from(value, false);
    }

    public Range from(int value) {
        return from(value, true);
    }

    public Range fromNegativeInfinity() {
        from = null;
        return this;
    }

    private Range to(int value, boolean include){
        this.to = new Edge(value, include);
        return this;
    }

    public Range includeTo(int value) {
        return to(value, true);
    }

    public Range excludeTo(int value) {
        return to(value, false);
    }

    public Range to(int value) {
        return to(value, false);
    }

    //  MARK - Standardising - Functions

    private void standardise() {
        if (from != null) {
            if (!from.include) {
                from = new Edge(from.value + 1, true);
            }
        }
        if (to != null) {
            if (to.include) {
                to = new Edge(to.value + 1, false);
            }
        }
    }

    private Range standardisedRange() {
        Range range = this.copy();
        range.standardise();
        return range;
    }

    private void shift(int value) {
        if (from != null) {
            from.value += value;
        }
        if (to != null) {
            to.value += value;
        }
    }

    //  MARK - Queries for Standardised Range - Functions

    public int getFromValue() {
        return from.value;
    }

    public int getToValue() {
        return to.value;
    }

    public int length() {
        return to.value - from.value;
    }

    //  MARK -

    public Range cut(Range subrange) {
        Range originalRange = this.copy().standardisedRange();
        subrange = subrange.standardisedRange();
        subrange.shift(originalRange.from.value);
        return intersect(originalRange, subrange);
    }

    private Range intersect(Range range) {
        return intersect(this, range);
    }

    private static Range intersect(Range r1, Range r2) {
        Range intersection = new Range();
        r1 = r1.standardisedRange();
        r2 = r2.standardisedRange();

        Edge from = null;
        if (r1.from == null && r2.from == null) {
            from = null;
        } else if (r1.from == null) {
            from = r2.from;
        } else if (r2.from == null) {
            from = r1.from;
        } else {
            if (r1.from.value < r2.from.value) {
                from = r2.from;
            } else {
                from = r1.from;
            }
        }
        intersection.from = from;

        Edge to = null;
        if (r1.to == null && r2.to == null) {
            to = null;
        } else if (r1.to == null) {
            to = r2.to;
        } else if (r2.to == null) {
            to = r1.to;
        } else {
            if (r1.to.value < r2.to.value) {
                to = r1.to;
            } else {
                to = r2.to;
            }
        }
        intersection.to = to;

        return intersection;
    }

    //  MARK - Iterators - Functions

    public Iterator<Integer> iterator() {
        return new RangeIterator();
    }

    private class RangeIterator implements Iterator<Integer> {
        private int value = Range.this.getFromValue();

        public boolean hasNext() {
            return value < Range.this.getToValue();
        }

        public Integer next() {
            int v = value;
            value++;
            return v;
        }

        public void remove() {

        }
    }


    //  MARK - Objects - Functions

    public Range copy() {
        Range range = new Range();
        range.from = from;
        range.to = to;
        return range;
    }

    @Override
    public String toString() {
        String string = "";
        if (from == null) {
            string += "(-Infinity";
        } else {
            string += from.include ? "[" : "(";
            string += from.value;
        }
        string += ", ";

        if (to == null) {
            string += "Infinity)";
        } else {
            string += to.value;
            string += to.include ? "]" : ")";
        }

        return string;
    }

    //  MARK - Edge

    private static class Edge {
        int value;
        boolean include;


        public Edge(int value, boolean include) {
            this.value = value;
            this.include = include;
        }

    }
}
