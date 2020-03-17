/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 * @author hp
 */
public class BinarySearchTree {

    /**
     * @param args the command line arguments
     */
    Node root;
    BinarySearchTree(){
        root=null;
    }
    public void insert(String key){
        root=insertRecord(root,key);
    }
    private Node insertRecord(Node root,String key){ //method to insert records in a BinarySearchTree
        //checks if tree is empty or not
        if(root==null){
            root=new Node(key);
            return root;
        }
        //start insertion at other positions(roots(
        if(key.compareTo(root.key)<0){
            root.left=insertRecord(root.left,key);
            return root;
        }else if(key.compareTo(root.key)>0){
              root.right=insertRecord(root.right,key);
              return root;
            }
        //return unchanged root when both key and root-node value is same
        return root;
    }
    //method that calls inorderTraversal\
    void inorder(){
        inorderTraversal(root);
    }
    private void inorderTraversal(Node root){
        if(root!=null){
            inorderTraversal(root.left);
            System.out.print(root.key+",");
            inorderTraversal(root.right);
        }
    }
    //searching in BinarySearchTree
     void search(String item){
        boolean result=searchBst(root,item);
        if(result==true)
            System.out.println("element present in Bst");
        else
            System.out.println("element not present in BSt");
    }
    private boolean searchBst(Node root,String item){
      //  boolean found=false;
        if(root==null)
            return false;
        else if(item.compareTo(root.key)==0)
                return true;
         else if(item.compareTo(root.key)>0)
                    return searchBst(root.right,item);
            else
                    return searchBst(root.left,item);
            
    }
    ///Deletion of a node from BinarySearchTree
    public void delete(String item){
        root=deleteBst(root,item);
        inorder();
    }
   //actuall delete method
    private Node deleteBst(Node root,String item){
        if(root==null)
            return root;
        if(item.compareTo(root.key)<0)
            root.left=deleteBst(root.left,item);
        else if(item.compareTo(root.key)>0)
                root.right=deleteBst(root.right,item);
            else{
                if(root.left==null)
                    return root.right;
                else if(root.right==null)
                    return root.left;
                
                root.key=minValue(root.right);
                root.right=deleteBst(root.right,item);
        }   
        return root;
    }
    //to find inorder successor
    String  minValue(Node root){
        String min=root.key;
        while(root.left!=null){
           min=root.left.key;
           root=root.left;
        }
        return min;
    }
    public static void main(String[] args) throws IOException {
        BinarySearchTree bst=new BinarySearchTree();
        String str="";
	str=new String(Files.readAllBytes(Paths.get("data.txt")));
	String[] words=str.split("[ .,]+");
        for(String word:words){
            bst.insert(word);
        }
        bst.inorder();
        String str_search;
        Scanner scan=new Scanner(System.in);
        do{
            System.out.println("\npress '1' to search||press '0' to quit\n");
            int ch=(scan.nextInt());
            scan.nextLine();
             if(ch==1){
                System.out.println("\nenter string to be searched\n");
                str_search=scan.nextLine();
                bst.search(str_search);
            }else if(ch==0)
                    break;
        }while(true);
        String str_delete;
       do{
            System.out.println("\npress '1' to delete||press '0' to quit\n");
            int ch=(scan.nextInt());
            scan.nextLine();
             if(ch==1){
                System.out.println("\nenter string to be deleted\n");
                str_delete=scan.nextLine();
                bst.delete(str_delete);
                bst.inorder();
            }else if(ch==0)
                    break;
        }while(true);
    }   
}
