package com.zty.springcloud.pojo;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor

@Accessors(chain = true)//链式写法
/*
链式写法
Dept dept=new Dept();
dept.setDeptNo(11).setDname('zzz').setDb_source('001');
 */
public class Dept implements Serializable {

  private Long deptno;
  private String dname;
  //这个数据存在哪个数据库   微服务：一个服务对应一个数据库,同一个信息可能存在不同的数据库
  private String db_source;

    public Dept(Long deptno, String dname) {
        this.deptno = deptno;
        this.dname = dname;
    }

}
