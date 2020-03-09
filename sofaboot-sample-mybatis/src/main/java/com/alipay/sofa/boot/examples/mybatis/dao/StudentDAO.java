package com.alipay.sofa.boot.examples.mybatis.dao;

import com.alipay.sofa.boot.examples.mybatis.domain.StudentDO;

import java.util.List;

public interface StudentDAO {

    int deleteByPrimaryKey(Integer id);

    int insert(StudentDO record);

    int insertSelective(StudentDO record);

    StudentDO selectByPrimaryKey(Integer id);

    List<StudentDO> selectByNameExactly(String name);

    List<StudentDO> selectByNameLikely(String name);

    int updateByPrimaryKeySelective(StudentDO record);

    int updateByPrimaryKey(StudentDO record);
}
