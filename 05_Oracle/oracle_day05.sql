��ͼ
��ͼ�����ݿ����֮һ
��ͼ��SQL��������ֵĽ�ɫ���һ�£�
���䲢���Ǳ�����ֻ�Ƕ�Ӧ��һ����ѯ
���Ľ������
CREATE VIEW v_emp_10
AS
SELECT empno,ename,sal,deptno
FROM emp
WHERE deptno=10

��ѯ��ͼ���ѯ��һ��:
SELECT * 
FROM v_emp_10

�鿴��ͼ�Ľṹ:
DESC v_emp_10

��ͼ��Ӧ���Ӳ�ѯ�����к������߱���ʽ
��ô�������������һ���ֶ�ʹ���˱���
��ô��ͼ�и��ֶε����־������������

�޸���ͼ
������ͼֻ�Ƕ�Ӧ��һ����ѯ��䣬�����޸�
��ͼ�����滻��SQL��䡣

CREATE OR REPLACE VIEW v_emp_10
AS
SELECT empno id,ename name,
       sal salary,deptno
FROM emp
WHERE deptno=10

����ͼ����DML����
����ͼ����DML�������Ƕ���ͼ������Դ
�Ļ��������еĲ���������ֻ�ܶ���ͼ��
����ֶν��С�

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

ͨ������ͼ���������ݣ�����ͼ���ɼ�
��ô�͵�ͬ�ڶԻ������ݽ�������Ⱦ��

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

ɾ������Ի�����Ⱦ
DELETE FROM v_emp_10
WHERE deptno=20

Ϊ��ͼ���Ӽ��ѡ����Ա������ͼ
����DML������Ի�����������Ⱦ�������
���ѡ��Ҫ�����ͼ���е�DML����������
��ͼ�������ɼ�����������������
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

READ ONLYѡ��
����ͼ������ֻ��ѡ��󣬸���ͼ���ܲ鿴
����ִ���κ�DML������
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

�����ֵ�
USER_OBJECTS:��¼���û�����������
���ݿ����

USER_VIEWS:��¼���û�������������ͼ
USER_TABLES:��¼���û����������б�

SELECT text FROM user_views 
WHERE view_name = 'V_EMP_10';

SELECT table_name FROM user_tables

������ͼ:
����һ�������Ź����������ͼ
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

�鿴���Լ����ڲ���ƽ�����ʸߵ�Ա��?
SELECT e.ename,e.sal,e.deptno
FROM emp e,v_emp_dept_salinfo v
WHERE e.deptno=v.deptno
AND e.sal>v.avg_sal

ɾ����ͼ
ɾ����ͼ�е�����ʱ�����Ӧ�Ľ�����
����ɾ��������ɾ����ͼ����ʱ���ǲ���
Ӱ������κ����ݵġ�
DROP VIEW v_emp_10

���У����ݿ����֮һ��
��������������һϵ�����ֵġ�
�������ɵ����ֳ�������ĳ�ű�����
�ֶε�ֵ��
CREATE SEQUENCE seq_emp_id
START WITH 1
INCREMENT BY 1

�����ṩ������α�����ڻ�ȡ��ǰ���е�ֵ��
NEXTVAL:��ȡ������һ���������α�л�
�������з��������������ǲ��ܻ��˵ġ���:
������һ�����ֺ󣬾Ͳ��ܵõ�֮ǰ�������ˡ�

CURRVAL:��ȡ���е�ǰֵ(���һ�����ɵ�ֵ)
���۵��ö��ٴΣ������ᵼ�����з���������
�´���������Ҫ���ٵ���һ��NEXTVAL���
����ʹ��CURRVAL��

SELECT seq_emp_id.NEXTVAL
FROM dual

INSERT INTO emp
(empno,ename,sal,job,deptno)
VALUES
(seq_emp_id.NEXTVAL,'ROSE','4500','CLERK',10)

SELECT * FROM emp

ɾ������
DROP SEQUENCE seq_emp_id

���������ݿ����֮һ����������߲�ѯЧ�ʡ�
�����Ľ��������ݿ�ִ����ɵģ����̶�������͸���ġ�
����ֻ��Ҫ�������ݿ��Ƿ������������ɡ�
������Ӧ��Ҳ���Զ��ģ������ڲ�ѯ�Ĺ���
�и�֪���ݿ��Ƿ�ʹ�����������ݿ������
�жϿ����������Զ�ʹ�á�

CREATE INDEX idx_emp_ename 
ON emp(ename)
��ֻ��ename��Ϊ��������(����LIKE),��Ϊ
������ֶΣ�ȥ�صȲ���ʱ�����ݿ���Զ�ʹ��
����idx_emp_ename��߲�ѯЧ��




Լ����Ϊ����Լ�����м�Լ��
NOT NULLֻ���м�Լ��
����Լ�����Ǳ���Ҳ���м�
�м�Լ��:Ϊĳ���ֶ�����Լ��ֻ��
�ڲ������е�ͬʱ���С�
����Լ��:����ֱ�ӶԱ���������Լ��
��ָ��Ϊ�ñ����Ǹ��ֶ�����
��˵:����Լ��ʱ���﷨��һ����

Ψһ��Լ��
Ψһ��Լ������Ҫ��ĳ���ֶ��ڱ����κ�
���е�ֵ������ͬ��NULLֵ���⡣
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

����Լ��
����Լ��ֻ�ܽ����ڵ����ϣ�����һ�ű�
ֻ����һ������Լ��������Լ�����Ա�֤
���ֶηǿ���Ψһ
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








