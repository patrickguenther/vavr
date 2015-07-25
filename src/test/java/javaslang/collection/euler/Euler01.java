/*     / \____  _    ______   _____ / \____   ____  _____
 *    /  \__  \/ \  / \__  \ /  __//  \__  \ /    \/ __  \   Javaslang
 *  _/  // _\  \  \/  / _\  \\_  \/  // _\  \  /\  \__/  /   Copyright 2014-2015 Daniel Dietrich
 * /___/ \_____/\____/\_____/____/\___\_____/_/  \_/____/    Licensed under the Apache License, Version 2.0
 */
package javaslang.collection.euler;

import javaslang.collection.List;
import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class Euler01 {

    /**
     * <strong>Problem 1: Multiples of 3 and 5</strong>
     * <p>If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
     * The sum of these multiples is 23.</p>
     * <p>Find the sum of all the multiples of 3 or 5 below 1000.</p>
     * <p>See also <a href="https://projecteuler.net/problem=1">projecteuler.net problem 1</a>.</p>
     */
    @Test
    public void shouldSolveProblem1() {
        assertThat(sumOfMultiplesOf3and5Below(10)).isEqualTo(23);
        assertThat(sumOfMultiplesOf3and5Below(1000)).isEqualTo(233168);
    }

    private static int sumOfMultiplesOf3and5Below(int limit) {
        return List.range(0, limit).filter(Euler01::isMultipleOf3or5).sum().intValue();
    }

    private static boolean isMultipleOf3or5(int num) {
        return num != 0 && (num % 3 == 0 || num % 5 == 0);
    }
}