package sujj.mathematics;

class linkNode
{
	public linkNode next = null;
	public linkNode random = null;
	public int value = -1;
	public linkNode(int value)
	{
		this.value = value;
	}
	public static linkNode cloneOn_1(linkNode startNode)
	{
		//create the node and set the random to null O(n)
		linkNode indexNode = startNode;
		do{
			linkNode tmpNode = new linkNode(indexNode.value);
			tmpNode.next = indexNode.next;
			indexNode.next = tmpNode;
			indexNode = indexNode.next;
			
		}
		while(indexNode!=null);
		//copy the relative random position O(2n)
		indexNode = startNode;
		do{
				
			
		}while(indexNode!=null);
		
		//relink the node to a new clone link O(2n)
		
		return null;
	}
}

public class LinkListClone {
	
	public static void createLink(linkNode startNode,int totalNodeNum)
	{
		linkNode tmpNode = startNode;
		for(int i=1;i<totalNodeNum;i++)
		{
			tmpNode.next = new linkNode(i+1);
			tmpNode = tmpNode.next;
		}
		tmpNode = startNode;
		linkNode indexNode = startNode;
		
		do{
			indexNode = startNode;
			int length =(int)( Math.random()*totalNodeNum);
		
			for(int i=1;i< length;i++)
			{
				indexNode = indexNode.next;
			}
			tmpNode.random = indexNode;
			tmpNode = tmpNode.next;
		}while(tmpNode!=null);
	}
	public static void printOrderNode(linkNode startNode)
	{
		linkNode tmpNode = startNode;
		System.out.print("Order Node:");
		do
		{
			System.out.print(" "+tmpNode.value);
			tmpNode = tmpNode.next;
		}while(tmpNode.next !=null);
		System.out.println(" "+tmpNode.value);
	}
	public static void pirntRandomNode(linkNode startNode)
	{
		linkNode tmpNode = startNode;
		System.out.print("Randdom Node:");
		do
		{
			System.out.print(" "+tmpNode.random.value);
			tmpNode = tmpNode.next;
		}while(tmpNode.next !=null);
		System.out.println(" "+tmpNode.random.value);
	}
	public static void  main(String[] args)
	{
		System.out.println(Math.random()*100000);
		linkNode startNode = new linkNode(1);
		createLink(startNode,5000);
		printOrderNode(startNode);
		pirntRandomNode(startNode);
	}
}
