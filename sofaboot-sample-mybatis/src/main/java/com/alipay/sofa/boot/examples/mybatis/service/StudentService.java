package com.alipay.sofa.boot.examples.mybatis.service;

import com.alipay.sofa.boot.examples.mybatis.dao.StudentDAO;
import com.alipay.sofa.boot.examples.mybatis.domain.StudentDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * StudentService
 *
 * @author Deven
 * @version : StudentService, v 0.1 2020-03-10 00:07 Deven Exp$
 */
@Service
public class StudentService {

    @Resource
    private StudentDAO studentDAO;

    @Transactional
    public StudentDO findById(Integer id) {
        return studentDAO.selectByPrimaryKey(id);
    }

    @Transactional
    public List<StudentDO> findByName(String name, boolean exactly) {
        if(exactly) {
            return studentDAO.selectByNameExactly(name);
        } else {
            return studentDAO.selectByNameLikely(name);
        }
    }
}
