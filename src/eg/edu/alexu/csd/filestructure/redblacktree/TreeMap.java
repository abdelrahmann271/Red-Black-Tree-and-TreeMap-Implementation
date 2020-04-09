package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;

import javax.management.RuntimeErrorException;

import java.util.Set;
import java.util.Stack;

public class TreeMap<T extends Comparable<T>, V> implements ITreeMap<T, V> {
     
	private static final Exception RuntimeErrorException = null;
    
	RedBlackTree<T,V> Tree=new RedBlackTree<T,V>();
	private boolean found=false;
	private Set <Entry<T, V>> set=new LinkedHashSet<>();
	@Override
	public Entry<T, V> ceilingEntry(T key) {
		// TODO Auto-generated method stub
		if(key == null) {
			Error e = null;
			throw new RuntimeErrorException(e);
		}
		INode temp=Tree.getRoot(),last=null;
		while(!temp.isNull()&&temp.getKey().compareTo(key)!=0) {
			if(temp.getKey().compareTo(key)<0) {
				temp=temp.getRightChild();
			}
			else {
				last=temp;
				temp=temp.getLeftChild();
			}
		}
		
		if(temp.getKey().compareTo(key)==0) {
			
			return Map.entry((T)temp.getKey(), (V)temp.getValue());
		}
		return  Map.entry((T)last.getKey(), (V)last.getValue());
	}

	@Override
	public T ceilingKey(T key) {
		// TODO Auto-generated method stub
		if(key == null) {
			Error e = null;
			throw new RuntimeErrorException(e);
		}
		
		return ceilingEntry(key).getKey();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		Tree.clear();
	}

	@Override
	public boolean containsKey(T key) {
		// TODO Auto-generated method stub
		if(key == null) {
			Error e = null;
			throw new RuntimeErrorException(e);
		}

		return Tree.contains(key);
	}

	@Override
	public boolean containsValue(V value) {
		// TODO Auto-generated method stub
		if(value == null) {
			Error e = null;
			throw new RuntimeErrorException(e);
		}
		if(Tree.isEmpty()) {
			return false;
		}
		loop(Tree.getRoot(),value);
		if(found) {
			found=false;
			return true;
		}
		return false;
		

	}
	public void loop(INode k,V value) {
	   
		if(k.getValue().equals(value)) {
			found=true;
			return;
		}
		if(!k.getLeftChild().isNull()&&!found) {
			loop(k.getLeftChild(),value);
		}
		if(!k.getRightChild().isNull()&&!found) {
			loop(k.getRightChild(),value);
		}
	}

	@Override
	public Set<Entry<T, V>> entrySet() {
		// TODO Auto-generated method stub
        if(Tree.isEmpty()) {
        	return set;
        }
        Nav(Tree.getRoot());
   
		return set;
	}
   public void Nav(INode k) {
	   if(!k.getLeftChild().isNull()) {
		   Nav(k.getLeftChild());
	   }
	   set.add(Map.entry((T)k.getKey(),(V)k.getValue()));
	   if(!k.getRightChild().isNull()) {
		   Nav(k.getRightChild());
	   }
   }
    
	@Override
	public Entry<T, V> firstEntry() {
		// TODO Auto-generated method stub
		if(Tree.isEmpty()) {
			return null;
		}
		INode temp=Tree.getRoot();
		while(!temp.getLeftChild().isNull()){
			temp=temp.getLeftChild();
		}
		return Map.entry((T)temp.getKey(), (V)temp.getValue());
	}

	@Override
	public T firstKey() {
		// TODO Auto-generated method stub
		Entry<T,V> entry=firstEntry();
		if(entry!=null) {
			return entry.getKey();
		}
		return null;
	}

	@Override
	public Entry<T, V> floorEntry(T key) {
		// TODO Auto-generated method stub
		if (key == null ) {
			Error e = null;
			throw new RuntimeErrorException(e);
		}
		
		INode temp=Tree.getRoot(),last=null;
		while(!temp.isNull()&&temp.getKey().compareTo(key)!=0) {
			if(temp.getKey().compareTo(key)<0) {
				last=temp;
				temp=temp.getRightChild();
			}
			else {
				
				temp=temp.getLeftChild();
			}
		}
		
		if(temp.getKey().compareTo(key)==0) {
			
			return Map.entry((T)temp.getKey(), (V)temp.getValue());
		}
		return  Map.entry((T)last.getKey(), (V)last.getValue());
	}

	@Override
	public T floorKey(T key) {
		// TODO Auto-generated method stub
		if(key == null) {
			Error e = null;
			throw new RuntimeErrorException(e);
		}
		return floorEntry(key).getKey();
	}

	@Override
	public V get(T key) {
		// TODO Auto-generated method stub
		if (key == null ) {
			Error e = null;
			throw new RuntimeErrorException(e);
		}
		return Tree.search(key);
	}

	@Override
	public ArrayList<Entry<T, V>> headMap(T toKey) {
		// TODO Auto-generated method stub

		ArrayList<Entry<T, V>> list=new ArrayList<>();
		Iterator<Entry<T, V>> it=entrySet().iterator();
		while(it.hasNext()) {
			Entry<T,V> entry = it.next();
			if(entry.getKey().compareTo((T)toKey)<0) {
				list.add(entry);
			}
			else {
				break;
			}
		}
		return list;
	}

	@Override
	public ArrayList<Entry<T, V>> headMap(T toKey, boolean inclusive) {
		// TODO Auto-generated method stub
		if(!inclusive) {
			return headMap(toKey);
		}
		ArrayList<Entry<T, V>> list=new ArrayList<>();
		Iterator<Entry<T, V>> it=entrySet().iterator();
		while(it.hasNext()) {
			Entry<T,V> entry = it.next();
			if(entry.getKey().compareTo((T)toKey)<=0) {
				list.add(entry);
			}
			else {
				break;
			}
		}
		return list;
	}

	@Override
	public Set<T> keySet() {
		// TODO Auto-generated method stub
		Iterator<Entry<T, V>> it=entrySet().iterator();
		Set <T> S=new LinkedHashSet<>();
		while(it.hasNext()) {
			Entry<T,V> entry = it.next();
			S.add((T)entry.getKey());
		}
		return S;
	}

	@Override
	public Entry<T, V> lastEntry() {
		// TODO Auto-generated method stub
		if(Tree.isEmpty()) {
			return null;
		}
		INode temp=Tree.getRoot();
		while(!temp.getRightChild().isNull()){
			temp=temp.getRightChild();
		}
		return Map.entry((T)temp.getKey(), (V)temp.getValue());
	}

	@Override
	public T lastKey() {
		// TODO Auto-generated method stub
		Entry<T,V> entry=lastEntry();
		if(entry!=null) {
			return entry.getKey();
		}
		return null;
	}

	@Override
	public Entry<T, V> pollFirstEntry() {
		// TODO Auto-generated method stub
		if(Tree.isEmpty()) {
			return null;
		}
		Entry<T,V> entry=firstEntry();
		
		Tree.delete(entry.getKey()) ;
		
		
		return entry;
	}

	@Override
	public Entry<T, V> pollLastEntry() {
		// TODO Auto-generated method stub
		if(Tree.isEmpty()) {
			return null;
		}
		Entry<T,V> entry=lastEntry();
		
		Tree.delete(entry.getKey()) ;
		
		
		return entry;
	}

	@Override
	public void put(T key, V value) {
		// TODO Auto-generated method stub
		if( key ==null || value ==null) {
			Error e = null;
		    throw new RuntimeErrorException(e);
		}				
		Tree.insert(key, value);
		
		
	}

	@Override
	public void putAll(Map<T, V> map) {
		// TODO Auto-generated method stub
		if( map ==null) {
			Error e = null;
		    throw new RuntimeErrorException(e);
		}	
	    Tree.clear();
	  
		for(Map.Entry<T, V> entry:map.entrySet()) {
			Tree.insert(entry.getKey(), entry.getValue());
		}
	
	}

	@Override
	public boolean remove(T key) {
		// TODO Auto-generated method stub
		if(key == null) {
			Error e = null;
			throw new RuntimeErrorException(e);
		}
		
		
		return Tree.delete(key);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return Tree.getSize();
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		Iterator<Entry<T, V>> it=entrySet().iterator();
		ArrayList<V> list=new ArrayList<>();
		while(it.hasNext()) {
			Entry<T,V> entry=it.next();
			list.add(entry.getValue());
		}
		return list;
	}

}