package Lab_exam_practice;
import java.util.*;

public class Bellmanford {
    public static class Edge{
        int from,to;
        double cost;
        public Edge(int from,int to,double cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    @SuppressWarnings("unchecked")
    public static List<Edge>[] createGraph(int V){
        List<Edge>[] graph = new List[V];
        for (int i = 0; i < V; i++) {
            graph[i]= new ArrayList<>();
        }
        return graph;
    }
    public static void addEdge(List<Edge>[] graph,int from,int to,double cost){
        graph[from].add(new Edge(from, to, cost));
    }
    public static double[] BellmanFord(List<Edge>[] graph,int src,int V){
        double dist[] = new double[V];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[src] =0;
        for(int i=0;i<V;i++){
            for(List<Edge> edges:graph){
                for(Edge edge:edges){
                    if(dist[edge.from]+edge.cost<dist[edge.to]){
                        dist[edge.to] = dist[edge.from]+edge.cost;
                    }
                }
            }
        }
        for(int i=0;i<V;i++){
            for(List<Edge> edges:graph){
                for(Edge edge:edges){
                    if(dist[edge.from]+edge.cost<dist[edge.to]){
                        dist[edge.to] = Double.NEGATIVE_INFINITY;
                    }
                }
            }
        }
        return dist;
    }    


public static void main(String[] args) {
    int V=4,start =0;
    List<Edge>[] graph = createGraph(V);
    addEdge(graph,0, 1,1);
    addEdge(graph,1, 2,2);
    addEdge(graph,2,3 ,4);
    addEdge(graph,3, 0,-1);
    double d[] = BellmanFord(graph, start, V);
    for (int i = 0; i < V; i++)
      System.out.printf("The cost to get from node %d to %d is %.2f\n", start, i, d[i]);


}
}