package org.web3j.abi.datatypes;


import java.util.Collection;
import java.util.List;

/**
 * Static array type.
 */
public class StaticArray<T extends Type> extends Array<T> {

    public StaticArray(T... values) {
        super(values[0].getTypeAsString() + "[" + values.length + "]", values);
    }

    public StaticArray(List<T> values) {
        super(values.get(0).getTypeAsString() + "[" + values.size() + "]", values);
    }

    private StaticArray(String type) {
        super(type);
    }

    private StaticArray(String type, int length) {
        super( type.contains("[")
               ? type.substring(0, type.indexOf("[") + 1) + length + "]"
               : type + "[" + length + "]" );
    }

    public static StaticArray empty(String type) {
        return new StaticArray(type);
    }

    /**
     * TODO: none of this will work because of the private access to {@code values} in {@link Array}
     *       we could make a better interface by adding {@code T getDefaultValue()} to the
     *       {@link Type} interface
     *
     * <p>Produce a fixed-sized StaticArray backed by an empty {@link Collection}
     *
     * <p>Calling empty("bytes32", 3) is equivalent to calling empty("bytes32[3]")
     *
     * @param type solidity-formatted type name, e.g. `bytes`, `uint256[3]`
     * @param length the size of the array to return; this takes precedence over the length --
     *               if any -- included in {@param type}
     * @return {@link StaticArray} of length {@param length}
     */
    public static StaticArray empty(String type, int length) {
        return new StaticArray(type, length);
    }
}
