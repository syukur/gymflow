package com.lylastudio.gymflow.util;

import com.lylastudio.gymflow.entity.MCompany;
import com.lylastudio.gymflow.entity.MEnpoint;
import com.lylastudio.gymflow.entity.MRole;
import com.lylastudio.gymflow.repository.MEnpointRespository;
import com.lylastudio.gymflow.repository.MRoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class Init {
    public static final String ROLE_SUPER_ADMIN = "SUPER_ADMIN";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_MEMBER = "MEMBER";

    public static final String ENDPOINT_AUTH_REGISTER = "/auth/register";
    public static final String ENDPOINT_AUTH_LOGIN = "/auth/login";
    public static final String ENDPOINT_AUTH_REFRESH_TOKEN = "/auth/refresh-token";
    public static final String ENDPOINT_AUTH_LOGOUT = "/auth/logout";
    public static final String ENDPOINT_AUTH_VERIFY_EMAIL = "/auth/verify-email";
    public static final String ENDPOINT_AUTH_FORGOT_PASSWORD = "/auth/forgot-password";
    public static final String ENDPOINT_AUTH_RESET_PASSWORD = "/auth/reset-password";

    public static final String ENDPOINT_API_HELLO = "/api/hello";
    public static final String ENDPOINT_API_ADMIN = "/api/admin/**";
    public static final String ENDPOINT_API_MEMBER = "/api/member/**";
    public static final String ENDPOINT_API_SUPER_ADMIN = "/api/super-admin/**";

    private final MEnpointRespository enpointRespository;
    private final MRoleRepository roleRepository;


    @PostConstruct
    private void insert(){
        log.info("INSERT START");
        insertEndpoint();
    }

    private void insertEndpoint() {
        MEnpoint e1 = new MEnpoint();
        e1.setEnpoint("/api/members/register");
        enpointRespository.save(e1);

        MEnpoint e2 = new MEnpoint();
        e2.setEnpoint("/api/companies");
        enpointRespository.save(e2);

//        MEnpoint e3 = new MEnpoint();
//        e1.setEnpoint("");
//        enpointRespository.save(e3);
//
//        MEnpoint e4 = new MEnpoint();
//        e1.setEnpoint("");
//        enpointRespository.save(e4);
    }

    private void insertRole(){

    }



}
