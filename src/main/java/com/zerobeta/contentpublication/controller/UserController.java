package com.zerobeta.contentpublication.controller;

import com.zerobeta.contentpublication.domain.BaseAuthRequest;
import com.zerobeta.contentpublication.domain.Profile;
import com.zerobeta.contentpublication.domain.Response;
import com.zerobeta.contentpublication.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Api(value = "User Auth Controller")
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:3000")
public class UserController {

    private  final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "singUp", response = Response.class, tags = "singUp")
    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> singUp(@RequestBody BaseAuthRequest singUpRequest){

        logger.info("Entering into singUp for UserName ::{}", singUpRequest.getLoginName());
        return ResponseEntity.ok().body(userService.singUp(singUpRequest));
    }

    @ApiOperation(value = "singIn", response = Response.class, tags = "singIn")
    @PostMapping("/signin")
    public ResponseEntity<Response> singIn(@RequestBody BaseAuthRequest loginRequest) {

        logger.info("Entering into singIn for UserName ::{}", loginRequest.getLoginName());
        return ResponseEntity.ok().body(userService.singIn(loginRequest));
    }

    @ApiOperation(value = "completeProfile", response = Response.class, tags = "completeProfile")
    @PostMapping(value = "/completeprofile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> completeProfile(@RequestBody Profile profile){

        logger.info("Entering into completeProfile ::{}",profile );
        return ResponseEntity.ok().body(userService.completeProfile(profile));
    }

    @ApiOperation(value = "contentSubscribe", response = Response.class, tags = "contentSubscribe")
    @GetMapping(value = "/contentsubscribe", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> contentSubscribe(@RequestParam(name = "contentCatagoryName") String contentCatagoryName,
                                                     @RequestParam(name = "subscribeStatus") Integer subscribeStatus){

        logger.info("Entering into contentSubscribe ::{}",contentCatagoryName );
        return ResponseEntity.ok().body(userService.contentSubscribe(contentCatagoryName, subscribeStatus));
    }

    @ApiOperation(value = "getUserSubscribe", response = Response.class, tags = "getUserSubscribe")
    @GetMapping(value = "/getusersubscribe", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getUserSubscribe(){

        logger.info("Entering into contentSubscribe" );
        return ResponseEntity.ok().body(userService.getUserSubscribe());
    }

    @ApiOperation(value = "singOut", response = Response.class, tags = "singOut")
    @GetMapping("/signout")
    public ResponseEntity<Response> singOut(HttpServletResponse httpServletResponse) {

        logger.info("Entering into singOut");
        return ResponseEntity.ok().body(userService.singOut(httpServletResponse));
    }

    @ApiOperation(value = "checkLoginName", response = Response.class, tags = "checkLoginName")
    @GetMapping("/checkloginname")
    public ResponseEntity<Response> checkLoginName(@RequestParam(name = "loginName") String loginName) {

        logger.info("Entering into checkLoginName  ::{}", loginName);
        return ResponseEntity.ok().body(userService.checkLoginName(loginName));
    }
}
