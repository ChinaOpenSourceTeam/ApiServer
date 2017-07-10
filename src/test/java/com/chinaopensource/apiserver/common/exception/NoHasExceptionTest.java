package com.chinaopensource.apiserver.common.exception;

import org.junit.Assert;
import org.junit.Test;

public class NoHasExceptionTest {

	@Test(expected=NoHasException.class)
	public void test1() throws NoHasException {
		throw new NoHasException("test");
	}
	
	@Test
	public void test2() {
		try {
			throw new NoHasException("test");
		} catch (NoHasException e) {
			Assert.assertEquals("test的值不存在",e.getMessage());
		}
	}

}
