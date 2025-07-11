package uz.oldiberdi.oldiberdibackend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.oldiberdi.oldiberdibackend.entity.User;
import uz.oldiberdi.oldiberdibackend.repository.UserRepository;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (!jwtUtils.isTokenValid(token)) {
                Long userId = jwtUtils.extractUserId(token);
                User user = userRepository.findById(userId).orElse(null);

                if (user != null) {
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(user, null,null);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
