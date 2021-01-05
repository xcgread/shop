package com.xuzhihao.test;

//java BinaryTree
//二叉树
//判断一个数是不是2的幂次方
public class BinaryTree {

	int data; // 根节点数据
	BinaryTree left;
	BinaryTree right;

	/**
	 * 构造
	 * 
	 * @param data
	 */
	public BinaryTree(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public void insert(BinaryTree root, int data) {
		if (data > root.data) {// 右子树
			if (root.right == null) {
				root.right = new BinaryTree(data);
			} else {
				this.insert(root.right, data);
			}
		} else {// 左子树
			if (root.left == null) {
				root.left = new BinaryTree(data);
			} else {
				this.insert(root.left, data);
			}
		}
	}

	public static void preOrder(BinaryTree root) {//
		if (root != null) {
			System.out.print(root.data + "-");
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	public static void inOrder(BinaryTree root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(root.data + "--");
			inOrder(root.right);
		}
	}

	public static void postOrder(BinaryTree root) {
		if (root != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.print(root.data + "--");
		}
	}

	public static void main2(String[] args) {
		int[] array = { 12, 76, 35, 22, 16, 48, 90, 46, 9, 40 };
		BinaryTree root = new BinaryTree(array[0]);// 创建二叉树
		for (int i = 1; i < array.length; i++) {
			root.insert(root, array[i]);// 向二叉树中插入数据
		}
		System.out.println("先根遍历：");
		preOrder(root);
		System.out.println();
		System.out.println("中根遍历：");
		inOrder(root);
		System.out.println();
		System.out.println("后根遍历：");
		postOrder(root);
	}

	public static void main(String[] args) {
		int n = 16;
		System.out.println((n & (n - 1)) == 0);
		System.out.println(nCF3(n));
	}

	public static boolean nCF3(int n) {
		boolean boo = true;
		String s = Integer.toBinaryString(n);
		byte[] b = s.getBytes();

		for (int i = 1; i < b.length; i++) {
			if (b[i] != 48) {
				boo = false;
				break;
			}
		}
		return boo;
	}

	public static void main4(String[] args) {
		int n = 17;
		System.out.println(n % 3);
		System.out.println(n / 3);

		System.out.println(nCF(n));
	}

	public static boolean nCF(int n) {
		boolean b = false;
		while (true) {
			int j = n % 2;
			n = n / 2;
			if (j == 1) {
				b = false;
				break;
			}
			if (n == 2) {
				b = true;
				break;
			}

		}
		return b;
	}

}
