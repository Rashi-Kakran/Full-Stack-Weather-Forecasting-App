package com.wApp.WeatherForcasting.config;

import com.wApp.WeatherForcasting.model.Users;
import com.wApp.WeatherForcasting.repository.UsersRepo;
import com.wApp.WeatherForcasting.security.JwtUtil;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsersRepo userRepo;

    @Value("${frontend.url}")
    private String frontendUrl;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

        userRepo.findById(email).orElseGet(() -> {
            Users user = new Users();
            user.setEmail(email);
            user.setName(name);
            return userRepo.save(user);
        });

        // Generate JWT
        String token = jwtUtil.generateToken(email);

        // Redirect to frontend with JWT
        response.sendRedirect(
                frontendUrl+"/oauth-success?token=" + token
        );
    }
}
