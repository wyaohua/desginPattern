package com.yaohua.designpattern.建造者模式.案例二;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Sql {



    private Sql(){

    }



    public static SelectSqlBuilder select(String ... cloums){
        SelectSqlBuilder sqlBuilder = new SelectSqlBuilder(cloums);
        return sqlBuilder;
    }

    public static UpdateSqlBuilder update(){
        return new UpdateSqlBuilder();

    }


    /**
     * 构建 查询语句
     */
    static class SelectSqlBuilder{
        private final String [] cloums;
        private  String table;
        private  String where;

        private SelectSqlBuilder(String [] cloums){
            this.cloums = cloums;
        }


        public SelectSqlBuilder from(String table){
            this.table = table;
            return this;
        }

        public SelectSqlBuilder where(String where){
            this.where = where;
            return this;
        }

        public String build(){
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ").append(String.join(",", cloums)).append(" FROM ").append(table);

            if (where != null) {
                sql.append(" where ").append(where);
            }
            return sql.toString();
        }



    }


    /**
     * 构建 update语句
     */

    static class UpdateSqlBuilder{
        private Map<String ,String> set = new LinkedHashMap<>();

        private  String table;
        private  String where;




        public UpdateSqlBuilder table(String table){
            this.table = table;
            return this;
        }

        public UpdateSqlBuilder where(String where){
            this.where = where;
            return this;
        }
        public UpdateSqlBuilder set(String col,String value){
            set.put(col, value);
            return this;
        }

        public String build(){
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ").append(table).append(" SET ");
            String setSql = set.entrySet().stream().map(item -> item.getKey() + "=" + item.getValue()).collect(Collectors.joining(","));
            sql.append(setSql);


            if (where != null) {
                sql.append(" where ").append(where);
            }
            return sql.toString();
        }


    }
}
