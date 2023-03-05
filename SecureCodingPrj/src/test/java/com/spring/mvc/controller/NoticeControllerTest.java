package com.spring.mvc.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;
import com.spring.mvc.dto.NoticeDto;
import com.spring.mvc.service.NoticeService;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.List;

public class NoticeControllerTest {

    @Mock
    private NoticeService mockNoticeService;

    @InjectMocks
    private NoticeController mockNoticeController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNoticeList() throws Exception {

        ModelAndView mav;
        List<NoticeDto> noticeDtoLst = null;
        when(mockNoticeService.selectNotice()).thenReturn(noticeDtoLst);
        mav = mockNoticeController.noticeList();
        assertEquals("notice_list", mav.getViewName());
        assertEquals(noticeDtoLst, mav.getModel().get("noticeList"));
    }

    @Test
    public void testNoticeUpdate() throws Exception {

        ModelAndView mav;
        // 해당 번호의 게시물이 없는 경우
        mav = mockNoticeController.noticeUpdate(new NoticeDto());
        assertEquals("redirect:notice_list", mav.getViewName());

        // 해당 번호의 게시룸이 있는 경우
        when(mockNoticeService.selectOneNotice(new NoticeDto())).thenReturn(new NoticeDto());
        mav = mockNoticeController.noticeUpdate(new NoticeDto());
        assertEquals("notice_update", mav.getViewName());
        assertEquals(new NoticeDto(), (NoticeDto) mav.getModel().get("notice"));
    }

    @Test
    public void testNoticeWrite() throws Exception {
        String view;
        view = mockNoticeController.noticeWrite();
        assertEquals("notice_write", view);
    }

    @Test
    public void testNoticeDelete() throws Exception {

        String view;
        // 게시물이 정상 삭제된 경우
        when(mockNoticeService.deleteNotice(new NoticeDto())).thenReturn(1);
        view = mockNoticeController.noticeDelete(new NoticeDto());
        assertEquals("redirect:notice_list", view);

        // 게시물 번호가 잘못 된 경우
        when(mockNoticeService.deleteNotice(new NoticeDto())).thenReturn(0);
        view = mockNoticeController.noticeDelete(new NoticeDto());
        assertEquals("error", view);
    }

    @Test
    public void testInsertNotice() throws Exception {

        String view;
        view = mockNoticeController.insertNotice(new NoticeDto());
        assertEquals("redirect:notice_list", view);
    }

    @Test
    public void testUpdateNotice() throws Exception {

        String view;
        // 게시물이 정상 수정 된 경우
        when(mockNoticeService.updateNotice(new NoticeDto())).thenReturn(1);
        view = mockNoticeController.updateNotice(new NoticeDto());
        assertEquals("redirect:notice_list", view);

        // 게시물 수정에 실패 한 경우
        when(mockNoticeService.updateNotice(new NoticeDto())).thenReturn(0);
        view = mockNoticeController.updateNotice(new NoticeDto());
        assertEquals("error", view);
    }
}
