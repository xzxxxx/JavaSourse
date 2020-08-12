package com.nx;

public class DCL {
    private volatile  static DCL dcl;
    private  static Object o = new Object();
    public static DCL getDcl(){
        if (dcl == null){
            synchronized (o){
                if (dcl == null) dcl = new DCL();
            }
        }
        return dcl;
    }
}
