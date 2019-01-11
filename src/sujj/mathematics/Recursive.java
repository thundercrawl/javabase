package sujj.mathematics;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

class LinkedNode
{
	LinkedNode left=null;
	LinkedNode right =null;
	int value=-1;
	public LinkedNode(int i)
	{
		value = i;
		
	}
	public void print()
	{
		System.out.print("\t"+value);
	}
	/*
	public boolean equals(Object obj)
	{
		if(this.value ==( (LinkedNode)obj).value)
			return true;
		else 
			return false;
	}*/
}

public class Recursive {
	
	static public boolean printN(int N)
	{
		boolean rt = true;
		if(N>0)
		{
			
			printN(N-1);
			System.out.println(N);;
		}
		return rt;
	}
	static public LinkedNode createBinaryTree(int treeNodeNumber)
	{
		ArrayList balanceList = new ArrayList();
		
		
		LinkedNode rootNode = null;
		LinkedNode currentRoot = null;
		for(int i=0;i<treeNodeNumber;i++)
		{
			if(i==0)
			{
				//root node
				rootNode = new LinkedNode(i);
				balanceList.add(rootNode);
				continue;
			}
			if(balanceList.size()>0)
			{
				LinkedNode tmpNode = new LinkedNode(i);
				currentRoot = (LinkedNode) balanceList.get(0);
				if(currentRoot.left ==null)
				{	
					currentRoot.left = tmpNode;
					balanceList.add(tmpNode);
				}
				else if(currentRoot.right ==null)
				{
					currentRoot.right = tmpNode;
					balanceList.add(tmpNode);
					balanceList.remove(currentRoot);
				}
			}
		}
		return rootNode;
	}
	public static void searchBinaryTreeRecursiveMiddleFirst(LinkedNode rootNode)
	{
		
		if(rootNode.left!=null)
		{
			rootNode.print();
			searchBinaryTreeRecursiveMiddleFirst(rootNode.left);
		}
		else if(rootNode.right == null && rootNode.left ==null)
			rootNode.print();
		if(rootNode.right!=null)
		{
			searchBinaryTreeRecursiveMiddleFirst(rootNode.right);
		}
		
		
	}
	public static boolean searchBinaryTreeRecursiveLeftFirst(LinkedNode rootNode)
	{
		boolean rt = false;
		if(rootNode.left!=null)
		{
			
			 rt = searchBinaryTreeRecursiveLeftFirst(rootNode.left);
			if(rt)
			{
				rootNode.print();
				rt = true;
			}
			
		}
		else if(rootNode.right == null && rootNode.left ==null)
			{
				rootNode.print();
				rt= true;
			}
		if(rootNode.right!=null)
		{
			searchBinaryTreeRecursiveLeftFirst(rootNode.right);
		}
		
		return rt;
	}
	public static void searchBinaryNonRecursive(LinkedNode rootNode)
	{
		
		LinkedNode currentRN = rootNode;
		Stack<LinkedNode> nodeStack = new  Stack<LinkedNode>();
		ArrayList<LinkedNode> printList = new ArrayList<LinkedNode>();
		
		while(currentRN.left !=null)
		{
			nodeStack.push(currentRN);
			currentRN = currentRN.left;
		}
		
		
		while(!printList.contains(rootNode))
		{
			//left leaf reach
			if(currentRN.left == null)
			{	
				currentRN.print();
				printList.add(currentRN);
			}
			//check the all subtree node printed.
			else if(printList.contains(currentRN.left)&&(printList.contains(currentRN.right)||currentRN.right==null))
			{
				currentRN.print();
				printList.add(currentRN);
			}
			//check the right subtree
			else if(currentRN.left!=null&&printList.contains(currentRN.left)&&currentRN.right!=null)
			{	
				nodeStack.push(currentRN);
				nodeStack.push(currentRN.right);
			}
			//check the left subtree
			else if(currentRN.left!=null)
			{
				nodeStack.push(currentRN);
				nodeStack.push(currentRN.left);
			}
			if(!nodeStack.isEmpty())
				currentRN = nodeStack.pop();
			
			
		}
		}
	public static void main(String[] args) {
		
		printN(10);
		// TODO Auto-generated method stub
		LinkedNode rootNode =null;
		System.out.println("Recursive------------>");
		long start = System.currentTimeMillis();
		searchBinaryTreeRecursiveMiddleFirst(rootNode);
		System.out.println("\nTime consumed:"+(System.currentTimeMillis()-start));
		System.out.println("\nNonRecursive--------->");
		start = System.currentTimeMillis();
		searchBinaryNonRecursive(rootNode);
		System.out.println("\nTime consumed:"+(System.currentTimeMillis()-start));
		searchBinaryTreeRecursiveLeftFirst(rootNode);
	}

}
