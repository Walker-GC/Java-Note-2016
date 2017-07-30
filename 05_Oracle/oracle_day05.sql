视图
视图是数据库对象之一
视图在SQL语句中体现的角色与表一致，
但其并不是表，它只是对应了一个查询
语句的结果集。
CREATE VIEW v_emp_10
AS
SELECT empno,ename,sal,deptno
FROM emp
WHERE deptno=10

查询视图与查询表一样:
SELECT * 
FROM v_emp_10

查看视图的结构:
DESC v_emp_10

视图对应的子查询若含有函数或者表达式
那么必须给别名。当一个字段使用了别名
那么视图中该字段的名字就是这个别名。

修改视图
由于视图只是对应了一个查询语句，所以修改
视图就是替换该SQL语句。

CREATE OR REPLACE VIEW v_emp_10
AS
SELECT empno id,ename name,
       sal salary,deptno
FROM emp
WHERE deptno=10

对视图进行DML操作
对视图进行DML操作就是对视图数据来源
的基础表进行的操作。并且只能对视图可
间的字段进行。

INSERT INTO v_emp_10
(id,name,salary,deptno)
VALUES
(1001,'JACK',5000,10)
SELECT * FROM v_emp_10
SELECT * FROM emp

UPDATE v_emp_10
SET salary=6000
WHERE id=1001
SELECT * FROM v_emp_10
SELECT * FROM emp

DELETE FROM v_emp_10
WHERE id=1001
SELECT * FROM v_emp_10
SELECT * FROM emp

通过对视图操作的数据，若视图不可见
那么就等同于对基表数据进行了污染。

INSERT INTO v_emp_10
(id,name,salary,deptno)
VALUES
(1001,'JACK',5000,20)
SELECT * FROM v_emp_10
SELECT * FROM emp

UPDATE v_emp_10
SET deptno=20
SELECT * FROM v_emp_10
SELECT * FROM emp

删除不会对基表污染
DELETE FROM v_emp_10
WHERE deptno=20

为视图添加检查选项可以避免对视图
进行DML操作后对基表有数据污染的情况。
检查选项要求对视图进行的DML操作的数据
视图必须对其可见，否则不允许操作。
CREATE OR REPLACE VIEW v_emp_10
AS
SELECT empno id,ename name,
       sal salary,deptno
FROM emp
WHERE deptno=10
WITH CHECK OPTION

INSERT INTO v_emp_10
(id,name,salary,deptno)
VALUES
(1001,'ROSE',5000,20)

SELECT * FROM v_emp_10
SELECT * FROM emp

READ ONLY选项
当视图添加了只读选项后，该视图仅能查看
不能执行任何DML操作。
CREATE OR REPLACE VIEW v_emp_10
AS
SELECT empno id,ename name,
       sal salary,deptno
FROM emp
WHERE deptno=10
WITH READ ONLY

INSERT INTO v_emp_10
(id,name,salary,deptno)
VALUES
(1001,'ROSE',5000,10)

数据字典
USER_OBJECTS:记录了用户创建的所有
数据库对象

USER_VIEWS:记录了用户创建的所有视图
USER_TABLES:记录了用户创建的所有表

SELECT text FROM user_views 
WHERE view_name = 'V_EMP_10';

SELECT table_name FROM user_tables

复杂视图:
创建一个各部门工资情况的视图
CREATE VIEW v_emp_dept_salinfo
AS
SELECT MAX(e.sal) max_sal,
       MIN(e.sal) min_Sal,
       AVG(e.sal) avg_sal,
       SUM(e.sal) sum_sal,
       d.deptno,d.dname
FROM emp e,dept d
WHERE e.deptno=d.deptno
GROUP BY d.deptno,d.dname

SELECT * FROM v_emp_dept_salinfo

查看比自己所在部门平均工资高的员工?
SELECT e.ename,e.sal,e.deptno
FROM emp e,v_emp_dept_salinfo v
WHERE e.deptno=v.deptno
AND e.sal>v.avg_sal

删除视图
删除视图中的数据时，会对应的将基表
数据删除，但是删除视图本身时，是不会
影响基表任何数据的。
DROP VIEW v_emp_10

序列，数据库对象之一。
序列是用来生成一系列数字的。
序列生成的数字常被用作某张表主键
字段的值。
CREATE SEQUENCE seq_emp_id
START WITH 1
INCREMENT BY 1

序列提供了两个伪列用于获取当前序列的值。
NEXTVAL:获取序列下一个数，这个伪列会
导致序列发生步进。序列是不能回退的。即:
生成下一个数字后，就不能得到之前的数字了。

CURRVAL:获取序列当前值(最后一次生成的值)
无论调用多少次，都不会导致序列发生步进。
新创建的序列要至少调用一次NEXTVAL后才
可以使用CURRVAL。

SELECT seq_emp_id.NEXTVAL
FROM dual

INSERT INTO emp
(empno,ename,sal,job,deptno)
VALUES
(seq_emp_id.NEXTVAL,'ROSE','4500','CLERK',10)

SELECT * FROM emp

删除序列
DROP SEQUENCE seq_emp_id

索引，数据库对象之一，作用是提高查询效率。
索引的建立是数据库执行完成的，过程对我们是透明的。
我们只需要告诉数据库是否添加索引即可。
索引的应用也是自动的，无需在查询的过程
中告知数据库是否使用索引，数据库会自行
判断可用索引并自动使用。

CREATE INDEX idx_emp_ename 
ON emp(ename)
当只用ename作为过滤条件(除了LIKE),作为
排序的字段，去重等操作时，数据库会自动使用
索引idx_emp_ename提高查询效率




约束分为表级约束与列级约束
NOT NULL只是列级约束
其他约束即是表级也是列级
列级约束:为某个字段添加约束只能
在操作该列的同时进行。
表级约束:可以直接对表进行添加约束
并指定为该表的那个字段添加
简单说:添加约束时的语法不一样。

唯一性约束
唯一性约束可以要求某个字段在表中任何
集中的值不能相同，NULL值除外。
CREATE TABLE employees1 (
  eid NUMBER(6) UNIQUE,
  name VARCHAR2(30),
  email VARCHAR2(50),
  salary NUMBER(7, 2),
  hiredate DATE,
  CONSTRAINT employees_email_uk UNIQUE(email)
)
INSERT INTO employees1
(eid,name,email)
VALUES
(NULL,'jack',NULL)

SELECT * FROM employees1

主键约束
主键约束只能建立在单列上，并且一张表
只能有一个主键约束，主键约束可以保证
该字段非空且唯一
CREATE TABLE employees2 (
  eid NUMBER(6) PRIMARY KEY,
  name VARCHAR2(30),
  email VARCHAR2(50),
  salary NUMBER(7, 2),
  hiredate DATE
)
INSERT INTO employees2
(eid,name)
VALUES
(NULL,'JACK')









