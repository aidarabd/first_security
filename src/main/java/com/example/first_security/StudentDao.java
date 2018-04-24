package com.example.first_security;

import com.example.first_security.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class StudentDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDao(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> getStudents()
    {
        return jdbcTemplate.query("select * from student", new RowMapper<Student>() {
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student student = new Student();

                student.setId(resultSet.getInt("id"));
                student.setFirstName(resultSet.getString("name"));
                student.setLastName(resultSet.getString("lastname"));
                student.setGroupName(resultSet.getString("groupname"));
                return student;
            }
        });
    }

    public Student getStudent(int id)
    {
        String sql = "SELECT * FROM student WHERE id = ?";
        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
        Student student = jdbcTemplate.queryForObject(sql, rowMapper, id);

        return student;
    }

    public Student getStudentByUsername(String username)
    {
        String sql = "SELECT * FROM student WHERE username = ?";
        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
        Student student = jdbcTemplate.queryForObject(sql, rowMapper, username);

        return student;
    }

    public boolean delete(int id)
    {
        return jdbcTemplate.update("delete from student where id = ?", id) == 1;
    }

    public boolean create(Student student)
    {
        return jdbcTemplate.update("insert into student (firstName, lastName, groupName) values (?, ?, ?)", student.getFirstName(), student.getLastName(), student.getGroupName()) == 1;
    }

    public boolean update(Student student)
    {
        return jdbcTemplate.update("update student set firstName =?, lastName=?, groupName=? where id=?", student.getFirstName(), student.getLastName(), student.getGroupName(), student.getId()) == 1;
    }
}