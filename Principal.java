import java.util.Scanner;

public class Principal {

    private static final int sempi = -1;

    public static void dijkstra(int[][] grafo, int s, int destino) {

        // Numero de arestas do grafo
        int V = grafo.length;

        // Vetor dos vertices visitados
        // Inicializado com o tamnho do numero de vertices
        boolean[] visitado = new boolean [V];

        // Vetor das distancias
        // Incializado com o tamanho do numero de verticess
        int[] distancia = new int[V];

        int[] pi = new int[V];

        pi[s] = sempi;

        // Instancia os valores para cada um dos vetores
        // Dando a todos os valores de distancia infinito
        // E false aos visitados
        for(int i = 0; i < V; i++) {
            distancia[i] = Integer.MAX_VALUE;
            visitado[i] = false;
        }

        /*
         * Passar s como parametro para caso se deseje
         * iniciar de um ponto desejado e nao de 0
         */
        distancia[s] = 0;

        // Acha o vertice com a menor distancia
        for(int i = 0; i < V - 1; i++) {
            int menorVertice = extrairMin(V, distancia, visitado);
            visitado[menorVertice] = true;
            // Explora os vizinhos/Relaxamento
            for(int j = 0; j < V; j++) {
                if((grafo[menorVertice][j] != 0) && !visitado[j] && distancia[menorVertice] != Integer.MAX_VALUE) {
                    pi[j] = menorVertice;
                    int novaDistancia = distancia[menorVertice] + grafo[menorVertice][j];
                    if(novaDistancia < distancia[j]){
                        distancia[j] = novaDistancia;
                    }
                }
            }

        }

        System.out.println("Origem: " + rotularVertices(s));
        System.out.println("Destino: " + rotularVertices(destino));
        //Print
        for(int i = 0; i < V; i++){
            if(i == destino){
                System.out.println("Menor distância: " + distancia[i] + " minutos\n");
            }
        }

        mostrarsoluçao(grafo,s, distancia, pi, destino);

    }

    public static int extrairMin(int V, int[] distancia, boolean[]visitado) {

        int min = Integer.MAX_VALUE, menorIndice = -1;

        for(int v = 0; v < V; v++) {
            if(!visitado[v] && distancia[v] < min) {
                min = distancia[v];
                menorIndice = v;
            }
        }

        return menorIndice;
    }

    public static void mostrarsoluçao(int[][] grafo, int verticeInicio, int[] distancias, int[] pi, int destino){

        int v = grafo.length;
        System.out.print("Vertice\t\t Distancica\t\tCaminho");

        for(int indexVertice = 0; indexVertice < v; indexVertice++){
            if(indexVertice != verticeInicio && (indexVertice == destino)){
                System.out.print("\n" + rotularVertices(verticeInicio) + " -> ");
                System.out.print(rotularVertices(indexVertice)  +" \t\t ");
                System.out.print(distancias[indexVertice] + " minutos" + "\t\t");
                mostrarCaminho(indexVertice, pi);
            }
        }
        System.out.println("\n");
    }

    public static String rotularVertices(int i) {
        String rotulos = "SABCDEFGHIJKL";
        if(i >= 0 && (i <= rotulos.length())){
            return rotulos.charAt(i) + "";
        } else {
             
            return ".";
        }
    }

    public static int desrotularVertices(char c){
        String rotulos = "sabcdefghijkl";
        int posicao = -1;
        for (int i = 0 ; i < rotulos.length(); i++){
            if(rotulos.charAt(i) == c){
                posicao = i;
            }
        }
        return posicao;
        
    }

    public static void mostrarCaminho(int verticeAtual, int[] pi){

        if( verticeAtual == sempi){
            return;
        }
        mostrarCaminho(pi[verticeAtual], pi);
        System.out.print(rotularVertices(verticeAtual) + " ");
    }

    public static int escolheDestino(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha o destino da ambulancia");
        char destino = sc.next().charAt(0);
        sc.close();
        return desrotularVertices(destino);
    }

    public static void main(String [] args){
        int destino = escolheDestino();

        int[][] grafo = 
                     
         {{0, 2, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
          {2, 0, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0},
          {5, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
          {0, 6, 1, 0, 2, 1, 3, 0, 0, 0, 0, 0, 0},
          {0, 0, 0, 2, 0, 0, 0, 6, 0, 0, 0, 0, 0},
          {0, 0, 0, 1, 0, 0, 0, 4, 0, 7, 0, 0, 0},
          {0, 0, 0, 3, 0, 0, 0, 0, 5, 0, 0, 0, 0},
          {0, 0, 0, 0, 6, 4, 0, 0, 3, 3, 3, 0, 0},
          {0, 0, 0, 0, 0, 0, 5, 3, 0, 0, 9, 0, 0},
          {0, 0, 0, 0, 0, 7, 0, 3, 0, 0, 9, 0, 0},
          {0, 0, 0, 0, 0, 0, 0, 3, 9, 0, 0,10,11},
          {0, 0, 0, 0, 0, 0, 0, 0, 0, 9,10, 0,10},
          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,11,10, 0}};

        dijkstra(grafo, 0, destino);

    }
}
