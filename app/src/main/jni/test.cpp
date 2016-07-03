//
// Created by noting on 16-7-4.
//
#include "test.h"

void toGrey(int m[],int n){
    int r,g,b,grey;
    for (int i = 0; i < n; ++i) {
        r=(m[i]>>16)&0xff;
        g=(m[i]>>8)&0xff;
        b=m[i]&0xff;
        grey=(int)(r*0.3+g*0.59+b*0.11);
        m[i]=0xffffff&(grey<<16|grey<<8|grey);
    }
}