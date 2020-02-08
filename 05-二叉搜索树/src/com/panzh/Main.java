package com.panzh;

import java.util.Comparator;

import com.panzh.printer.BinaryTrees;


@SuppressWarnings("unused")
public class Main {
	

	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<>();
		tree.add(4);
		tree.add(2);
		tree.add(3);
		tree.add(6);
		tree.add(5);
		tree.add(8);
		tree.add(1);
		tree.add(15);
		tree.add(5);
		tree.add(66);
		tree.add(12);
		tree.add(55);
		tree.add(41);
		tree.add(14);
		
		BinaryTrees.print(tree);
		System.out.println();
		System.out.println("----------------------");
		tree.remove(3);
		BinaryTrees.print(tree);
//		System.out.println();

	}
}
