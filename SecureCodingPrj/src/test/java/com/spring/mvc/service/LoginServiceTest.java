/**
  * @파일명 : LoginServiceTest.java
  * @작성일 : 2023. 2. 28.
  * @작성자 : 김영철
  */
package com.spring.mvc.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.SecretKey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.spring.mvc.dao.UserDao;
import com.spring.mvc.dto.UserDto;
import com.spring.mvc.util.AESUtil;
import com.spring.mvc.util.RSAUtil;
import com.spring.mvc.vo.KeyPairVo;

/**
 * @프로젝트명 : AIBK-Security
 * @패키지명 : com.spring.mvc.service
 * @파일명 : LoginServiceTest.java
 * @작성일 : 2023. 2. 28.
 * @작성자 : 김영철
 */
public class LoginServiceTest {

    private static final Logger LOG = LogManager.getLogger(LoginServiceTest.class);

    @Mock
    private UserDao userDao;

    @InjectMocks
    private LoginServiceImpl loginService;

    /**
     * @메소드타입 : LoginServiceTest
     * @메소드명 : setUp
     * @return : void
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test method for
     * {@link com.spring.mvc.service.LoginServiceImpl#loginConfirm(PrivateKey, com.spring.mvc.dto.UserDto)}.
     * 
     * @throws Exception
     */
    @Test
    public final void testUserLogin() throws Exception {
        /* Given */

        /* When */
        KeyPairVo keyPairVo = loginService.userLogin();
        /* Then */
        assertNotNull(keyPairVo);
    }

    @SuppressWarnings({ "unchecked" })
    @Test
    public final void testLoginConfirm() throws Exception {

        /* Given */
        RSAUtil rsaUtil = new RSAUtil();
        AESUtil aesUtil = new AESUtil();

        String algorithm = "RSA";
        int keysize = 2048;
        KeyPair keyPair = rsaUtil.generatorKeyPair(algorithm, keysize);

        PublicKey publicKey = (PublicKey) keyPair.getPublic();
        PrivateKey privateKey = (PrivateKey) keyPair.getPrivate();

        SecretKey secretKey = aesUtil.generateKey("AES", 128);
        byte[] bytesSecKey = secretKey.getEncoded();
        byte[] base64SecKey = Base64.getEncoder().encode(bytesSecKey);
        String base64strSecKey = new String(base64SecKey, StandardCharsets.UTF_8);

        String standardCipher1 = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";
        String rsaEncInput = rsaUtil.encrypt(standardCipher1, publicKey, base64strSecKey);

        String standardCipher = "AES/CBC/PKCS5Padding";
        String plainText = "hong-password";
        byte[] base64Text = Base64.getEncoder().encode(plainText.getBytes());
        String base64strText = new String(base64Text, StandardCharsets.UTF_8);
        String base64Encrypt = aesUtil.encrypt(standardCipher, secretKey, base64strText);

        byte[] bytesIv = Base64.getEncoder().encode(aesUtil.getIv());
        String base64strIv = new String(bytesIv, StandardCharsets.UTF_8);

        String jsonString = "{\r\n" + "\"v\":\"hybrid-crypto-js_0.1.2\",\r\n"
                + "\"iv\": \"CmtyaZTyzoAp1mTNUTztic0v1...\",\r\n" + "\"keys\": {\r\n"
                + "\"d3:48:6a:e9:13...\":\"t9eds3...\"\r\n" + "},\r\n" + "\"cipher\":\"+iwVFsC2dECBQvwcm9DND...\"\r\n"
                + "\"signature\":\"sdL93kfdm12feds3C2...\"\r\n" + "}";

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonString);
        LOG.debug("jsonObj.toString() ... {}", jsonObj.toString());

        JSONObject keys = new JSONObject();
        jsonObj.replace("iv", base64strIv);
        jsonObj.replace("cipher", base64Encrypt);
        keys = (JSONObject) jsonObj.get("keys");
        keys.replace("d3:48:6a:e9:13...", rsaEncInput);
        LOG.debug("jsonObj.toString() ... {}", jsonObj.toString());

        byte[] bytes = Base64.getEncoder().encode(jsonObj.toString().getBytes(StandardCharsets.UTF_8));
        String strpasswd = new String(bytes, StandardCharsets.UTF_8);

        UserDto userDto = new UserDto();
        userDto.setUserid("hong-gi-dong2");
        userDto.setUserpw(strpasswd);

        /* When */
        when(userDao.userLogin(userDto)).thenReturn(new UserDto());
        UserDto userDtoOut = loginService.loginConfirm(privateKey, userDto);

        /* Then */
        assertNotNull(userDtoOut);
    }
}
