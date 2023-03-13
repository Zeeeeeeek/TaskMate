package me.zeeeeeeek.backend.configs.filters;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zeeeeeeek.backend.services.jwt.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * This filter is fired anytime a request is made to the server.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;


    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String jwtToken;
        String username;
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            //If no auth header or not a Bearer token
            filterChain.doFilter(request, response);// continue the filter chain
            return;
        }
        jwtToken = authHeader.substring(7);// 7 is the length of "Bearer "
        try {
            username = jwtService.extractUsername(jwtToken);
        } catch (MalformedJwtException | ExpiredJwtException | SignatureException e ) {
            response.resetBuffer();
            response.setStatus(498);
            response.flushBuffer();
            return;
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // If the username has been extracted from the token, and it's not yet authenticated, then authenticate it
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        null
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);

        }
        filterChain.doFilter(request, response);// continue the filter chain
    }
}
