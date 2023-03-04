/**
  * @파일명 : SHAUtilTest.java
  * @작성일 : 2023. 3. 4.
  * @작성자 : 김영철
  */
package com.spring.mvc.util;

import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;

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
 * @파일명 : SHAUtilTest.java
 * @작성일 : 2023. 3. 4.
 * @작성자 : 김영철
 */
public class SHAUtilTest {

    @InjectMocks
    private SHAUtil shaUtil;

    /**
     * @메소드타입 : SHAUtilTest
     * @메소드명 : setUpBeforeClass
     * @return : void
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @메소드타입 : SHAUtilTest
     * @메소드명 : tearDownAfterClass
     * @return : void
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @메소드타입 : SHAUtilTest
     * @메소드명 : setUp
     * @return : void
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * @메소드타입 : SHAUtilTest
     * @메소드명 : tearDown
     * @return : void
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.spring.mvc.util.SHAUtil#encrypt(java.lang.String, java.lang.String)}.
     * @throws NoSuchAlgorithmException 
     */
    @Test
    public final void testEncrypt() throws NoSuchAlgorithmException {
        String algorithm = "SHA-256";
        String input = "Hello World";
        String encrypt = shaUtil.encrypt(algorithm, input);
        assertNotNull(encrypt);
    }
}
