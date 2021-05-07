package com.tuling.dao;

import com.tuling.bean.User;
import com.tuling.bean.UserCache;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by smlz on 2019/6/26.
 */
@Component
public class UserDao {

    public Mono<User> findOneById(Integer userId) {
        return Mono.just(UserCache.userInfos.get(userId));
    }

    public Flux<User> findAll() {

        List<User> userList = new ArrayList<>();

        Set<Integer> keySet = UserCache.userInfos.keySet();
        Iterator<Integer> integerIterator = keySet.iterator();
        while (integerIterator.hasNext()) {
            Integer key = integerIterator.next();
            userList.add(UserCache.userInfos.get(key));
        }

        return Flux.fromIterable(userList);
    }

    public void save(User user) {
        UserCache.userInfos.put(user.getUserId(),user);
    }
}
