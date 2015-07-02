

import java.util.Arrays;

/**This class defines the Heap Data Structure with two constructors.
 * One for creating empty Heap with default capacity and another is for creating an heap from an array
 * 
 * @author subhojit.mukherjee
 *
 */
public class MaxHeap {

	private int array[];
	private int count;
	private int capacity;
	public int heap_type;
	
	/**This Constructor makes blank Heap with given capacity 
	 * 
	 * @param capacity
	 */
	public MaxHeap(int capacity)
	{
		this.capacity=capacity;
		array=new int[capacity];
		this.count=0;
		
	}
	
	/**This Constructor makes Heap from a given array
	 * 
	 * @param arr
	 */
	public MaxHeap(int arr[])
	{
		this.capacity=arr.length;
		this.count=arr.length;
		this.array=new int[this.capacity];
		System.arraycopy(arr, 0, this.array, 0, arr.length);
		buildHeap();
		
	}
	public MaxHeap()
	{
		
	}
	
	/**Returns the current Heap Size
	 * 
	 * @return
	 */
	public int getArraySize()
	{
		return this.array.length;
	}
	
	
	/**Gives parent of a given Heap Item
	 * 
	 * @param i Location of Item
	 * @return
	 */
	public int parent(int i)
	{
		if(i<=0 || i>count)
			return -1;
		return (i-1)/2;
	}
	
	/**Gives left child location of a given Item
	 * 
	 * @param i Location of Item
	 * @return
	 */
	public int leftChild(int i)
	{
		if((2*i+1)<count)
			return 2*i+1;
		return -1;
	}
	
	/**Gives right child location of a given Item
	 * 
	 * @param i Location of Item
	 * @return
	 */
	public int rightChild(int i)
	{
		if((2*i+2)<count)
			return 2*i+2;
		return -1;
	}
	
	/**Returns the maximum element of Heap
	 * 
	 * @return Maximum Element
	 */
	public int getMaximum()
	{
		if(this.count==0)
			return -1;
		return array[0];
	}
	
	/**Prints the current items in Heap
	 * 
	 */
	public void printArray()
	{
		System.out.println(Arrays.toString(this.array));
	}
	
	/**Private method to resize the Heap
	 * 
	 */
	private void resizeHeap()
	{
		int []temp=new int[this.capacity];
		System.arraycopy(array, 0, temp, 0, this.capacity);
		this.array=new int[this.capacity*2];
		this.capacity*=2;
		temp=null;
		
	}
	
	/**This method makes the heap with Heap Property
	 * 
	 */
	public void buildHeap()
	{
		for(int i=(this.count-1)/2;i>=0;i--)
		{
			percolateDown(i);
		}
	}
	
	/**This is a private method which is used by buildHeap() method to properly link Parent with Children
	 * 
	 * @param i
	 */
	private void percolateDown(int i)
	{
		int left,right,temp,max;
		left=leftChild(i);
		right=rightChild(i);
		if(left!=-1 && array[left]>array[i])
		 max=left;
		else 
		 max=i;
		if(right!=-1 && array[right]>array[max])
			max=right;
		if(max!=i)
		{
			temp=array[i];
			array[i]=array[max];
			array[max]=temp;
			percolateDown(max);	
		}
		
	}
	
	/**Inserts data in the Heap
	 * 
	 * @param elm
	 */
	public void insertItem(int elm)
	{
		if(this.count==this.capacity)
		{
			resizeHeap();
		}
		
		this.count++;
		this.array[count-1]=elm;
		buildHeap();
	}
	
	/**Deletes the data from the Heap and return it,Whcich is the largest element
	 * 
	 * @return
	 */
	public int deleteItem()
	{
		if(this.count==0)
			return -1;
		int elm=array[0];
		array[0]=array[this.count-1];
		this.count--;
		buildHeap();
		return elm;
	}
	
	
	public static void main(String[] args) {
		int arr[]={10,31,12,5,8,21,18,3,2,1,7};
		MaxHeap mh=new MaxHeap(arr);
		mh.printArray();
		mh.buildHeap();
		mh.printArray();
		System.out.println(mh.getArraySize());
		System.out.println("Inserting..");
		mh.insertItem(15);
		System.out.println(mh.getArraySize());
		mh.printArray();
		mh.deleteItem();
		mh.printArray();
		mh.insertItem(32);
		mh.printArray();
	}
	

}
