package com.treshermanitos.treshermanitos.user;

public class testPermision {
    public static void main(String[] args) {
        Role adm = Role.ADMIN;
        //System.out.println(adm.getPermissions());
        System.out.println(adm.getAuthorities());
        
    }
}
