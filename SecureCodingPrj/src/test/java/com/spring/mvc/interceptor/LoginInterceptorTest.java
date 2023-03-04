/**
  * @파일명 : LoginInterceptorTest.java
  * @작성일 : 2023. 2. 28.
  * @작성자 : 김영철
  */
package com.spring.mvc.interceptor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.dto.UserDto;

/**
 * @프로젝트명 : AIBK-Security
 * @패키지명 : com.spring.mvc.interceptor
 * @파일명 : LoginInterceptorTest.java
 * @작성일 : 2023. 2. 28.
 * @작성자 : 김영철
 */
public class LoginInterceptorTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    HttpServletResponse response;

    @Mock
    Object handler;

    @Mock
    ModelAndView modelAndView;

    @Mock
    ModelMap mm;

    @Mock
    UserDto userDto;

    @InjectMocks
    private LoginInterceptor loginInterceptor;

    /**
     * @메소드타입 : LoginInterceptorTest
     * @메소드명 : setUpBeforeClass
     * @return : void
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @메소드타입 : LoginInterceptorTest
     * @메소드명 : tearDownAfterClass
     * @return : void
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @메소드타입 : LoginInterceptorTest
     * @메소드명 : setUp
     * @return : void
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * @메소드타입 : LoginInterceptorTest
     * @메소드명 : tearDown
     * @return : void
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.spring.mvc.interceptor.LoginInterceptor#LoginInterceptor()}.
     */
    @Test
    public final void testLoginInterceptor() {
        assertNotNull(loginInterceptor);
    }

    /**
     * Test method for {@link com.spring.mvc.interceptor.LoginInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)}.
     * @throws Exception 
     */
    @Test
    public final void testPreHandleHttpServletRequestHttpServletResponseObject() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getId()).thenReturn("hello");
        when(session.getAttribute("login")).thenReturn("hong-gi-dong");
        assertTrue(loginInterceptor.preHandle(request, response, handler));
    }

    /**
     * Test method for {@link com.spring.mvc.interceptor.LoginInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)}.
     * @throws Exception 
     */
    @Test
    public final void testPostHandleHttpServletRequestHttpServletResponseObjectModelAndView() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getId()).thenReturn("sessionid");
        when(modelAndView.getModelMap()).thenReturn(mm);
        when(mm.get("LoginSuccess")).thenReturn(userDto);
        loginInterceptor.postHandle(request, response, handler, modelAndView);
        verify(request, times(2)).getSession();
    }
}
