package com.hungteen.pvz.utils.others;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.mojang.datafixers.util.Pair;

public class WeightList<T> {
	
	private final List<T> itemList = new ArrayList<>();
	private final List<Integer> weightList = new ArrayList<>();
	private Optional<T> leftItem = Optional.empty();
	private int tot;
	private int sum = 0;
	
	public WeightList() {
	}
	
	public WeightList(int tot) {
		this.tot = tot;
	}
	
	public WeightList<T> addItem(T item, int w) {
		itemList.add(item);
		weightList.add(w);
		sum += w;
		return this;
	}
	
	public WeightList<T> setLeftItem(T item) {
		this.leftItem = Optional.ofNullable(item);
		return this;
	}
	
	public WeightList<T> setTotal(int tot){
		this.tot = tot;
		return this;
	}
	
	/**
	 * get the weight item randomly.
	 */
	public Optional<T> getRandomItem(Random rand) {
		int now = 0;
		int pos = rand.nextInt(this.tot);
		for(int i = 0;i < itemList.size(); ++ i) {
			now += weightList.get(i);
			if(pos < now) {
				return Optional.of(itemList.get(i));
			}
		}
		return leftItem;
	}
	
	@SafeVarargs
	public static <W> WeightList<W> of(int tot, Pair<W, Integer> ... pairs ){
		WeightList<W> list = new WeightList<>(tot);
		for(Pair<W, Integer> p : pairs) {
			list.addItem(p.getFirst(), p.getSecond());
		}
		return list;
	}
	
	@SafeVarargs
	public static <W> WeightList<W> of(Pair<W, Integer> ... pairs ){
		WeightList<W> list = new WeightList<>();
		int sum = 0;
		for(Pair<W, Integer> p : pairs) {
			list.addItem(p.getFirst(), p.getSecond());
			sum += p.getSecond();
		}
		return list.setTotal(sum);
	}
	
}
