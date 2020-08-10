/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/*
 * This file is generated by FieldGen.jsh. Do not modify it directly.
 */

package sun.security.util.math.intpoly;

import java.math.BigInteger;
public class IntegerPolynomialP256 extends IntegerPolynomial {
    private static final int BITS_PER_LIMB = 26;
    private static final int NUM_LIMBS = 10;
    private static final int MAX_ADDS = 2;
    public static final BigInteger MODULUS = evaluateModulus();
    private static final long CARRY_ADD = 1 << 25;
    private static final int LIMB_MASK = -1 >>> (64 - BITS_PER_LIMB);
    public IntegerPolynomialP256() {

        super(BITS_PER_LIMB, NUM_LIMBS, MAX_ADDS, MODULUS);

    }
    private static BigInteger evaluateModulus() {
        BigInteger result = BigInteger.valueOf(2).pow(256);
        result = result.subtract(BigInteger.valueOf(2).pow(224));
        result = result.add(BigInteger.valueOf(2).pow(192));
        result = result.add(BigInteger.valueOf(2).pow(96));
        result = result.subtract(BigInteger.valueOf(1));
        return result;
    }
    @Override
    protected void finalCarryReduceLast(long[] limbs) {
        long c = limbs[9] >> 22;
        limbs[9] -= c << 22;
        limbs[8] += (c << 16) & LIMB_MASK;
        limbs[9] += c >> 10;
        limbs[7] -= (c << 10) & LIMB_MASK;
        limbs[8] -= c >> 16;
        limbs[3] -= (c << 18) & LIMB_MASK;
        limbs[4] -= c >> 8;
        limbs[0] += c;
    }
    private void carryReduce(long[] r, long c0, long c1, long c2, long c3, long c4, long c5, long c6, long c7, long c8, long c9, long c10, long c11, long c12, long c13, long c14, long c15, long c16, long c17, long c18) {
        long c19 = 0;
        //reduce from position 18
        c16 += (c18 << 20) & LIMB_MASK;
        c17 += c18 >> 6;
        c15 -= (c18 << 14) & LIMB_MASK;
        c16 -= c18 >> 12;
        c11 -= (c18 << 22) & LIMB_MASK;
        c12 -= c18 >> 4;
        c8 += (c18 << 4) & LIMB_MASK;
        c9 += c18 >> 22;
        //reduce from position 17
        c15 += (c17 << 20) & LIMB_MASK;
        c16 += c17 >> 6;
        c14 -= (c17 << 14) & LIMB_MASK;
        c15 -= c17 >> 12;
        c10 -= (c17 << 22) & LIMB_MASK;
        c11 -= c17 >> 4;
        c7 += (c17 << 4) & LIMB_MASK;
        c8 += c17 >> 22;
        //reduce from position 16
        c14 += (c16 << 20) & LIMB_MASK;
        c15 += c16 >> 6;
        c13 -= (c16 << 14) & LIMB_MASK;
        c14 -= c16 >> 12;
        c9 -= (c16 << 22) & LIMB_MASK;
        c10 -= c16 >> 4;
        c6 += (c16 << 4) & LIMB_MASK;
        c7 += c16 >> 22;
        //reduce from position 15
        c13 += (c15 << 20) & LIMB_MASK;
        c14 += c15 >> 6;
        c12 -= (c15 << 14) & LIMB_MASK;
        c13 -= c15 >> 12;
        c8 -= (c15 << 22) & LIMB_MASK;
        c9 -= c15 >> 4;
        c5 += (c15 << 4) & LIMB_MASK;
        c6 += c15 >> 22;
        //reduce from position 14
        c12 += (c14 << 20) & LIMB_MASK;
        c13 += c14 >> 6;
        c11 -= (c14 << 14) & LIMB_MASK;
        c12 -= c14 >> 12;
        c7 -= (c14 << 22) & LIMB_MASK;
        c8 -= c14 >> 4;
        c4 += (c14 << 4) & LIMB_MASK;
        c5 += c14 >> 22;
        //reduce from position 13
        c11 += (c13 << 20) & LIMB_MASK;
        c12 += c13 >> 6;
        c10 -= (c13 << 14) & LIMB_MASK;
        c11 -= c13 >> 12;
        c6 -= (c13 << 22) & LIMB_MASK;
        c7 -= c13 >> 4;
        c3 += (c13 << 4) & LIMB_MASK;
        c4 += c13 >> 22;
        //reduce from position 12
        c10 += (c12 << 20) & LIMB_MASK;
        c11 += c12 >> 6;
        c9 -= (c12 << 14) & LIMB_MASK;
        c10 -= c12 >> 12;
        c5 -= (c12 << 22) & LIMB_MASK;
        c6 -= c12 >> 4;
        c2 += (c12 << 4) & LIMB_MASK;
        c3 += c12 >> 22;
        //reduce from position 11
        c9 += (c11 << 20) & LIMB_MASK;
        c10 += c11 >> 6;
        c8 -= (c11 << 14) & LIMB_MASK;
        c9 -= c11 >> 12;
        c4 -= (c11 << 22) & LIMB_MASK;
        c5 -= c11 >> 4;
        c1 += (c11 << 4) & LIMB_MASK;
        c2 += c11 >> 22;
        //reduce from position 10
        c8 += (c10 << 20) & LIMB_MASK;
        c9 += c10 >> 6;
        c7 -= (c10 << 14) & LIMB_MASK;
        c8 -= c10 >> 12;
        c3 -= (c10 << 22) & LIMB_MASK;
        c4 -= c10 >> 4;
        c0 += (c10 << 4) & LIMB_MASK;
        c1 += c10 >> 22;
        c10 = 0;

        carryReduce0(r, c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19);
    }
    void carryReduce0(long[] r, long c0, long c1, long c2, long c3, long c4, long c5, long c6, long c7, long c8, long c9, long c10, long c11, long c12, long c13, long c14, long c15, long c16, long c17, long c18, long c19) {

        //carry from position 8
        long t0 = (c8 + CARRY_ADD) >> 26;
        c8 -= (t0 << 26);
        c9 += t0;
        //carry from position 9
        t0 = (c9 + CARRY_ADD) >> 26;
        c9 -= (t0 << 26);
        c10 += t0;
        //reduce from position 10
        c8 += (c10 << 20) & LIMB_MASK;
        c9 += c10 >> 6;
        c7 -= (c10 << 14) & LIMB_MASK;
        c8 -= c10 >> 12;
        c3 -= (c10 << 22) & LIMB_MASK;
        c4 -= c10 >> 4;
        c0 += (c10 << 4) & LIMB_MASK;
        c1 += c10 >> 22;
        //carry from position 0
        t0 = (c0 + CARRY_ADD) >> 26;
        c0 -= (t0 << 26);
        c1 += t0;
        //carry from position 1
        t0 = (c1 + CARRY_ADD) >> 26;
        c1 -= (t0 << 26);
        c2 += t0;
        //carry from position 2
        t0 = (c2 + CARRY_ADD) >> 26;
        c2 -= (t0 << 26);
        c3 += t0;
        //carry from position 3
        t0 = (c3 + CARRY_ADD) >> 26;
        c3 -= (t0 << 26);
        c4 += t0;
        //carry from position 4
        t0 = (c4 + CARRY_ADD) >> 26;
        c4 -= (t0 << 26);
        c5 += t0;
        //carry from position 5
        t0 = (c5 + CARRY_ADD) >> 26;
        c5 -= (t0 << 26);
        c6 += t0;
        //carry from position 6
        t0 = (c6 + CARRY_ADD) >> 26;
        c6 -= (t0 << 26);
        c7 += t0;
        //carry from position 7
        t0 = (c7 + CARRY_ADD) >> 26;
        c7 -= (t0 << 26);
        c8 += t0;
        //carry from position 8
        t0 = (c8 + CARRY_ADD) >> 26;
        c8 -= (t0 << 26);
        c9 += t0;

        r[0] = c0;
        r[1] = c1;
        r[2] = c2;
        r[3] = c3;
        r[4] = c4;
        r[5] = c5;
        r[6] = c6;
        r[7] = c7;
        r[8] = c8;
        r[9] = c9;
    }
    private void carryReduce(long[] r, long c0, long c1, long c2, long c3, long c4, long c5, long c6, long c7, long c8, long c9) {
        long c10 = 0;
        //carry from position 8
        long t0 = (c8 + CARRY_ADD) >> 26;
        c8 -= (t0 << 26);
        c9 += t0;
        //carry from position 9
        t0 = (c9 + CARRY_ADD) >> 26;
        c9 -= (t0 << 26);
        c10 += t0;
        //reduce from position 10
        c8 += (c10 << 20) & LIMB_MASK;
        c9 += c10 >> 6;
        c7 -= (c10 << 14) & LIMB_MASK;
        c8 -= c10 >> 12;
        c3 -= (c10 << 22) & LIMB_MASK;
        c4 -= c10 >> 4;
        c0 += (c10 << 4) & LIMB_MASK;
        c1 += c10 >> 22;
        //carry from position 0
        t0 = (c0 + CARRY_ADD) >> 26;
        c0 -= (t0 << 26);
        c1 += t0;
        //carry from position 1
        t0 = (c1 + CARRY_ADD) >> 26;
        c1 -= (t0 << 26);
        c2 += t0;
        //carry from position 2
        t0 = (c2 + CARRY_ADD) >> 26;
        c2 -= (t0 << 26);
        c3 += t0;
        //carry from position 3
        t0 = (c3 + CARRY_ADD) >> 26;
        c3 -= (t0 << 26);
        c4 += t0;
        //carry from position 4
        t0 = (c4 + CARRY_ADD) >> 26;
        c4 -= (t0 << 26);
        c5 += t0;
        //carry from position 5
        t0 = (c5 + CARRY_ADD) >> 26;
        c5 -= (t0 << 26);
        c6 += t0;
        //carry from position 6
        t0 = (c6 + CARRY_ADD) >> 26;
        c6 -= (t0 << 26);
        c7 += t0;
        //carry from position 7
        t0 = (c7 + CARRY_ADD) >> 26;
        c7 -= (t0 << 26);
        c8 += t0;
        //carry from position 8
        t0 = (c8 + CARRY_ADD) >> 26;
        c8 -= (t0 << 26);
        c9 += t0;

        r[0] = c0;
        r[1] = c1;
        r[2] = c2;
        r[3] = c3;
        r[4] = c4;
        r[5] = c5;
        r[6] = c6;
        r[7] = c7;
        r[8] = c8;
        r[9] = c9;
    }
    @Override
    protected void mult(long[] a, long[] b, long[] r) {
        long c0 = (a[0] * b[0]);
        long c1 = (a[0] * b[1]) + (a[1] * b[0]);
        long c2 = (a[0] * b[2]) + (a[1] * b[1]) + (a[2] * b[0]);
        long c3 = (a[0] * b[3]) + (a[1] * b[2]) + (a[2] * b[1]) + (a[3] * b[0]);
        long c4 = (a[0] * b[4]) + (a[1] * b[3]) + (a[2] * b[2]) + (a[3] * b[1]) + (a[4] * b[0]);
        long c5 = (a[0] * b[5]) + (a[1] * b[4]) + (a[2] * b[3]) + (a[3] * b[2]) + (a[4] * b[1]) + (a[5] * b[0]);
        long c6 = (a[0] * b[6]) + (a[1] * b[5]) + (a[2] * b[4]) + (a[3] * b[3]) + (a[4] * b[2]) + (a[5] * b[1]) + (a[6] * b[0]);
        long c7 = (a[0] * b[7]) + (a[1] * b[6]) + (a[2] * b[5]) + (a[3] * b[4]) + (a[4] * b[3]) + (a[5] * b[2]) + (a[6] * b[1]) + (a[7] * b[0]);
        long c8 = (a[0] * b[8]) + (a[1] * b[7]) + (a[2] * b[6]) + (a[3] * b[5]) + (a[4] * b[4]) + (a[5] * b[3]) + (a[6] * b[2]) + (a[7] * b[1]) + (a[8] * b[0]);
        long c9 = (a[0] * b[9]) + (a[1] * b[8]) + (a[2] * b[7]) + (a[3] * b[6]) + (a[4] * b[5]) + (a[5] * b[4]) + (a[6] * b[3]) + (a[7] * b[2]) + (a[8] * b[1]) + (a[9] * b[0]);
        long c10 = (a[1] * b[9]) + (a[2] * b[8]) + (a[3] * b[7]) + (a[4] * b[6]) + (a[5] * b[5]) + (a[6] * b[4]) + (a[7] * b[3]) + (a[8] * b[2]) + (a[9] * b[1]);
        long c11 = (a[2] * b[9]) + (a[3] * b[8]) + (a[4] * b[7]) + (a[5] * b[6]) + (a[6] * b[5]) + (a[7] * b[4]) + (a[8] * b[3]) + (a[9] * b[2]);
        long c12 = (a[3] * b[9]) + (a[4] * b[8]) + (a[5] * b[7]) + (a[6] * b[6]) + (a[7] * b[5]) + (a[8] * b[4]) + (a[9] * b[3]);
        long c13 = (a[4] * b[9]) + (a[5] * b[8]) + (a[6] * b[7]) + (a[7] * b[6]) + (a[8] * b[5]) + (a[9] * b[4]);
        long c14 = (a[5] * b[9]) + (a[6] * b[8]) + (a[7] * b[7]) + (a[8] * b[6]) + (a[9] * b[5]);
        long c15 = (a[6] * b[9]) + (a[7] * b[8]) + (a[8] * b[7]) + (a[9] * b[6]);
        long c16 = (a[7] * b[9]) + (a[8] * b[8]) + (a[9] * b[7]);
        long c17 = (a[8] * b[9]) + (a[9] * b[8]);
        long c18 = (a[9] * b[9]);

        carryReduce(r, c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18);
    }
    @Override
    protected void reduce(long[] a) {
        carryReduce(a, a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8], a[9]);
    }
    @Override
    protected void square(long[] a, long[] r) {
        long c0 = (a[0] * a[0]);
        long c1 = 2 * ((a[0] * a[1]));
        long c2 = 2 * ((a[0] * a[2])) + (a[1] * a[1]);
        long c3 = 2 * ((a[0] * a[3]) + (a[1] * a[2]));
        long c4 = 2 * ((a[0] * a[4]) + (a[1] * a[3])) + (a[2] * a[2]);
        long c5 = 2 * ((a[0] * a[5]) + (a[1] * a[4]) + (a[2] * a[3]));
        long c6 = 2 * ((a[0] * a[6]) + (a[1] * a[5]) + (a[2] * a[4])) + (a[3] * a[3]);
        long c7 = 2 * ((a[0] * a[7]) + (a[1] * a[6]) + (a[2] * a[5]) + (a[3] * a[4]));
        long c8 = 2 * ((a[0] * a[8]) + (a[1] * a[7]) + (a[2] * a[6]) + (a[3] * a[5])) + (a[4] * a[4]);
        long c9 = 2 * ((a[0] * a[9]) + (a[1] * a[8]) + (a[2] * a[7]) + (a[3] * a[6]) + (a[4] * a[5]));
        long c10 = 2 * ((a[1] * a[9]) + (a[2] * a[8]) + (a[3] * a[7]) + (a[4] * a[6])) + (a[5] * a[5]);
        long c11 = 2 * ((a[2] * a[9]) + (a[3] * a[8]) + (a[4] * a[7]) + (a[5] * a[6]));
        long c12 = 2 * ((a[3] * a[9]) + (a[4] * a[8]) + (a[5] * a[7])) + (a[6] * a[6]);
        long c13 = 2 * ((a[4] * a[9]) + (a[5] * a[8]) + (a[6] * a[7]));
        long c14 = 2 * ((a[5] * a[9]) + (a[6] * a[8])) + (a[7] * a[7]);
        long c15 = 2 * ((a[6] * a[9]) + (a[7] * a[8]));
        long c16 = 2 * ((a[7] * a[9])) + (a[8] * a[8]);
        long c17 = 2 * ((a[8] * a[9]));
        long c18 = (a[9] * a[9]);

        carryReduce(r, c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18);
    }
}

