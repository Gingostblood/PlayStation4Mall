package com.gingost.layui.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gingost.layui.domain.dto.req.UserReqDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * @author:lezzy
 * @Date:2020/4/2 0:50
 */
@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "statu")
    private int statu;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "creat_time")
    private Date creatTtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "phone")
    private Long phone;
    @Column(name = "email")
    private String email;

    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if(this.username.equals(((User) o).username)&& this.email.equals(((User) o).email) && this.phone.equals(((User) o).phone) &&this.roles.equals(((User) o).roles)){
            return true;
        }else
            return false;
    }

    @Override
    public int hashCode() {
        int res=17;
        res=31*res+username.hashCode();
        res=31*res+email.hashCode();
        res=31*res+phone.hashCode();
        res=31*res+roles.hashCode();
        return res;
    }
    public User(UserReqDto req){
        this.id=req.getId();
        this.email=req.getEmail();
        this.phone=req.getPhone();
        this.username=req.getUsername();
    }
}
