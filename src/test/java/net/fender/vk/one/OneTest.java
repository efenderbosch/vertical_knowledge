package net.fender.vk.one;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class OneTest {

	@Test
	public void test() {
		One one = new One();
		int max = one.run("1234567890");
		assertThat(max, is(257));
	}
}
