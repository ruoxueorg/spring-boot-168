package org.ruoxue.spring_boot_168.test.assertj.exception;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AssertJExceptionWithExamplesTest {

	public int divide(int value1, int value2) {
		return value1 / value2;
	}

	@Test
	public void isThrownBy() {
		assertThatExceptionOfType(ArithmeticException.class).isThrownBy(() -> divide(1, 0))
				.withMessageContaining("zero").withMessage("/ by zero");
	}

	@Test
	public void withCauseExactlyInstanceOf() {
		assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
			try {
				throw new IOException();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}).withCauseExactlyInstanceOf(IOException.class).withStackTraceContaining("IOException");
	}

	@Test
	public void thrown() {
		Throwable thrown = catchThrowable(() -> {
			List<Integer> list = Arrays.asList(1, 2);
			list.get(2);
		});
		assertThat(thrown).isInstanceOf(IndexOutOfBoundsException.class).hasMessageContaining("2");
	}
}
