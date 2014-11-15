package jstore.test;

import jstore.StringSet;

import org.junit.Assert;
import org.junit.Test;

public class TrieTest {

	@Test
	public void test() {
		String[] data = { "a", "aa", "ab", "abc" };		
		StringSet set = StringSet.create(data);
		String[] actual = set.getStrings();
		
		Assert.assertArrayEquals("oops", data, actual);
	}

}
