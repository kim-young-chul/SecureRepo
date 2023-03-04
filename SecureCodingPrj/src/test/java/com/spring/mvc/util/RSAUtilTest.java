/**
  * @파일명 : RSAUtilTest.java
  * @작성일 : 2023. 3. 3.
  * @작성자 : 김영철
  */
package com.spring.mvc.util;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
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
 * @파일명 : RSAUtilTest.java
 * @작성일 : 2023. 3. 3.
 * @작성자 : 김영철
 */
public class RSAUtilTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LogManager.getLogger(RSAUtilTest.class);

    @InjectMocks
    private RSAUtil rsaUtil;

    /**
     * @메소드타입 : RSAUtilTest
     * @메소드명 : setUpBeforeClass
     * @return : void
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @메소드타입 : RSAUtilTest
     * @메소드명 : tearDownAfterClass
     * @return : void
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @메소드타입 : RSAUtilTest
     * @메소드명 : setUp
     * @return : void
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * @메소드타입 : RSAUtilTest
     * @메소드명 : tearDown
     * @return : void
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.spring.mvc.util.RSAUtil#generatorKeyPair(java.lang.String, int)}.
     * 
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    @SuppressWarnings("unused")
    @Test
    public final void testGeneratorKeyPair() throws NoSuchAlgorithmException, IOException, InvalidKeyException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        String algorithm = "RSA";
        int keysize = 2048;
        KeyPair keyPair = rsaUtil.generatorKeyPair(algorithm, keysize);

        PublicKey publicKey = (PublicKey) keyPair.getPublic();
        PrivateKey privateKey = (PrivateKey) keyPair.getPrivate();
        String pem = rsaUtil.convertKeytoPEM(publicKey);

        String standardCipher = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";
        String input = "Hello World";
        byte[] bytesInput = input.getBytes(StandardCharsets.UTF_8);
        byte[] base64bytesInput = Base64.getEncoder().encode(bytesInput);
        String base64StrInput = new String(base64bytesInput, StandardCharsets.UTF_8);
        String rsaEncInput = rsaUtil.encrypt(standardCipher, publicKey, base64StrInput);

        String algorithm2 = "AES";
        SecretKey secretKey = rsaUtil.getSecretKey(standardCipher, privateKey, rsaEncInput, algorithm2);
        assertNotNull(secretKey);
    }
}
