/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gephi.statistics.plugin;

import java.util.HashMap;
import java.util.LinkedList;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.HierarchicalGraph;
import org.gephi.graph.api.HierarchicalUndirectedGraph;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.UndirectedGraph;
import org.gephi.io.generator.plugin.GraphGenerator;
import org.gephi.project.api.ProjectController;
import org.gephi.project.impl.ProjectControllerImpl;
import org.openide.util.Lookup;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Anna
 */
public class HitsNGTest {
    
    private ProjectController pc;
    private GraphGenerator generator;
    
    @BeforeClass
    public void setUp() {
      pc = Lookup.getDefault().lookup(ProjectControllerImpl.class);
      generator=Lookup.getDefault().lookup(GraphGenerator.class);
    }
    
//    @Test
//    public void testOneNodeHits() {
//        pc.newProject();
//        GraphModel graphModel=generator.generateNullUndirectedGraph(1);
//        HierarchicalGraph hgraph = graphModel.getHierarchicalUndirectedGraph();
//
//        Hits hit = new Hits();
//        
//        double[] authority = new double[1];
//        double[] hubs = new double[1];
//        
//        HashMap<Node, Integer> indicies = hit.createIndiciesMap(hgraph);
//        
//        LinkedList<Node> hub_list = new LinkedList<Node>();
//        LinkedList<Node> auth_list = new LinkedList<Node>();
//        
//        hit.calculateHits(hgraph, hubs, authority, hub_list, auth_list, indicies, false, 0.01);
//        
//        Node n1 = hgraph.getNode("0");
//        int index = indicies.get(n1);
//        double hub1 = hubs[index];
//        double auth1 = authority[index];
//
//        assertEquals(hub1, 1.0);
//        assertEquals(auth1, 1.0);
//    }
    
    @Test
    public void testTwoConnectedNodesHits() {
        pc.newProject();
        GraphModel graphModel=generator.generateCompleteUndirectedGraph(2);
        HierarchicalGraph hgraph = graphModel.getHierarchicalUndirectedGraph();

        Hits hit = new Hits();
        
        double[] authority = new double[2];
        double[] hubs = new double[2];
        
        HashMap<Node, Integer> indicies = hit.createIndiciesMap(hgraph);
        
        LinkedList<Node> hub_list = new LinkedList<Node>();
        LinkedList<Node> auth_list = new LinkedList<Node>();
        
        hit.calculateHits(hgraph, hubs, authority, hub_list, auth_list, indicies, false, 0.01);
        
        Node n1 = hgraph.getNode("0");
        Node n2 = hgraph.getNode("1");
        int index1 = indicies.get(n1);
        int index2 = indicies.get(n2);
        double hub1 = hubs[index1];
        double auth2 = authority[index2];

        assertEquals(hub1, 0.5);
        assertEquals(auth2, 0.5);
    }
    
//    @Test
//    public void testNullGraphHits() {
//        pc.newProject();
//        GraphModel graphModel=generator.generateNullUndirectedGraph(5);
//        HierarchicalGraph hgraph = graphModel.getHierarchicalUndirectedGraph();
//
//        Hits hit = new Hits();
//        
//        double[] authority = new double[5];
//        double[] hubs = new double[5];
//        
//        HashMap<Node, Integer> indicies = hit.createIndiciesMap(hgraph);
//        
//        LinkedList<Node> hub_list = new LinkedList<Node>();
//        LinkedList<Node> auth_list = new LinkedList<Node>();
//        
//        hit.calculateHits(hgraph, hubs, authority, hub_list, auth_list, indicies, false, 0.01);
//        
//        Node n2 = hgraph.getNode("1");
//        Node n3 = hgraph.getNode("2");
//        int index2 = indicies.get(n2);
//        int index3 = indicies.get(n3);
//        double hub2 = hubs[index2];
//        double auth3 = authority[index3];
//
//        assertEquals(hub2, 0.2);
//        assertEquals(auth3, 0.2);
//    }
    
    @Test
    public void testCompleteGraphHits() {
        pc.newProject();
        GraphModel graphModel=generator.generateCompleteUndirectedGraph(5);
        HierarchicalGraph hgraph = graphModel.getHierarchicalUndirectedGraph();

        Hits hit = new Hits();
        
        double[] authority = new double[5];
        double[] hubs = new double[5];
        
        HashMap<Node, Integer> indicies = hit.createIndiciesMap(hgraph);
        
        LinkedList<Node> hub_list = new LinkedList<Node>();
        LinkedList<Node> auth_list = new LinkedList<Node>();
        
        hit.calculateHits(hgraph, hubs, authority, hub_list, auth_list, indicies, false, 0.01);
        
        Node n1 = hgraph.getNode("0");
        Node n5 = hgraph.getNode("4");
        int index1 = indicies.get(n1);
        int index5 = indicies.get(n5);
        double hub1 = hubs[index1];
        double auth5 = authority[index5];

        assertEquals(hub1, 0.2);
        assertEquals(auth5, 0.2);
    }
    
    @Test
    public void testStarGraphHits() {
        pc.newProject();
        GraphModel graphModel=generator.generateStarUndirectedGraph(5);
        HierarchicalGraph hgraph = graphModel.getHierarchicalUndirectedGraph();

        Hits hit = new Hits();
        
        double[] authority = new double[6];
        double[] hubs = new double[6];
        
        HashMap<Node, Integer> indicies = hit.createIndiciesMap(hgraph);
        
        LinkedList<Node> hub_list = new LinkedList<Node>();
        LinkedList<Node> auth_list = new LinkedList<Node>();
        
        hit.calculateHits(hgraph, hubs, authority, hub_list, auth_list, indicies, false, 0.01);
        
        Node n1 = hgraph.getNode("0");
        Node n3 = hgraph.getNode("2");
        Node n4 = hgraph.getNode("3");
        int index1 = indicies.get(n1);
        int index3 = indicies.get(n3);
        int index4 = indicies.get(n4);
        
        double hub1 = hubs[index1];
        double hub3 = hubs[index3];
        double auth1 = authority[index1];
        double auth4 = authority[index4];
        
        boolean b1 = hub1 > hub3;
        boolean b2 = auth1 > auth4;

        assertTrue(b1);
        assertTrue(b2);
    }
    
//    @Test
//    public void testGraphWithSelfLoopsHits() {
//        pc.newProject();
//        GraphModel graphModel=Lookup.getDefault().lookup(GraphController.class).getModel();
//        
//        UndirectedGraph undirectedGraph=graphModel.getUndirectedGraph();
//        Node node1=graphModel.factory().newNode("0");
//        Node node2=graphModel.factory().newNode("1");
//        Node node3=graphModel.factory().newNode("2");
//        
//        undirectedGraph.addNode(node1);
//        undirectedGraph.addNode(node2);
//        undirectedGraph.addNode(node3);
//        
//        Edge edge12=graphModel.factory().newEdge(node1, node2);
//        Edge edge23=graphModel.factory().newEdge(node2, node3);
//        Edge edge11=graphModel.factory().newEdge(node1, node1);
//        Edge edge33=graphModel.factory().newEdge(node3, node3);
//        
//        undirectedGraph.addEdge(edge12);
//        undirectedGraph.addEdge(edge23);
//        undirectedGraph.addEdge(edge11);
//        undirectedGraph.addEdge(edge33);
//        
//        HierarchicalUndirectedGraph hgraph = graphModel.getHierarchicalUndirectedGraph();
//               
//        Hits hit = new Hits();
//        
//        HashMap<Node, Integer> indicies = hit.createIndiciesMap(hgraph);
//        
//        double[] authority = new double[3];
//        double[] hubs = new double[3];
//        
//        LinkedList<Node> hub_list = new LinkedList<Node>();
//        LinkedList<Node> auth_list = new LinkedList<Node>();
//        
//        hit.calculateHits(hgraph, hubs, authority, hub_list, auth_list, indicies, false, 0.01);
//        
//        Node n1 = hgraph.getNode("0");
//        Node n2 = hgraph.getNode("1");
//        int index1 = indicies.get(n1);
//        int index2 = indicies.get(n2);
//        
//        double hub1 = hubs[index1];
//        double hub2 = hubs[index2];
//        
//        boolean b1 = hub2 > hub1;
//
//        assertTrue(b1);
// 
//    }
    
    
}