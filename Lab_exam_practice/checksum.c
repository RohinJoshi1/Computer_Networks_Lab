#include <stdio.h>
#include<math.h>
#include<stdlib.h>
#include<string.h>
char str[100];

int generateChecksum(int mode,int checksum){
    int n;
    int temp,sum=0;
    if(strlen(str)%2!=0){
        n = (strlen(str)+1)/2;
    }
    else{
        n = strlen(str)/2;
    }
    //Forouzan
    for(int i=0;i<n;i++){
        temp = str[2*i];
        temp = temp*256 + str[2*i+1];
        printf("\n%x",temp);
        sum+=temp;
    }
    if(mode ==1){
        printf("%x",checksum);
        sum +=checksum;
    }
    if(sum%(int)pow(2,16)!=0){
        int carry = sum % (int)pow(2,16);
        sum=sum/(int)pow(2,16) + carry;
    }
    printf("sum is %d",sum);
    sum = (int)pow(2,16) - sum;
    printf("\nChecksum is %d",sum);
    return sum;

}
int main(int argc, char const *argv[])
{
    int sum_sender =0;
    int sum_rcv = 0;
    printf("SENDER SIDE\n");
    //SEnDER side
    printf("Enter text\n");
    scanf("%s",str);
    sum_sender = generateChecksum(0,0);    
    //Receiver
    printf("\n RECEIVER SIDE\n");
    sum_rcv = generateChecksum(1,sum_sender);
    if(sum_rcv==0){
        printf("No error");
    }else{
        printf("Error in transmission");
    }
}
