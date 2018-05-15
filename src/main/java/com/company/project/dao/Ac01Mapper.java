package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Ac01;
import org.apache.ibatis.annotations.Select;

public interface Ac01Mapper extends Mapper<Ac01> {
    @Select("select sq_aac001.nextval from dual")
    Long selectByCountryName(String countryname);

}