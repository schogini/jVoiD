package com.jvoid.core.model;

import java.util.Collection;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.jvoid.core.uricostants.ServerUris;
import com.jvoid.core.uricostants.URIConstants;

/**
 * Uer Authentication Provider
 * 
 * @author Gayatri
 * @version 1.0
 */

public class UserAuthenticationProvider implements UserDetailsService {
	
//	JSONObject userservice;
//	public UserAuthenticationProvider(JSONObject userservice) {
//		this.userservice = userservice;
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// Make an API Call to the Customer WAR to get the user data based on this email address.
		//JSONObject user = userservice.getCustomerByEmail(username);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ServerUris.CUSTOMER_SERVER_URI+URIConstants.GET_CUSTOMER_BY_EMAIL)
		        .queryParam("params", "{email: " + username + "}");	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> returnString = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, String.class);
		
		SerializableJSONObject user = null;
		try {
			JSONObject temp = new JSONObject(returnString.getBody());
			user = new SerializableJSONObject(temp);
			System.out.println("User: " + user.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(user == null) {
			throw new UsernameNotFoundException(String.format("User %s not exist!", username));
		}
		
		return new UserRepositoryUserDetails(user);
	}
	
	private final static class UserRepositoryUserDetails extends SerializableJSONObject implements UserDetails {

		private static final long serialVersionUID = 1L;
		private SerializableJSONObject user;
		
		private UserRepositoryUserDetails(SerializableJSONObject user) {
            super(user);
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.createAuthorityList("ROLE_CUSTOMER");
        }

        @Override
        public String getUsername() {
        	try {
        		return user.getJSONObject().getString("email");
        	}  catch (JSONException e) {
        		return "";
        	}
        }
        
        @Override
        public String getPassword() {
        	try {
        		return user.getJSONObject().getString("password");
        	}  catch (JSONException e) {
        		return "";
        	}
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
	
}