package net.fender.vk.one;

public class One {

	public static void main(String[] args) {
		String num = args[0];
		int max = new One().run(num);
		System.out.println(max);
	}

	public int run(String num) {
		int length = num.length();
		int[] nums = new int[length];
		for (int i = 0; i < length; i++) {
			nums[i] = Integer.parseInt("" + num.charAt(i));
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < length - 3; i++) {
			int first = Integer.parseInt("" + nums[i] + nums[i + 1]);
			int second = Integer.parseInt("" + nums[i + 1] + nums[i + 2]);
			int third = Integer.parseInt("" + nums[i + 2] + nums[i + 3]);
			max = Math.max(max, first + second + third);
		}
		return max;
	}
}
