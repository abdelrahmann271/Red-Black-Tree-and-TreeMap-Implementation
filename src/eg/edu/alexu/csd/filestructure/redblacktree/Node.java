package eg.edu.alexu.csd.filestructure.redblacktree;

public class Node<T extends Comparable<T>, V> implements INode<T, V> {

	private Node<T, V> parent = null;
	private Node<T, V> leftChild = null;
	private Node<T, V> rightChild = null;
	private T key = null;
	private V value = null;
	private Boolean color= false;
	
	@Override
	public void setParent(INode<T, V> parent) {
		// TODO Auto-generated method stub
		this.parent = (Node<T, V>) parent;
	}

	@Override
	public INode<T, V> getParent() {
		// TODO Auto-generated method stub
		return this.parent;
	}

	@Override
	public void setLeftChild(INode<T, V> leftChild) {
		// TODO Auto-generated method stub
		this.leftChild = (Node<T, V>) leftChild;
	}

	@Override
	public INode<T, V> getLeftChild() {
		// TODO Auto-generated method stub
		return this.leftChild;
	}

	@Override
	public void setRightChild(INode<T, V> rightChild) {
		// TODO Auto-generated method stub
		this.rightChild = (Node<T, V>) rightChild;
	}

	@Override
	public INode<T, V> getRightChild() {
		// TODO Auto-generated method stub
		return this.rightChild;
	}

	@Override
	public T getKey() {
		// TODO Auto-generated method stub
		return this.key;
	}

	@Override
	public void setKey(T key) {
		// TODO Auto-generated method stub
		this.key = key;
	}

	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public void setValue(V value) {
		// TODO Auto-generated method stub
		this.value = value;
	}

	@Override
	public boolean getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	@Override
	public void setColor(boolean color) {
		// TODO Auto-generated method stub
		this.color = color;
	}

	@Override
	public boolean isNull() {
		// TODO Auto-generated method stub
		if(this.key == null && this.value == null)
			return true;
		return false;
	}

}
