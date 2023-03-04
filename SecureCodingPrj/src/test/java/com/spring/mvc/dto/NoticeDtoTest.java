/**
  * @파일명 : NoticeDtoTest.java
  * @작성일 : 2023. 2. 28.
  * @작성자 : 김영철
  */
package com.spring.mvc.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @프로젝트명 : AIBK-Security
 * @패키지명 : com.spring.mvc.dto
 * @파일명 : NoticeDtoTest.java
 * @작성일 : 2023. 2. 28.
 * @작성자 : 김영철
 */
public class NoticeDtoTest {

    private NoticeDto noticeDto;

    /**
     * @메소드타입 : NoticeDtoTest
     * @메소드명 : setUpBeforeClass
     * @return : void
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @메소드타입 : NoticeDtoTest
     * @메소드명 : tearDownAfterClass
     * @return : void
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @메소드타입 : NoticeDtoTest
     * @메소드명 : setUp
     * @return : void
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        noticeDto = new NoticeDto();
    }

    /**
     * @메소드타입 : NoticeDtoTest
     * @메소드명 : tearDown
     * @return : void
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.spring.mvc.dto.NoticeDto#hashCode()}.
     */
    @Test
    public final void testHashCode() {
        UserDto noticeDto = new UserDto();
        assertNotEquals(this.noticeDto.hashCode(), noticeDto.hashCode());
    }

    /**
     * Test method for {@link com.spring.mvc.dto.NoticeDto#getIdnotice()}.
     */
    @Test
    public final void testGetIdnotice() {
        noticeDto.getIdnotice();
    }

    /**
     * Test method for {@link com.spring.mvc.dto.NoticeDto#getSubject()}.
     */
    @Test
    public final void testGetSubject() {
        noticeDto.getSubject();
    }

    /**
     * Test method for {@link com.spring.mvc.dto.NoticeDto#getContent()}.
     */
    @Test
    public final void testGetContent() {
        noticeDto.getContent();
    }

    /**
     * Test method for {@link com.spring.mvc.dto.NoticeDto#setIdnotice(int)}.
     */
    @Test
    public final void testSetIdnotice() {
        noticeDto.setIdnotice(0);
    }

    /**
     * Test method for {@link com.spring.mvc.dto.NoticeDto#setSubject(String)}.
     */
    @Test
    public final void testSetSubject() {
        noticeDto.setSubject("hello");
    }

    /**
     * Test method for {@link com.spring.mvc.dto.NoticeDto#setContent(String)}.
     */
    @Test
    public final void testSetContent() {
        noticeDto.setContent("hello world");
    }

    /**
     * Test method for
     * {@link com.spring.mvc.dto.NoticeDto#equals(java.lang.Object)}.
     */
    @Test
    public final void testEqualsObject() {
        NoticeDto noticeDto2 = new NoticeDto();
        this.noticeDto.setContent("hello");
        this.noticeDto.setIdnotice(0);
        this.noticeDto.setSubject("hello");
        noticeDto2.setContent("hello");
        noticeDto2.setIdnotice(0);
        noticeDto2.setSubject("hello");
        assertEquals(this.noticeDto, noticeDto2);
        noticeDto2.setSubject("hollo");
        assertNotEquals(this.noticeDto, noticeDto2);

        assertEquals(this.noticeDto, this.noticeDto);
        assertNotEquals(this.noticeDto, null);
        UserDto noticeDto = new UserDto();
        assertNotEquals(this.noticeDto, noticeDto);

    }

    /**
     * Test method for
     * {@link com.spring.mvc.dto.NoticeDto#canEqual(java.lang.Object)}.
     */
    @Test
    public final void testCanEqual() {
        assertTrue(noticeDto.equals(noticeDto));
    }

    /**
     * Test method for {@link com.spring.mvc.dto.NoticeDto#toString()}.
     */
    @Test
    public final void testToString() {
        assertNotNull(noticeDto.toString());
    }

    /**
     * Test method for {@link com.spring.mvc.dto.NoticeDto#NoticeDto()}.
     */
    @Test
    public final void testNoticeDto() {
        NoticeDto obj = new NoticeDto();
        assertNotNull(obj);
    }

}
