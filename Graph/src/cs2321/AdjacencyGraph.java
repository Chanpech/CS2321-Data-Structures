package cs2321;

import net.datastructures.*;

/*
 * Implement Graph interface. A graph can be declared as either directed or undirected.
 * In the case of an undirected graph, methods outgoingEdges and incomingEdges return the same collection,
 * and outDegree and inDegree return the same value.
 * 
 * @author ruihong-adm
 * @author Chanpech Hoeng
 */
public class AdjacencyGraph<V, E> implements Graph<V, E> {
	//A vertex using map implementation
	private class InnerVertex<V> implements Vertex<V>{
		private V element;
		private Position<Vertex<V>> position;
		private HashMap<Vertex<V>, Edge<E>> outgoing;
		private HashMap<Vertex<V>, Edge<E>> incoming;

		public InnerVertex(V elem, boolean directed) {
			element = elem;
			outgoing = new HashMap<>();
			if(directed) {
				incoming = new HashMap<>();
			}else {
				incoming = outgoing;
			}
		}

		@Override
		public V getElement() {
			// TODO Auto-generated method stub
			return element;
		}
		public void setElment(V elem) {
			element = elem;
		}
		public void setPositon(Position<Vertex<V>> p) {
			position = p;
		}
		public Position<Vertex<V>> getPositon(){
			return position;
		}
		public HashMap<Vertex<V>, Edge<E>> getOutgoing(){
			return outgoing;
		}
		public HashMap<Vertex<V>, Edge<E>> getIncoming(){
			return incoming;
		}
	}

	//A edge using map implementation
	private class InnerEdge<E> implements Edge<E>{
		private E element;
		private Position<Edge<E>> pos;
		private Vertex<V>[] endpoints;

		public InnerEdge(Vertex<V> u, Vertex<V> v, E elem) {
			element = elem;
			endpoints = (Vertex<V>[]) new Vertex[] {u,v};
		}	

		@Override
		public E getElement() {
			// TODO Auto-generated method stub
			return element;
		}
		public void setElment(E elem) {
			element = elem;
		}
		public Vertex<V>[] getEndpoints(){
			return endpoints;
		}
		public void setPosition(Position<Edge<E>> e) {
			pos = e;
		}
		public Position<Edge<E>> getPostion() {
			return pos;
		}

	}

	private boolean isDirect; //Check if directed or simple graph
	private PositionalList<Vertex<V>> vertices = new DoublyLinkedList<>(); //List of vertices
	private PositionalList<Edge<E>> edges  = new DoublyLinkedList<>(); //List of edges

	//Overloaded Constructor 
	public AdjacencyGraph(boolean directed) {
		//directed constructor
		isDirect = directed;
	}

	public AdjacencyGraph() {
		//default constructor
	}



	/* (non-Javadoc)
	 * @see net.datastructures.Graph#edges()
	 */
	@SpaceComplexity("O(m)")
	@TimeComplexity("O(m)")
	public Iterable<Edge<E>> edges() {
		/* TCJ:
		 * Return Iterable m number of edges.
		 */
		return edges;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#endVertices(net.datastructures.Edge)
	 */
	 /**
	   * Returns the vertices of edge e as an array of length two.
	   * If the graph is directed, the first vertex is the origin, and
	   * the second is the destination.  If the graph is undirected, the
	   * order is arbitrary.
	   */
	public Vertex[] endVertices(Edge<E> e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		InnerEdge<E> edge = validate(e);
		Vertex<V>[] endpoints = edge.getEndpoints();
		if(!(isDirect)) {
			return edge.getEndpoints();
		}else {
			Vertex<V> u = endpoints[0];
			InnerVertex<V> origin = validate(u);
			Vertex<V> v = endpoints[1];
			InnerVertex<V> dest = validate(v);
			endpoints = (Vertex<V>[]) new Vertex[] {u,v};
			return endpoints;
		}
	}


	/* (non-Javadoc)
	 * @see net.datastructures.Graph#insertEdge(net.datastructures.Vertex, net.datastructures.Vertex, java.lang.Object)
	 */
	@SpaceComplexity("O(log n)")
	@TimeComplexityExpected("O(1)")
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E o)
			throws IllegalArgumentException {
		/* TCJ:
		 * It is expected that insert edges will be 1 since we are implementing using map data structure.
		 */
		if(getEdge(u,v) == null) {
			InnerEdge<E> edge = new InnerEdge<>(u,v, o);
			edge.setPosition(edges.addLast(edge));
			InnerVertex<V> origin = validate(u);
			InnerVertex<V> dest = validate(v);
			origin.getOutgoing().put(v, edge);
			dest.getIncoming().put(u, edge);
			return edge;
		}else {
			throw new IllegalArgumentException("Edge already exist from u and v");
		}
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#insertVertex(java.lang.Object)
	 */
	@SpaceComplexity("O(1)")
	@TimeComplexity("O(1)")
	public Vertex<V> insertVertex(V o) {
		/* TCJ:
		 * At worst the insertVertex will take O(1) because of hashmap.
		 */
		InnerVertex<V> vert = new InnerVertex<>(o, isDirect);
		vert.setPositon(vertices.addLast(vert));
		return vert;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numEdges()
	 */
	@SpaceComplexity("O(1)")
	@TimeComplexity("O(1)")
	public int numEdges() {
		return edges.size();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numVertices()
	 */
	@SpaceComplexity("O(1)")
	@TimeComplexity("O(1)")
	public int numVertices() {
		// TODO Auto-generated method stub
		return vertices.size();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#opposite(net.datastructures.Vertex, net.datastructures.Edge)
	 */
	@SpaceComplexity("O(1)")
	@TimeComplexity("O(1)")
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e)
			throws IllegalArgumentException {
		/* TCJ:
		 * At worst the opposite will take O(1) because of hashmap data structure.
		 */
		InnerEdge<E> edge = validate(e);
		Vertex<V>[] endpoints = edge.getEndpoints();
		if(endpoints[0] == v) {
			return endpoints[1];
		}else if(endpoints[1] == v) {
			return endpoints[0];
		}else {
			throw new IllegalArgumentException("v is not incident to this edge");
		}
	}

	private InnerEdge<E> validate(Edge<E> e) {
		// TODO Auto-generated method stub
		if(!(e instanceof InnerEdge))
			throw new IllegalArgumentException();
		InnerEdge<E> edge = (InnerEdge) e;
		return edge;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#removeEdge(net.datastructures.Edge)
	 */
	@SpaceComplexity("O(log n)")
	@TimeComplexityExpected("O(1)")
	public void removeEdge(Edge<E> e) throws IllegalArgumentException {
		/* TCJ:
		 * It is expected to be O(1) because of remove method from hashmap
		 */
		InnerEdge<E> edge = validate(e);
		edge.getEndpoints();
		edges.remove(edge.getPostion());
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#removeVertex(net.datastructures.Vertex)
	 */
	@SpaceComplexity("O(degree)")
	@TimeComplexity("O(degree v)")
	public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
		/* TCJ:
		 * At worst it will remove based on the numbers of degree for our vertex.
		 */
		InnerVertex<V> vert = validate(v);
		for(Edge<E> e: vert.getOutgoing().values()) {
			removeEdge(e);
		}
		for(Edge<E> e: vert.getIncoming().values()) {
			removeEdge(e);
		}
		vertices.remove(vert.getPositon());
	}

	/* 
	 * replace the element in edge object, return the old element
	 */
	public E replace(Edge<E> e, E o) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		InnerEdge<E> edge = validate(e);
		E old = edge.getElement();
		edge.setElment(o);
		return old;
	}

	/* 
	 * replace the element in vertex object, return the old element
	 */
	public V replace(Vertex<V> v, V o) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		InnerVertex<V> vert = validate(v);
		V old = vert.getElement();
		vert.setElment(o);
		return old;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#vertices()
	 */
	@SpaceComplexity("O(n)")
	@TimeComplexity("O(n)")
	public Iterable<Vertex<V>> vertices() {
		/* TCJ:
		 * Will return an collection of vertices which implement doublylinkedlist data structure.
		 */
		return vertices;
	}


	@SpaceComplexity("O(1)")
	@TimeComplexity("O(1)")
	@Override
	public int outDegree(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
		return vert.getOutgoing().size();
	}


	@SpaceComplexity("O(1)")
	@TimeComplexity("O(1)")
	@Override
	public int inDegree(Vertex<V> v) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		InnerVertex<V> vert = validate(v);
		return vert.getIncoming().size();
	}

	@SpaceComplexity("O(degree v)")
	@TimeComplexity("O(degree v)")
	@Override
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		/* TCJ:
		 * Will return collections outgoing edges based on the degree of the vertex. 
		 */
		InnerVertex<V> vert = validate(v);
		return vert.getOutgoing().values();
	}

	@TimeComplexity("O(1)")
	private InnerVertex<V> validate(Vertex<V> v) {
		// TODO Auto-generated method stub
		if(!(v instanceof InnerVertex))
			throw new IllegalArgumentException();
		InnerVertex<V> vertex = (InnerVertex)v;
		return vertex;
	}


	@SpaceComplexity("O(degree v)")
	@TimeComplexity("O(degree v)")
	@Override
	public Iterable<Edge<E>> incomingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		/* TCJ:
		 * Will return collections incoming edges based on the degree of the vertex. 
		 */
		InnerVertex<V> vert = validate(v);
		return vert.getIncoming().values();
	}


	@SpaceComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	@Override
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v)
			throws IllegalArgumentException {
		/* TCJ:
		 * Will be expect to return 1 because of hashmap implementation.
		 */
		InnerVertex<V> origin = validate(u);
		return origin.getOutgoing().get(v);
	}

}
