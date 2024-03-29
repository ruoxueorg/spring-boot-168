package org.ruoxue.spring_boot_168.test.assertj.string;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import org.junit.jupiter.api.Test;

public class TestingAssertJStringTest {

	@Test
	// A=65, a=97
	public void isLessThan() {
		String value = "AssertJ 155";
		System.out.println(value);
		assertThat(value).isLessThan("as").isLessThan("bs");
		assertThat(value).isLessThan("aS").isLessThan("At");
	}

	@Test
	// A=65, a=97
	public void isLessThanOrEqualTo() {
		String value = "AssertJ 155";
		System.out.println(value);
		assertThat(value).isLessThanOrEqualTo("AssertJ 155").isLessThanOrEqualTo("bs");
		assertThat(value).isLessThanOrEqualTo("assertj 155").isLessThanOrEqualTo("At");
	}

	@Test
	// A=65, a=97
	public void isGreaterThan() {
		String value = "assertj 155";
		System.out.println(value);
		assertThat(value).isGreaterThan("As").isGreaterThan("Bs");
		assertThat(value).isGreaterThan("aS").isGreaterThan("aT");
	}

	@Test
	// A=65, a=97
	public void isGreaterThanOrEqualTo() {
		String value = "assertj 155";
		System.out.println(value);
		assertThat(value).isGreaterThanOrEqualTo("AssertJ 155").isGreaterThanOrEqualTo("Bs");
		assertThat(value).isGreaterThanOrEqualTo("assertj 155").isGreaterThanOrEqualTo("aT");
	}

	@Test
	public void usingDefaultComparator() {
		String value = "AssertJ 155";
		System.out.println(value);
		assertThat(value).usingDefaultComparator().contains("AssertJ", "155").doesNotContain("rtJx");
		value = "AssertJ";
		assertThat(value).usingDefaultComparator().startsWith("Ass").endsWith("rtJ");
	}

	@Test
	public void usingComparator() {
		String value = "AssertJ 155";
		System.out.println(value);
		Comparator<String> ignoreCase = (s1, s2) -> s1.toLowerCase().compareTo(s2.toLowerCase());
		assertThat(value).usingComparator(ignoreCase).contains("ASSERTJ", "155").doesNotContain("RTJX");
		value = "AssertJ";
		assertThat(value).usingComparator(ignoreCase).startsWith("ASS").endsWith("rtJ");
	}

	@Test
	public void usingComparatorWithDescription() {
		String value = "AssertJ 155";
		System.out.println(value);
		Comparator<String> ignoreCase = (s1, s2) -> s1.toLowerCase().compareTo(s2.toLowerCase());
		assertThat(value).usingComparator(ignoreCase, "ignoreCase").contains("ASSERTJ", "155")
				.doesNotContain("RTJX");
		value = "AssertJ";
		assertThat(value).usingComparator(ignoreCase, "ignoreCase").startsWith("ASS").endsWith("rtJ");
	}
}
