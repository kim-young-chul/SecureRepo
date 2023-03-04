/**
  * @파일명 : NoticeServiceTest.java
  * @작성일 : 2023. 2. 28.
  * @작성자 : 김영철
  */
package com.spring.mvc.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.spring.mvc.dao.NoticeDao;
import com.spring.mvc.dto.NoticeDto;

/**
 * @프로젝트명 : AIBK-Security
 * @패키지명 : com.spring.mvc.service
 * @파일명 : NoticeServiceTest.java
 * @작성일 : 2023. 2. 28.
 * @작성자 : 김영철
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@WebAppConfiguration
public class NoticeServiceTest {

    @Mock
    private NoticeDao noticeDao;

    @InjectMocks
    private NoticeServiceImpl noticeService;

    /**
     * @메소드타입 : NoticeServiceTest
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
     * {@link com.spring.mvc.service.NoticeServiceImpl#NoticeServiceImpl()}.
     */
    @Test
    public final void testNoticeServiceImpl() {
        assertNotNull(noticeService);
    }

    /**
     * Test method for
     * {@link com.spring.mvc.service.NoticeServiceImpl#selectNotice()}.
     */
    @Test
    public final void testSelectNotice() {
        List<NoticeDto> noticeDtoLst = null;
        when(noticeDao.selectNotice()).thenReturn(noticeDtoLst);
        noticeService.selectNotice();
        verify(noticeDao).selectNotice();
    }

    /**
     * Test method for
     * {@link com.spring.mvc.service.NoticeServiceImpl#insertNotice(com.spring.mvc.dto.NoticeDto)}.
     */
    @Test
    public final void testInsertNotice() {
        assertNotNull(noticeService);
    }

    /**
     * Test method for
     * {@link com.spring.mvc.service.NoticeServiceImpl#deleteNotice(com.spring.mvc.dto.NoticeDto)}.
     */
    @Test
    public final void testDeleteNotice() {
        assertNotNull(noticeService);
    }

    /**
     * Test method for
     * {@link com.spring.mvc.service.NoticeServiceImpl#selectOneNotice(com.spring.mvc.dto.NoticeDto)}.
     */
    @Test
    public final void testSelectOneNotice() {
        assertNotNull(noticeService);
    }

    /**
     * Test method for
     * {@link com.spring.mvc.service.NoticeServiceImpl#updateNotice(com.spring.mvc.dto.NoticeDto)}.
     */
    @Test
    public final void testUpdateNotice() {
        assertNotNull(noticeService);
    }

}
