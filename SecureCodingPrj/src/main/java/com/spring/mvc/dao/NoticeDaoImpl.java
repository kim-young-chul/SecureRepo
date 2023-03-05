/**
  * @파일명 : NoticeDaoImpl.java
  * @작성일 : 2023. 2. 26.
  * @작성자 : 김영철
  */
package com.spring.mvc.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.spring.mvc.dto.NoticeDto;

/**
 * @프로젝트명 : AIBK-Security
 * @패키지명 : com.spring.mvc.dao
 * @파일명 : NoticeDaoImpl.java
 * @작성일 : 2023. 2. 26.
 * @작성자 : 김영철
 */
@Repository
public class NoticeDaoImpl implements NoticeDao {

    /**
     * @필드타입 : Logger
     * @필드명 : LOG
     */
    private static final Logger LOG = LogManager.getLogger(NoticeDaoImpl.class);

    /**
     * @필드타입 : SqlSessionTemplate
     * @필드명 : template
     */
    @Resource
    private SqlSessionTemplate template;

    /**
     * @메소드타입 : NoticeDaoImpl
     */
    public NoticeDaoImpl() {
        LOG.debug("NoticeDaoImpl constructor");
    }

    /**
     * @메소드타입 : NoticeDaoImpl
     * @param paramTemplate
     */
    public NoticeDaoImpl(final SqlSessionTemplate paramTemplate) {
        this.template = paramTemplate;
    }

    /**
     * @메소드타입 : NoticeDaoImpl
     * @메소드명 : selectNotice
     * @return
     */
    @Override
    public List<NoticeDto> selectNotice() {
        return template.selectList("mappers.notice-mapper.selectNotice");
    }

    /**
     * @메소드타입 : NoticeDaoImpl
     * @메소드명 : insertNotice
     * @param noticeDto
     * @return
     */
    @Override
    public int insertNotice(final NoticeDto noticeDto) {
        return template.insert("mappers.notice-mapper.insertNotice", noticeDto);
    }

    /**
     * @메소드타입 : NoticeDaoImpl
     * @메소드명 : deleteNotice
     * @param noticeDto
     * @return
     */
    @Override
    public int deleteNotice(final NoticeDto noticeDto) {
        return template.delete("mappers.notice-mapper.deleteNotice", noticeDto);
    }

    /**
     * @메소드타입 : NoticeDaoImpl
     * @메소드명 : selectOneNotice
     * @param noticeDto
     * @return
     */
    @Override
    public NoticeDto selectOneNotice(final NoticeDto noticeDto) {
        return template.selectOne("mappers.notice-mapper.selectOneNotice", noticeDto);
    }

    /**
     * @메소드타입 : NoticeDaoImpl
     * @메소드명 : updateNotice
     * @param noticeDto
     * @return
     */
    @Override
    public int updateNotice(final NoticeDto noticeDto) {
        return template.update("mappers.notice-mapper.updateNotice", noticeDto);
    }
}
