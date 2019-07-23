package 数据结构;

import 数据结构.AVLTest.FileOperation;

import java.util.ArrayList;

public class AVL<K extends Comparable<K>, V>{
    /**
     * 平衡二叉树：对任意一个结点，左子树与右子树高度差不超过1
     * 高度和节点数量的关系：O(logn)
     *
     * 平衡因子：左右子树高度差
     */

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public int getSize() {
        return size;
    }

    //获取节点的高度
    private int getHeight(Node node){
        if(node==null)
            return 0;
        return node.height;
    }

    //获取node的平衡因子
    private int getBalanceFactor(Node node){
        if(node ==null)
            return 0;
        return getHeight(node.left)-getHeight(node.right);
    }

    public boolean isEmpty(){return size==0;}

    // 向二分搜索树添加元素
    public void add(K key, V value){ root = add(root, key, value);}


    private Node add(Node node, K key, V value) {
        if(node == null){
            size ++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key)<0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) >0 ){
            node.right = add(node.right, key, value);
        }else{
            node.value = value;
        }

        //更新当前节点的height：1+左右子树的最大高度
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor)>1)
            System.out.println("unbalanced");

        return node;
    }


    private Node getNode(Node node, K key){
        if(node == null)
            return null;

        if (key.equals(node.key))
            return node;
        else if(key.compareTo(node.key)<0)
            return getNode(node.left, key);
        else
            return getNode(node.right, key);
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){
        Node node = getNode(root, key);
        return node==null ? null : node.value;
    }

    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + "doesn't exist");

        node.value = newValue;
    }


    //删除键为key的节点，返回值为key对应的value
    public V remove(K key){
        Node node = getNode(root, key);
        if (node == null){
            return null;
        }else{
            root = remove(root, key);
            return node.value;
        }

    }


    private Node remove (Node node,K key){
        if (node == null){
            return null;
        }
        if(key.compareTo(node.key)<0){
            node.left = remove(node.left, key);
            return node;
        }else if(key.compareTo(node.key)>0){
            node.right = remove(node.right, key);
            return node;
        }else{
            //删除左子树为空
            if (node.left==null){
                Node rightNode = node.right;
                size--;
                node.right = null;
                return rightNode;
            }

            //删除右子树为空
            if (node.right == null){
                Node leftNode = node.left;
                size--;
                node.left = null;
                return leftNode;
            }

            //删除有左右子树:用比结点大的最小值代替节点位置，即右子树的最小节点
            Node minNode = minimum(node.right);
            minNode = removeMin(node.right);
            minNode.left = node.left;

            node.left = null;
            node.right = null;
            return minNode;
        }
    }

    //删除最小节点
    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }


    //返回以node为根节点的最小值所在节点
    private Node minimum(Node node){
        if(node.left==null)
            return node;
        return minimum(node.left);
    }


    //判断该二叉树是否是二分搜索树
    //二分搜索树中序遍历是有序的，根据中序遍历判断
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if(keys.get(i-1).compareTo(keys.get(i))>0)
                return false;
        }
        return true;
    }

    //中序遍历
    private void inOrder(Node node, ArrayList<K> keys) {
        if (node==null)
            return;
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    //是否是平衡二叉树-->根据每个节点的平衡因子判断，若都不大于1则是平衡二叉树
    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if(node == null)
            return true;
        int balancedFactor = getBalanceFactor(node);
        if(balancedFactor>1)
            return false;

        return isBalanced(node.left) && isBalanced(node.right);
    }


    //test: 对test.txt文本进行词频统计
    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("test.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVL<String, Integer> map = new AVL<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST :" + map.isBST());
            System.out.println("is balanced :" + map.isBalanced());
        }

        System.out.println();
    }



}
