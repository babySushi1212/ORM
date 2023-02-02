package Orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptDAOImpl implements DeptDAO {
    private static final String INSERT_STATEMENT = """
        INSERT INTO department VALUES(?,?,?);
        """;
    private static final String SELECT_BY_DEPTNO = """
        SELECT * FROM department WHERE deptno = ?;
        """;
    private static final String SELECT_ALL = """
        SELECT * FROM department;
        """;

    @Override
    public void insert(Dept dept) {
        Connection conn = null;
        PreparedStatement preparedSTMT = null;

        try {
            conn = DriverManager.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASSWORD);
            preparedSTMT = conn.prepareStatement(INSERT_STATEMENT);
            preparedSTMT.setInt(1, dept.getDeptno());
            preparedSTMT.setString(2, dept.getDname());
            preparedSTMT.setString(3, dept.getLoc());
            preparedSTMT.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeResource(conn, preparedSTMT);
        }

    }

    @Override
    public void update(Dept dept) {

    }

    @Override
    public void deleteByDeptno(Integer deptno) {

    }

    @Override
    public Dept getByDeptno(Integer deptno) {
        Connection conn = null;
        PreparedStatement preparedSTMT = null;
        ResultSet set = null;
        Dept dept = null;

        try {
            conn = DriverManager.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASSWORD);
            preparedSTMT = conn.prepareStatement(SELECT_BY_DEPTNO);
            preparedSTMT.setInt(1, deptno);
            set = preparedSTMT.executeQuery();
            if (set.next()) {
                dept = new Dept();
                dept.setDname(set.getString("dname"));
                dept.setLoc(set.getString("loc"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeResource(conn, preparedSTMT, set);
        }
        return dept;
    }

    @Override
    public List<Dept> getAll() {
        Connection conn = null;
        PreparedStatement preparedSTMT = null;
        ResultSet set = null;
        List<Dept> list;

        try {
            conn = DriverManager.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASSWORD);
            preparedSTMT = conn.prepareStatement(SELECT_ALL);
            set = preparedSTMT.executeQuery();
            list = new ArrayList<>();
            while (set.next()) {
                list.add(
                    new Dept(set.getInt("deptno"), set.getString("dname"), set.getString("loc")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeResource(conn, preparedSTMT, set);
        }
        return list;
    }

}
