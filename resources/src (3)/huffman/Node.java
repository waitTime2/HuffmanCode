package huffman;

/**
 * @author Wait
 */
public class Node {
    private Integer value;
    private Integer weight;
    private Node leftChild;
    private Node rightChild;
    private Node parentNode;
    public Node(Integer value, Integer weight) {
        this.value = value;
        this.weight = weight;
    }

    public Node(Integer weight) {
        this.weight = weight;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
        leftChild.setParentNode(this);
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
        rightChild.setParentNode(this);
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }
}
