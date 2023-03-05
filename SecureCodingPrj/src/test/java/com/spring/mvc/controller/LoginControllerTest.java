package com.spring.mvc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.dto.UserDto;
import com.spring.mvc.service.LoginService;
import com.spring.mvc.vo.KeyPairVo;

public class LoginControllerTest {

    @Mock
    private HttpSession session;

    @Mock
    private LoginService loginService;

    @Mock
    private KeyPairVo keyPairVo;

    @Mock
    private PrivateKey privateKey;

    @Mock
    private UserDto userDto;

    @InjectMocks
    private LoginController loginController;

    private static final String REUSRLOGIN = "redirect:user_login";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public final void testUserLogin() throws NoSuchAlgorithmException, IOException {
        when(loginService.userLogin()).thenReturn(keyPairVo);
        when(keyPairVo.getPrivateKey()).thenReturn(privateKey);
        when(keyPairVo.getPemPublicKey()).thenReturn("pemPublickKey");
        ModelAndView mav = loginController.userLogin(session);

        assertEquals("user_login", mav.getViewName());
    }

    @Test
    public final void testUserLogout() {
        String view = loginController.userLogout(session);
        verify(session, times(1)).invalidate();
        assertEquals(REUSRLOGIN, view);
    }

    @Test
    public final void testLoginConfirm() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, ParseException {

        UserDto userDtoOut = new UserDto();

        when(session.getAttribute("privateKey")).thenReturn(privateKey);
        when(loginService.loginConfirm(privateKey, userDto)).thenReturn(null);
        ModelAndView mav = loginController.loginConfirm(session, userDto);
        assertEquals(REUSRLOGIN, mav.getViewName());

        when(loginService.loginConfirm(privateKey, userDto)).thenReturn(userDtoOut);
        mav = loginController.loginConfirm(session, userDto);
        assertEquals("login_success", mav.getViewName());
        assertNotNull("LoginSuccess", mav.getModelMap().get("LoginSuccess"));
    }
}
