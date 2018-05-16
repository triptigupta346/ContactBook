/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contactbook;

//import org.glassfish.jersey.internal.util.Base64;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.Base64;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
        if (authHeader != null) {
            System.out.print(authHeader.size());
        }

        if (authHeader != null && authHeader.size() > 0) {
            String authToken = authHeader.get(0);
            authToken = authToken.replace(AUTHORIZATION_HEADER_PREFIX, "");
            byte[] bytes = Base64.getDecoder().decode(authToken);
            String decodeString = new String(bytes);
            System.out.print(decodeString);
            StringTokenizer tokenizer = new StringTokenizer(decodeString, ":");
            String username = tokenizer.nextToken();
            String Password = tokenizer.nextToken();

            if ("Harsit".equals(username) && "123456".equals(Password)) {
                return;
            }

        }

        Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
                .entity("User can not access the resource").build();

        requestContext.abortWith(unauthorizedStatus);
    }

}
