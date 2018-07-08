package ru.weisert.primes;

/*
 * Class intended to generate primes.
 */
public final class PrimesGenerator {
    public static final long MIN_PRIME = 2L;
    public static final long MAX_PRIME = 9223372036854775783L;

    public static long nextPrime(long value) {
        if (value < MIN_PRIME)
            return MIN_PRIME;
        if (value >= MAX_PRIME)
            return MAX_PRIME;
        long result = value + 1;
        while (!isPrime(result).value)
            ++result;
        return result;
    }

    public static long prevPrime(long value) {
        if (value <= MIN_PRIME)
            return MIN_PRIME;
        if (value - 1 >= MAX_PRIME)
            return MAX_PRIME;
        long result = value - 1;
        while (!isPrime(result).value)
            --result;
        return result;
    }

    static class Verdict {
        public boolean value = true;
        public String reason = "";
    }

    public static Verdict isPrime(long value) {
        Verdict result = new Verdict();
        if (value < 2) {
            result.value = false;
            result.reason = "Integer 2 is the smallest possible prime.";
        } else if (value < 4) {
            result.value = true;
        } else {
            long root = (long)Math.sqrt(value);
            for (long i = 2; i <= root; ++i) {
                if (value % i == 0) {
                    result.value = false;
                    result.reason = "Value of " + value + " is evenly divisible by " + i + ".";
                    break;
                }
            }
        }
        return result;
    }
}
