package com.example.API_GATEWAY.filter;

import com.example.API_GATEWAY.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Autowired
    private Validator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {

            // Check whether this endpoint requires authentication
            if (validator.predicate.test(exchange.getRequest())) {

                // Authorization header must exist
                if (!exchange.getRequest()
                        .getHeaders()
                        .containsHeader("Authorization")) {

                    throw new BadRequestException(
                            "Authorization token is missing.",
                            HttpStatus.UNAUTHORIZED
                    );
                }

                String authHeader = exchange.getRequest()
                        .getHeaders()
                        .get(HttpHeaders.AUTHORIZATION).get(0);

                if (authHeader == null || !authHeader.startsWith("Bearer ")) {

                    throw new BadRequestException(
                            "Invalid Authorization header.",
                            HttpStatus.UNAUTHORIZED
                    );
                }

                String token = authHeader.substring(7);

                try {
                    jwtUtil.validateToken(token);
                } catch (Exception e) {
                    throw new BadRequestException(
                            "Invalid token.",
                            HttpStatus.UNAUTHORIZED
                    );
                }
            }

            return chain.filter(exchange);
        };
    }

    public static class Config {
    }
}





//package com.example.API_GATEWAY.filter;
//
//import com.example.API_GATEWAY.exception.BadRequestException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//@Component
//public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
//
//    @Autowired
//    private Validator validator;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    public AuthFilter(){
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return ((exchange, chain) -> {
//            if(validator.predicate.test(exchange.getRequest())){
//                if (!exchange.getRequest().getHeaders().containsHeader("Authorization"))
//                    throw new BadRequestException("Authorization token is missing.", HttpStatus.UNAUTHORIZED);
//
//                String authHeader = exchange.getRequest().getHeaders().get("Authorization").get(0);
//                String token = null;
//                if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                    token = authHeader.substring(7);
//                }
//                try{
//                    jwtUtil.validateToken(token);
//                }catch (Exception e){
//                    throw new BadRequestException("Invalid token.", HttpStatus.UNAUTHORIZED);
//                }
//            }
//            return chain.filter(exchange);
//        });
//    }
//
//    public static class Config {
//
//    }
//
//}