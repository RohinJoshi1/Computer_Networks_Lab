#include<stdio.h>
#include<math.h>

int gcd(int a, int b)
{
    if (b == 0)
        return a;
    return gcd(b, a % b);
}
int main(int argc, char const *argv[])
{
    int p,q,msg,e,d;
    e =2;
    printf("Enter 2 prime numbers\n");
    scanf("%d%d",&p,&q);
    printf("\nEnter message\n");
    scanf("%d",&msg);
    int n = p*q;
    int phi = (p-1)*(q-1);
    while(e<phi){
        if(gcd(e,phi)==1)break;
        else e++;
    }
    // ed = 1modn
    //d = e^-1modn
    while(1){
        if(fmod(d*e,phi)==1)break;
        else d++;
    }
    //c = m^emodn
    long long encrypted = fmod(pow(msg,e),n);
    long long decrypted = fmod(pow(encrypted,d),n);
    printf("Message is %d",msg);
    printf("encrypted is %ld",encrypted);
    printf("Decrypted is %ld",decrypted);
    return 0;
}
// Write a program for simple RSA algorithm to encrypt and decrypt the data.

// #include<stdio.h>
// #include<math.h>
// int gcd(int a, int h)
// {   while(1)
//     {   int temp = a%h;
//         if(temp==0)
//             return h;
//         a = h;
//         h = temp;
//     }
// }
//  int main()
// {   //e for encrypts(public key),d for decrypts(private key)
//     int p,q,msg,e=2,d=e;
//     printf("Enter two prime numbers:");
//     scanf("%d%d",&p,&q);
//     printf("Enter the messsage:");
//     scanf("%d",&msg);
//     int n=p*q,phi_of_n = (p-1)*(q-1);
//     //for checking co-prime which satisfies e>1
//     while(e<phi_of_n)
//     {
//         if(gcd(e,phi_of_n)==1)
//             break;
//         else
//             e++;
//     }
//     //choosing d such that it satisfies d*e mod phi_of_n= 1 
//     while(1)
//     {
//         if(fmod(d*e,phi_of_n)==1)
//             break;
//         else
//             d++; 
//     }
//     //encrpyt and decrypt data and print
//     long  encrpted =fmod(pow(msg,e),n);
//     long  decrypted = fmod(pow(encrpted,d),n);
//     printf("Message data =%d ",msg);
//     printf("\np = %d",p);
//     printf("\nq = %d",q);
//     printf("\nn = pq = %d",n);
//     printf("\nphi(n) = %d",phi_of_n);
//     printf("\ne = %d",e);
//     printf("\nd = %d ",d);
//     printf("\nEncrypted data = %ld",encrpted);
//     printf("\nDecrypted data (from encrypted)=%ld\n",decrypted);
// }