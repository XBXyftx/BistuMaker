package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    /*
     * 增加用户
     */
    @Insert("insert into users (username, password, create_time, modify_time, role) "+
            "values (#{username} ,#{password},now(),now(),#{role})")
    void insert(User user);

    /*
     * 查询所有用户
     */
     @Select("select * from users")
     List<User> selectAllUser();

     /*
     *根据用户名
      */
    @Select("select * from users where username = #{username}")
    User selectByUsername(String username);

     /*
     * 删除用户
      */
    @Select("delete from users where id = #{id}")
    void deleteById(Integer id);

    /*
     * 修改用户
     */
    @Select("update users set username=#{username},password=#{password},modify_time=now() ,role=#{role} where id=#{id}")
    void update(User user);


}
