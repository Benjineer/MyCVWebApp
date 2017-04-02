/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author NIYI
 */
public class TokenIssuer {

    public static String issueToken(String email, UriInfo uriInfo) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("MyAPISecretKey");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        String jwtToken = Jwts.builder()
                .setSubject(email)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(5L).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(signatureAlgorithm, signingKey)
                .compact();
        return jwtToken;
    }

}
