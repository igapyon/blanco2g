package blanco.validate;

import junit.framework.Assert;

import org.junit.Test;

public class BlancoValidateRuntimeUtilTest {
	@Test
	public void testTrim() {
		Assert.assertEquals(null, BlancoValidateRuntimeUtil.trim(null));
		Assert.assertEquals("", BlancoValidateRuntimeUtil.trim(""));
		Assert.assertEquals("", BlancoValidateRuntimeUtil.trim(" "));
		Assert.assertEquals("", BlancoValidateRuntimeUtil.trim("　"));
		Assert.assertEquals("あ", BlancoValidateRuntimeUtil.trim(" あ"));
		Assert.assertEquals("あ", BlancoValidateRuntimeUtil.trim("　あ"));
		Assert.assertEquals("あ", BlancoValidateRuntimeUtil.trim("あ "));
		Assert.assertEquals("あ", BlancoValidateRuntimeUtil.trim("あ　"));
		Assert.assertEquals("あ", BlancoValidateRuntimeUtil.trim("　あ　"));
		Assert.assertEquals("A", BlancoValidateRuntimeUtil.trim("A　"));
		Assert.assertEquals("あ　い", BlancoValidateRuntimeUtil.trim("　あ　い　"));
		Assert.assertEquals("全角半角の空白混じり", "あ 　い",
				BlancoValidateRuntimeUtil.trim(" 　あ 　い　 "));
	}

	@Test
	public void testIsTrimChar() {
		Assert.assertEquals(false, BlancoValidateRuntimeUtil.isTrimChar('A'));
		Assert.assertEquals(true, BlancoValidateRuntimeUtil.isTrimChar(' '));
		Assert.assertEquals(true, BlancoValidateRuntimeUtil.isTrimChar('\t'));
		Assert.assertEquals(true, BlancoValidateRuntimeUtil.isTrimChar('\n'));
		Assert.assertEquals(true, BlancoValidateRuntimeUtil.isTrimChar('\r'));
		Assert.assertEquals(true, BlancoValidateRuntimeUtil.isTrimChar('　'));
		Assert.assertEquals(false, BlancoValidateRuntimeUtil.isTrimChar('あ'));
	}
}
