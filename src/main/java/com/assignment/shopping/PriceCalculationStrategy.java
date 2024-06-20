package com.assignment.shopping;

import java.util.function.Function;

@FunctionalInterface
interface PriceCalculationStrategy extends Function<Integer, Integer> {
}
