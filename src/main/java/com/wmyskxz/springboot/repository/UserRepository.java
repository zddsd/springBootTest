package com.wmyskxz.springboot.repository;


import com.wmyskxz.springboot.entity.Role;
import com.wmyskxz.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> ,JpaSpecificationExecutor<User> {
    //And用法
    User findById(long id);

    User findByIdAndUsername(long id, String username);

    //Or用法
    User findByIdOrUsername(long id, String name);

    //Between用法
    User findByCreateDateBetween(Date start, Date end);

    //LessThan用法
    List<User> findByCreateDateLessThan(Date start);

    //GreaterThan用法
    List<User> findByCreateDateGreaterThan(Date start);

    //IsNull/IsNutNull用法
    List<User> findByUsernameIsNull();
    List<User> findByUsername(String username);
    //Like/NotLike用法
    List<User> findByUsernameLike(String username);

    //OrderBy用法
    List<User> findByUsernameOrderByIdAsc(String username);

    //Not用法
    List<User> findByUsernameNot(String username);

    //In/NotIn用法
    List<User> findByUsernameIn(Collection<String> nameList);


}
