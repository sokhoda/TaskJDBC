/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentJDBCDAO {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public StudentJDBCDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public void add(StudentBean studentBean) {
        try {
            String queryString = "INSERT INTO student(id, Name, Course, Address) VALUES(?,?,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, studentBean.getId());
            ptmt.setString(2, studentBean.getName());
            ptmt.setString(3, studentBean.getCourse());
            ptmt.setString(4, studentBean.getAddress());
            ptmt.executeUpdate();
            System.out.println("Data Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void update(StudentBean studentBean) {

        try {
            String queryString = "UPDATE student SET Name=? WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, studentBean.getName());
            ptmt.setInt(2, studentBean.getId());
            ptmt.executeUpdate();
            System.out.println("Table Updated Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    public void delete(int id) {

        try {
            String queryString = "DELETE FROM student WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            ptmt.executeUpdate();
            System.out.println("Data deleted Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void findAll() {
        try {
            String queryString = "SELECT * FROM student";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                System.out.println("Id " + resultSet.getInt("id")
                        + ", Name " + resultSet.getString("Name") + ", Course "
                        + resultSet.getString("Course") + ", Address "
                        + resultSet.getString("Address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
