#include<stdio.h>
#include<math.h>
#include<stdlib.h>
//x^y mod b
int exponent(int x,int y,int b){
    int res =1;
    x = x%b;
    if(x==0) return 0;
    while(y>0){
        //if y is odd
        if((y&1) !=0){
            res = res*x % b;
        }
        y= y >>1; //y/2
        x = (x*x) % b;
    }
    return res;
}
int main(int argc, char const *argv[])
{
    
    /// @brief 2 parties, with public and private key x and y, x = g^ymodn

    /// @param argc 
    /// @param argv 
    /// @return 
    int g =5;
    int n = 11;
    int a_pub,a_priv,b_pub,b_priv;
    a_priv = rand();
    printf("Alice private key is %d",a_priv);
    a_pub = exponent(g,a_priv,n);
    printf("Alice public is %d",a_pub);
    
    b_priv = rand();
    printf("Bob private key is %d",b_priv);
    b_pub = exponent(g,b_priv,n);
    printf("Bob public is %d",b_pub);

    //Shared keys Alice takes bobs public key and her private key to get g^xy mod n
    int a_shared_key = (int)pow(a_priv,b_pub)%n;
    int b_shared_key = (int)pow(b_priv,a_pub)%n;

    printf("Alice shared key is %d",a_shared_key);
    printf("Bob shared key is %d",b_shared_key);

    return 0;
}
