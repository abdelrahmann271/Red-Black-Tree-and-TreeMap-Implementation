package eg.edu.alexu.csd.filestructure.redblacktree;

import javax.management.RuntimeErrorException;

public class RedBlackTree<T extends Comparable<T>, V> implements IRedBlackTree<T, V>  {
	
	private INode<T,V> root = null;
	
	
	@Override
	public INode<T, V> getRoot() {
		// TODO Auto-generated method stub
		return root;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(this.getRoot() == null)
			return true;
		return false;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.root = null;
	}
	@Override
	public V search(T key) {
		// TODO Auto-generated method stub
		if(key ==null) {
				Error e = null;
				throw new RuntimeErrorException(e);
			}
		INode<T, V> nodeItr = this.root;
		while(nodeItr!=null) {
			if(nodeItr.getKey() != null && nodeItr.getKey().compareTo(key)==0)
				return nodeItr.getValue();
			if(nodeItr.getKey() != null && key.compareTo(nodeItr.getKey()) > 0) {
				nodeItr = nodeItr.getRightChild();
			}
			else {
				nodeItr = nodeItr.getLeftChild();
			}
		}
		return null;
	}

	@Override
	public boolean contains(T key) {
		// TODO Auto-generated method stub
		if(key ==null) {
			Error e = null;
			throw new RuntimeErrorException(e);
		}
		return null!=this.search(key);
	}
	//true stands for red
	//false stands for black
	@Override
	public void insert(T key, V value) {
		if(value==null) {
			Error e = null;
			throw new RuntimeErrorException(e);
		}
		if(key ==null) {
			Error e = null;
			throw new RuntimeErrorException(e);
		}
		// TODO Auto-generated method stub
		if(this.isEmpty()) {
			this.root = new Node<>();
			this.root.setValue(value);
			this.root.setKey(key);
			this.root.setColor(false);
			this.root.setLeftChild(new Node<>());
			this.root.setRightChild(new Node<>());
		}
		else {
			Node<T, V> inserted = new Node<>();
			inserted.setValue(value);
			inserted.setKey(key);
			inserted.setColor(true);
			
			inserted.setLeftChild(new Node<>());
			inserted.setRightChild(new Node<>());
			
			INode<T,V> itrNode = this.root;
			INode<T,V> tempNode = null;
			while( !itrNode.isNull() ) {
				if(itrNode.getKey()!=null && inserted.getKey().compareTo(itrNode.getKey()) > 0) {
					tempNode = itrNode;
					itrNode = (Node<T, V>) tempNode.getRightChild();
					if( itrNode.isNull()) {
						tempNode.setRightChild(inserted);
						inserted.setParent(tempNode);
//						if(tempNode.getColor() == true)
//							return;
						insert_fix_up(inserted);
					}		
				}
				else if ( itrNode.getKey()!=null && inserted.getKey().compareTo(itrNode.getKey()) < 0 ) {
					tempNode = itrNode;
					itrNode = (Node<T, V>) tempNode.getLeftChild();
					if( itrNode.isNull()) {
						tempNode.setLeftChild(inserted);
						inserted.setParent(tempNode);
						insert_fix_up(inserted);
						return;
					}	
				}
				else {
					//it's inserted from before , so no insert-fix-up , just update the value
					itrNode.setValue(value);
					break;
				}
			}		
		}	
	}	
	private void insert_fix_up(Node<T, V> node)
	{
		Node<T, V> nodeItr = node ;
		Node<T, V> sibling = null;
		// check for root node and null
		while(nodeItr.getParent()!=null && nodeItr.getParent().getColor() == true) {
			//The Case of L
			if(nodeItr.getParent() == nodeItr.getParent().getParent().getLeftChild()) {
				sibling = (Node<T, V>) nodeItr.getParent().getParent().getRightChild();
				//If sibling is red
				if(sibling != null && sibling.getColor() == true) {
					sibling.setColor(false);
					nodeItr.getParent().setColor(false);
					if( nodeItr.getParent().getParent()!= this.root) {
						nodeItr.getParent().getParent().setColor(true);
						nodeItr = (Node<T, V>) nodeItr.getParent().getParent();
					}
					else {
						return;
					}
				}
				//If color of sibling is black
				else{  //if(sibling.getColor() == true)
					//The case of L,R
					if (nodeItr == nodeItr.getParent().getRightChild()) { 
						//do left rotate for nodeItr for nodeItr.getparent
						LeftRotate(nodeItr.getParent());
						// do right rotate for parent of parent
						//RightRotate(nodeItr.getParent().getParent());
						RightRotate(nodeItr.getParent());
						nodeItr.setColor(false);
						nodeItr.getLeftChild().setColor(true);
						nodeItr.getRightChild().setColor(true); 		//Here is a redundancy because of Pointers Problem
					}
					else {
						//The case of L , L
						//do right rotate for parent of the parent of the node aka nodeItr.getparent
						RightRotate(nodeItr.getParent().getParent());
						nodeItr.setColor(true);
						nodeItr.getParent().setColor(false);
						nodeItr.getParent().getRightChild().setColor(true);
					}
				}
			}
			//The case of R
			else if (nodeItr.getParent() == nodeItr.getParent().getParent().getRightChild()) { 
				sibling = (Node<T, V>) nodeItr.getParent().getParent().getLeftChild();
				//If sibling is red
				if(sibling!=null &&sibling.getColor() == true) {
					sibling.setColor(false);
					nodeItr.getParent().setColor(false);
					if( nodeItr.getParent().getParent()!= this.root) {
						nodeItr.getParent().getParent().setColor(true);
					} 
					else {
						return;
					}
					nodeItr = (Node<T, V>) nodeItr.getParent().getParent(); 
				}
				//If color of sibling is black
				else {   //if(sibling.getColor() == true) 
					//The case of R,L 
					if (nodeItr == nodeItr.getParent().getLeftChild()) { 
						//do right rotate for nodeItr for nodeItr.getparent
						RightRotate(nodeItr.getParent());
						// do right rotate for parent of parent
						LeftRotate(nodeItr.getParent());
						nodeItr.setColor(false);
						nodeItr.getLeftChild().setColor(true);
						nodeItr.getRightChild().setColor(true); 		//Here is a redundancy because of Pointers Problem
					}
					else {
						//The case of R , R
						//do left rotate for parent of the parent of the node aka nodeItr.getparent
						LeftRotate(nodeItr.getParent().getParent());
						nodeItr.setColor(true);
						nodeItr.getParent().setColor(false);
						nodeItr.getParent().getLeftChild().setColor(true);
					}
				}	
			}
		}
	}
	private void RightRotate (INode<T, V> iNode) 
	{
		Node<T, V> y = (Node<T, V>) iNode.getLeftChild();	
		if(y != null)
		{
			if(iNode.getParent() == null) {
				this.root = y;
				y.setParent(null);
			}
			else {
				y.setParent(iNode.getParent()); //y parent changed
			}
			//Check First if the node passed was a root Node
			if(iNode.getParent() != null  ) {
				if(iNode == iNode.getParent().getRightChild()) {
					iNode.getParent().setRightChild(y);  //Changing the parent of node child
				}
				else {
					iNode.getParent().setLeftChild(y);
				}
			}
	
		}
		if(iNode!= null && y!=null) {
			iNode.setLeftChild(y.getRightChild()); //x right child changed
			if(y.getRightChild() != null ) {
				y.getRightChild().setParent(iNode); //B parent changed , which is y left child
			}
			iNode.setParent(y);					  //x parent changed
			y.setRightChild(iNode);				  //y left child changed
		}
		
	}
	private void LeftRotate (INode<T, V> iNode) 
	{
		Node<T, V> y = (Node<T, V>) iNode.getRightChild();	
		if(y != null)
		{
			
			if(iNode.getParent() == null) {
				this.root = y;
				y.setParent(null);
			}
			else {
				y.setParent(iNode.getParent()); //y parent changed
			}
			//Check First if the node passed was a root Node
			if(iNode.getParent() != null) {
				if( iNode == iNode.getParent().getLeftChild()) {
					iNode.getParent().setLeftChild(y);  //Changing the parent of node child
				}
				else {
					iNode.getParent().setRightChild(y);
				}	
			}

		}
		if(iNode!= null && y!=null) {
			iNode.setRightChild(y.getLeftChild()); //x right child changed
			if(y.getLeftChild() != null ) {
				y.getLeftChild().setParent(iNode); //B parent changed , which is y left child
			}
			iNode.setParent(y);					  //x parent changed
			y.setLeftChild(iNode);				  //y left child changed
		}
	}
	@Override
	public boolean delete(T key) { //Normal BST deletion
		// TODO Auto-generated method stub
		if(key ==null) {
			Error e = null;
			throw new RuntimeErrorException(e);
		}
		INode<T, V> NodeItr = this.getRoot();
		while(NodeItr!=null && NodeItr.getKey()!=key) {
			if(NodeItr.getKey().compareTo(key)>0) { //go left
				NodeItr = NodeItr.getLeftChild();
			}
			else { //go right
				NodeItr = NodeItr.getRightChild();
			}
		}
		if(NodeItr == null) //key not found
			return false;
		//Key is found
		return this.DeleteNode(NodeItr);
	}
	
	private Boolean DeleteNode (INode<T, V> NodeItr) {
		
		//Key is found
		if(!(NodeItr.getLeftChild()!=null && NodeItr.getRightChild()!=null)) {
			//case 3 --> It's a Leaf Node
			if( NodeItr.getLeftChild()==null && NodeItr.getRightChild()==null ) {
				//Call the deletion of RB Tree
				return FixDelete(NodeItr);
			}
			else if (NodeItr.getLeftChild() == null) { 			//case 1 --> Having 1 children
				//swap with right - keys and values only
				this.SwapNodes(NodeItr, NodeItr.getRightChild());
				return this.DeleteNode(NodeItr.getRightChild());
			}
			else{ 									   //case 1 --> Having 1 children
				//swap with left = keys and values only
				this.SwapNodes(NodeItr, NodeItr.getLeftChild());
				return this.DeleteNode(NodeItr.getLeftChild());
			}
		}
		else { //case 2 --> Having 2 children
			//We will get the INORDER successor and swap the node with it
			INode<T, V> SuccessorItr = NodeItr.getRightChild();
			INode<T, V> Successor = null ;
			while(SuccessorItr!= null ) {
				Successor = SuccessorItr;
				SuccessorItr = SuccessorItr.getLeftChild();
			}
			SwapNodes(NodeItr, Successor);
			return this.DeleteNode(Successor);
		}
	}
	
	private void SwapNodes(INode<T, V> Node1,INode<T, V> Node2 ) {
		T key = Node1.getKey();
		V value = Node1.getValue();
		Node1.setKey(Node2.getKey());
		Node1.setValue(Node2.getValue());
		Node2.setKey(key);
		Node2.setValue(value);
	}
	
	private Boolean FixDelete(INode<T, V> NodeItr) {
		//You are sure that it's a Leaf Node 
		//Case 1
		if (NodeItr.getColor() == true ) {
			if(NodeItr == NodeItr.getParent().getLeftChild() ) {
				NodeItr.getParent().setLeftChild(null);
				return true;
			}
			else {
				NodeItr.getParent().setRightChild(null);
				return true;
			}
		}
		
		//Node is Black .. so it's now a DB
		//NodeItr is considered DB 
		
		//case 2 --DB is root
		if(NodeItr == this.getRoot()) {
			return true;
		}
		
		//Determining where is the Sibling First
		INode<T, V> siblingINode= null;
		Boolean isSiblingRight = false;
		//System.out.println(NodeItr.getValue());
		//System.out.println(NodeItr.getParent());
		if(NodeItr == NodeItr.getParent().getLeftChild()) {
			System.out.println("a7a");
			siblingINode = NodeItr.getParent().getRightChild();
			isSiblingRight=true;
		}
		else {
			System.out.println("a7o");
			siblingINode = NodeItr.getParent().getLeftChild();
		}
		
		// case 4 if DB sibling is red
		//System.out.println(siblingINode.getValue());
		if(siblingINode.getColor()==true) {
			swapColors(NodeItr.getParent(), NodeItr.getParent().getRightChild()); //Swap Colors of parent and sibling
			if(isSiblingRight) {												  //Rotate parent to DB direction determined by sibling
				LeftRotate(NodeItr.getParent());
			}
			else {
				RightRotate(NodeItr.getParent());
			}	
			return this.FixDelete(NodeItr);			//Reapply cases on DB node
		}
		//case 3 ..DB's sibling is black and it's two child are black
		if(siblingINode.getColor()==false && 
		   siblingINode.getRightChild().getColor()==false &&
		   siblingINode.getLeftChild().getColor()==false ) {
			
			//saving parent
			INode<T, V> parent = NodeItr.getParent();
			//deleting DB
			parent.setRightChild(null);
			//make sibling Red
			siblingINode.setColor(true);		
			//add black to parent
			if(parent.getColor()==true) { //if it's red
				parent.setColor(false);
				return true;
			}
			else { //if it's black
				//solve another DB
				this.FixDelete(parent);
			}				
		}
		//case 5 
		if(siblingINode.getColor() == false) { //am sure sibling two children are not both black
			if(isSiblingRight) {	//db is left
				if(siblingINode.getLeftChild()!=null && siblingINode.getLeftChild().getColor()==true) { //near is  red
					swapColors(siblingINode, siblingINode.getLeftChild());
					RightRotate(siblingINode);
					return this.FixDelete(NodeItr);
				}
				else {
					//node to changed of color
					INode<T, V> RedNode = siblingINode.getRightChild();
					INode<T, V> parent = NodeItr.getParent();
					swapColors(NodeItr.getParent(), siblingINode);
					LeftRotate(NodeItr.getParent());
					RedNode.setColor(false);
					parent.setLeftChild(null);
					return true;
				}
				
			} else { //db is right
				
				if(siblingINode.getRightChild()!= null && siblingINode.getRightChild().getColor()==true) { //near is  red
					swapColors(siblingINode, siblingINode.getRightChild());
					LeftRotate(siblingINode);
					return this.FixDelete(NodeItr);
				}
				else {
					//node to changed of color
					INode<T, V> RedNode = siblingINode.getLeftChild();
					INode<T, V> parent = NodeItr.getParent();
					swapColors(NodeItr.getParent(), siblingINode);
					RightRotate(NodeItr.getParent());
					RedNode.setColor(false);
					parent.setRightChild(null);
					return true;
				}
			}
		}
		return true;
	}
	
	private void swapColors(INode<T, V> Node1 , INode<T, V> Node2 ) {
		Boolean color = Node1.getColor();
		Node1.setColor(Node2.getColor());
		Node2.setColor(color);
	}
}
