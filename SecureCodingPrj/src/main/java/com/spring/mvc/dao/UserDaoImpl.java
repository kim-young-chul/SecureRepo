package com.spring.mvc.dao;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import com.spring.mvc.dto.UserDto;

/**
 * @프로젝트명 : AIBK-Security
 * @패키지명 : com.spring.mvc.dao
 * @파일명 : UserDaoImpl.java
 * @작성일 : 2023. 2. 27.
 * @작성자 : 김영철
 */
@Repository
public class UserDaoImpl implements UserDao {

    /**
     * @필드타입 : Logger
     * @필드명 : LOG
     */
    private static final Logger LOG = LogManager.getLogger(UserDaoImpl.class);

    /**
     * @필드타입 : SqlSessionTemplate
     * @필드명 : template
     */
    @Resource
    private SqlSessionTemplate template;

    /**
     * @메소드타입 : UserDaoImpl
     */
    public UserDaoImpl() {
        LOG.debug("UserDaoImpl constructor");
    }

    /**
     * @메소드타입 : UserDaoImpl
     */
    public UserDaoImpl(final SqlSessionTemplate paramTemplate) {
        this.template = paramTemplate;
    }

    /**
     * @메소드타입 : UserDaoImpl
     * @메소드명 : selectUser
     * @param userid
     * @return
     */
    @Override
    public UserDto selectUser(final int userid) {
        final UserDto userDto = new UserDto();
        userDto.setId(userid);
        return template.selectOne("mappers.user-mapper.selectUser", userDto);
    }

    /**
     * @메소드타입 : UserDaoImpl
     * @메소드명 : insertUser
     * @param userid
     * @param userpw
     * @param id
     */
    @Override
    public void insertUser(final String userid, final String userpw, final int id) {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setUserid(userid);
        userDto.setUserpw(userpw);

        LOG.debug("template.insert method call");
        template.insert("mappers.user-mapper.insertUser", userDto);
    }

    /**
     * @메소드타입 : UserDaoImpl
     * @메소드명 : userLogin
     * @param userDto
     * @return
     */
    @Override
    public UserDto userLogin(final UserDto userDto) {
        return template.selectOne("mappers.user-mapper.userLogin", userDto);
    }
}
