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
public class Warrior extends Card {
    
    private String rarity;
    private String race;
    private double hp;
    private double damage;
    private int timesDead;

    public Warrior(String name, String id, String rarity, String race, double hp, double damage) {
        super(name, id);
        this.rarity = rarity;
        this.race = race;
        this.hp = hp;
        this.damage = damage;
    }

    public int getTimesDead() {
        return timesDead;
    }
    

    public String getRarity() {
        return rarity;
    }

    public String getRace() {
        return race;
    }

    public double getHp() {
        return hp;
    }

    public double getDamage() {
        return damage;
        
    }

    public void loseHp(double damage) {
        if(damage>=hp){
            hp=0;
        }
        else{
           hp= hp-damage; 
        }
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    
    
    
}
