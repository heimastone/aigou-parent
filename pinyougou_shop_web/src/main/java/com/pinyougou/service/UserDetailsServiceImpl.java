package com.pinyougou.service;


import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.pinyougou.pojo.TbSeller;

import java.util.ArrayList;
import java.util.List;

/*
 * 认证类
 */
public class UserDetailsServiceImpl implements UserDetailsService {

  private SellerService sellerService;

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<GrantedAuthority> grantAuths = new ArrayList<GrantedAuthority>();
        grantAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
    TbSeller seller = sellerService.findOne(username);
        if (seller != null) {
            if (seller.getStatus().equals("1")) {
                return new User(username, seller.getPassword(), grantAuths);
            } else {
                return null;
            }
        } else {
            return null;
        }

    }
}