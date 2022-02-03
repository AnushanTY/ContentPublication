package com.zerobeta.contentpublication.serviceimpl;

import com.zerobeta.contentpublication.domain.BaseAuthRequest;
import com.zerobeta.contentpublication.domain.Profile;
import com.zerobeta.contentpublication.domain.Response;
import com.zerobeta.contentpublication.domain.UserInfo;
import com.zerobeta.contentpublication.entity.ContentCategory;
import com.zerobeta.contentpublication.entity.Role;
import com.zerobeta.contentpublication.entity.User;
import com.zerobeta.contentpublication.entity.UserDetail;
import com.zerobeta.contentpublication.respository.ContentCategoryRepository;
import com.zerobeta.contentpublication.respository.RoleRepository;
import com.zerobeta.contentpublication.respository.UserRepository;
import com.zerobeta.contentpublication.security.CustomUserDetails;
import com.zerobeta.contentpublication.security.JwtTokenUtil;
import com.zerobeta.contentpublication.service.UserService;
import com.zerobeta.contentpublication.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private  final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ContentCategoryRepository contentCategoryRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    @Transactional
    public Response singUp(BaseAuthRequest singUpRequest) {

        User user = new User();
        user.setLoginName(singUpRequest.getLoginName());
        user.setPassword(encoder.encode(singUpRequest.getPassword()));
        user.setProfileCompleted(Boolean.FALSE);
        user.setCreatedDate(new Date());

        Optional<Role> role = roleRepository.findById(1);
        if (role.isPresent()){
            user.getUserRole().add(role.get());
            role.get().getUsers().add(user);
        }

        return Response.success(userRepository.save(user));

    }

    @Override
    @Transactional
    public Response completeProfile(Profile profile) {

        Optional<User> user = userRepository.findById(profile.getId());

        if (user.isPresent()){
            user.get().setProfileCompleted(true);
            user.get().setProfileCompletedDate(new Date());
            Optional<Role> role = roleRepository.findById(2);
            Optional<ContentCategory> contentCategory = contentCategoryRepository.findById(1);

            if (role.isPresent()){
                user.get().getUserRole().add(role.get());
                role.get().getUsers().add(user.get());
            }

            UserDetail userDetail = new UserDetail();
            userDetail.setUser(user.get());
            userDetail.setName(profile.getName());
            userDetail.setCountry(profile.getCountry());
            userDetail.setDescriptions(profile.getDescriptions());

            user.get().setUserDetail(userDetail);

            return Response.success(userRepository.save(user.get()));

        } else {
            return Response.success("User Not found");
        }
    }

    @Override
    public Response contentSubscribe(Integer contentId) {

        Optional<User> user = userRepository.findById(Util.getUserDetails().getId());
        Optional<ContentCategory> contentCategory = contentCategoryRepository.findById(1);

        if (user.isPresent()){
            if (contentCategory.isPresent()){
                user.get().getContentCategories().add(contentCategory.get());
                contentCategory.get().getUsers().add(user.get());
            }
            return Response.success(userRepository.save(user.get()));
        } else {
            return Response.failure("user not found");
        }
    }

    @Override
    public Response getUserSubscribe() {
        Optional<User> user = userRepository.findById(Util.getUserDetails().getId());
        return user.map(value -> Response.success(value.getContentCategories().stream()
                .map(ContentCategory::getCategoryName).
                        collect(Collectors.toList()))).orElseGet(() -> Response.failure("user not found"));

    }

    @Override
    public Response singIn(BaseAuthRequest singInRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(singInRequest.getLoginName(),
                        singInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String jwt = jwtTokenUtil.generateTokenFromUsername(userDetails.getUsername());

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        Optional<User> user = userRepository.findById(Util.getUserDetails().getId());

        if (user.isPresent()){
            user.get().setLastLoginDate(new Date());

            UserInfo userInfo = new UserInfo();
            userInfo.setAccessToken(jwt);
            userInfo.setId(userDetails.getId());
            userInfo.setProfileCompleted(userDetails.getProfileCompleted());
            userInfo.setLoginName(userDetails.getUsername());
            userInfo.setRoles(roles);

            userRepository.save(user.get());
            return Response.success(userInfo);
        } else {
            return Response.success("User Not found");
        }
    }

    @Override
    public Response singOut(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader(HttpHeaders.AUTHORIZATION, null);

        return Response.success("User Successfully logout");
    }

    @Override
    public Response checkLoginName(String loginName) {
        return Response.success(userRepository.findByLoginName(loginName) != null);
    }
}
