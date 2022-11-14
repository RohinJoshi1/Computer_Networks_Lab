#include <stdio.h>
#include<stdlib.h>
#include<string.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<netinet/in.h>
#include<arpa/inet.h>

#define PORT 8801

int main(){
    //socket file descriptor
    int socket_fd;
    struct sockaddr_in serverAddr;

    //Destination
    int newSocket;
    struct sockaddr_in newAddr;
    socklen_t addr_size;
    char buffer[1024];
    socket_fd = socket(AF_INET,SOCK_STREAM,0);
    printf("Server socket created sucessfully \n");
    memset(&serverAddr,'\0',sizeof(serverAddr));
    serverAddr.sin_family = AF_INET; //IP details inside the socket
    serverAddr.sin_port = htons(PORT); //host to network byte order
    serverAddr.sin_addr.s_addr = inet_addr("127.0.0.1");
    bind(socket_fd,(struct sockaddr*)&serverAddr,sizeof(serverAddr));
    printf("Bind to port number %d\n",PORT);
    listen(socket_fd,5);
    //Listen is what performs the TCP handshake
    printf("Listening....");
    newSocket = accept(socket_fd,(struct sockaddr*)&newAddr,&addr_size);
    strcpy(buffer,"Hello");
    send(newSocket,buffer,strlen(buffer),0);
    printf("Closing connection\n");
    return 0;


}