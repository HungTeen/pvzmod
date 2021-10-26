package com.hungteen.pvz.api.types;

import net.minecraft.util.text.IFormattableTextComponent;

/**
 * used for type interfaces which need an identity to store information.
 */
public interface IIDType {

	/**
	 * get type name, such as pea_shooter.
	 */
	String toString();
	
	/**
	 * used to specify store information (nbt) of type. <br>
	 * [mod id]:[type name], such as pvz:pea_shooter.
	 */
	String getIdentity();
	
	/**
	 * get translation text.
	 */
	IFormattableTextComponent getText();
	
	/**
	 * specific mod id.
	 */
	String getModID();
	
}
