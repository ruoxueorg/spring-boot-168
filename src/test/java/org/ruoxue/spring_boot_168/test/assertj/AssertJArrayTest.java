package org.ruoxue.spring_boot_168.test.assertj;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AssertJArrayTest {

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
		
		assertThat(array)
		.contains("coffee", atIndex(0))
		.contains("juice", atIndex(1))
		.contains("tea", atIndex(2));
	}	

	@Test
	public void extracting() {
		Drink coffee = new Drink("coffee", 4, 2);
		Drink juice = new Drink("juice", 5, 2);
		Drink tea = new Drink("tea", 6, 2);
		Drink[] array = new Drink[] { coffee, juice, tea };

		assertThat(array).extracting(Drink::getName)
		.containsExactly("coffee", "juice", "tea");
	}
	
	@Test
	public void extractingTuple() {
		Drink coffee = new Drink("coffee", 4, 2);
		Drink juice = new Drink("juice", 5, 2);
		Drink tea = new Drink("tea", 6, 2);
		Drink[] array = new Drink[] { coffee, juice, tea };

		assertThat(array).extracting("name", "quantity")
		.containsExactly(
				tuple("coffee", 4d), 
				tuple("juice", 5d),
				tuple("tea", 6d));

		assertThat(array)
		.extracting(e -> e.getName(), Drink::getQuantity)
		.containsExactly(
				tuple("coffee", 4d),
				tuple("juice", 5d), 
				tuple("tea", 6d));
	}

	@Test
	public void anyMatch() {
		Drink coffee = new Drink("coffee", 4, 2);
		Drink juice = new Drink("juice", 5, 2);
		Drink tea = new Drink("tea", 6, 2);
		Drink[] array = new Drink[] { coffee, juice, tea };

		assertThat(array)
		.allMatch(e -> e.getType() == 2)
		.anyMatch(e -> e.getName().equals("coffee"))
		.noneMatch(e -> e.getType() == 0)
		.doesNotContainNull();
	}

	@Test
	public void filteredOn() {
		int expectedSize = 3;
		Drink coffee = new Drink("coffee", 4, 2);
		Drink juice = new Drink("juice", 5, 2);
		Drink tea = new Drink("tea", 6, 2);
		Drink[] array = new Drink[] { coffee, juice, tea };

		assertThat(array)
		.filteredOn(Drink::getType, 2)
		.containsOnly(coffee, juice, tea)
		.hasSize(expectedSize);
	}
	
	@Test
	public void anySatisfy() {
		Drink coffee = new Drink("coffee", 4, 2);
		Drink juice = new Drink("juice", 5, 2);
		Drink tea = new Drink("tea", 6, 2);
		Drink[] array = new Drink[] { coffee, juice, tea };

		assertThat(array)
		.allSatisfy(e -> {
			 assertThat(e.getType()).isEqualTo(2);
			 assertThat(e.getName()).isNotNull();
		});
		
		assertThat(array)
		.anySatisfy(e -> {
			 assertThat(e.getType()).isEqualTo(2);
			 assertThat(e.getName()).isEqualTo("juice");
		});
		
		assertThat(array)
		.noneSatisfy(e -> assertThat(e.getType()).isEqualTo(0));
	}		
	
	@Test
	public void isEqualTo() {
		Drink coffee = new Drink("coffee", 4, 2);
		Drink juice = new Drink("juice", 5, 2);
		Drink tea = new Drink("tea", 6, 2);
		Drink[] array = new Drink[] { coffee, juice, tea };
		Drink[] array2 = new Drink[] { coffee, juice, tea };

		assertThat(array)
		.isEqualTo(array2);
	}	
}
