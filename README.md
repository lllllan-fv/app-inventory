# 库存管理系统



### [Android下拉列表框](https://blog.csdn.net/pfe_nova/article/details/38362193)



## 数据库表



#### commodity

| 字段名称 | 字段类型 | 是否主键 | 是否为空 | 长度 |   描述   |
| :------: | :------: | :------: | :------: | :--: | :------: |
|    id    |   int    |    是    |          |  11  |   编码   |
|   type   | varchar  |          |          |  20  | 货品类别 |
|   name   | varchar  |          |          |  20  | 货品名称 |

```mysql
CREATE TABLE IF NOT EXISTS `commodity` (
	`id` INT UNSIGNED AUTO_INCREMENT,
    `type` VARCHAR(20) NOT NULL,
    `name` VARCHAR(20) NOT NULL,
    PRIMARY KEY ( `id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```



#### record

| 字段名称  | 字段类型 | 是否主键 | 是否为空 | 长度 |     描述     |
| :-------: | :------: | :------: | :------: | :--: | :----------: |
|    id     |   int    |    是    |          |  11  |     编码     |
|   type    | varchar  |          |          |  20  |  出入库类别  |
|  company  | varchar  |          |          |  20  | 供应商或客户 |
| commodity | varchar  |          |          |  20  |   货品名称   |
|   price   |  double  |          |          |  11  |     单价     |
| quantity  |   int    |          |          |  11  |     数量     |
|   time    | varchar  |          |          |  20  |   操作日期   |

```mysql
CREATE TABLE IF NOT EXISTS `record` (
    `id` INT UNSIGNED AUTO_INCREMENT,
    `type` VARCHAR(20) NOT NULL,
    `company` VARCHAR(20) NOT NULL,
    `commodity` VARCHAR(20) NOT NULL,
    `price` INT NOT NULL,
    `quantity` INT NOT NULL,
    `time` VARCHAR(20) NOT NULL,
    PRIMARY KEY ( `id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

