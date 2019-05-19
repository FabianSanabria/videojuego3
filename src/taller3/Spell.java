/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3;

/**
 *
 * @author fabianxd
 */
public class Spell extends Card {
    
    private String rarity;
    private double damage;

    public Spell(String name, String id, String rarity,double damage) {
        super(name, id);
        this.rarity = rarity;
        this.damage = damage;
    }

    public String getRarity() {
        return rarity;
    }

    public double getDamage() {
        return damage;
    }
            
    
}
