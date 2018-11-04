package service;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

public class MaxBinaryHeap<E extends Comparable<E>> {

	private ArrayList<E> list;
	public MaxBinaryHeap(){
		this.list = new ArrayList<>();
	}
	public MaxBinaryHeap(List<E> norList) {
		this.list = new ArrayList<>();
		this.list.addAll(norList);
		int lastNonLeafIndex = this.parentIndex(list.size()-1);
		for(int currIndex = lastNonLeafIndex;currIndex >= 0; currIndex--) {
			sinkDown(currIndex);
		}
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	public int parentIndex(int index) {
		if (index == 0)
			return 0;		
		return index/2;
	}
	public int leftNodeIndex(int index) {
		return (index<<1) + 1;
	}
	public int rightNodexIndex(int index) {
		return (index <<1) + 2;
	}
	public void add(E e) {
		list.add(e);
		int lastIndex = list.size()-1;
		floatingUp(lastIndex);
		
	}
	private void floatingUp(int lastIndex) {
		int parentIndex = this.parentIndex(lastIndex);
		while(list.get(parentIndex).compareTo(list.get(lastIndex)) < 0) {
			swapElement(parentIndex,lastIndex);
			lastIndex =parentIndex;
			parentIndex = this.parentIndex(parentIndex);
		}		
	}
	private void swapElement(int parentIndex, int lastIndex) {
		E lastElement = list.get(lastIndex);
		E parentElement = list.get(parentIndex);
		list.set(lastIndex, parentElement);
		list.set(parentIndex, lastElement);
	}
	public E extractMax() {
		E maxElement = list.get(0);
		
		int lastIndex = list.size() -1;
		swapElement(0, lastIndex);
		list.remove(lastIndex);
		
		int currIndex = 0;
		sinkDown(currIndex);
	
		return maxElement;
	}
	private void sinkDown(int currIndex) {
		int maxElementIndex = getMaxLeafIndex(currIndex);
		while (maxElementIndex != -1 
				&& list.get(currIndex).compareTo(list.get(maxElementIndex)) < 0) {
			swapElement(currIndex, maxElementIndex);
			currIndex = maxElementIndex;
			
			maxElementIndex = getMaxLeafIndex(currIndex);
		}		
	}
	private int getMaxLeafIndex(int currIndex) {
		int leftIndex = this.leftNodeIndex(currIndex);
		int rightIndex = this.rightNodexIndex(currIndex);
		int maxIndex = list.size()-1;
		if(leftIndex > maxIndex)
			return -1;
		if(rightIndex > maxIndex)
			return leftIndex;
		if(list.get(leftIndex).compareTo(list.get(rightIndex)) >= 0)
			return leftIndex;
		
		return rightIndex;
	}
	@Override
	public String toString() {
		return list.toString();
	}
	public static void main(String[] args) {
//		ArrayList<Integer> arrayList = new ArrayList<>();
//		for(int i = 0;i< 10; i++) {
//			arrayList.add(i);
//		}
//		MaxBinaryHeap<Integer> maxHeap = new MaxBinaryHeap<>(arrayList);
//		System.out.println(maxHeap);

		MaxBinaryHeap<Integer> maxHeap = new MaxBinaryHeap<>();
		for(int i = 0;i< 10; i++) {
			maxHeap.add(i);
			System.out.println(maxHeap);
		}
		for(int i = 0;i< 10; i++) {
			maxHeap.extractMax();
			System.out.println(maxHeap);
		}
		
	}
}
