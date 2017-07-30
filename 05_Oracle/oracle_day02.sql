DQL查询语句
SELECT语句用来查询表中数据
一条SQL语句是由多个子句组成，
一个子句有一个关键字加一系列内容组成。
SELECT语句中必须含有的是两个子句，
SELECT子句和FROM子句
SELECT子句用于指定要查询的内容
(字段,函数,表达式)，FROM子句用来
指定数据来源的表

SELECT ename,job,sal,deptno
FROM emp
WHERE deptno=20

SELECT ename,job,sal,deptno
FROM emp
WHERE sal>2500

查看每个员工的年薪
SELECT ename,sal,sal*12
FROM emp

字符串函数:
CONCAT函数：连接字符串
SELECT CONCAT(ename,sal)
FROM emp

SELECT CONCAT(CONCAT(ename,':'),sal)
FROM emp

连接字符串常用"||"
SELECT ename||':'||sal
FROM emp

LENGTH函数:字符串长度
SELECT ename, LENGTH(ename) 
FROM emp

名字4个字母的员工?
SELECT ename
FROM emp
WHERE LENGTH(ename)=4


UPPER,LOWER,INITCAP
转换大小写
dual:伪表,当查询的内容不是任何
表中数据时，可以用伪表代替。
SELECT 
  UPPER('helloworld'),
  LOWER('HELLOWORLD'),
  INITCAP('HELLO WORLD')
FROM dual

SELECT ename,sal,deptno
FROM emp
WHERE ename=UPPER('scott')

TRIM,LTRIM,RTRIM
去除字符串两侧的指定字符
SELECT 
 TRIM('e' FROM 'eeeeliteee')
FROM
 dual

SELECT 
 LTRIM('etsettstestesliteee','ets')
FROM
 dual

LPAD,RPAD补位函数 
SELECT 
  ename, LPAD(sal,6,' ') 
FROM emp 


SUBSTR函数：截取字符串
SUBSTR(str,m,n)
截取str字符串从m处开始连续
截取n个字符
数据库中下标都是从1开始的!
SELECT 
  SUBSTR('thinking in java',10,2)
FROM 
  dual
  
省略截取数量，或截取数量超过实际内容都是
截取到末尾。
SELECT 
  SUBSTR('thinking in java',10,100)
FROM 
  dual

若位置是负数，这是从倒数位置开始截取
SELECT 
  SUBSTR('thinking in java',-7,2)
FROM 
  dual


INSTR:查看字符串的位置
INSTR(str1,str2,m,n)
查找str2在str1中的位置
m,n是可选项，分别表示:
m:从第几个字符开始查找
n:第几次出现的位置
SELECT
 INSTR('thinking in java','in',4,2)
FROM 
 dual


数字函数
ROUND(m,n):四舍五入
保留m到小数点后n位
n为0或不指定这是保留到整数
n为负数这是保留到小数点前的位数
SELECT ROUND(45.678,2) FROM DUAL
SELECT ROUND(45.678,0) FROM DUAL
SELECT ROUND(45.678,-1) FROM DUAL

TRANC(m,n):截取数字
参数作用于ROUND一致
SELECT TRUNC(45.678,2) FROM DUAL
SELECT TRUNC(45.678,0) FROM DUAL
SELECT TRUNC(45.678,-1) FROM DUAL

MOD(m,n):求余
SELECT ename,sal,MOD(sal,1000) 
FROM emp; 

CEIL,FLOOR:向上取整，向下取整
SELECT CEIL(45.678) FROM DUAL 
SELECT FLOOR(45.678) FROM DUAL

日期相关操作
关键字:
SYSDATE:对应数据库一个内置函数，
返回一个DATE类型的值，该值表示
当前系统时间。
SYSTIMESTAMP:返回当前系统时间
的时间戳类型的值。
SELECT SYSDATE FROM dual
SELECT SYSTIMESTAMP FROM dual

日期转换函数
TO_DATE()可以将一个字符串按照给定
的日期格式解析为一个DATE类型的值

日期格式字符串中凡不是英文，符号，数字
之外的其他字符要使用双引号括起来
SELECT
 TO_DATE('2008年08月08日 20:08:05',
         'YYYY"年"MM"月"DD"日" HH24:MI:SS'
        )
FROM
 dual

TO_CHAR():
将日期按照指定的格式转换为字符串
SELECT 
 TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI:SS')
FROM 
 dual

日期类型可以比大小，越晚的越大
日期类型之间可以进行减法，差为相差的天数
日期可以进行减价数字，等同于加减天数
  
查看每个员工入职至今多少天?
SELECT ename,SYSDATE-hiredate
FROM emp

SELECT SYSDATE-TO_DATE('1992-07-05','YYYY-MM-DD')
FROM dual

SELECT
 TO_CHAR(
  TO_DATE('49-08-01','RR-MM-DD'),
  'YYYY-MM-DD'
 )
FROM
 dual

LAST_DAY(date)
查看给定日期所在月的月底
SELECT LAST_DAY(SYSDATE)
FROM dual

ADD_MONTHS(date,i)
对给定日期加上指定的月，若i
为负数，则是减去
SELECT 
 ename, 
 ADD_MONTHS(hiredate,20*12)
FROM emp



MONTHS_BETWEEN(date1,date2)
计算两个日期之间相差的月，计算
是用date1-date2换算得到的
SELECT 
 ename,
 MONTHS_BETWEEN(SYSDATE,hiredate)
FROM
 emp

NEXT_DAY(date,i)
返回给定日期第二天开始一周内
的周几的日期。i表示周几:
1为周日，2为周一，以此类推
SELECT NEXT_DAY(SYSDATE,6)
FROM dual

LEAST,GREATEST
求最小值与最大值
参数至少一个。
SELECT 
 LEAST(SYSDATE, 
 TO_DATE('2008-10-10','YYYY-MM-DD')
 ) 
FROM DUAL;

EXTRACT函数:
提取指定日期中指定时间分量的值
DATE可以提取年月日，时间戳还可以
提取时分秒
SELECT 
 EXTRACT(YEAR FROM SYSDATE) 
FROM 
 dual;

查看82年入职的员工?
SELECT ename,sal,hiredate
FROM emp
WHERE 
 EXTRACT(YEAR FROM hiredate)=1982

CREATE TABLE student
    (id NUMBER(4), name CHAR(20), gender CHAR(1));

INSERT INTO student VALUES(1000, '李莫愁', 'F');

INSERT INTO student VALUES(1001, '林平之', NULL);

INSERT INTO student(id, name) VALUES(1002, '张无忌');

UPDATE student 
SET gender=NULL
WHERE id=1000

SELECT * FROM student

在判断某个字段的值是否为空时，要使用
IS NULL和IS NOT NULL判断。不能用
等号"="判断为空
UPDATE student
SET gender='M'
WHERE gender IS NULL

NULL的操作
NULL与字符串连接，等于什么都没做
NULL与数字计算，结果还是NULL

SELECT 
 ename,sal,comm,sal+comm
FROM 
 emp
 
空值函数
NVL(a1,a2)
若a1为NULL则函数返回a2
否则返回a1自身
所以该函数的作用是将NULL值替换为
非NULL值

SELECT 
 ename,sal,comm,sal+NVL(comm,0)
FROM 
 emp

若该员工有奖金，则显示为"有奖金",
若该员工奖金为NULL，则显示"没有奖金"
NVL2(a1,a2,a3)
当a1不为NULL时，函数返回a2
当a1为NULL时，函数返回a3
SELECT 
 ename,comm,
 NVL2(comm,'有奖金','没有奖金')
FROM
 emp

SELECT 
 ename,sal,comm,
 NVL2(comm,sal+comm,sal)
FROM
 emp





