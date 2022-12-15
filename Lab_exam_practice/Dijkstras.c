#include <stdio.h>
#include <stdlib.h>

int cost[20][20];
void dijkstra(int n,int source){
    int dist[20];
    int visited[20];
    int count;
    int min;
    int nextnode;
    int pred[20];
    for(int i=0;i<n;i++){
        dist[i] = cost[source][i];
        visited[i]=0;
        pred[i] = source;
    }
    dist[source]=0;
    visited[source]=1;
    count =1;
    while(count<n){
        //Find min edge from current node and go greedy
        min = 999;
        for(int i=0;i<n;i++){
            if(dist[i]<min && !visited[i]){
                min = dist[i];
                nextnode = i;
            }
        }
        visited[nextnode] = 1;
        for(int i=0;i<n;i++){
            if(!visited[i]){
                if(min+cost[nextnode][i]<dist[i]){
                    dist[i] = min + cost[nextnode][i];
                    pred[i]=nextnode;
                }
            }
        }
        count++;
    }
    //print min dist and path
    for(int i=0;i<n;i++){
        if(i!=source){
            printf("\nDistance of node %d from %d=%d",i,source,dist[i]);
            printf("\nPath= %d",i);
            int j=i;
            do{
                j = pred[j];
                printf("<--%d",j);
            }while(j!=source);
        }
    }

}
int main(int argc, char const *argv[])
{
    int i,j,n,source;
    // get the number of vertices.
    printf("\nEnter the number of vertices");
    scanf("%d",&n);

    //get the cost matrix for the same.
    printf("\nEnter the cost matrix:\n");
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            scanf("%d",&cost[i][j]);
            //If cost is 0 and its not the same i and j, we replace by INFINITY right.
            if(cost[i][j] == 0 && i != j){
                cost[i][j] = 999;
            }
        }
    }

    //get the starting node.
    printf("\nEnter tthe starting node: ");
    scanf("%d",&source);
    dijkstra(n,source); 
}


// 0 4 0 0 8 0 0 0 0
// 4 0 8 0 11 0 0 0 0
// 0 8 0 7 0 0 4 0 2
// 0 0 7 0 0 0 4 0 2
// 8 11 0 0 0 1 0 0 7
// 0 0 0 0 1 0 2 0 6
// 0 0 4 14 0 0 0 10 0
// 0 0 0 9 0 0 10 0 0
// 0 0 2 0 7 6 0 0 0