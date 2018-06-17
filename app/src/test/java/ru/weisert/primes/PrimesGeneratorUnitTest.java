package ru.weisert.primes;

import org.junit.Test;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class PrimesGeneratorUnitTest {
    private final long primesUnder100[] =
            {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89, 97};
    private final long bigPrimes[] = {
            5915587277L, 1500450271L, 3267000013L, 5754853343L, 4093082899L,
            9576890767L, 3628273133L, 2860486313L, 5463458053L, 3367900313L,
            9223372036854775783L  // Max possible long prime
    };

    @Test
    public void twoIsSmallestPossiblePrime() {
        for (long i = -100; i <= 1; ++i) {
            PrimesGenerator.Verdict verdict = PrimesGenerator.isPrime(i);
            assertEquals(false, verdict.value);
            assertEquals("Integer 2 is the smallest possible prime.", verdict.reason);
        }
    }

    @Test
    public void setOfSmallPrimes() {
        for (long prime : primesUnder100) {
            PrimesGenerator.Verdict verdict = PrimesGenerator.isPrime(prime);
            assertEquals(true, verdict.value);
            assertEquals("", verdict.reason);
        }
    }

    @Test
    public void nonPrimes() {
        Set<Long> primes = new HashSet<Long>();
        for (long i : primesUnder100)
            primes.add(i);
        for (long i = 2; i < 100; ++i) {
            if (primes.contains(i))
                continue;
            PrimesGenerator.Verdict verdict = PrimesGenerator.isPrime(i);
            assertEquals(false, verdict.value);
            if (i % 2 == 0) {
                assertEquals("Value of " + i + " is evenly divisible by 2.",
                             verdict.reason);
            }
        }
    }

    @Test
    public void setOfBigPrimes() {
        for (long prime : bigPrimes) {
            PrimesGenerator.Verdict verdict = PrimesGenerator.isPrime(prime);
            assertEquals(true, verdict.value);
            assertEquals("", verdict.reason);
        }
    }

    @Test
    public void nextPrime() {
        assertEquals(2, PrimesGenerator.nextPrime(-1));
        assertEquals(2, PrimesGenerator.nextPrime(0));
        assertEquals(2, PrimesGenerator.nextPrime(1));
        for (int i = 0; i < primesUnder100.length - 1; ++i) {
            assertEquals(primesUnder100[i + 1], PrimesGenerator.nextPrime(primesUnder100[i]));
        }
        assertEquals(PrimesGenerator.MAX_PRIME, PrimesGenerator.nextPrime(Long.MAX_VALUE));
    }

    @Test
    public void prevPrime() {
        assertEquals(2, PrimesGenerator.prevPrime(-1));
        assertEquals(2, PrimesGenerator.prevPrime(0));
        assertEquals(2, PrimesGenerator.prevPrime(1));
        for (int i = 1; i < primesUnder100.length; ++i) {
            assertEquals(primesUnder100[i - 1], PrimesGenerator.prevPrime(primesUnder100[i]));
        }
        assertEquals(PrimesGenerator.MAX_PRIME, PrimesGenerator.prevPrime(Long.MAX_VALUE));
    }
}
