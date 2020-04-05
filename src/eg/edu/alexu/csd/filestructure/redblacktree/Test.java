package eg.edu.alexu.csd.filestructure.redblacktree;

public class Test {
	
	public static void main(String[] args) {
		
		RedBlackTree<Integer, Integer> v = new RedBlackTree<Integer,Integer>();
		
		v.insert(7, 7);				
		v.insert(10, 10);
		v.insert(6, 6);
		v.insert(9, 9);
		v.insert(13, 13);
		v.insert(5, 5);
		v.insert(4, 4);
		
		
		System.out.println(v.getRoot().getValue() +"  " + v.getRoot().getColor()); 
		System.out.println(v.getRoot().getRightChild().getValue()+" " + v.getRoot().getRightChild().getColor());
		System.out.println(v.getRoot().getLeftChild().getValue() +" " + v.getRoot().getLeftChild().getColor());
		System.out.println(v.getRoot().getRightChild().getLeftChild().getValue() +" " + v.getRoot().getRightChild().getLeftChild().getColor());
		System.out.println(v.getRoot().getRightChild().getRightChild().getValue() +" " + v.getRoot().getRightChild().getRightChild().getColor());
		System.out.println(v.getRoot().getLeftChild().getLeftChild().getValue() +" " +v.getRoot().getLeftChild().getLeftChild().getColor());
		System.out.println(v.getRoot().getLeftChild().getRightChild().getValue() + " " + v.getRoot().getLeftChild().getRightChild().getColor() );
	}


}
