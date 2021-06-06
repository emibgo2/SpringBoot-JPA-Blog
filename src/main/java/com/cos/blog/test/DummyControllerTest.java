package com.cos.blog.test;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController

public class DummyControllerTest {
    @Autowired
    private UserRepository userRepository;

    @DeleteMapping("dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패하엿습니다 해당 id는 DB에 없습니다.";
        }

        return "삭제되었습니다. id:"+id;
    }

    // save함수는 id를 전달하지 않으면 insert를 해주고
    // save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를해주고
    // save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 한다.
    // email, password
    @Transactional // update시 save 사용 안할수 잇도록 해주는 어노테이션
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User requestUser) {
        System.out.println("id: "+id);
        System.out.println("password: "+requestUser.getPassword());
        System.out.println("email: "+ requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
        return new IllegalArgumentException("수정에 실패하였습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

    //    userRepository.save(user);
        return user;
    }

    // http://localhost:8000/blog/dummy/user/
    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    };

    // 한페이지당 2건에 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size =2,sort = "id",direction = Sort.Direction.DESC)Pageable pageable) {
        Page<User> pagingUser= userRepository.findAll(pageable);
        List<User> users    = pagingUser.getContent();
        return users;
    }

    //{id} 주소로 파라메터를 전달 받을 수 있음.
    // http:// localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {

        User user=userRepository.findById(id).orElseThrow(new Supplier<IllegalStateException>() {
            @Override
            public IllegalStateException get() {
                return new IllegalStateException("해당 유저는 없습니다."+id);
            }
        });
//        요청: 웹브라우저
//        user 객체 = 자바 오브젝트
//        변환(웹 브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
//        스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
//        만약에 자바 오브젝트를 리턴하게되면 MessageConverter가 Jackson 라이브러리를 호출해서
//        user 오브젝트를 json으로 변환해서 브라우저에게 던져준다
        return user;

    }

    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("id:" + user.getId());
        System.out.println("username:"+user.getUsername());
        System.out.println("password:"+user.getPassword());
        System.out.println("email:"+user.getEmail());
        System.out.println("role:"+user.getRole());

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }
}
