package com.shop.entity;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    //이메일로 회원구분을 하기 떄문에 유니크 속성을 지정함
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING) //enum 타입을 엔티티속성으로 지정함
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        //Member 엔티티 생성
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        //bean 을 파라미터로 넘겨 비밀번호 암호화
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }

}
