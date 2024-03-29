package org.ruoxue.spring_boot_168.test.assertj.predicate;

import static org.assertj.core.api.Assertions.*;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class AssertJPredicateAssertionsTest {

	@Test
	public void accepts() {
		Predicate<String> lengthGreaterThan = s -> s.length() > 3;
		System.out.println(lengthGreaterThan);
		assertThat(lengthGreaterThan).accepts("AssertJ", "JUnit");

		Predicate<Integer> greaterThan = i -> i > 10;
		System.out.println(greaterThan);
		assertThat(greaterThan).accepts(155, 151);
	}

	@Test
	public void acceptsWithNegate() {
		Predicate<String> lengthGreaterThan = s -> s.length() > 3;
		System.out.println(lengthGreaterThan.negate());
		assertThat(lengthGreaterThan.negate()).accepts("155", "151");

		Predicate<Integer> greaterThan = i -> i > 10;
		System.out.println(greaterThan.negate());
		assertThat(greaterThan.negate()).accepts(1, 2);
	}

	@Test
	public void acceptsWithAnd() {
		Predicate<String> startsWith = s -> s.startsWith("A");
		Predicate<String> endsWith = s -> s.endsWith("J");
		Predicate<String> predicate = startsWith.and(endsWith);
		System.out.println(predicate);
		assertThat(predicate).accepts("AssertJ", "AJ");

		Predicate<Integer> greaterThan = i -> i > 3;
		Predicate<Integer> lessThan = i -> i < 6;
		Predicate<Integer> intPredicate = greaterThan.and(lessThan);
		System.out.println(intPredicate);
		assertThat(intPredicate).accepts(4, 5);
	}

	@Test
	public void acceptsWithOr() {
		Predicate<String> startsWith = s -> s.startsWith("A");
		Predicate<String> endsWith = s -> s.endsWith("t");
		Predicate<String> predicate = startsWith.or(endsWith);
		System.out.println(predicate);
		assertThat(predicate).accepts("AssertJ", "JUnit");

		Predicate<Integer> greaterThan = i -> i > 3;
		Predicate<Integer> lessThan = i -> i < 6;
		Predicate<Integer> intPredicate = greaterThan.or(lessThan);
		System.out.println(intPredicate);
		assertThat(intPredicate).accepts(7, 2);
	}

	@Test
	public void acceptsWithChaining() {
		Predicate<String> nonNull = Objects::nonNull;
		Predicate<String> startsWith = s -> s.startsWith("A");
		Predicate<String> contains = Pattern.compile("\\wU").asPredicate();
		Predicate<String> chaining = nonNull.and(startsWith).or(contains);
		System.out.println(chaining);
		assertThat(chaining).accepts("AssertJ", "JUnit");

		Predicate<Integer> intNonNull = Objects::nonNull;
		Predicate<Integer> greaterThan = i -> i > 3;
		Predicate<Integer> lessThan = i -> i < 6;
		Predicate<Integer> intChaining = intNonNull.and(greaterThan).or(lessThan);
		System.out.println(intChaining);
		assertThat(intChaining).accepts(7, 2);
	}
}
