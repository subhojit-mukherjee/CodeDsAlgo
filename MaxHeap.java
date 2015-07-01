

import java.util.Arrays;

public class MaxHeap {

	private int array[];
	private int count;
	private int capacity;
	public int heap_type;
	public MaxHeap(int capacity)
	{
		this.capacity=capacity;
		array=new int[capacity];
		this.count=0;
		
	}
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
	public int parent(int i)
	{
		if(i<=0 || i>count)
			return -1;
		return (i-1)/2;
	}
	
	public int leftChild(int i)
	{
		if((2*i+1)<count)
			return 2*i+1;
		return -1;
	}
	
	public int rightChild(int i)
	{
		if((2*i+2)<count)
			return 2*i+2;
		return -1;
	}
	
	public int getMaximum()
	{
		if(this.count==0)
			return -1;
		return array[0];
	}
	public void initializeArray(int arr[])
	{
	  this.count=arr.length;
	  array=new int[this.count];
	  System.arraycopy(arr, 0, this.array, 0, arr.length);
	  
	}
	public void printArray()
	{
		System.out.println(Arrays.toString(this.array));
	}
	public void insert(int element)
	{
		
	}
	
	private void resizeHeap()
	{
		int []temp=new int[this.capacity];
		System.arraycopy(array, 0, temp, 0, this.capacity);
		this.array=new int[this.capacity*2];
		System.arraycopy(temp, 0, array, 0, this.capacity);
		this.capacity*=2;
		temp=null;
		
	}
	public void buildHeap()
	{
		for(int i=(this.count-1)/2;i>=0;i--)
		{
			percolateDown(i);
		}
	}
	private void percolateDown(int i)
	{
		int left,right,temp,max;
		left=leftChild(i);
		right=rightChild(i);
		System.out.println("i:"+i+"  left:"+left+"  right:"+right);
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
	public static void main(String[] args) {
		int arr[]={10,31,12,5,8,21,18,3,2,1,7};
		System.out.println(Arrays.toString(arr));
		MaxHeap mh=new MaxHeap();
		mh.initializeArray(arr);
		mh.printArray();
		mh.buildHeap();
		mh.printArray();
	}
	

}
