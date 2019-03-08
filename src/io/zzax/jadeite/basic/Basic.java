package io.zzax.jadeite.basic;

import java.util.*;
import java.util.stream.Collectors;

public class Basic {

    public static String toString(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof String) {
            return toString((String)obj);
        }
        if (obj instanceof Character) {
            return toString((Character)obj);
        }
        if (obj.getClass().isArray()) {
            if (obj.getClass().getComponentType().isPrimitive()) {
                obj = toObjectArray(obj);
            }
            return toString((Object[])obj);
        }
        if (obj instanceof Set) {
            return toString((Set)obj);
        }
        if (obj instanceof List) {
            return toString((List)obj);
        }
        if (obj instanceof Map) {
            return toString((Map)obj);
        }
        return obj.toString();
    }

    private static String toString(String str) {
        return '"' + str + '"';
    }

    private static String toString(char ch) {
        return "'" + ch + "'";
    }

    private static <E> String toString(E[] arr) {
        List<Object> list = Arrays.asList(arr);
        return toString(list, true);
    }

    private static Object[] toObjectArray(Object obj) {
        if (obj.getClass().getComponentType() == boolean.class) {
            return toObjectArray((boolean[])obj);
        } else if (obj.getClass() == byte.class) {
            return toObjectArray((byte[])obj);
        } else if (obj.getClass().getComponentType() == short.class) {
            return toObjectArray((short[])obj);
        } else if (obj.getClass().getComponentType() == char.class) {
            return toObjectArray((char[])obj);
        } else if (obj.getClass().getComponentType() == int.class) {
            return toObjectArray((int[])obj);
        } else if (obj.getClass().getComponentType() == long.class) {
            return toObjectArray((long[])obj);
        } else if (obj.getClass().getComponentType() == float.class) {
            return toObjectArray((float[])obj);
        } else if (obj.getClass().getComponentType() == double.class) {
            return toObjectArray((double[])obj);
        } else  {
            return (Object[])obj;
        }
    }

    private static Boolean[] toObjectArray(boolean[] arr) {
        Boolean[] objects = new Boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            objects[i] = arr[i];
        }
        return objects;
    }

    private static Byte[] toObjectArray(byte[] arr) {
        Byte[] objects = new Byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            objects[i] = arr[i];
        }
        return objects;
    }

    private static Short[] toObjectArray(short[] arr) {
        Short[] objects = new Short[arr.length];
        for (int i = 0; i < arr.length; i++) {
            objects[i] = arr[i];
        }
        return objects;
    }

    private static Character[] toObjectArray(char[] arr) {
        Character[] objects = new Character[arr.length];
        for (int i = 0; i < arr.length; i++) {
            objects[i] = arr[i];
        }
        return objects;
    }

    private static Integer[] toObjectArray(int[] arr) {
        Integer[] objects = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            objects[i] = arr[i];
        }
        return objects;
    }

    private static Long[] toObjectArray(long[] arr) {
        Long[] objects = new Long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            objects[i] = arr[i];
        }
        return objects;
    }

    private static Float[] toObjectArray(float[] arr) {
        Float[] objects = new Float[arr.length];
        for (int i = 0; i < arr.length; i++) {
            objects[i] = arr[i];
        }
        return objects;
    }

    private static Double[] toObjectArray(double[] arr) {
        Double[] objects = new Double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            objects[i] = arr[i];
        }
        return objects;
    }

    private static String toString(List list) {
        return toString(list, false);
    }

    private static String toString(List linear, boolean isArray) {
        StringBuilder builder = new StringBuilder();
        builder.append(isArray ? "[" : "<");
        List<String> parts = ((List<Object>)linear).stream()
                .map(Basic::toString)
                .collect(Collectors.toList());
        builder.append(String.join(", ", parts));
        builder.append(isArray ? "]" : ">");
        return builder.toString();
    }

    private static String toString(Set set) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        List<String> parts = ((Set<Object>)set).stream()
                .map(Basic::toString)
                .collect(Collectors.toList());
        builder.append(String.join(", ", parts));
        builder.append("}");
        return builder.toString();
    }

    private static String toString(Map map) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        List<String> parts = ((Map<Object, Object>)map).keySet().stream()
                .map(key -> toString(key) + " -> " + toString(map.get(key)))
                .collect(Collectors.toList());
        builder.append(String.join(", ", parts));
        builder.append("}");
        return builder.toString();
    }

    public static boolean equals(Object obj1, Object obj2) {
        if (obj1 == obj2) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        if (obj1.getClass().isArray()) {
            return equalsArray(obj1, obj2);
        }
        return obj1.equals(obj2);
    }

    private static boolean equalsArray(Object arr1, Object arr2) {
        Object[] objectArr1 = toObjectArray(arr1);
        Object[] objectArr2 = toObjectArray(arr2);
        if (objectArr1.length != objectArr2.length) {
            return false;
        }
        for (int i = 0; i < objectArr1.length; i++) {
            Object obj1 = objectArr1[i];
            Object obj2 = objectArr2[i];
            if (!equals(obj1, obj2)) {
                return false;
            }
        }
        return true;
    }
}
