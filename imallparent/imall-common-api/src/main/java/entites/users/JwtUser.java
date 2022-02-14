package entites.users;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2Data2/10 15:42
 */
@Data
public class JwtUser implements UserDetails {

    private String id;

    private String userName;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(){

    }

    public JwtUser(User user){
        id= user.getId();
        userName= user.getUserName();
        password= user.getPassword();
        authorities= user.getRoles();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
