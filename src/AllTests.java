import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

// import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import BitSetTest.BitSetMain;


public class AllTests {
    private BitSet allZeros, allOnes, mixedOnesZeros, filter;

    @Before
    public void setup() {
        allZeros = BitSetMain.fromString("00000000");
        allOnes = BitSetMain.fromString("11111111");
        mixedOnesZeros = BitSetMain.fromString("11010010");
        filter = BitSetMain.fromString("10100000");
    }

    @Test
    public void testGetBitLocs() {
        assertEquals((long)BitSetMain.getBitLocs(allZeros).size(), (long)0);
        assertEquals((long)BitSetMain.getBitLocs(allOnes).size(), (long)8);
        assertEquals((long)BitSetMain.getBitLocs(mixedOnesZeros).size(), (long)4);
    }

    @Test
    public void testMatchFilter() {
        List<Integer> bitLocs = BitSetMain.getBitLocs(filter);

        assertEquals(BitSetMain.matchFilter(bitLocs, allZeros), false);
        assertEquals(BitSetMain.matchFilter(bitLocs, allOnes), true);
        assertEquals(BitSetMain.matchFilter(bitLocs, mixedOnesZeros), false);
    }
}
