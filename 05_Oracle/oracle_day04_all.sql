子查询
子查询也是一条查询语句，只是它嵌套在
其他SQL语句之中，作用是为外层的SQL
提供数据。

查看比CLARK工资高的员工?
SELECT ename,sal
FROM emp
WHERE sal>(SELECT sal
          FROM emp
          WHERE ename='CLARK')

谁与SMITH相同部门?
SELECT ename,deptno
FROM emp
WHERE deptno=(SELECT deptno
              FROM emp
              WHERE ename='SMITH')

查看比公司平均工资高的员工?
SELECT ename,sal
FROM emp
WHERE sal>(SELECT AVG(sal)
           FROM emp)


子查询也可以在DDL和DML中使用。
DDL中使用子查询:
基于一个子查询的结果集快速创建一张表
CREATE TABLE myemployee 
AS
SELECT e.empno,e.ename,e.job,
       e.deptno,d.dname,d.loc
FROM emp e,dept d
WHERE e.deptno=d.deptno(+)

SELECT * FROM myemployee

DML中使用子查询
将SMITH部门所有员工工资提高10%:
UPDATE emp
SET sal=sal*1.1
WHERE deptno=(SELECT deptno
              FROM emp
              WHERE ename='SMITH')

查看与职位是SALESMAN相同部门
的其他职位员工?
SELECT ename,job,deptno
FROM emp
WHERE deptno IN (SELECT deptno
                 FROM emp
                 WHERE job='SALESMAN')
AND job <> 'SALESMAN'

查看比职位是CLERK和SALESMAN工资都高的
员工?
SELECT ename,sal
FROM emp
WHERE sal > ALL(SELECT sal
                FROM emp
                WHERE job IN('CLERK','SALESMAN'))

EXISTS关键字
EXISTS后面要跟一个子查询，当该子查询
可以至少查询出一条记录时，EXISTS返回
真。NOT EXISTS这是相反的操作。
SELECT deptno, dname 
FROM dept d
WHERE EXISTS 
     (SELECT * FROM emp e
      WHERE d.deptno = e.deptno)

HAVING中使用子查询:
查看最低薪水高于30号部门最低薪水的部门?
SELECT MIN(sal),deptno
FROM emp
GROUP BY deptno
HAVING MIN(sal)>(SELECT MIN(sal)
                 FROM emp
                 WHERE deptno=30)


多列子查询常被当做一张表看待而出现在
FROM子句中。
查看高于自己部门平均工资的员工?
SELECT e.ename,e.sal,e.deptno
FROM emp e,
    (SELECT AVG(sal) avg_sal,deptno
     FROM emp
     GROUP BY deptno) t
WHERE e.deptno=t.deptno
AND e.sal>t.avg_sal

子查询中的字段若是函数或者表达式
那么必须给别名。

SELECT子句中也可以使用子查询：
SELECT 
 e.ename, e.sal, 
 (SELECT d.dname FROM dept d 
 WHERE d.deptno = e.deptno) dname
FROM emp e

分页查询
分页查询是将查询的数据分段显示
这样做的目的可以减少资源占用，
提高响应速度。
分页在不同的数据库中的SQL语句是
不一样的(方言)

ORACLE中的分页是依靠伪列:ROWNUM实现的
ROWNUM不存在与任何表中，但是任何表都可以
查询该列，该列的值时随着查询数据的过程中
生成的，只要可以查询出一条记录，那么ROWNUM
字段的值就是该记录的行号，从1开始。

在使用ROWNUM对结果集进行编号的过程中
不要使用ROWNUM做>1以上的过滤判断，否则
结果集将得不到任何记录
SELECT ROWNUM,ename,sal,deptno
FROM emp
WHERE ROWNUM >1

查看6-10记录
SELECT *
FROM (SELECT 
       ROWNUM rn,ename,sal,deptno
      FROM emp)
WHERE rn BETWEEN 6 AND 10

查看公司工资排名的第6-10名:
SELECT *
FROM (SELECT 
       ROWNUM rn,ename,sal,deptno
      FROM emp)
WHERE rn BETWEEN 6 AND 10
ORDER BY sal DESC

由于排序在查询语句中的执行顺序
是最低的，所以，在分页中若有排序
需求时，应当最先排序。然后再根据
排序的结果进行分页查询。
SELECT *
FROM(SELECT ROWNUM rn,t.*
     FROM(SELECT ename,sal,deptno
          FROM emp
          ORDER BY sal DESC) t)
WHERE rn BETWEEN 6 AND 10


SELECT *
FROM(SELECT ROWNUM rn,t.*
     FROM(SELECT ename,sal,deptno
          FROM emp
          ORDER BY sal DESC) t
     WHERE ROWNUM<=10)
WHERE rn >=6

根据页数以及每页显示的条目数来求
范围的公式:
pageSize:5   每页显示的条目数
page:2       页数
start = (page-1)*pageSize+1
end = pageSize*page

java伪代码:
int pageSize=5;
int page=2;
int start= (page-1)*pageSize+1;
int end = pageSize*page;

String sql = "SELECT * "+
             "FROM(SELECT ROWNUM rn,t.* "+
             "     FROM(SELECT ename,sal,deptno "+
             "          FROM emp "+
             "          ORDER BY sal DESC) t) "+
             "WHERE rn BETWEEN "+start+" AND "+end;

DECODE函数
DECODE函数可以实现分支的效果

SELECT ename, job, sal,
     DECODE(job,  
            'MANAGER', sal*1.2,
            'ANALYST', sal*1.1,
            'SALESMAN', sal*1.05,
            sal) bonus
FROM emp

可以利用DECODE将字段值不一样的记录看做
同一组，只需要将看做一组的记录该字段值
替换为一个相同值即可。
SELECT
  COUNT(*),DECODE(job,
           'MANAGER','VIP',
           'ANALYST','VIP',
           'OTHER')
FROM emp
GROUP BY DECODE(job,
         'MANAGER','VIP',
         'ANALYST','VIP',
         'OTHER')


可以按照自定的顺序排序
SELECT deptno, dname, loc
FROM dept
ORDER BY 
    DECODE(dname, 
          'OPERATIONS',1,
          'ACCOUNTING',2,
          'SALES',3)


排序函数
排序函数允许对结果集按照给定的字段分组，
然后在组内按照指定的字段排序，然后生成
一个组内的编号。

ROW_NUMBER生成组内连续且唯一的数字
查看每个部门工资排名
SELECT 
 ename,sal,deptno,
 ROW_NUMBER() OVER(
  PARTITION BY deptno
  ORDER BY sal DESC 
 ) rank
FROM emp

RANK:生成组内不连续且不唯一的数字
SELECT 
 ename,sal,deptno,
 RANK() OVER(
  PARTITION BY deptno
  ORDER BY sal DESC 
 ) rank
FROM emp

DENSE_RANK:生成组内连续但不唯一的数字
SELECT 
 ename,sal,deptno,
 DENSE_RANK() OVER(
  PARTITION BY deptno
  ORDER BY sal DESC 
 ) rank
FROM emp

高级分组函数
高级分组函数可以按照该函数要求的
分组方式对数据进行分组统计，然后将
这些分组方式统计的结果并到一个结果集
中显示。
相比我们按照不同分组方式分别统计结果
后再使用UNION ALL并在一起，书写起来
要简便的多。

SELECT 
 year_id,month_id,
 day_id,sales_value
FROM
 sales_tab
ORDER BY
 year_id,month_id,day_id

查看每天营业额?
SELECT 
 year_id,month_id,
 day_id,SUM(sales_value)
FROM sales_tab
GROUP BY 
 year_id,month_id,day_id
ORDER BY
 year_id,month_id,day_id

每月营业额?
SELECT
 year_id,month_id,SUM(sales_value)
FROM sales_tab
GROUP BY
 year_id,month_id
ORDER BY
 year_id,month_id

每年营业额?
SELECT
 year_id,SUM(sales_value)
FROM sales_tab
GROUP BY
 year_id
ORDER BY
 year_id

总收入
SELECT
 SUM(sales_value)
FROM sales_tab


在一个结果集中，体现每天，每月，每年
以及总收入?
GROUP BY ROLLUP(a,b,c)
等同于
GROUP BY a,b,c
UNION ALL
GROUP BY a,b
UNION ALL
GROUP BY a
UNION ALL
全表

SELECT 
 year_id,month_id,
 day_id,SUM(sales_value)
FROM sales_tab
GROUP BY 
 ROLLUP(year_id,month_id,day_id)
ORDER BY
 year_id,month_id,day_id

GROUP BY CUBE(a,b,c)
等同
a,b,c
a,b
a,c
b,c
a
b
c
全表

SELECT 
 year_id,month_id,
 day_id,SUM(sales_value)
FROM sales_tab
GROUP BY 
 CUBE(year_id,month_id,day_id)
ORDER BY
 year_id,month_id,day_id

GROUPING SETS()
每个参数是一种分组方式，然后将这些分组
统计的结果并在一个结果集显示。
由于分组方式可以通过参数传入，所以相比
ROLLUP,CUBE的内定分组方式要灵活。

查看每月与每天的营业额?
SELECT 
 year_id,month_id,
 day_id,SUM(sales_value)
FROM sales_tab
GROUP BY 
 GROUPING SETS(
  (year_id,month_id),
  (year_id,month_id,day_id)
 )
ORDER BY
 year_id,month_id,day_id





