package eg.edu.alexu.csd.filestructure.redblacktree;

public class Test {
	
	public static <T> void main(String[] args) {
		
		RedBlackTree<Integer, String> v = new RedBlackTree<Integer,String>();
		//50 is root
//		INode<Integer, String> Node20 = new Node<Integer, String>();
		INode<Integer, String> Node65 = new Node<Integer, String>();
		INode<Integer, String> Node15 = new Node<Integer, String>();
//		INode<Integer, String> Node35 = new Node<Integer, String>();
		INode<Integer, String> Node55 = new Node<Integer, String>();
		INode<Integer, String> Node70 = new Node<Integer, String>();
		INode<Integer, String> Node80 = new Node<Integer, String>();
		INode<Integer, String> Node90 = new Node<Integer, String>();
		INode<Integer, String> Node68 = new Node<Integer, String>();
		
		INode<Integer, String> Node20 = new Node<Integer, String>();
		INode<Integer, String> Node35 = new Node<Integer, String>();
		
		v.insert(526, "4427"); 
		v.insert(370, "1597");
		v.insert(1021, "6449");
		
		System.out.println(v.getRoot().getValue());
		System.out.println(v.getRoot().getLeftChild().getValue());
		System.out.println(v.getRoot().getRightChild().getValue());
		System.out.println(v.getRoot().getRightChild().getLeftChild().isNull());
		System.out.println(v.getRoot().getRightChild().getRightChild().isNull());
		
		System.out.println("Deleteion");
		System.out.println(v.delete(370));
		System.out.println(v.delete(526));
		System.out.println(v.delete(1021));
		
		System.out.println(v.getRoot().isNull() +" root.isnull");
		
//		Node20.setKey(20);
//		Node20.setValue("20");
//		Node20.setParent(v.getRoot());
//		Node20.setLeftChild(Node15);
//		Node20.setRightChild(Node35);
//		Node20.setColor(false);
//		
//
//		Node65.setKey(65);
//		Node65.setValue("65");
//		Node65.setParent(v.getRoot());
//		Node65.setLeftChild(Node55);
//		Node65.setRightChild(Node70);
//		Node65.setColor(false);
//		
//
//		Node15.setKey(15);
//		Node15.setValue("15");
//		Node15.setParent(Node20);
//		Node15.setLeftChild(new Node<Integer, String>());
//		Node15.setRightChild(new Node<Integer, String>());
//		Node15.setColor(false);
//		
//
//		Node35.setKey(35);
//		Node35.setValue("35");
//		Node35.setParent(Node20);
//		Node35.setLeftChild(new Node<Integer, String>());
//		Node35.setRightChild(new Node<Integer, String>());
//		Node35.setColor(false);
//		
//
//		Node55.setKey(55);
//		Node55.setValue("55"); 
//		Node55.setParent(Node65);
//		Node55.setLeftChild(new Node<Integer, String>());
//		Node55.setRightChild(new Node<Integer, String>());
//		Node55.setColor(false);
//		
//
//		 Node70.setKey(70);
//		 Node70.setValue("70");
//		 Node70.setParent(Node65);
//		 Node70.setLeftChild(Node68);
//		 Node70.setRightChild(Node80);
//		 Node70.setColor(true);
//		
//
//		Node80.setKey(80);
//		Node80.setValue("80");
//		Node80.setParent(Node70);
//		Node80.setLeftChild(new Node<Integer, String>());
//		Node80.setRightChild(Node90);
//		Node80.setColor(false);
//		
//
//		Node90.setKey(90);
//		Node90.setValue("90");
//		Node90.setParent(Node80);
//		Node90.setLeftChild(new Node<Integer, String>());
//		Node90.setRightChild(new Node<Integer, String>());
//		Node90.setColor(true);
//		
//
//		Node68.setKey(68);
//		Node68.setValue("68");
//		Node68.setParent(Node70);
//		Node68.setLeftChild(new Node<Integer, String>());
//		Node68.setRightChild(new Node<Integer, String>());
//		Node68.setColor(false);
//		
//		UnitTest r = new UnitTest();
//		System.out.println(r.verifyProperty5(v.getRoot()));
		
//		System.out.println(v.getRoot().getLeftChild().isNull());
//		System.out.println(v.getRoot().getRightChild().isNull());

//		System.out.println(v.delete(55));		
//		System.out.println(	r.verifyProperty5(v.getRoot()));
//		System.out.println(Node65.getLeftChild().isNull());
//
//		System.out.println(v.delete(20));
//		System.out.println(	r.verifyProperty5(v.getRoot()));
//		
//		System.out.println(v.delete(90));
//		System.out.println(	r.verifyProperty5(v.getRoot()));
//		
//		System.out.println(v.delete(80));
//		System.out.println(	r.verifyProperty5(v.getRoot()));
//		
//		System.out.println(v.delete(50));
//		System.out.println(	r.verifyProperty5(v.getRoot()));
//		
//		System.out.println(v.delete(35));
//		System.out.println(	r.verifyProperty5(v.getRoot()));
//		
//		System.out.println(v.delete(15));
//		System.out.println(	r.verifyProperty5(v.getRoot()));
//		
//		System.out.println(v.delete(65));
//		System.out.println(	r.verifyProperty5(v.getRoot()));
//		
//		System.out.println(v.getRoot().getKey()+"----------asda");
//		System.out.println(v.delete(68));
//		System.out.println(	r.verifyProperty5(v.getRoot()));
//		
//		System.out.println(v.getRoot().getKey() +" sadas");
//		System.out.println(v.getRoot().getLeftChild().isNull() +" sadas");
//		System.out.println(v.getRoot().getRightChild().isNull() +" sadas");
//		System.out.println(v.delete(70));
//		System.out.println(	r.verifyProperty5(v.getRoot()));
//		
//		System.out.println(v.getRoot());
//		System.out.println(v.getRoot().getLeftChild());
//		System.out.println(v.getRoot().getRightChild());
//		System.out.println(v.getRoot().getLeftChild().getLeftChild());
		
	}


}
