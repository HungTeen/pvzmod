package com.hungteen.pvz.common.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.item.Item;

public class EssenceType {

    public static final List<EssenceType> ESSENCES = new ArrayList<>();
	
	private final Supplier<Item> essenceSuppiler;
	private final String essenceName; 
	
	public EssenceType(String name, Supplier<Item> sup) {
		this.essenceName = name;
		this.essenceSuppiler = sup;
		ESSENCES.add(this);
	}
	
	public Item getEssenceItem() {
		return this.essenceSuppiler.get();
	}
	
	@Override
	public String toString() {
		return this.essenceName;
	}
	
}
