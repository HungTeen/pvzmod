package com.hungteen.pvz.utils;

public class RenderUtil {

	public static int getRenderBarLen(int num, int maxNum, int maxLen) {
		int percent = num*maxLen/maxNum;
		if(percent==0&&num!=0) return 1;
		if(percent==maxLen&&num!=maxNum) return maxLen-1;
		return percent;
	}
}
