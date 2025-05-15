package com.ho.bootframework.auth.controller;

import com.ho.bootframework.auth.dto.TokenResponse;
import com.ho.bootframework.auth.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final JwtProvider jwtProvider;

  public AuthController(JwtProvider jwtProvider) {
    this.jwtProvider = jwtProvider;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestParam String userId) {
    String token = jwtProvider.generateAccessToken(userId);
    return ResponseEntity.ok(token);
  }

  @GetMapping("/auth/oauth2/success")
  public TokenResponse oauth2Success(@AuthenticationPrincipal OAuth2User oAuth2User, HttpServletResponse response) {
    // OAuth2User에서 사용자 정보 가져오기 (예: email)
    String email = oAuth2User.getAttribute("email");

    // JWT 생성
    String accessToken = jwtProvider.generateAccessToken(email);
    String refreshToken = jwtProvider.generateRefreshToken(email);

    // 필요하면 Refresh Token 쿠키에 저장 가능 (생략 가능)
    // 예: CookieUtil.addRefreshTokenCookie(response, refreshToken);

    return new TokenResponse(accessToken, refreshToken);
  }
}