import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class MyNeuralNetwork {
    private int numLayers;
    private int[] numNodes;
    private Map<MyEdge, Double> myWeights;

    public MyNeuralNetwork(int numLayers, int[] numNodes) {
        this.numLayers = numLayers;
        this.numNodes = numNodes;
        this.myWeights = new HashMap<>();

        setWeights();
    }

    private void setWeights() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < numLayers - 1; i++) {
            for (int node1 = 0; node1 < numNodes[i]; node1++) {
                for (int node2 = 0; node2 < numNodes[i + 1]; node2++) {
                    System.out.print("Enter weight for connection from layer " + i + ", node " + node1 +
                            " to layer " + (i + 1) + ", node " + node2 + ": ");
                    double weight = scanner.nextDouble();
                    myWeights.put(new MyEdge(i, node1, i + 1, node2), weight);
                }
            }
        }
    }

    public double getWeight(int layerFrom, int nodeFrom, int layerTo, int nodeTo) {
        MyEdge edge = new MyEdge(layerFrom, nodeFrom, layerTo, nodeTo);
        return myWeights.getOrDefault(edge, 0.0);
    }
}

class MyEdge {
    private int layerFrom, nodeFrom, layerTo, nodeTo;

    public MyEdge(int layerFrom, int nodeFrom, int layerTo, int nodeTo) {
        this.layerFrom = layerFrom;
        this.nodeFrom = nodeFrom;
        this.layerTo = layerTo;
        this.nodeTo = nodeTo;
    }

    @Override
    public int hashCode() {
        return (layerFrom * 31 + nodeFrom) * 31 + (layerTo * 31 + nodeTo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyEdge edge = (MyEdge) obj;
        return layerFrom == edge.layerFrom && nodeFrom == edge.nodeFrom && layerTo == edge.layerTo && nodeTo == edge.nodeTo;
    }
}

public class MyMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of layers in the neural network: ");
        int numLayers = scanner.nextInt();

        int[] numNodes = new int[numLayers];
        for (int i = 0; i < numLayers; i++) {
            System.out.print("Enter the number of nodes in layer " + i + ": ");
            numNodes[i] = scanner.nextInt();
        }

        MyNeuralNetwork myNeuralNetwork = new MyNeuralNetwork(numLayers, numNodes);

        System.out.print("Enter the source layer: ");
        int layerFrom = scanner.nextInt();
        System.out.print("Enter the source node: ");
        int nodeFrom = scanner.nextInt();
        System.out.print("Enter the destination layer: ");
        int layerTo = scanner.nextInt();
        System.out.print("Enter the destination node: ");
        int nodeTo = scanner.nextInt();

        double weight = myNeuralNetwork.getWeight(layerFrom, nodeFrom, layerTo, nodeTo);
        System.out.println("Weight for connection from layer " + layerFrom + ", node " + nodeFrom +
                " to layer " + layerTo + ", node " + nodeTo + ": " + weight);
    }
}
