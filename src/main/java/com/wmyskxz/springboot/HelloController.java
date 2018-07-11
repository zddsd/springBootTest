package com.wmyskxz.springboot;

import com.wmyskxz.springboot.entity.Department;
import com.wmyskxz.springboot.entity.Role;
import com.wmyskxz.springboot.entity.User;
import com.wmyskxz.springboot.repository.DepartmentRepository;
import com.wmyskxz.springboot.repository.RoleRepository;
import com.wmyskxz.springboot.repository.UserRepository;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.PageRequest.of;

@RestController
public class HelloController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Value("${content}")
    private String content;

    @RequestMapping("/hello")
    public String hello() {

        return content;
    }


    @GetMapping("/user")
    public List<User> findUser (){
        List<User> users=userRepository.findByUsernameLike("张%");
        return users;
    }

    @RequestMapping("/pageNum")
    public int findPageNum(){
        Pageable pageable =  PageRequest.of(0, 5, new Sort(Sort.Direction.ASC, "id"));
        Page<Department> page = departmentRepository.findAll(pageable);
        System.out.println(page.getNumberOfElements());

        return  page.getNumberOfElements();
    }

    @PostMapping("/user")
    public int addUser(@RequestBody User user){
        Optional<Department> d =departmentRepository.findById((long) 1);
        Optional<Role> r =roleRepository.findById((long) 1);
        List<Role> roles = new ArrayList<Role>();
        roles.add(r.get());
        User u = new User(user);
        u.setDepartment(d.get());
        u.setRoles(roles);
        userRepository.save(u);

        return  200;
    }

    @PutMapping("/user")
    public int putUser(@RequestParam(name="userName",required = true) String userName,@RequestParam(name="afterUserName",required = true)String afterUserName){
            List<User> users=userRepository.findByUsername(userName);
            for(User user:users){
                user.setUsername(afterUserName);
                user.setCreateDate(new Date());
            }
            userRepository.saveAll(users);

            return 200;
    }

    @DeleteMapping("/user")
    public int deleteUser(@RequestBody User user){
       System.out.println(user.getUsername());
       /*  List<User> users=userRepository.findByUsername(userName);
        System.out.println(users.size());
        userRepository.deleteAll(users);*/
      //  userRepository.deleteById(id);
        return 200;
    }

    @DeleteMapping("/user/{id}")
    public int deleteUser(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return 200;
    }

}
