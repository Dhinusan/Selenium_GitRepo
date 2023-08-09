/*
package Resources;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;

import java.util.HashMap;
import java.util.Map;

public class Filetoken {

    public static String getAccessToken() {
        final String clientId = Config.getProperty(AppConstants.CLIENTID);
        final String region = Config.getProperty(Constant.Region);
        final String username = Config.getProperty(AppConstants.USERNAME);
        final String password = Config.getProperty(AppConstants.PWD);
        AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder.standard().withRegion(region)
                .build();
        final Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", username);
        authParams.put("PASSWORD", password);
        final InitiateAuthRequest authRequest = new InitiateAuthRequest();
        authRequest.withAuthFlow(AuthFlowType.USER_PASSWORD_AUTH).withClientId(clientId).withAuthParameters(authParams);
        InitiateAuthResult result = cognitoClient.initiateAuth(authRequest);
        return result.getAuthenticationResult().getAccessToken();
    }

    public static void main(String[] args) {

    }
}
*/
