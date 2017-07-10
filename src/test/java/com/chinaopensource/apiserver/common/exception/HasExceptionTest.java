package com.chinaopensource.apiserver.common.exception;

import org.junit.Assert;
import org.junit.Test;

public class HasExceptionTest {

	@Test(expected = HasException.class)
	public void test1() throws HasException {
		throw new HasException("test");
	}
	@Test
	public void test2() {
		try {
			throw new HasException("test");
		} catch (HasException e) {
			Assert.assertEquals("test的值已经存在",e.getMessage());
		}
	}

}
