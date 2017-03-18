package com.chinaopensource.apiserver.common.constant;

import org.junit.Assert;
import org.junit.Test;

public class ErrorMessageTest {

	@Test
	public void test() {
		Assert.assertEquals(0, ErrorCode.OK);
		Assert.assertEquals("成功", ErrorMessage.getMessage(ErrorCode.OK));
	}

}
