package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable{

	/*Serializable is an interface which makes this class it self writable 
	 * and readable, to declare what ever variables needed to be 
	 * saved and loaded. like player status ...
	 * this used and not a config file so it would prevent players from 
	 * controlling their player's life, weapons..*/
	
	// player stats
	int level;
	int maxLife;
	int life;
	int maxDefense;
	int defense;
	int mana;
	int maxMana;
	int strength;
	int dexterity;
	int exp;
	int nextLevelExp;
	int coin;
	
	//player inventory
	ArrayList<String> itemNames = new ArrayList<>();
	ArrayList<Integer> itemAmounts = new ArrayList<>();
	int currentWeaponSlot;
	int currentShieldSlot;
	
	//objects on map
	String mapObjectNames[][];
	int mapObjectWorldX[][];
	int mapObjectWorldY[][];
	String mapObjectLootNames[][];
	boolean mapObjectOpened[][];
}
