package com.tuling.bean.handler;

import com.tuling.bean.User;
import com.tuling.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * Created by smlz on 2019/6/26.
 */
@Component
public class UserHandler {

    @Autowired
    private UserDao userDao;

    /**
     * 查询当个
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> findOne(ServerRequest serverRequest) {
        Optional<String> optional = serverRequest.queryParam("userId");
        Integer userId = Integer.valueOf(optional.get());
        return ok().contentType(APPLICATION_JSON).body(userDao.findOneById(userId), User.class);
    }

    /**
     * 查询列表
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> findAll(ServerRequest serverRequest){
        Flux<User> userFlux = userDao.findAll();
        return ok().contentType(APPLICATION_JSON).body(userFlux, User.class);
    }

}
