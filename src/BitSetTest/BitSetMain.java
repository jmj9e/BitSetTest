package BitSetTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

public class BitSetMain {

	/**
	 * @param args
	 */
    public static void main(String[] args) {
        BitSet bitFilter = fromString("1100000");
        List<BitSet> bits = new ArrayList<BitSet>();

        File file = new File("bitsRun1.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                bits.add(fromString(line));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long startTime = System.nanoTime();
        List<BitSet> results = sparseBitFilter(bitFilter, bits);
        long endTime = System.nanoTime();
        Iterator<BitSet> resultsIterator = results.iterator();
        System.out.println(results.size());
//        while (resultsIterator.hasNext()) {
//            System.out.println(toString(resultsIterator.next()));
//        }
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
