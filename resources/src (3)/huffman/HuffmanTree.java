package huffman;

import java.util.*;

/**
 * @author Wait
 */
public class HuffmanTree {
    private List<Node> nodeList;
    private Map<Integer,Node> leafMap;
    private Node root;


    public HuffmanTree(int initSize){
        this.nodeList = new ArrayList<>(initSize);
        this.leafMap = new HashMap<>(initSize);
        this.root = new Node(0);
    }

    public HuffmanTree(){
        this.nodeList = new ArrayList<>(26);
        this.leafMap = new HashMap<>(26);
        this.root = new Node(0);
    }


    public Node getRoot() {
        return root;
    }


    /**
     * create a huffman tree according to the nodeList that save the all leaf node.
     * after sorting, the first and second nodes of nodeList have the smallest weight.
     */
    public void createTree(){
        List<Node> nodes = nodeList;

        while(nodeList.size() >1){
            sort();
            Node left = nodeList.get(0);
            Node right = nodeList.get(1);
            Node middle = new Node(left.getWeight() + right.getWeight());
            middle.setLeftChild(left);
            middle.setRightChild(right);

            nodeList.remove(0);
            nodeList.remove(0);
            nodeList.add(middle);
        }
        root = nodeList.get(0);
        nodeList.remove(0);
    }


    /**
     * create a huffman tree according to the leafMap.
     * @param leafMap all symbols and their codes
     */
    public void createTree(Map<Integer, String> leafMap){
        Node proxy = root;
        Set<Integer> keySet = leafMap.keySet();
        for(Integer val : keySet){
            char[] bits = leafMap.get(val).toCharArray();
            for(char bit : bits ){
                if(bit == '0'){
                    if(proxy.getLeftChild() == null){
                        proxy.setLeftChild(new Node(0));
                    }
                    proxy = proxy.getLeftChild();
                }
                else if(bit == '1'){
                    if(proxy.getRightChild() == null){
                        proxy.setRightChild(new Node(0));
                    }
                    proxy = proxy.getRightChild();
                }
            }
            proxy.setValue(val);
            this.leafMap.put(val,proxy);
            proxy = root;
        }

    }


    /**
     * get the leaf node's huffman code
     * from the node to root-node, if it's left node, coding '0';else coding '1'.
     * @param node : leaf node that needs to know code
     * @return  the huffman code
     */
    public String getLeafCode(Node node){
        if(node == null){
            throw new NullPointerException();
        }
        StringBuilder builder = new StringBuilder();
        Node parent = node.getParentNode();
        while (parent != null){
            if(parent.getLeftChild() == node){
                builder.insert(0,'0');
            }else{
                builder.insert(0,'1');
            }
            node = parent;
            parent = node.getParentNode();
        }
        return builder.toString();
    }

    public int getCode(Node node){
        int val = 0;
        Node parent = node.getParentNode();
        while (parent != null){
            val <<= 1;
            if(parent.getLeftChild() == node){
                val |= 0x01;
            }else{
                val |= 0x01;
            }
            node = parent;
            parent = node.getParentNode();
        }
        return val;
    }


    /**
     * get all leaf-node's code.
     * @return the key is symbol; the value is huffman code.
     */
    public Map<Integer, String> getAllLeafCode(){
        Map<Integer, String> codeMap = new HashMap<>(leafMap.size());
        Set<Integer> keySet = leafMap.keySet();
        for(Integer val : keySet){
            String code = getLeafCode(leafMap.get(val));
            codeMap.put(val, code);
        }
        return codeMap;
    }

    /**
     * loading the leaf nodes statistics form the file.
     * @param array   the index is code-symbol; the value is its count of appearance.
     */
    public void loadNodes(int[] array){
        for(int i=0; i<array.length; i++){
            if(array[i] != 0){
                Node node = new Node(i,array[i]);
                nodeList.add(node);
                leafMap.put(i,node);
            }
        }
    }

    /**
     * the node list sort by the node's weight.
     */
    private void sort(){
        nodeList.sort((a, b)->a.getWeight().compareTo(b.getWeight()));
    }



    public boolean isLeafNode(Node node){
        if(node == null){
            return false;
        }
        return node.getLeftChild() == null && node.getRightChild() == null;
    }


}
