package com.hungteen.pvz.utils;

import java.util.Comparator;

import net.minecraft.entity.Entity;

public class AlgorithmUtil {

	public static class EntitySorter implements Comparator<Entity> {
		private final Entity entity;

		public EntitySorter(Entity entityIn) {
			this.entity = entityIn;
		}

		public int compare(Entity a, Entity b) {
			double d0 = this.entity.distanceToSqr(a);
			double d1 = this.entity.distanceToSqr(b);
			if (d0 < d1) {
				return -1;
			} else {
				return d0 > d1 ? 1 : 0;
			}
		}
	}
	
	public static class KMP {
		private static int calcMatchValue(String subStr) {
		 
		    int length = subStr.length();
		    String preFixStr = subStr.substring(0, length - 1);
		    String suffFixStr = subStr.substring(1);
		 
		    while (preFixStr.length() > 0 && suffFixStr.length() > 0) {
		        if (preFixStr.equals(suffFixStr)) {
		            return preFixStr.length();
		        }
		 
		        if (preFixStr.length() == 1 && suffFixStr.length() == 1) {
		            break;
		        }
		        preFixStr = preFixStr.substring(0, preFixStr.length() - 1);
		        suffFixStr = suffFixStr.substring(1, suffFixStr.length());
		    }
		 
		    return 0;
		}
		
		private static int[] createPartialMatchTable(String pattern) {

			int patternLen = pattern.length();
			int[] matchTable = new int[patternLen];

			int i = 0;
			int matchValue = 0;
			while (i < patternLen) {
				if (i == 0) {
					matchValue = 0;
				} else {
					matchValue = calcMatchValue(pattern.substring(0, i + 1));
				}

				matchTable[i] = matchValue;
				i++;
			}

			return matchTable;
		}

		public static boolean kmp(String target, String pattern) {
			int[] partialMatchTable = createPartialMatchTable(pattern);
			char[] targetCharArr = target.toCharArray();
			char[] patterncharArr = pattern.toCharArray();
			int matchCharCounts = 0;
			int i = 0, j = 0, moveCounts = 0;
			while (i < targetCharArr.length) {
				if (targetCharArr[i] == patterncharArr[j]) {
					matchCharCounts++;
					i++;
					j++;
				}
				else if (j == 0) {
					i++;
				}
				else {
					moveCounts = matchCharCounts - partialMatchTable[j - 1];
					j = j - moveCounts;
					matchCharCounts = matchCharCounts - moveCounts;
				}
				if (j == patterncharArr.length) {
					return true;
				}
			}
			return false;

		}

	}
}
