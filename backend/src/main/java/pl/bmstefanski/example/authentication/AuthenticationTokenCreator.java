package pl.bmstefanski.example.authentication;

import com.nimbusds.jose.util.JSONObjectUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Map;
import net.minidev.json.JSONObject;
import org.springframework.security.core.Authentication;
import pl.bmstefanski.example.infrastructure.converter.ObjectToMapConverter;

class AuthenticationTokenCreator {

  private final AuthenticationProperties authenticationProperties;
  private final ObjectToMapConverter objectToMapConverter;

  AuthenticationTokenCreator(AuthenticationProperties authenticationProperties,
      ObjectToMapConverter objectToMapConverter) {
    this.authenticationProperties = authenticationProperties;
    this.objectToMapConverter = objectToMapConverter;
  }

  String create(Authentication authentication) {
    Map<String, Object> principal = this.objectToMapConverter.convert(authentication.getPrincipal());
    LocalDate expirationDate = LocalDate.now().plusDays(this.authenticationProperties.getToken().getExpiration());

    return Jwts.builder()
        .setExpiration(Date.valueOf(expirationDate))
        .setIssuedAt(Date.valueOf(LocalDate.now()))
        .setSubject(String.valueOf(principal.get("name")))
        .signWith(SignatureAlgorithm.HS512, this.authenticationProperties.getToken().getSecret())
        .compact();
  }

  private JSONObject parsePrincipalStringToJsonObject(Object principal) {
    try {
      return JSONObjectUtils.parse(principal.toString());
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return null;
  }

}
