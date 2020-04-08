package eg.edu.alexu.csd.filestructure.redblacktree;

import javax.management.RuntimeErrorException;

public class RedBlackTree<T extends Comparable<T>, V> implements IRedBlackTree<T, V>  {
	
	private INode<T,V> root = new Node<>();
	
	@Override
	public INode<T, V> getRoot() {
		// TODO Auto-generated method stub
		return root;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(this.getRoot().isNull())
			return true;
		return false;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.root = new Node<>();
	}
	@Override
	public V search(T key) {
		// TODO Auto-generated method stub
		if(key ==null) {
				Error e = null;
				throw new RuntimeErrorException(e);
			}
		INode<T, V> nodeItr = this.root;
		while(!nodeItr.isNull()) {
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
						insert_fix_up(inserted);
						return;
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
				if(!sibling.isNull() && sibling.getColor() == true) {
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
				else {  
					//The case of L,R
					if (nodeItr == nodeItr.getParent().getRightChild()) { 
						//do left rotate for nodeItr for nodeItr.getparent
						nodeItr = (Node<T, V>)nodeItr.getParent();
						LeftRotate(nodeItr);
						nodeItr.getParent().setColor(false);
						nodeItr.getParent().getParent().setColor(true);
						RightRotate(nodeItr.getParent().getParent());
					}
					else {
						//The case of L , L
						//do right rotate for parent of the parent of the node aka nodeItr.getparent
						nodeItr.getParent().setColor(false);
						nodeItr.getParent().getParent().setColor(true);
						RightRotate(nodeItr.getParent().getParent());
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
						nodeItr = (Node<T, V>)nodeItr.getParent();
						RightRotate(nodeItr);
						nodeItr.getParent().setColor(false);
						nodeItr.getParent().getParent().setColor(true);
						LeftRotate(nodeItr.getParent().getParent());
					}
					else {
						//The case of R , R
						//do left rotate for parent of the parent of the node aka nodeItr.getparent
						nodeItr.getParent().setColor(false);
						nodeItr.getParent().getParent().setColor(true);
						LeftRotate(nodeItr.getParent().getParent());
					}
				}	
			}
		}
	}
	private void RightRotate (INode<T, V> iNode) 
	{
		Node<T, V> y = (Node<T, V>) iNode.getLeftChild();	
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
			if(y.getRightChild() == null) {
				y.setRightChild(new Node<>());
			}
			iNode.setLeftChild(y.getRightChild()); //x right child changed
			if(!y.getRightChild().isNull() ) {
				y.getRightChild().setParent(iNode); //B parent changed , which is y left child
			}
			iNode.setParent(y);					  //x parent changed
			y.setRightChild(iNode);				  //y left child changed
		
	}
	private void LeftRotate (INode<T, V> iNode) 
	{
		Node<T, V> y = (Node<T, V>) iNode.getRightChild();	
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
			if(y.getLeftChild() == null) {
				y.setLeftChild(new Node<>());
			}
			iNode.setRightChild(y.getLeftChild()); //x right child changed
			if(y.getLeftChild() != null ) {
				y.getLeftChild().setParent(iNode); //B parent changed , which is y left child
			}
			iNode.setParent(y);					  //x parent changed
			y.setLeftChild(iNode);				  //y left child changed
	}
	@Override
	public boolean delete(T key) { //Normal BST deletion
		// TODO Auto-generated method stub
		if(key ==null) {
			Error e = null;
			throw new RuntimeErrorException(e);
		}
		INode<T, V> NodeItr = this.getRoot();
		while(!NodeItr.isNull() && NodeItr.getKey().compareTo(key)!=0) {
			if(NodeItr.getKey().compareTo(key)>0) { //go left
				NodeItr = NodeItr.getLeftChild();
			}
			else { //go right
				NodeItr = NodeItr.getRightChild();
			}
		}
		if(NodeItr.isNull()) //key not found
			return false;
		//Key is found		
		return this.DeleteNode(NodeItr);
	}
	
	private Boolean DeleteNode (INode<T, V> NodeItr) {
		if(!(!NodeItr.getLeftChild().isNull() && !NodeItr.getRightChild().isNull())) {
			//case 3 --> It's a Leaf Node
			if( NodeItr.getLeftChild().isNull() && NodeItr.getRightChild().isNull() ) {
				//Call the deletion of RB Tree
				return this.FixDelete(NodeItr);
			}
			else if (NodeItr.getLeftChild().isNull()) { 			//case 1 --> Having 1 children
				//swap with right - keys and values only
				this.SwapNodes(NodeItr, NodeItr.getRightChild());
				return this.DeleteNode(NodeItr.getRightChild());
			}
			else if (NodeItr.getRightChild().isNull()){ 									   //case 1 --> Having 1 children
				//swap with left = keys and values only
				this.SwapNodes(NodeItr, NodeItr.getLeftChild());
				return this.DeleteNode(NodeItr.getLeftChild());
			}
		}
		else { //case 2 --> Having 2 children
			//We will get the INORDER successor and swap the node with it
			INode<T, V> SuccessorItr = NodeItr.getRightChild();
			INode<T, V> Successor = null ;
			while(!SuccessorItr.isNull() ) {
				Successor = SuccessorItr;
				SuccessorItr = SuccessorItr.getLeftChild();
			}
			SwapNodes(NodeItr, Successor);
			return this.DeleteNode(Successor);
		}
		return false;
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
				NodeItr.getParent().setLeftChild(new Node<>());
				return true;
			}
			else {
				NodeItr.getParent().setRightChild(new Node<>());
				return true;
			}
		}
		
		//Node is Black .. so it's now a DB
		//NodeItr is considered DB 
		//case 2 --DB is root
		if(NodeItr == this.getRoot()) {
			if(NodeItr.getLeftChild().isNull() && NodeItr.getRightChild().isNull()) {
				this.root = new Node<>();
			}
			return true;
		}
		
		//Deleting the node ..it's now double
		//Determining where is the Sibling First
		INode<T, V> siblingINode= null;
		Boolean isSiblingRight = false;
		
		if(NodeItr == NodeItr.getParent().getLeftChild()) {
			siblingINode = NodeItr.getParent().getRightChild();
			isSiblingRight=true;
		}
		else {
			siblingINode = NodeItr.getParent().getLeftChild();
		}
		
		// case 4 if DB sibling is red
		if(siblingINode.getColor()==true) {
			swapColors(NodeItr.getParent(), siblingINode); //Swap Colors of parent and sibling
			if(isSiblingRight) {												  //Rotate parent to DB direction determined by sibling
				LeftRotate(NodeItr.getParent());
			}
			else {
				RightRotate(NodeItr.getParent());
			}	
			return this.FixDelete(NodeItr);			//Reapply cases on DB node
		}
		//case 3 ..DB's sibling is black and it's two child are black
		if(siblingINode.isNull() ||
		 (siblingINode.getColor()==false && siblingINode.getRightChild().getColor()==false &&  siblingINode.getLeftChild().getColor()==false) ||
		 (siblingINode.getColor()==false && siblingINode.getRightChild().isNull() && siblingINode.getLeftChild().isNull() ) ||
		 (siblingINode.getColor()==false && (siblingINode.getRightChild().isNull() && siblingINode.getLeftChild().getColor()==false)) ||
		 (siblingINode.getColor()==false && (siblingINode.getLeftChild().isNull() && siblingINode.getRightChild().getColor()==false))
		 ) {

			//saving parent
			INode<T, V> parent = NodeItr.getParent();
			//deleting DB
			if(NodeItr.getLeftChild().isNull() && NodeItr.getRightChild().isNull() ) {
			NodeItr.setValue(null);
			NodeItr.setKey(null);
			NodeItr.setLeftChild(null);
			NodeItr.setRightChild(null);
		}
			//make sibling Red
			siblingINode.setColor(true);		
			//add black to parent
			if(parent.getColor()==true) { //if it's red
				parent.setColor(false);
				return true;
			}
			else { //if it's black
				//solve another DB
				return this.FixDelete(parent);
			}				
		}
		//case 5 
		if(siblingINode.getColor() == false) { //am sure sibling two children are not both black
			if(isSiblingRight) {	//db is left
				if(siblingINode.getRightChild().getColor()==false && siblingINode.getLeftChild().getColor()==true) { //near is  red
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
					if(NodeItr.getLeftChild().isNull() && NodeItr.getRightChild().isNull() ) {
					NodeItr.setValue(null);
					NodeItr.setKey(null);
					NodeItr.setLeftChild(null);
					NodeItr.setRightChild(null);
				}
					return true;
				}
				
			} else { //db is right
				
				if(siblingINode.getLeftChild().getColor()==false && siblingINode.getRightChild().getColor()==true) { //near is  red
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
					if(NodeItr.getLeftChild().isNull() && NodeItr.getRightChild().isNull() ) {
					NodeItr.setValue(null);
					NodeItr.setKey(null);
					NodeItr.setLeftChild(null);
					NodeItr.setRightChild(null);
				}
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