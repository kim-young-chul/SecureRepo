/**
  * @파일명 : ServletInterceptorTest.java
  * @작성일 : 2023. 2. 28.
  * @작성자 : 김영철
  */
package com.spring.mvc.interceptor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @프로젝트명 : AIBK-Security
 * @패키지명 : com.spring.mvc.interceptor
 * @파일명 : ServletInterceptorTest.java
 * @작성일 : 2023. 2. 28.
 * @작성자 : 김영철
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@WebAppConfiguration
public class ServletInterceptorTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    HttpServletResponse response;

    @Mock
    Object handler;

    @InjectMocks
    private ServletInterceptor servletInterceptor;

    /**
     * @메소드타입 : ServletInterceptorTest
     * @메소드명 : setUpBeforeClass
     * @return : void
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @메소드타입 : ServletInterceptorTest
     * @메소드명 : tearDownAfterClass
     * @return : void
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @메소드타입 : ServletInterceptorTest
     * @메소드명 : setUp
     * @return : void
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * @메소드타입 : ServletInterceptorTest
     * @메소드명 : tearDown
     * @return : void
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.spring.mvc.interceptor.ServletInterceptor#ServletInterceptor()}.
     */
    @Test
    public final void testServletInterceptor() {
        assertNotNull(servletInterceptor);
    }

    /**
     * Test method for
     * {@link com.spring.mvc.interceptor.ServletInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)}.
     * 
     * @throws Exception
     */
    @Test
    public final void testPreHandleHttpServletRequestHttpServletResponseObject() throws Exception {
       // 로그인 세션이 없는 경우
        when(request.getSession()).thenReturn(session);
        assertFalse(servletInterceptor.preHandle(request, response, handler));
        // 로그인 세션이 있고, 권한이 없는 경우
        when(session.getAttribute("grade")).thenReturn("user");
        when(session.getAttribute("login")).thenReturn("hong-gi-dong1");
        
        when(request.getRequestURI()).thenReturn("/servlet/notice_write");
        assertFalse(servletInterceptor.preHandle(request, response, handler));
        
        when(request.getRequestURI()).thenReturn("/servlet/notice_update");
        assertFalse(servletInterceptor.preHandle(request, response, handler));
        
        when(request.getRequestURI()).thenReturn("/servlet/notice_delete");
        assertFalse(servletInterceptor.preHandle(request, response, handler));
        
        when(request.getRequestURI()).thenReturn("/servlet/insert_notice");
        assertFalse(servletInterceptor.preHandle(request, response, handler));

        when(request.getRequestURI()).thenReturn("/servlet/update_notice");
        assertFalse(servletInterceptor.preHandle(request, response, handler));

        // 로그인 세션이 있고, 권한이 있는 경우
        when(session.getAttribute("grade")).thenReturn("manager");
        assertTrue(servletInterceptor.preHandle(request, response, handler));
    }

}
