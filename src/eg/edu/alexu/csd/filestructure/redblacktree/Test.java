package eg.edu.alexu.csd.filestructure.redblacktree;

public class Test {
	
	public static void main(String[] args) {
		
		RedBlackTree<Integer, String> v = new RedBlackTree<Integer,String>();
		
//		v.insert(7, 7);				
//		v.insert(10, 10);
//		v.insert(6, 6);
//		v.insert(9, 9);
//		v.insert(13, 13);
//		v.insert(5, 5);
//		v.insert(4, 4);
		
		//Test 20,15,10,7,9,12,24,22,13,11
		v.insert(20, "20");
		v.insert(15, "15");
		v.insert(10, "10");
		v.insert(7, "7");
		v.insert(9, "9");
		v.insert(12,"12");
		v.insert(24,"24");
		v.insert(22,"22");
		v.insert(13, "13");
		v.insert(11, "11");
		
		UnitTest f = new UnitTest();
		
		System.out.println(f.verifyProperty5(v.getRoot()));
		
		//System.out.println(v.search(9));
		
		//All right before inserting 11
		System.out.println(v.getRoot().getValue() +"  " + v.getRoot().getColor()); 
		System.out.println(v.getRoot().getRightChild().getValue()+" " + v.getRoot().getRightChild().getColor());
		System.out.println(v.getRoot().getLeftChild().getValue() +" " + v.getRoot().getLeftChild().getColor());
		System.out.println(v.getRoot().getRightChild().getRightChild().getValue() +" " + v.getRoot().getRightChild().getRightChild().getColor());
		System.out.println(v.getRoot().getRightChild().getLeftChild().getValue() +" " + v.getRoot().getRightChild().getLeftChild().getColor());
		System.out.println(v.getRoot().getLeftChild().getLeftChild().getValue() +" "+v.getRoot().getLeftChild().getLeftChild().getColor());
		System.out.println(v.getRoot().getLeftChild().getRightChild().getValue() + " " + v.getRoot().getLeftChild().getRightChild().getColor() );
		System.out.println(v.getRoot().getLeftChild().getRightChild().getRightChild().getValue() +" "+v.getRoot().getLeftChild().getRightChild().getRightChild().getColor());	
		System.out.println(v.getRoot().getRightChild().getRightChild().getRightChild().getValue() +" "+v.getRoot().getRightChild().getRightChild().getRightChild().getColor());
		System.out.println(v.getRoot().getRightChild().getRightChild().getLeftChild().getValue() +" "+v.getRoot().getRightChild().getRightChild().getLeftChild().getColor());
//		v.delete(11);
//		v.delete(20);
//		v.delete(24);
//		v.delete(7);
//		v.delete(15);
//		System.out.println(v.getRoot().getValue() +"  " + v.getRoot().getColor()); 
//		System.out.println(v.getRoot().getRightChild().getValue()+" " + v.getRoot().getRightChild().getColor());
//		System.out.println(v.getRoot().getLeftChild().getValue() +" " + v.getRoot().getLeftChild().getColor());
//		System.out.println(v.getRoot().getRightChild().getRightChild().getValue() +" " + v.getRoot().getRightChild().getRightChild().getColor());
//		System.out.println(v.getRoot().getRightChild().getLeftChild().getValue() +" " + v.getRoot().getRightChild().getLeftChild().getColor());
//		System.out.println(v.getRoot().getLeftChild().getLeftChild().getValue() +" "+v.getRoot().getLeftChild().getLeftChild().getColor());
//		System.out.println(v.getRoot().getLeftChild().getRightChild().getValue() + " " + v.getRoot().getLeftChild().getRightChild().getColor() );
//		//System.out.println(v.getRoot().getLeftChild().getRightChild().getRightChild().getValue() +" "+v.getRoot().getLeftChild().getRightChild().getRightChild().getColor());	
//		System.out.println(v.getRoot().getRightChild().getRightChild().getRightChild().getValue() +" "+v.getRoot().getRightChild().getRightChild().getRightChild().getColor());
//		System.out.println(v.getRoot().getRightChild().getRightChild().getLeftChild().getValue() +" "+v.getRoot().getRightChild().getRightChild().getLeftChild().getColor());
//		//System.out.println(v.getRoot().getRightChild().getRightChild().getRightChild().getValue() +" " + v.getRoot().getRightChild().getRightChild().getRightChild().getColor());
//		//System.out.println(v.getRoot().getRightChild().getRightChild().getLeftChild().getValue() +" " + v.getRoot().getRightChild().getRightChild().getLeftChild().getColor());
		
		
//		System.out.println(v.getRoot().getRightChild().getLeftChild().getValue() +" " + v.getRoot().getRightChild().getLeftChild().getColor());
//		System.out.println(v.getRoot().getRightChild().getRightChild().getValue() +" " + v.getRoot().getRightChild().getRightChild().getColor());
//		System.out.println(v.getRoot().getLeftChild().getLeftChild().getValue() +" " +v.getRoot().getLeftChild().getLeftChild().getColor());
//		System.out.println(v.getRoot().getLeftChild().getRightChild().getValue() + " " + v.getRoot().getLeftChild().getRightChild().getColor() );
	}


}
