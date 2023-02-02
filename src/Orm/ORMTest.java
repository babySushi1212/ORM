package Orm;

import java.util.List;
import java.util.Scanner;

public class ORMTest {
    private static final DeptDAO DAO = new DeptDAOImpl();

    public static void main(String[] args) {
        ORMTest.queryByNo();
        ORMTest.queryAll();
        ORMTest.insert();
    }

    static void insert() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input [deptno] [dname] [loc]");
        Dept dept = new Dept(scanner.nextInt(), scanner.next(), scanner.next());
        DAO.insert(dept);
        System.out.println("Insert to success!");
    }

    static void queryByNo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input [deptno]");

        Dept dept = DAO.getByDeptno(scanner.nextInt());
        System.out.println(dept);
    }

    static void queryAll() {
        List<Dept> deptList = DAO.getAll();
        for (Dept d: deptList) {
            System.out.println(d);
        }
    }
}
