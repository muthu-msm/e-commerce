package org.mkt.user.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mkt.common.dto.VerifyTokenResponse;
import org.mkt.user.service.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {



    static final String AUTHORIZATION = "x-auth-token";

   private final AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);

        if (authHeader == null || authHeader.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            VerifyTokenResponse verifyTokenResponse = authenticationService.getByToken(authHeader);
            if (verifyTokenResponse != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (true) { //TODO validate expiry time
                    UserDetails userDetails = new UserDetailsDTO(verifyTokenResponse.getUser().getUsername(),
                            verifyTokenResponse.getUser().getPassword(),
                            verifyTokenResponse.getUser().getRole());
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                            null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Unauthorized: Try logging again");
                    response.getWriter().flush();
                    return;
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error(" error on token", e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: JWT Token has expired");
            response.getWriter().flush();
        }
    }

}
