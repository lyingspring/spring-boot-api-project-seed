package com.company.project.dao;

import com.company.project.model.Aa10;
import com.company.project.model.Ac01;
import oracle.sql.DATE;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.core.annotation.Order;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PublicMapper {
    /**
     * 获取序列号
     * @param param
     * @return
     */
    @SelectProvider(type = PublicProvider.class, method = "querySequenceByParam")
    Long querySequenceByParam(String param);

    /**
     * 获取数据库时间
     * @return
     */
    @SelectProvider(type = PublicProvider.class, method = "queryDBdate")
    Date queryDBdate();

    /**
     * 获取数据库时间
     * @return
     */
    @Select("select sbp_public.f_Get_Codeview(#{aaa100},#{aaa102}) from dual")
    String getCodeValue(@Param("aaa100") String aaa100,@Param("aaa102") String aaa102);

    /**
     * 调用存储过程
     * @param aa10
     */
    @Select(" #{aaa103,mode=IN,jdbcType=VARCHAR}= call Sbp_Public.f_Get_Codeview(#{aaa100,mode=IN,jdbcType=VARCHAR},#{aaa102,mode=IN,jdbcType=VARCHAR})")
    @Options(statementType= StatementType.CALLABLE )
    public void callCodeview(Aa10 aa10);

}




