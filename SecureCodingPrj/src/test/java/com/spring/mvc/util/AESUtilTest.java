/**
  * @파일명 : AESUtilTest.java
  * @작성일 : 2023. 3. 3.
  * @작성자 : 김영철
  */
package com.spring.mvc.util;

import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * @프로젝트명 : AIBK-Security
 * @패키지명 : com.spring.mvc.util
 * @파일명 : AESUtilTest.java
 * @작성일 : 2023. 3. 3.
 * @작성자 : 김영철
 */
public class AESUtilTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LogManager.getLogger(AESUtilTest.class);

    @InjectMocks
    private AESUtil aesUtil;

    /**
     * @메소드타입 : AESUtilTest
     * @메소드명 : setUpBeforeClass
     * @return : void
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @메소드타입 : AESUtilTest
     * @메소드명 : tearDownAfterClass
     * @return : void
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @메소드타입 : AESUtilTest
     * @메소드명 : setUp
     * @return : void
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * @메소드타입 : AESUtilTest
     * @메소드명 : tearDown
     * @return : void
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.spring.mvc.util.AESUtil#noPadding(java.lang.String)}.
     */
    @Test
    public final void testNoPadding() {

        String sample = "1234567890";
        byte[] b = new byte[1];
        b[0] = 0x03;
        String pad = new String(b);
        String input = sample + pad;
        String result = aesUtil.noPadding(input);
        assertEquals("12345678", result);
    }

    /**
     * @메소드타입 : AESUtilTest
     * @메소드명 : testAES
     * @return : void
     */
    @Test
    public final void testAES() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        SecretKey secretKey = aesUtil.generateKey("AES", 128);
        String standardCipher = "AES/CBC/PKCS5Padding";
        String plainText = "Hello World";
        byte[] base64Text = Base64.getEncoder().encode(plainText.getBytes());
        String base64strText = new String(base64Text, StandardCharsets.UTF_8);
        String base64Encrypt = aesUtil.encrypt(standardCipher, secretKey, base64strText);

        byte[] bytesIv = Base64.getEncoder().encode(aesUtil.getIv());
        String base64strIv = new String(bytesIv, StandardCharsets.UTF_8);
        String decryptText = aesUtil.decrypt(standardCipher, secretKey, base64strIv, base64Encrypt);
        assertEquals(plainText, decryptText);
    }
}
