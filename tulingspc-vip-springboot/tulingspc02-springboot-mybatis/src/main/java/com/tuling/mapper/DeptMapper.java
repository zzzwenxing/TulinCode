package com.tuling.mapper;

import com.tuling.bean.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by smlz on 2019/3/22.
 */
@Mapper
public interface DeptMapper {


    Dept findOne(Integer id);

    List<Dept> list();

    int save(Dept dept);

}
