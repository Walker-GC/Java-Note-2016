SELECT SYSDATE FROM dual

--创建表employee
CREATE TABLE employee(
	id NUMBER(4),
	name VARCHAR2(20),
	gender CHAR(1),
	birth DATE,
  salary NUMBER(6,2),
  job VARCHAR2(30),
  deptno NUMBER(2)
)

查看表结构
DESC table_name
DESC employee

删除表
DROP TABLE table_name
DROP TABLE employee

数据库中所有字段的默认值都是NULL，
当插入数据时，某个字段没有给值的
时候，则会将NULL值最为该字段的值
可以通过DEFAULT关键字为指定的字段
设置一个指定的默认值。
对于字符串的字面量而言，在数据库中
是使用单引号括起来的。
SQL语句不区分大小写，但是字面量的值
是区分大小写的！

CREATE TABLE employee(
	id NUMBER(4),
	name VARCHAR2(20) NOT NULL,
	gender CHAR(1) DEFAULT 'M',
	birth DATE,
  salary NUMBER(6,2) DEFAULT 5000,
  job VARCHAR2(30),
  deptno NUMBER(2)
);

DESC employee

修改表
1:修改表名
2:修改表结构

修改表名
RENAME old_name TO new_name
RENAME employee TO myemp

DESC myemp

2:修改表结构
2.1:添加新字段
添加的字段只能被添加到表的末尾。
ALTER TABLE myemp 
ADD(
  hiredate DATE DEFAULT SYSDATE
)
DESC myemp

2.2修改表中现有字段
修改只能修改字段的类型，长度，
默认值以及是否非空
该操作不建议在表中已经含有数据
的情况下进行，否则有可能修改不成功。
例如:尽量不修改字段类型，修改长度
尽量增加而不是减少。
ALTER TABLE myemp
MODIFY(
  job VARCHAR2(40)
)

DESC myemp

2.3删除表中字段
ALTER TABLE myemp
DROP(hiredate)

DESC myemp




DML语句
对表中数据进行操作
包含:增删改操作
DML是伴随事务的

INSERT语句
向表中插入数据
INSERT INTO myemp
(id,name,job,deptno)
VALUES
(1,'jack','CLERK',10)
COMMIT 提交事务
ROLLBACK  回滚事务

SELECT * FROM myemp

若不指定字段，则是全列插入
INSERT INTO myemp
VALUES
(2,'rose','F',SYSDATE,5000,'CLERK',20)

插入日期建议使用内置函数
TO_DATE,当然也可以使用
字符串，但是格式必须遵循:
DD-MON-RR,由于有语言差异
不建议只用
INSERT INTO myemp
(id,name,deptno,birth)
VALUES
(3,'mike',20,
 TO_DATE('1992-08-02',
         'YYYY-MM-DD')
)
SELECT * FROM myemp

UPDATE语句
修改表中数据
UPDATE myemp
SET salary=8000,deptno=30
WHERE gender='M'

DELETE语句
删除表中数据
DELETE FROM myemp
WHERE gender='M'

UPDATE与DELETE通常都需要添加WHERE条件
否则就是对表中所有数据进行的操作!






