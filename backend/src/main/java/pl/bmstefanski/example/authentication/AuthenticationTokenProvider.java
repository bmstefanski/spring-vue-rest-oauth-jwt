package pl.bmstefanski.example.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.bson.types.ObjectId;

class AuthenticationTokenProvider {

  ObjectId obtainUserId(String token, String secretToken) {
    Claims claims = Jwts.parser()
        .setSigningKey(secretToken)
        .parseClaimsJws(token)
        .getBody();
    return new ObjectId(claims.getSubject());
  }

}
