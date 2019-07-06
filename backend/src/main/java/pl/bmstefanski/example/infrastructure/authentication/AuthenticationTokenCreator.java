package pl.bmstefanski.example.infrastructure.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.security.core.Authentication;
import pl.bmstefanski.example.infrastructure.converter.ObjectToMapConverter;

class AuthenticationTokenCreator {

  private final ObjectToMapConverter objectToMapConverter;

  AuthenticationTokenCreator(ObjectToMapConverter objectToMapConverter) {
    this.objectToMapConverter = objectToMapConverter;
  }

  String create(Authentication authentication, long expiration, String secretToken) {
    Map<String, Object> principal = this.objectToMapConverter.convert(authentication.getPrincipal());
    LocalDate expirationDate = LocalDate.now().plus(expiration, ChronoUnit.MILLIS);

    return Jwts.builder()
        .setExpiration(Date.valueOf(expirationDate))
        .setIssuedAt(Date.valueOf(LocalDate.now()))
        .setSubject(((ObjectId) principal.get("id")).toHexString())
        .signWith(SignatureAlgorithm.HS512, secretToken)
        .compact();
  }

}
