public class Principal {
	
	public static void djikstra(int[][] grafo, int s, int destino) {
		
		// Numero de arestas do grafo		
		int V = grafo.length;
		
		// Vetor dos vertices visitados		
		// Inicializado com o tamnho do numero de vertices
		boolean[] visitado = new boolean [V];
		
		// Vetor das distancias
		// Incializado com o tamanho do numero de verticess
		int[] distancia = new int[V];

		//Vetor dos pais de um vertice
		int[] pi = new int [V];
		
		// Instancia os valores para cada um dos vetores
		// Dando a todos os valores de distancia infinito
		// E false aos visitados
		for(int i = 0; i < V; i++) {
			distancia[i] = Integer.MAX_VALUE;
			visitado[i] = false;
			pi[i] = -1;
		}
		
		/*
		* Passar s como parametro para caso se deseje 
		* iniciar de um ponto desejado e nao de 0
		*/
		distancia[s] = 0;
		pi[s] = 0;
		
		// Acha o vertice com a menor distancia
		for(int i = 0; i < V - 1; i++) {
			int menorVertice = extrairMin(V, distancia, visitado);
			visitado[menorVertice] = true;
			// Explora os vizinhos/Relaxamento
			for(int j = 0; j < V; j++) {
				if((grafo[menorVertice][j] != 0) && !visitado[j] && distancia[menorVertice] != Integer.MAX_VALUE) {
					int novaDistancia = distancia[menorVertice] + grafo[menorVertice][j];
					if(novaDistancia < distancia[j]){
						distancia[j] = novaDistancia;
						pi[j] = j;
					} 
				}
			}
			
		}
		


		System.out.println("Origem: " + s);
		System.out.println("Destino: " + destino);
		//Print
		for(int i = 0; i < V; i++){
			System.out.println("PI: " + pi[i]);
			if(i == destino){
				System.out.println("Menor distância: " + distancia[i]);
			}
		}
		
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

	public static void main(String [] args){
		int[][] grafo = 
			  { { 0, 10, 0, 5, 0 }, // s
				{ 0, 0, 1, 2, 0 }, // t
				{ 0, 0, 0, 0, 2 }, // x
				{ 0, 3, 9, 0, 2 }, // y
				{ 7, 0, 6, 0, 0 } };// z

		
		djikstra(grafo, 0, 4);
		
	}

	

}
