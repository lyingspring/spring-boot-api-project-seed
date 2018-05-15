package com.company.project.dao;

import org.apache.ibatis.jdbc.SQL;


    public class PublicProvider {
        private final String TBL_ORDER = "dual";

        public String querySequenceByParam(String param) {
            SQL sql = new SQL().SELECT(param+".nextval").FROM(TBL_ORDER);
            return sql.toString();
        }

        public String queryDBdate() {
            SQL sql = new SQL().SELECT("sysdate").FROM(TBL_ORDER);
            return sql.toString();
        }
    }

