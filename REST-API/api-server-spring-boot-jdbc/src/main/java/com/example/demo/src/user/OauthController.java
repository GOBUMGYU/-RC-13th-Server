package com.example.demo.src.user;

import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/oauth")
public class OauthController {

    private final OauthService oauthService;

    public OauthController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    @ResponseBody
    @GetMapping("/naver")
    public void naverCallBack(@RequestParam("code") String code, HttpSession httpSession) {
        System.out.println("code = " + code);
        String access_Token = oauthService.getNaverAccessToken(code);
    }
}


