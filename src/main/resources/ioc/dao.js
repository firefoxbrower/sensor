//可以看到, 新增了一个叫conf的bean, 而dataSource中的一些field改为从conf读取 
var ioc = {
        conf : {
            type : "org.nutz.ioc.impl.PropertiesProxy",
            fields : {
                paths : ["/db/db.properties"]
            }
        },
        dataSource : {
                factory : "$conf#make",
                args : ["com.alibaba.druid.pool.DruidDataSource", "db."],
                type : "com.alibaba.druid.pool.DruidDataSource",
                events : {
                    create : "init",
                        depose : 'close'
                }
        },
      dao : {
          type : "org.nutz.dao.impl.NutDao",
            args : [{refer:"dataSource"}]
      }
};