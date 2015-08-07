package com.gt.chapter5;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月7日上午10:16:11
 * 最长共子序列，最长公共子串问题，当然还有背包问题,最大子数组和
 * 
 * 关于动态规划的一点点总结：首先看是否最优子结构性质---原问题解中子问题的解是否是子问题的最优解； 然后先看递归终止条件一般是为0的时候；
 * 然后是最重要的递推公式：一般是先假设子问题已知，看看如何根据题目条件得到下一个问题，这里出现不同情况的递推公式；最后就是编码求解；
 * 
 * 
 */
public class LCS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LCS lcs = new LCS();
		String s1 = "www.hankcs.com";
		String s2 = "w.ehjlkcsYou";
//		lcs.lcsContinus(s1, s2);
//		lcs.lcsNoContinus(s1, s2);
		
		int[] w = {2,1,6,5,4};
		int[] v = {6,3,5,4,6};
		lcs.pack(w, v, 5, 10);
		
		
	}
	
	/**
	 * 返回最长公共子序列，不要求连续
	 * 终止条件，一方为0的时候另一方无论是什么dp[][]数组都是0；
	 * 递推公式：已知s1[1,……i]和s2[1,……j]的最长公共子序列，那么考虑，s1[1,……i+1]和s2[1,……j+1]
	 * 如果s1[i+1]==s2[j+1]那么在原先的基础上+1就OK，如果不相等呢，那就看那个最长{s1[1,……i+1]和s2[1,……j]} 和{s1[1,……i]和s2[1,……j+1]}
	 * @param s1
	 * @param s2
	 * @return
	 */
	public String lcsNoContinus(String s1, String s2){
		if(s1==null||s1.isEmpty()||s2==null||s2.isEmpty())
			return "";
		int len1 = s1.length();
		int len2 = s2.length();
		int[][] match = new int[len1+1][len2+1];
		for(int i=1; i<=len1; i++){
			for (int j=1; j<=len2; j++){
				if(s1.charAt(i-1)==s2.charAt(j-1)){
					match[i][j]=match[i-1][j-1]+1;
				}else{
					match[i][j]=Math.max(match[i-1][j], match[i][j-1]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(len1>0 && len2>0){
			if(s1.charAt(len1-1)==s2.charAt(len2-1)){
				sb.append(s1.charAt(len1-1));
				len1--;
				len2--;
			}else if(match[len1][len2-1]>match[len1-1][len2]){
				len2--;
			}else{
				len1--;
			}
		}
		System.out.println(sb.reverse().toString());
		
		return sb.reverse().toString();
	}
	
	
	/**
	 * 返回最长公共子串，使用的是二维数组
	 * 递推终止条件，在下面程序中是要看s1[0]和s2[1,……i]是否相等的，相等为1，不等为0；
	 * 递推公式：已知s1前i个与s2前j个的最长公共子串，求s1的前i+1与s2的前j+1;
	 * 		还是看s1[i+1]与s2[j+1]是否相等，相等就在原先基础+1，不等就直接为0了；因为公共子串要求连续。
	 * @param s1
	 * @param s2
	 * @return
	 */
	public String lcsContinus(String s1, String s2){
		if(s1==null||s1.isEmpty()||s2==null||s2.isEmpty())
			return "";
		int len1 = s1.length();
		int len2 = s2.length();
		int[][] match = new int[len1][len2];
		int max = 0; //子字符串的最大长度
		int lastIndex =0 ; // 子字符串的最后一个索引
		for(int i=0; i<len1; i++){
			for(int j=0; j<len2; j++){
				if(s2.charAt(j)==s1.charAt(i)){
					if(i>0&&j>0&&match[i-1][j-1]!=0){
						match[i][j]=match[i-1][j-1]+1;
					}else{
						match[i][j]=1;
					}
					if(match[i][j]>max){
						max = match[i][j];
						lastIndex = i;
					}
				}//相等就要判断最长是否变化
				else {
					match[i][j]=0;
				}
			}
		}
		
		if(max==0)
			return "";
		StringBuilder sb = new StringBuilder();
		//根据长度和最大索引位置返回最长子字符串
		for(int i= lastIndex-max+1; i<=lastIndex; i++){
			sb.append(s1.charAt(i));
		}
		
		System.out.println(sb.toString());
		return sb.toString();
	}


	/**
	 * 01背包问题，n个物品重量，价值，背包的容量
	 * 同样递推，当前wi<C的话，dp[i][C]与dp[i-1, C-wi]+vi比较大小
	 * @param w
	 * @param v
	 * @param len
	 * @param C
	 * @return
	 */
	public int pack(int[] w, int[] v,int len, int C){
		if(len<=0||C<=0)
			return 0;
		int[][] dp = new int[len+1][C+1]; //构造一个数组，这样00不用初始化了
		for(int i=0; i<=len; i++)
			dp[i][0]=0;
		for(int j=0; j<=C; j++)
			dp[0][j]=0;
		for(int i=1; i<=len; i++){
			for(int j=1; j<=C; j++){ //表示每一次的背包容量
				if(w[i-1]>j){
					dp[i][j]=dp[i-1][j];
				}else{//如果
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i-1]]+v[i-1]);
				}
			}
		}
		int tmpw = C;
		int allV = 0;
		ArrayList solution = new ArrayList();
		for (int i=len; i>=1; i--){
			if(dp[i][tmpw] > dp[i-1][tmpw]){
				solution.add(w[i-1]);
				allV += v[i-1];
				tmpw = tmpw-w[i-1];
			}
		}
		System.out.println(Arrays.toString(solution.toArray()));
		
		return allV;
	}
	
	/**
	 * 最大子数组和
	 * @param arr
	 * @return
	 */
	public int maxSubArray(int[] arr){
		if(arr==null||arr.length<=0)
			return Integer.MIN_VALUE;
		int maxSum = Integer.MIN_VALUE;
		int curSum = 0;
		int curStart = 0, start=0, end=0;
		for(int i=0; i<arr.length; i++){
			if(curSum<0){
				curSum=arr[i];
				curStart = i;
			}else{
				curSum+=arr[i];
			}
			if(curSum>maxSum){
				maxSum = curSum;
				start = curStart;
				end = i;
			}
		}
		return maxSum;
	}
	
	/**
	 * 最大子数组和，动态规划思想
	 * 最后一个以i结尾的子数组分为以下几种情况：arr[i]本身， arr[i]+value[i-1]，如果连续就没有(value[i-1])这一项。
	 * @param arr
	 * @return
	 */
	public int maxSubArrDP(int[] arr){
		if(arr==null||arr.length<=0)
			return Integer.MIN_VALUE;
		int start=0, end=0;
		int maxSum ;
		int len = arr.length;
		int[] dp = new int[len];
		
		dp[0]=arr[0]; //初始化
		maxSum = dp[0];
		
		int tmpStart=0;
		for (int i=1; i<len; i++){
			if(dp[i-1]<=0){//上面三者的最大值
				dp[i]=arr[i];
				tmpStart=i;
			}else
				dp[i]=arr[i]+dp[i-1];
			
			if(dp[i]>maxSum){
				maxSum = dp[i];
				start=tmpStart;
				end = i;
			}
		}
		return maxSum;
	}
	
	
	/*public int maxSubArrDP2(int[] arr){
		int len=arr.length;
		int[] dp = new int[len];
		dp[0] = arr[0];
		int maxSum = dp[0];
		for (int i=1; i<len; i++){
			dp[i]=
		}
	}*/
	
	
}
