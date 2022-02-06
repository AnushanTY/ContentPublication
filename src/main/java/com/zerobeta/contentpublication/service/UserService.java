package com.zerobeta.contentpublication.service;

import com.zerobeta.contentpublication.domain.BaseAuthRequest;
import com.zerobeta.contentpublication.domain.Profile;
import com.zerobeta.contentpublication.domain.Response;
import com.zerobeta.contentpublication.entity.User;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

    Response singUp(BaseAuthRequest singUpRequest);

    Response completeProfile(Profile profile);

    Response contentSubscribe(String contentCatagoryName, Integer subbscribeStatus);

    Response getUserSubscribe();

    Response singIn(BaseAuthRequest singInRequest);

    Response singOut(HttpServletResponse httpServletResponse);

    Response checkLoginName(String loginName);
}
