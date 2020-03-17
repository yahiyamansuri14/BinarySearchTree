/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtree;

/**
 *
 * @author yahiyamansuri
 */
//This class is for creating nodes of a binary search tree
public class Node {
    String key;
    Node left,right;
    public Node(String item){
        key=item;
        left=right=null;
    }
}
