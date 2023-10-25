package com.dayone.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "MEMBER")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // READ, WRITE 권한 둘 다 가질 수 있기 때문
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
    // 해당 필드가 기본타입(String) 또는 embeddable 컬렉션이며 엔티티의 컬렉션은 아니라고 JPA에 알려준다.

    private String username;

    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
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
