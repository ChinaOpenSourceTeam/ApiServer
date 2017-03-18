package com.chinaopensource.apiserver.common.util.encryption;

import org.junit.Assert;
import org.junit.Test;

public class EncryptionUtilTest {

	@Test
	public void test() {
		String encrytion =EncryptionUtil.getHash("李其伟", "MD5");
		Assert.assertEquals("A032A69D20144E3700A3803AA479FB84", encrytion);
		Assert.assertEquals(32, encrytion.length());
	}

}
