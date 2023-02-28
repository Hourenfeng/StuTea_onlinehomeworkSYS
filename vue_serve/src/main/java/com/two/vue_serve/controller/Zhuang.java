package com.two.vue_serve.controller;

public class Zhuang {
    int[] scores1 = new int[]{72,90,59,80,90};
    int sum = 0;
    public static void main (String[] args){
        int n=5;
        Zhuang zhuang = new Zhuang();
        int a= zhuang.getExcellentNum(5);
        System.out.println(a);
    }
    public int getExcellentNum(int n){
        int i,num=0,min;
        min = getMin();

        for(i=0;i<n;i++){
            if(getScore(i)<min){
                sum+=getScore(i);
                num++;
            }
        }
        sum=sum/num;
        return sum;
    }
    public int getMin(){
        int a= 60;
        return a;
    }
    public int getScore(int i){
        return scores1[i];
    }
}
