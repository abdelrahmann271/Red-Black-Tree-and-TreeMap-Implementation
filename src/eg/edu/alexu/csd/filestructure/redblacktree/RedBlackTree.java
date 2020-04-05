package eg.edu.alexu.csd.filestructure.redblacktree;

import javax.management.RuntimeErrorException;

public class RedBlackTree<T extends Comparable<T>, V> implements IRedBlackTree<T, V>  {
	
	private Node<T,V> root = null;
	
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
			if(nodeItr.getKey().compareTo(key)==0)
				return nodeItr.getValue();
			if(key.compareTo(nodeItr.getKey()) > 0) {
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
		}
		else {
			Node<T, V> inserted = new Node<>();
			inserted.setValue(value);
			inserted.setKey(key);
			inserted.setColor(true);
			Node<T,V> itrNode = this.root;
			Node<T,V> tempNode = null;
			while(itrNode != null) {
				if(inserted.getKey().compareTo(itrNode.getKey()) > 0) {
					tempNode = itrNode;
					itrNode = (Node<T, V>) tempNode.getRightChild();
					if( itrNode == null) {
						tempNode.setRightChild(inserted);
						inserted.setParent(tempNode);
//						if(tempNode.getColor() == true)
//							return;
						insert_fix_up(inserted);
					}		
				}
				else if ( inserted.getKey().compareTo(itrNode.getKey()) < 0 ) {
					tempNode = itrNode;
					itrNode = (Node<T, V>) tempNode.getLeftChild();
					if( itrNode == null) {
						tempNode.setLeftChild(inserted);
						inserted.setParent(tempNode);
						insert_fix_up(inserted);
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
	public boolean delete(T key) {
		// TODO Auto-generated method stub
		if(key ==null) {
			Error e = null;
			throw new RuntimeErrorException(e);
		}
		return false;
	}
}
