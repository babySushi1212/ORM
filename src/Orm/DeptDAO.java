package Orm;

import java.util.List;

public interface DeptDAO {
    void insert(Dept dept);
    void update(Dept dept);
    void deleteByDeptno(Integer deptno);
    Dept getByDeptno(Integer deptno);
    List<Dept> getAll();
}

