package com.spring.mvc.controller;

import static org.junit.Assert.assertEquals;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.spring.mvc.service.LoginService;

/**
 * @프로젝트명 : AIBK-Security
 * @패키지명 : com.spring.mvc.controller
 * @파일명 : LoginControllerTest.java
 * @작성일 : 2023. 2. 27.
 * @작성자 : 김영철
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@WebAppConfiguration
public class LoginControllerTest {

    @Mock
    private HttpSession mockSession;

    @Mock
    private LoginService mockLoginService;

    @InjectMocks
    private LoginController mockLoginController;

    /**
     * @메소드타입 : LoginControllerTest
     */
    public LoginControllerTest() {
        super();
    }

    /**
     * @메소드타입 : LoginControllerTest
     * @메소드명 : setUp
     * @return : void
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * @메소드타입 : LoginControllerTest
     * @메소드명 : testUserLogin
     * @return : void
     * @throws Exception
     */
//    @Test
//    public final void testUserLogin() throws Exception {
//
//        // 로그인 페이지 호출 및 RSA 키 생성, 서비스 호출 없음
//        ModelAndView mav = mockLoginController.userLogin(mockSession);
//        assertEquals("user_login", mav.getViewName());
//        assertNotNull(mav.getModel().get("base64PublicKey"));
//
//    }

    /**
     * @메소드타입 : LoginControllerTest
     * @메소드명 : testUserLogout
     * @return : void
     * @throws Exception
     */
    @Test
    public final void testUserLogout() throws Exception {

        String view;
        view = mockLoginController.userLogout(mockSession);
        assertEquals("redirect:user_login", view);

    }

    /**
     * @메소드타입 : LoginControllerTest
     * @메소드명 : testLoginConfirm_account_valid
     * @return : void
     * @throws Exception 계정 정보가 일치하는 경우.
     */
//    @Test
//    public final void testLoginConfirm_account_valid() throws Exception {
//
//        final RSAUtil rsaUtil = new RSAUtil();
//        final KeyPair keyPair = rsaUtil.genRSAKeyPair();
//
//        final PublicKey publicKey = keyPair.getPublic();
//        final PrivateKey privateKey = keyPair.getPrivate();
//
//        final String encodeUserPw = rsaUtil.encryptRSA("hong-password", publicKey);
//
//        UserDto userDto = new UserDto();
//        userDto.setUserid("hong-gi-dong2");
//        userDto.setUserpw(encodeUserPw);
//
//        ModelAndView mav;
//
//        when(mockSession.getAttribute("privateKey")).thenReturn(privateKey);
//        when(mockLoginService.userLogin(userDto)).thenReturn(userDto);
//        mav = mockLoginController.loginConfirm(mockSession, userDto);
//
//        assertEquals("login_success", mav.getViewName());
//        assertNotNull(mav.getModel().get("LoginSuccess"));
//    }

    /**
     * @메소드타입 : LoginControllerTest
     * @메소드명 : testLoginConfirm_account_invalid
     * @return : void
     * @throws Exception 계정 정보가 일치하지 않는 경우.
     */
//    @Test
//    public final void testLoginConfirm_account_invalid() throws Exception {
//
//        final RSAUtil rsaUtil = new RSAUtil();
//        final KeyPair keyPair = rsaUtil.genRSAKeyPair();
//
//        final PublicKey publicKey = keyPair.getPublic();
//        final PrivateKey privateKey = keyPair.getPrivate();
//
//        final String encodeUserPw = rsaUtil.encryptRSA("hong-password", publicKey);
//
//        UserDto userDto = new UserDto();
//        userDto.setUserid("hong-gi-dong2");
//        userDto.setUserpw(encodeUserPw);
//
//        ModelAndView mav;
//
//        UserDto userDtoOut = null;
//        when(mockSession.getAttribute("privateKey")).thenReturn(privateKey);
//        when(mockLoginService.userLogin(userDto)).thenReturn(userDtoOut);
//        mav = mockLoginController.loginConfirm(mockSession, userDto);
//
//        assertEquals("redirect:user_login", mav.getViewName());
//        assertNull(mav.getModel().get("LoginSuccess"));
//    }

    /**
     * @메소드타입 : LoginControllerTest
     * @메소드명 : testLoginConfirm_pattern_invalid
     * @return : void
     * @throws Exception 입력 패턴이 영문, 숫자, 지정된 특수 기호 외에 입력되었을 경우 cross site scripting
     *                   대처.
     */
//    @Test
//    public final void testLoginConfirm_pattern_invalid() throws Exception {
//
//        final RSAUtil rsaUtil = new RSAUtil();
//        final KeyPair keyPair = rsaUtil.genRSAKeyPair();
//
//        final PublicKey publicKey = keyPair.getPublic();
//        final PrivateKey privateKey = keyPair.getPrivate();
//
//        final String encodeUserPw = rsaUtil.encryptRSA("hong-password", publicKey);
//
//        UserDto userDto = new UserDto();
//        userDto.setUserid("<script>alert('Hello World')</script>");
//        userDto.setUserpw(encodeUserPw);
//
//        ModelAndView mav;
//
//        when(mockSession.getAttribute("privateKey")).thenReturn(privateKey);
//        when(mockLoginService.userLogin(userDto)).thenReturn(userDto);
//        mav = mockLoginController.loginConfirm(mockSession, userDto);
//
//        assertEquals("redirect:user_login", mav.getViewName());
//        assertNull(mav.getModel().get("LoginSuccess"));
//    }
}
