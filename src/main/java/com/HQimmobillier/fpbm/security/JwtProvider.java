package com.HQimmobillier.fpbm.security;

import com.HQimmobillier.fpbm.entity.Roles;
import com.HQimmobillier.fpbm.exception.ApiRequestException;
import com.HQimmobillier.fpbm.services.AccountService;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtProvider {

    private String SecretKey="rida";
    private final long validityInMilliseconds = 8*3600000;
    private final CustumeUserDetailImp userDetailImp;

    public JwtProvider(CustumeUserDetailImp userDetailImp) {

        this.userDetailImp = userDetailImp;
    }

    public String secretKey(String secretKey){
        return Base64.getEncoder().encodeToString(secretKey.getBytes());

    }
    public String createToken(String email, List<Roles> userRoles){
        Claims claims = Jwts.claims().setSubject(email);

        Date now = new Date();
        Date validity = new Date(now.getTime()+validityInMilliseconds);
        return Jwts.builder().setClaims(claims).setIssuedAt(now)
                .setExpiration(validity)
                .claim("roles",userRoles.stream().map(Roles::getRoleName).collect(Collectors.toList()))
                .signWith(SignatureAlgorithm.HS512,SecretKey)
                .compact();
    }
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailImp.loadUserByUsername(getEmail(token));
        Collection<?> roles =  Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody().get("roles",Collection.class);
        //System.out.println(roles.toString());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(Object r:roles){
            System.out.println(r);
            authorities.add(new SimpleGrantedAuthority(r.toString()));
        }

        return new UsernamePasswordAuthenticationToken(userDetails,"",authorities);
    }
    public String getEmail(String token){
        return Jwts.parser().setSigningKey(SecretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }
    public String resolveToken(HttpServletRequest req){
        String bearerToken = req.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){

            return bearerToken.substring(7);
        }

        return null;
    }
    public boolean validateToken(String token) throws ApiRequestException {
        try{
            Jwts.parser().setSigningKey(SecretKey)
                    .parseClaimsJws(token).getBody();
            return true;

        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: {}"+ e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: {} "+e.getMessage());
        } catch (ExpiredJwtException e) {

            System.out.println("JWT token is expired: {}" +e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: {}" +e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: {}"+ e.getMessage());
        }
        return false;
    }

}
