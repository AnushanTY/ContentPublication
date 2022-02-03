package com.zerobeta.contentpublication.util;


import com.zerobeta.contentpublication.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;


public class Util {

    public static CustomUserDetails getUserDetails(){
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
}
