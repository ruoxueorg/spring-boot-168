package org.ruoxue.spring_boot_168.test.assertj;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.ruoxue.spring_boot_168.test.assertj.ArraysWithAssertJTest.Drink;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AssertJArrayTest {

	public AssertJArrayTest() {

	}

	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class Drink {
		private String name;
		private double quantity;
		private int type;

		public Drink(String name, double quantity, int type) {
			this.name = name;
			this.quantity = quantity;
			this.type = type;
		}
	}

	@Test
	public void hasSize() {
		int expectedSize = 3;
		String[] array = new String[] { "coffee", "juice", "tea" };

		assertThat(array).hasSize(expectedSize);
	}

	@Test
	public void contains() {
		String[] array = new String[] { "coffee", "juice", "tea" };

		assertThat(array).contains("juice", "coffee");
	}

	@Test
	public void doesNotContain() {
		String[] array = new String[] { "coffee", "juice", "tea" };

		assertThat(array).doesNotContain("duck");
	}

	@Test
	public void containsOnly() {
		String[] array = new String[] { "coffee", "juice", "tea" };

		assertThat(array).containsOnly("juice", "tea", "coffee");
	}

	@Test
	public void containsExactly() {
		String[] array = new String[] { "coffee", "juice", "tea" };

		assertThat(array).containsExactly("coffee", "juice", "tea");
	}

	@Test
	public void containsAnyOf() {
		String[] array = new String[] { "coffee", "juice", "tea" };

		assertThat(array).containsAnyOf("duck", "juice", "egg");
	}

	@Test
	public void containsAtIndex() {
		String[] array = new String[] { "coffee", "juice", "tea" };

		assertThat(array).contains("coffee", atIndex(0)).contains("juice", atIndex(1)).contains("tea", atIndex(2));
	}

	@Test
	public void isEqualTo() {
		Drink coffee = new Drink("coffee", 4, 2);
		Drink juice = new Drink("juice", 5, 2);
		Drink tea = new Drink("tea", 6, 2);
		Drink[] array = new Drink[] { coffee, juice, tea };
		Drink[] array2 = new Drink[] { coffee, juice, tea };

		assertThat(array).isEqualTo(array2);
	}
}
