package MyDoing;


import java.io.BufferedReader;
import java.io.InputStreamReader;

 
/**Binary Search Tree using Java
 * 
 * @author subhojit.mukherjee
 *
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {

	private Node root;

	private class Node {
		private Value val;
		private Key key;
		private Node lchild;
		private Node rchild;
		private int N;// number of nodes in subtree

		public Node(Key k, Value v, int n) {
			key = k;
			val = v;
			N = n;
		}

		@Override
		public String toString() {
			return "Key:" + this.key + "...." + "Value:" + this.val;
		}
	}

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null) return 0;
		return x.N;
	}

	public boolean contains(Key key) {// private not needed

		return get(key) != null;
	}

	public Value get(Key key) {

		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if (x == null)
			return null;
		int comp = key.compareTo(x.key);
		if (comp == 0)
			return x.val;
		if (comp < 0)
			return get(x.lchild, key);
		return get(x.rchild, key);

	}

	public void put(Key key, Value val) {

		root = put(root, key, val);

	}

	private Node put(Node x, Key key, Value val) {
		if (x == null)
			return new Node(key, val, 1);
		int comp = key.compareTo(x.key);
		if (comp<0)
			x.lchild = put(x.lchild, key, val);
		else if (comp>0)
			x.rchild = put(x.rchild, key, val);
		else
			x.val=val;
		
		x.N = size(x.lchild) + size(x.rchild) + 1;
		return x;

	}

	/***********************************************************************
	 * Delete
	 ***********************************************************************/

	public void deleteMin() {

		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.lchild == null)
			return x.rchild;
		x = deleteMin(x.lchild);
		x.N = size(x.lchild) + size(x.rchild) + 1;
		return x;

	}

	public void deleteMax() {
		root = deleteMax(root);
	}

	private Node deleteMax(Node x) {
		if (x.rchild == null)
			return x.lchild;
		x = deleteMax(x.rchild);
		x.N = size(x.lchild) + size(x.rchild) + 1;
		return x;

	}

	public void delete(Key key) {

		root = delete(root, key);

	}
	

	private Node delete(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.lchild = delete(x.lchild, key);
		else if (cmp > 0)
			x.rchild = delete(x.rchild, key);
		else {
			if (x.rchild == null)
				return x.lchild;
			if (x.lchild == null)
				return x.rchild;
			Node temp = x;
			x = min(x.rchild);
			x.rchild = deleteMin(temp.rchild);
			x.lchild = temp.lchild;
		}
		x.N = size(x.lchild) + size(x.rchild) + 1;
		return x;

	}

	/***********************************************************************
	 * Min, max, floor, and ceiling
	 ***********************************************************************/
	public Key min() {
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.lchild == null)
			return x;
		return min(x.lchild);
	}

	public Key max() {
		return max(root).key;
	}

	private Node max(Node x) {
		if (x.rchild == null)
			return x;
		return min(x.rchild);
	}

	public Key floor(Key key) {
		Node x = floor(root, key);
		if (x == null)
			return null;
		else
			return x.key;
	}

	private Node floor(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		if (cmp < 0)
			return floor(x.lchild, key);
		Node temp = floor(x.rchild, key);
		if (temp != null)
			return temp;
		return x;
	}

	public Key ceiling(Key key) {
		Node x = ceiling(root, key);
		if (x == null)
			return null;
		else
			return x.key;

	}

	private Node ceiling(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		if (cmp < 0) {
			Node temp = ceiling(x.lchild, key);
			if (temp != null)
				return temp;
			return x;
		}
		return ceiling(x.rchild, key);
	}

	/*
	 * public int rank(Key key) {
	 * 
	 * }
	 */

	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(Node x) {
		if (x == null)
			return;
		System.out.println(x);
		preOrder(x.lchild);
		preOrder(x.rchild);
	}

	public void inOrder() {
		inOrder(root);
	}

	private void inOrder(Node x) {
		if (x == null)
			return;
		inOrder(x.lchild);
		System.out.println(x);
		inOrder(x.rchild);
	}

	public void postOrder() {
		postOrder(root);
	}

	private void postOrder(Node x) {
		if (x == null)
			return;
		postOrder(x.lchild);
		postOrder(x.rchild);
		System.out.println(x);
	}

	public static void main(String[] args)throws Exception {
		
		BinarySearchTree<Integer, Integer> bst=new BinarySearchTree<Integer, Integer>();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int i=Integer.parseInt(br.readLine());
		while(i-->0){
			String[] st=br.readLine().split(" ");
			bst.put(Integer.parseInt(st[0]), Integer.parseInt(st[1]));
			System.out.println(bst.size());
		}
		System.out.println("Preorder");
		bst.preOrder();
		
		System.out.println("Postorder");
		bst.postOrder();
		System.out.println("Inorder");
		bst.inOrder();
		System.out.println("Give the item to delete:"  );
		bst.delete(Integer.parseInt(br.readLine()));
		System.out.println("Inorder");
		bst.inOrder();
		int floor=Integer.parseInt(br.readLine());
		System.out.println("Floor of "+floor+" is-"+bst.floor(floor));
		br.close();
		

	}
}
