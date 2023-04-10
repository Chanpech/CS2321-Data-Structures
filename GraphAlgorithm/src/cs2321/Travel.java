package cs2321;

import cs2321.HeapAPQ.PQEntry;
import net.datastructures.Edge;
import net.datastructures.Entry;
import net.datastructures.Graph;
import net.datastructures.Position;
import net.datastructures.PositionalList;
import net.datastructures.Vertex;

/**
 * @author Ruihong Zhang
 * @author Chanpech Hoeng
 * Reference textbook R14.16 P14.81 
 *
 */
public class Travel {
	private AdjacencyGraph<String, Integer> graph;
	private HashMap<String, Vertex> checkMap;	
	/**
	 * @param routes: Array of routes between cities. 
	 *                routes[i][0] and routes[i][1] represent the city names on both ends of the route. 
	 *                routes[i][2] represents the cost in string type. 
	 *                
	 *                Example:
	 *                  routes = {  {"A","B","8"},
	 *							    {"A","D","1"},
	 *							    {"B","C","11"},
	 * 							    {"C","D","1"}
	 *                           };
	 *                           
	 *                Hint: In Java, use Integer.valueOf to convert string to integer. 
	 */
	public Travel(String [][] routes) {
		//TODO: complete the constructor
		graph = new AdjacencyGraph<>();
		checkMap = new HashMap<>();
		for(int i = 0; i < routes.length; i++) {
			Vertex<String> u = checkVertex(routes[i][0]);
			Vertex<String> v = checkVertex(routes[i][1]);
			System.out.println(u.getElement() +" " + v.getElement() + " " + Integer.valueOf(routes[i][2]));
			graph.insertEdge(u, v, Integer.valueOf(routes[i][2]));
		}

	}
	private Vertex checkVertex(String city) {
		//.get(B)
		if(checkMap.get(city) == null) {
			Vertex v = graph.insertVertex(city);
			//System.out.println("Element v is " + v.getElement());
			checkMap.put(city, v);
			return v;
		}
		return checkMap.get(city);
	}

	/**
	 * @param departure: the departure city name 
	 * @param destination: the destination city name
	 * @return Return the path from departure city to destination using Depth First Search algorithm. 
	 *         The path should be represented as ArrayList or DoublylinkedList of city names. 
	 *         The order of city names in the list should match order of the city names in the path.  
	 *         
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F  
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by 
	 *              the opposite city names.
	 *              	              
	 *              See the method sortedOutgoingEdges below. 
	 */
	//Path(G,U,V)
	//Aplhabetical order

	public Iterable<String> DFSRoute(String departure, String destination ) {
		//TODO: find the path based Depth First Search and return it
		Vertex start = checkMap.get(departure);
		Vertex end = checkMap.get(destination);;
		HashMap<Vertex, Boolean> visited = new HashMap<>();
		HashMap<Vertex, Edge> forest = new HashMap<>();
		ArrayList path = new ArrayList<>();
		DFSRoute(start, end, visited, forest);
		Vertex walk = end;
		while(walk != start) {
			Edge<Integer> edge = forest.get(walk);
			path.addFirst(walk.getElement());
			walk = graph.opposite(walk, edge);
		}
		path.addFirst(start.getElement());
		Iterable<String> iter = path;
		return iter;
	}
	private boolean DFSRoute(Vertex u, Vertex v, HashMap<Vertex,Boolean> visit, HashMap<Vertex,Edge> f ) {
		if(u == v) {
			return true;
		}
		visit.put(u, true);
		Iterable<Edge<Integer>> outGoingEdges = sortedOutgoingEdges(u);
		for(Edge<Integer> e: outGoingEdges) {
			Vertex w = graph.opposite(u, e);
			if(visit.get(w) == null) {
				f.put(w, e);
				boolean found = DFSRoute(w, v, visit,f);
				if(found) {
					return true;
				}
			}
		}
		return false;
	}


	/**
	 * @param departure: the departure city name 
	 * @param destination: the destination city name
	 * @return Return the path from departure city to destination using Breadth First Search algorithm. 
	 *         The path should be represented as ArrayList or DoublylinkedList of city names. 
	 *         The order of city names in the list should match order of the city names in the path.  
	 *         
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F  
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by 
	 *              the opposite city names.
	 *              	             
	 *              See the method sortedOutgoingEdges below. 
	 */

	public Iterable<String> BFSRoute(String departure, String destination ) {
		Vertex start = checkMap.get(departure);
		Vertex end = checkMap.get(destination);;
		HashMap<Vertex, Boolean> visited = new HashMap<>();
		HashMap<Vertex, Edge> forest = new HashMap<>();
		ArrayList path = new ArrayList<>();
		BFSRoute(start, end, visited, forest);
		Vertex walk = end;
		while(walk != start) {
			Edge<Integer> edge = forest.get(walk);
			path.addFirst(walk.getElement());
			walk = graph.opposite(walk, edge);
		}
		path.addFirst(start.getElement());
		Iterable<String> iter = path;
		return iter;
	}
	private void BFSRoute(Vertex u, Vertex v, HashMap<Vertex, Boolean> visited, HashMap<Vertex,Edge> forest) {
		CircularArrayQueue<Vertex> queue = new CircularArrayQueue(3);
		queue.enqueue(u);
		visited.put(u, true);
		while(!(queue.isEmpty())){
			Vertex vert = queue.dequeue();
			Iterable<Edge<Integer>> outGoingEdges = sortedOutgoingEdges(vert);
			for(Edge e: outGoingEdges) {
				Vertex w = graph.opposite(vert, e);
				if(visited.get(w) == null) {
					System.out.println("Vertex "  + w.getElement());
					forest.put(w, e);
					queue.enqueue(w);
					System.out.println("PUT " + w.getElement() + " Edge " + e.getElement());
					visited.put(w, true);
				}
			}
			if(vert == v) {
				return;
			}
		}

	}


	/**
	 * @param departure: the departure city name 
	 * @param destination: the destination city name
	 * @param itinerary: an empty PositionalList object will be passed in to the method. 
	 * 	       When a shorted path is found, the city names in the path should be added to the list in the order. 
	 * @return return the cost of the shortest path from departure to destination. 
	 *         
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F  
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by 
	 *              the opposite city names.
	 *              
	 *              See the method sortedOutgoingEdges below. 
	 */

	@TimeComplexity("O(log(n)(n+m))")
	public int DijkstraRoute(String departure, String destination, PositionalList<String> itinerary ) { 
		//Itinerary is an empty object we put the path information into this object with addlast or addfirst.

		//TODO: find the path based Breadth First Search, insert the oath to itinerary and return the cost

		//Estiamte the distance each vertex
		HashMap<Vertex, Integer> distanceMap = new HashMap<>();
		//Find the minimum distance
		HeapAPQ<Integer,Vertex> pq = new HeapAPQ<>();
		//Record the edge to vertex when  distance is updated
		HashMap<Vertex,Edge> forest = new HashMap<>();

		//Update the distance to vertex
		HashMap<Vertex, PQEntry> entryMap = new HashMap<>();

		//Initialize distance and pq
		Integer distance = null;
		//Our starting point
		Vertex start = checkMap.get(departure);
		//Our end point
		Vertex end = checkMap.get(destination);
		for(Vertex v: graph.vertices()) {
			if(v == start){
				distance = 0;
			}else {
				distance = Integer.MAX_VALUE;
			}
			System.out.println("distance " + distance);
			distanceMap.put(v, distance);
			PQEntry<Integer, Vertex> e = (PQEntry<Integer, Vertex>) pq.insert(distance, v);
			entryMap.put(v, e);

		}
		while(pq.size() > 0) {
			PQEntry<Integer, Vertex> e = (PQEntry<Integer, Vertex>) pq.removeMin();
			Integer d = e.getKey();
			Vertex w = e.getValue();
			Iterable<Edge<Integer>> outGoingEdges = sortedOutgoingEdges(w);
			for(Edge<Integer> i: outGoingEdges) {
				Vertex oppositeV = graph.opposite(w, i);
				Integer newDistance = d + i.getElement();
				System.out.println("New distance" + newDistance);
				//Compare newDistance with V's current distance
				if(newDistance < distanceMap.get(oppositeV)) {
					distance = newDistance;
					distanceMap.put(oppositeV, newDistance);
					forest.put(oppositeV, i);
					pq.replaceKey(entryMap.get(oppositeV), newDistance);
				}
			}
		}
		Vertex walk = end;
		while(walk != start) { //Path constructor
			Edge<Integer> edge = forest.get(walk);
			itinerary.addFirst((String) walk.getElement());
			walk = graph.opposite(walk, edge);
		}
		itinerary.addFirst((String)start.getElement());
		return distance;
	}



	/**
	 * I strongly recommend you to implement this method to return sorted outgoing edges for vertex V
	 * You may use any sorting algorithms, such as insert sort, selection sort, etc.
	 * 
	 * @param v: vertex v
	 * @return a list of edges ordered by edge's name
	 */
	public Iterable<Edge<Integer>> sortedOutgoingEdges(Vertex<String> v)  {
		//Create ArrayList and call removeMin().getValue() which return edge
		//and we could place it in the that data structure we created.
		HeapAPQ<String, Edge> pq = new HeapAPQ<>();
		ArrayList list = new ArrayList<>();
		Iterable<Edge<Integer>> outGoingEdges = graph.outgoingEdges(v);
		for(Edge e: outGoingEdges) {
			Vertex<String> opposite = graph.opposite(v, e);
			pq.insert(opposite.getElement(), e);
		}
		for(Edge e:outGoingEdges) {
			Edge value = pq.removeMin().getValue();
			list.addLast(value);
		}
		Iterable<Edge<Integer>> itr = list; //Convert to iterable

		//TODO: sort the outgoing edges and return the sorted list
		// You can adapt any previous sorting algorithm idea here. 
		// For example: Use the PQ sorting idea. First insert edges in a PQ<cityname, edges>, then call removeMin() repeatedly. 

		return itr;
	}
}
