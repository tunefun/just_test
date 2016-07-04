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
        m[i]=0xff000000|(grey<<16|grey<<8|grey);
    }
}
void preDo(int pixels1[],int n1,int pixels2[],int n2){
    int g1,g2;
    for (int i = 0; i < n1; ++i) {
        g1 = pixels1[i]&0xff;
        g2 = pixels2[i]&0xff;
        g1=g1/2+128;
        g2/=2;
        g1 = 0xff<<24|(g1 << 16) | (g1 << 8) | g1;
        pixels1[i] = g1;
        g2 = 0xff<<24|(g2 << 16) | (g2 << 8) | g2;
        pixels2[i] = g2;
    }
}
void comb(int pixels1[],int n1,int pixels2[],int n2){
    int white=0xff;
    int alpha = 0;
    int g1,g2;
    for (int i = 0; i < n1; ++i) {
        g1 = pixels1[i]&0xff;
        g2 = pixels2[i]&0xff;
        alpha=white-(g1-g2);
        if(alpha<0){
            alpha=0xff;
        }
        else if(alpha!=0){
            g1=(g2*white)/alpha;
        }
        //grey = (int)((float) red * 0.3 + (float)green * 0.59 + (float)blue * 0.11);
        g1 = (alpha<<24) | (g1 << 16) | (g1 << 8) | g1;
        pixels1[i] = g1;
    }
}