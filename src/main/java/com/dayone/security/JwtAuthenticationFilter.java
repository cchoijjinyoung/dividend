package com.dayone.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // 컨트롤러로 요청이 들어오기 전에,
    // 제일 먼저 Filter를 거치게된다. -> servlet -> 인터셉터 -> AOP layer -> Controller
    // 응답이 나갈때는 반대로 거치게됨.
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 요청이 들어올 때마다 토큰이 유효한지 안한지 체크작업해줄예정.
        String token = this.resolveTokenFromRequest(request);

        if (StringUtils.hasText(token) && this.tokenProvider.validateToken(token)) {
            Authentication auth = this.tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);

            log.info(String.format("[%s] -> %s : %s", this.tokenProvider.getUsername(token), request.getMethod(), request.getRequestURI()));
        }

        filterChain.doFilter(request, response);
    }

    // 요청 헤더에서 token 가져오기
   private String resolveTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);
        if (!ObjectUtils.isEmpty(token) && token.startsWith(TOKEN_PREFIX)) {
            return token.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
