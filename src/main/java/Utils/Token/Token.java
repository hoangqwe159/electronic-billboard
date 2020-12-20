package Utils.Token;

import java.util.Base64;

/**
 * Encode, decode token
 */
public class Token {
    /**
     * Encode a payload into a random string
     * @param payload A readable payload
     * @return A Token
     */
    public static String encodePayload(String payload){
        return new String(Base64.getEncoder().encode(payload.getBytes()));
    }
    /**
     * Decode a token into a readable string
     * @param token A random string
     * @return A payload
     */
    public static String decodeToken(String token){
        return new String(Base64.getDecoder().decode(token.getBytes()));
    }

}
