package BitSetTest;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

public class BitSetMain {

	/**
	 * @param args
	 */
    public static void main(String[] args) {
        BitSet bitFilter = fromString("1010001");
        List<BitSet> bits = new ArrayList<BitSet>();
        bits.add(fromString("1000001"));
        bits.add(fromString("1011111"));
        bits.add(fromString("1000001"));
        bits.add(fromString("1011111"));
        bits.add(fromString("1000001"));
        bits.add(fromString("1011111"));
        bits.add(fromString("1000001"));
        bits.add(fromString("1011111"));
        bits.add(fromString("1000001"));
        bits.add(fromString("1011111"));
        bits.add(fromString("1000001"));
        bits.add(fromString("1011111"));
        bits.add(fromString("1000001"));
        bits.add(fromString("1011111"));
        bits.add(fromString("1000001"));
        bits.add(fromString("1011111"));
        bits.add(fromString("1000001"));
        bits.add(fromString("1011111"));
        bits.add(fromString("1000001"));
        bits.add(fromString("1011111"));
        bits.add(fromString("1000001"));
        bits.add(fromString("1011111"));
        bits.add(fromString("1000001"));
        bits.add(fromString("1011111"));
        bits.add(fromString("1000001"));
        bits.add(fromString("1011111"));
        bits.add(fromString("1000001"));
        bits.add(fromString("1011111"));
        bits.add(fromString("1000001"));
        bits.add(fromString("1011111"));
        bits.add(fromString("1000001"));
        bits.add(fromString("1011111"));
        bits.add(fromString("1110111"));
        bits.add(fromString("1101001"));

        long startTime = System.nanoTime();
        List<BitSet> results = sparseBitFilter(bitFilter, bits);
        long endTime = System.nanoTime();
        Iterator<BitSet> resultsIterator = results.iterator();
        while (resultsIterator.hasNext()) {
            System.out.println(toString(resultsIterator.next()));
        }
        System.out.println(endTime - startTime);
	}

    public static BitSet fromString(final String s) {
        return BitSet.valueOf(new long[] { Long.parseLong(s, 2) });
    }

    public static String toString(BitSet bs) {
        return Long.toString(bs.toLongArray()[0], 2);
    }

    public static List<Integer> getBitLocs(BitSet bits) {
        List<Integer> bitLocs = new ArrayList<Integer>();
        for (int ndx = 0;ndx < bits.length();ndx++) {
            if (bits.get(ndx)) {
                bitLocs.add(ndx);
            }
        }

        return bitLocs;
    }

//    public static boolean matchFilter(Iterator<Integer> bitLocIterator, BitSet value) {
    public static boolean matchFilter(List<Integer> bitLocs, BitSet value) {
        boolean matched = true;
        Iterator<Integer> bitLocIterator = bitLocs.iterator();

        while (bitLocIterator.hasNext()) {
            if (!value.get(bitLocIterator.next())) {
                matched = false;
                break;
            }
        }

        return matched;
    }

    public static List<BitSet> sparseBitFilter(BitSet filter, List<BitSet> bits) {
        List<Integer> bitLocs = getBitLocs(filter);
        Iterator<BitSet> bitsIterator = bits.iterator();
        List<BitSet> results = new ArrayList<BitSet>();
        BitSet currItem = null;

        while (bitsIterator.hasNext()) {
            currItem = bitsIterator.next();
            if (matchFilter(bitLocs, currItem)) {
                results.add(currItem);
            }
        }

        return results;
    }
}
