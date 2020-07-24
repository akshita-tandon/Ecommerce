package com.springbootcamp.springsecurity.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    AppUserDetailsService userDetailsService;


    public ResourceServerConfiguration() {
        super();
    }

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        final LimitLoginAuthenticationProvider authenticationProvider = new LimitLoginAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }


    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
                .addFilterBefore(corsFilter(), SessionManagementFilter.class)
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/role").permitAll()
                .antMatchers("/welcome").permitAll()
                .antMatchers("/swagger-ui.html**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/buyer/profile").hasAnyRole("BUYER")
                .antMatchers("/buyer/updateProfile").hasAnyRole("BUYER")
                .antMatchers("/buyer/updatePassword").hasAnyRole("BUYER")
                .antMatchers("/buyer/addAddress").hasAnyRole("BUYER")
                .antMatchers("/buyer/address").hasAnyRole("BUYER")
                .antMatchers("/seller/profile").hasAnyRole("SELLER")
                .antMatchers("/seller/updatePassword").hasAnyRole("SELLER")
                .antMatchers("/seller/updateProfile").hasAnyRole("SELLER")
                .antMatchers("/seller/addAddress").hasAnyRole("SELLER")
                .antMatchers("/seller/profile").hasAnyRole("SELLER")
                .antMatchers("/doLogout").hasAnyRole("ADMIN","SELLER","BUYER")
                .antMatchers("/confirm/seller").permitAll()
                .antMatchers("/confirm/buyer").permitAll()
                .antMatchers("/register/seller").permitAll()
                .antMatchers("/register/buyer").permitAll()
                .antMatchers("/reactivatelink/buyer").permitAll()
                .antMatchers("/sellers").hasAnyRole("ADMIN")
                .antMatchers("/buyers").hasAnyRole("ADMIN") //change to admin role
                .antMatchers("/activate/buyer").hasAnyRole("ADMIN")
                .antMatchers("/activate/seller").hasAnyRole("ADMIN")
                .antMatchers("/deactivate/buyer").hasAnyRole("ADMIN")
                .antMatchers("/deactivate/seller").hasAnyRole("ADMIN")
                .antMatchers("/recievetoken").permitAll()
                .antMatchers("/reset").permitAll()
                .antMatchers("/addcategory").hasAnyRole("ADMIN")
                .antMatchers("/viewcategory").hasAnyRole("ADMIN")
                .antMatchers("/allcategories").hasAnyRole("ADMIN")
                .antMatchers("/updatecategory").hasAnyRole("ADMIN")
                .antMatchers("/addmetadata").hasAnyRole("ADMIN")
                .antMatchers("/viewmetadata").hasAnyRole("ADMIN")
                .antMatchers("/add/metadatavalue").hasAnyRole("ADMIN")
                .antMatchers("/update/metadatavalue").hasAnyRole("ADMIN")
                .antMatchers("/seller/categories").permitAll()
                .antMatchers("/add/product").hasAnyRole("SELLER")
                .antMatchers("/view/product").hasAnyRole("SELLER")
                .antMatchers("/view/allproduct").hasAnyRole("SELLER")
                .antMatchers("/delete/product").hasAnyRole("SELLER")
                .antMatchers("/update/product").hasAnyRole("SELLER")
                .antMatchers("/activate/product").hasAnyRole("ADMIN")
                .antMatchers("/deactivate/product").hasAnyRole("ADMIN")
                .antMatchers("/uploadimage").hasAnyRole("SELLER","BUYER")
                 .antMatchers("/add/productVariation").hasAnyRole("SELLER")
                .antMatchers("/view/product/variation").hasAnyRole("SELLER")
                .antMatchers("/view/all/product/variations").hasAnyRole("SELLER")
                .antMatchers("/buyer/view/product").hasAnyRole("BUYER")
                .antMatchers("/buyer/view/allproduct").hasAnyRole("BUYER")
                .antMatchers("/admin/view/product").hasAnyRole("ADMIN")
                .antMatchers("/admin/view/allproduct").hasAnyRole("ADMIN")
                .antMatchers("/buyer/similarproducts").hasAnyRole("BUYER")
                .antMatchers("/addtocart").hasAnyRole("BUYER")
                .antMatchers("/movetowishlist").hasAnyRole("BUYER")
                .antMatchers("/movetocart").hasAnyRole("BUYER")
                .antMatchers("/addtowishlist").hasAnyRole("BUYER")
                .antMatchers("/removefromcart").hasAnyRole("BUYER")
                .antMatchers("/getcart").hasAnyRole("BUYER")
                .antMatchers("/chooseaddress").hasAnyRole("BUYER")
                .antMatchers("/getwishlist").hasAnyRole("BUYER")
                .antMatchers("/updatecart").hasAnyRole("BUYER")
                .antMatchers("/ordernow").hasAnyRole("BUYER")
                .antMatchers("/ordernowfromcart").hasAnyRole("BUYER")
                .antMatchers("/removeorder").hasAnyRole("BUYER")
                .antMatchers("/buyer/allcategories").hasAnyRole("BUYER")
                .antMatchers("/buyer/filtercategory").hasAnyRole("BUYER")
                .antMatchers("/update/product/variation").hasAnyRole("SELLER")
                .antMatchers("/seller/orders").hasAnyRole("SELLER")
                .antMatchers("/buyer/orders").hasAnyRole("BUYER")
                .antMatchers("/seller/orderchangestatus").hasAnyRole("SELLER")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable();
    }
}