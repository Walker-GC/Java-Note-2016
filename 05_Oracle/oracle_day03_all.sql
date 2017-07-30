列的别名
当一个SELECT子句中的字段是一个函数
或者表达式时，那么结果集中对应的该字段
的名字就是这个函数或表达式，可读性差，
这时可以为该字段指定别名。
别名可以使用双引号括起来以区分大小写
和添加空格
SELECT 
 ename,sal*12 sal,
 TO_CHAR(hiredate,'YYYY-MM-DD') hiredate
FROM emp

WHERE在查询中是用来添加过滤条件的，
它的过滤时机是在查询表的过程中进行
的，每当遍历表中一条数据时就会检查
其是否满足WHERE条件，满足则会被查询
出来，不满足则被忽略。


AND和OR是用来连接多个过滤条件的，并且
AND的优先级是高于OR的。

查看工资高于1000的职位是CLERK和SALESMAN
的员工信息
SELECT ename,job,sal
FROM emp
WHERE sal>1000
AND (job='SALESMAN'
OR job='CLERK')

LIKE用于模糊匹配字符串，支持两个通配符:
_:表示任意的一个字符
%:表示任意个字符

查看名字第三个字母是L的员工
SELECT ename,sal,deptno
FROM emp
WHERE ename LIKE '__L%'

IN和NOT IN是判断是否在列表
中或者不能在列表中
IN和NOT IN通常用作子查询
SELECT ename, job FROM emp  
WHERE job IN ('MANAGER', 'CLERK');

SELECT ename, job FROM emp 
WHERE deptno NOT IN (10, 20);

BETWEEN...AND...
判断是否在一个范围内
SELECT ename, sal FROM emp  
WHERE sal BETWEEN 1500 AND 3000

ANY与ALL是用作判断一个值是否
>,>=,<,<=列表中的内容
>ALL(list):大于列表中最大的
<ALL(list):小于列表中最小的
>ANY(list):大于列表中最小的
<ANY(list):小于列表中最大的
AND与ALL的列表通常不会给定确定的
值，都是配合一个子查询使用。
SELECT empno, ename, job, sal, deptno
FROM emp
WHERE sal > ANY (3500,4000,4500);


DISTINCT关键字
用于将结果集中指定的字段的重复记录去除，DISTINCT应当紧跟在
SELECT关键字之后，可以对单列去重也可以对多列去重。

查看公司的职位有哪些？
SELECT DISTINCT job
FROM emp

多字段去重，不保证每个字段没有重复值。
结果集中这些字段值的组合没有重复记录。
SELECT DISTINCT job,deptno
FROM emp

排序结果集
ORDER BY子句可以将当前结果集
按照给定的字段的值进行升序或者
降序排列。
ORDER BY子句只能写在DQL语句的
最后一个子句上。
ASC:升序(默认就是升序，通常不写)
DESC:降序。

查看公司中工资的排名
SELECT ename,sal,deptno
FROM emp
ORDER BY sal DESC

排序的字段中若含有NULL值，那么NULL
被视作最大值。
按照奖金排序
SELECT ename,sal,comm
FROM emp
ORDER BY comm DESC

多字段排序
排序存在优先级，先按照第一个字段排序结果集，当第一个字段的值有重复的时候
才会将这些记录中按照第二个字段的排序规则进行排序，以此类推。每个字段都需要单独指定排序方式。

SELECT ename,deptno,sal
FROM emp
ORDER BY deptno DESC,sal DESC

聚合函数，又称为多行函数、分组函数。聚合函数是用来统计的，
可以将多条记录中指定的字段值进行统计，然后得到一个结果。

MAX(),MIN()
统计最大值与最小值
SELECT MAX(sal),MIN(sal)
FROM emp

AVG(),SUM()
求平均值，总和
SELECT AVG(sal),SUM(sal)
FROM emp

COUNT()
统计记录数
SELECT COUNT(ename)
FROM emp

聚合函数都是忽略NULL值的。
SELECT COUNT(comm)
FROM emp

通常统计表中记录数可以使用
SELECT COUNT(*) FROM emp

SELECT SUM(comm),AVG(comm)
FROM emp

SELECT AVG(NVL(comm,0))
FROM emp

GROUP BY子句
GROUP BY可以将当前查询的结果集按照GROUP BY子句给定的字段的值
相同的记录划分为一个组，配合聚合函数可以进行更细分的统计工作。

查看每个部门的平均工资?
SELECT AVG(sal),deptno
FROM emp
GROUP BY deptno

SELECT MAX(sal),job
FROM emp
GROUP BY job

当SELECT子句中出现了聚合函数，那么凡不在聚合函数中的其他字段必须出现在
GROUP BY子句中，反过来则不是必须的。
通常不使用聚合函数就没必要使用GROUP BY

GROUP BY根据多字段分组
分组原则是将结果集中，GROUP BY指定的这些字段的值的组合相同的记录看做一组

查看每个部门每个职位都有多少人?
SELECT COUNT(*),job,deptno
FROM emp
GROUP BY job,deptno

查看部门平均工资高于2000的这些部门的具体平均工资是是多少?
SELECT AVG(sal),deptno
FROM emp
WHERE AVG(sal)>2000
GROUP BY deptno

WHERE中不允许有聚合函数，原因在于过滤的时机并不对。
聚合函数的统计是建立在结果集的基础之上的，这就说明在统计前，
数据应当已经查询出来并生成了结果集，而WHERE的过滤就是在查询生成结果集
的过程中进行的。所以WHERE在前，统计在后，所以不能再WHERE中使用聚合函数进行过滤。

HAVING子句
HAVING子句可以使用聚合函数的结果进行过滤。HAVING是配合GROUP BY分组的，
目的是根据过滤条件取舍某些分组的记录。HAVING必须跟在GROUP BY之后。不能单独定义。
SELECT AVG(sal),deptno
FROM emp
GROUP BY deptno
HAVING AVG(sal)>2000

查看平均工资高于2000的那些部门的
最高工资和最低工资都分别是多少?
SELECT MAX(sal),MIN(sal),deptno
FROM emp
GROUP BY deptno
HAVING AVG(sal)>2000

关联查询
联合多张表进行查询，结果集中的字段可能来自多个表。
关联查询要添加连接条件，数据库根据连接条件将满足的记录进行连线，
从而提取这些记录中对应查询的字段值构成结果集。若不添加连接条件会
产生笛卡尔积，这通常是一个无意义的结果集。

查看每个员工以及其部门信息
SELECT e.ename,e.job,
       e.deptno,d.dname
FROM emp e,dept d
WHERE e.deptno=d.deptno

不添加连接条件会产生笛卡尔积，笛卡尔积的记录数是参与关联查询的表的记录数的乘积。
会将表的每一条记录与另一张表的每条记录连接一次并产生一条记录。
SELECT e.ename,d.dname
FROM emp e,dept d

通常N张表关联查询就要有至少N-1个连接条件。

关联查询中的连接条件要与过滤条件同时成立。
查看在NEW YORK工作的员工?
SELECT e.ename,e.job,
       d.dname,d.loc
FROM emp e,dept d
WHERE e.deptno=d.deptno
AND d.loc='NEW YORK'

内连接
内连接也是关联查询的一种

查看在NEW YORK工作的员工?
SELECT e.ename,e.job,
       d.dname,d.loc
FROM emp e JOIN dept d
ON e.deptno=d.deptno
WHERE d.loc='NEW YORK'

不满足连接条件的记录是不会出现在结果集中的

外连接
外连接在进行关联查询时除了会将满足连接条件
的记录列出来，也会将不满足连接条件的记录列出来。
外连接分为:
左外连接:以JOIN左侧表作为驱动表(主要显示记录的表)
        该表中的所有记录都要显示出来，当某条记录
        不满足连接条件时，该条记录中来自JOIN右侧
        表中的字段全部以NULL作为值。
右外连接，
全外连接

SELECT e.ename,e.job,d.dname
FROM emp e 
LEFT|RIGHT|FULL OUTER JOIN dept d
ON e.deptno=d.deptno

自连接
自连接保存的数据是当前表的一条记录
可以对应当前表的多条记录
这种设计用于解决记录属性相同但记录间
又存在上下级关系的树状结构时使用。

查看每个员工以及其上司的名字?
SELECT e.ename,m.ename
FROM emp e,emp m
WHERE e.mgr=m.empno

SELECT e.ename,m.ename
FROM emp e JOIN emp m
ON e.mgr=m.empno

查看SMITH的上司在哪个城市?
SELECT e.ename,m.ename,d.loc
FROM emp e JOIN emp m
ON e.mgr = m.empno
JOIN dept d
ON m.deptno = d.deptno
WHERE e.ename='SMITH'

SELECT e.ename,m.ename,d.loc
FROM emp e,emp m,dept d
WHERE e.mgr = m.empno
AND m.deptno = d.deptno
AND e.ename='SMITH'

作业:
1:查看工资高于2000的员工
2:查看不是"CLERK"职位的员工
3:查看工资在1000-2500之间的员工
4:查看名字是以K结尾的员工
5:查看20,30号部门的员工
6:查看奖金为NULL的员工
7:查看年薪高于20000的员工
8:查看公司共有多少种职位
9:按部门号从小到大排列查看员工
10:查看每个部门的最高，最低，平均工资，和工资总和
11:查看平均工资高于2000的部门的最低薪水
12:查看在NEWYORK工作的员工
13:查看所有员工及所在部门信息，若该员工没有部门，则
    部门信息以NULL显示
14:查看ALLEN的上司是谁
