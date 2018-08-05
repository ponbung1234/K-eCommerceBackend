import io.jsonwebtoken.Jwts;

public class JWTTest {
	static final String SECRET = "ThisIsASecret";
	  static final String TOKEN_PREFIX = "Bearer";
	  static final String HEADER_STRING = "Authorization";
	  static String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTMzMjY1NDUwfQ.aF6S7TWF1DJPYT__ctdP2VcwiP4ZFK2pzCGMTL0kpoybtjOJnpSAIuNtBpU0K1S28v1_iHYmxTgNi3hTPjWZuA";
	  
	public static void main(String[] args) {
		System.out.println(Jwts.parser()
	          .setSigningKey(SECRET)
	          .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
	        
	          );
	}
}
