package shootgame.model;

public class CircularQueue<T> {
	private int head;
	private int tail;
	private int maxSize;
	private Object[] queueArray;
	
	public CircularQueue(int maxSize) {
		super();
		this.head = 0;
		this.tail = 0;
		this.maxSize = maxSize+1;
		this.queueArray = new Object[this.maxSize];
	}
	
	public static void main(String[] args) {
		CircularQueue<String> cq = new CircularQueue<>(5);
		System.out.println("full : " + cq.isFull());
		System.out.println("empty: " + cq.isEmpty());
		System.out.println();
		cq.offer("1");
		System.out.println("full : " + cq.isFull());
		System.out.println();
		cq.offer("2");
		System.out.println("full : " + cq.isFull());
		System.out.println();
		cq.offer("3");
		System.out.println("full : " + cq.isFull());
		System.out.println();
		cq.offer("4");
		System.out.println("full : " + cq.isFull());
		System.out.println();
		cq.offer("5");
		System.out.println("full : " + cq.isFull());
		System.out.println();	
		cq.offer("4");
		System.out.println("full : " + cq.isFull());
		System.out.println();
		

		
//		cq.printArray();
//		System.out.println("remove : " + cq.remove());
//		cq.offer("4");
//		
//		cq.remove();
//		cq.offer("A");
//		cq.remove();
//		cq.offer("B");
//		System.out.println("full : " + cq.isFull());
//		System.out.println();
		cq.printArray();
	}
	
	public T remove()
	{
		if(isEmpty())
			return null;
		int currentTail = tail++;
		if(tail==maxSize-1)
			tail = 0;
		return (T)queueArray[currentTail];
	}
	
	public boolean offer(T obj)
	{
		if(isFull())
			return false;
		int currentHead = head++;
		queueArray[currentHead] = obj;
		if(head==maxSize-1)
			head=0;
			
		return true;
	}
	
	public boolean isFull()
	{
		if(tail-head==1 || head-tail==maxSize-1)
			return true;
		return false;
	}
	
	public boolean isEmpty()
	{
		return head==tail;
	}
	
	public int getHead() {
		return head;
	}
	
	public int getTail() {
		return tail;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public T[] getQueueArray() {
		return (T[])queueArray;
	}
	
	public void printArray()
	{
		for(int i=0; i<maxSize; i++)
		{
			System.out.println("queueArray["+i+"] : " + queueArray[i]);
		}
		System.out.println();
		System.out.println("head : " + head);
		System.out.println("tail : " + tail);
	}


}	
