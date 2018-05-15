package com.company.project.dao;

import com.company.project.model.Aa10;
import com.company.project.model.Ac01;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.HashMap;
import java.util.List;

public interface HnsiAPIMapper {
    /**
     * 居民医疗是否参保缴费
     * @param ac01
     * @return
     */
    @Select("select count(1) from ac02 a,ac20 b where a.aaz159=b.aaz159 and a.aae140='25' and aac008='1' and aac031='1' and a.aac001=#{aac001}")
    public Long countac02ac20(Ac01 ac01);

    /**
     * 居民医疗是否可以参保缴费
     * @param ac01
     * @return
     */
    @Select("select t.aac001,                                                                            "+
            "       t.aac003,                                                                            "+
            "       t.aae135, /*(select decode(aa.aae140,'28','1','20',decode(eac070,'0','1','3')) */    "+
            "       nvl((select min(decode(aac008, '4', '3', decode(eac070, '2', '1', aac031)))              "+
            "          from ac20 aa, ac02 bb                                                             "+
            "         where aa.aac001 = bb.aac001                                                        "+
            "           and aa.aaz159 = bb.aaz159                                                        "+
            "           and aa.aac001 = t.aac001                                                         "+
            "           and aa.aae140 in ('20', '28')),3) aac031,                                           "+
            "       a.eab042,                                                                            "+
            "       t.aac006,                                                                            "+
            "       c.aab005,                                                                            "+
            "       a.eab062,                                                                            "+
            "       c.eab009,                                                                            "+
            "       c.eab030,                                                                            "+
            "       c.eab008,                                                                            "+
            "       e.aaa082,                                                                            "+
            "       e.aae010                                                                             "+
            "  from Ag01 c, Ag02 a, Ac01 t, Ac11 e                                                       "+
            " where c.aab005 = a.aab005                                                                  "+
            "   and a.aac001 = t.aac001                                                                  "+
            "   and (a.eab058 is null or a.eab058 <> '8')                                                "+
            "   and not exists                                                                           "+
            " (select 1                                                                                  "+
            "          from Ac43 d                                                                       "+
            "         where d.aac001 = t.aac001                                                          "+
            "           and d.aae003 >= to_char(sysdate, 'yyyy') || '07'                                 "+
            "           and d.aae003 <= to_char(sysdate, 'yyyy') +1 || '06'                              "+
            "           and d.aae140 = '25'                                                              "+
            "           and d.eac002 = 0                                                                 "+
            "           and d.aae017 = 0)                                                                "+
            "   and a.aac001=#{aac001}                                                                     "+
            "   and t.aac001 = e.aac001(+)                                                               "+
            "   and not exists (select 1                                                                 "+
            "          from ic05                                                                         "+
            "         where aac001 = t.aac001                                                            "+
            "           and eac044 in ('01', '05', '07', '16'))                                          "+
            "   and not exists (select 1                                                                 "+
            "          from ict1                                                                         "+
            "         where aac001 = t.aac001                                                            "+
            "           and eac157 is not null)                                                          "
    )
    public List<HashMap> checkinfo(Ac01 ac01);
}
