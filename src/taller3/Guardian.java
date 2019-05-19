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
public class Guardian extends Card {
    private String race;
    private double hp;
    private double damage;

    public Guardian(String race, double hp, double damage, String name, String id) {
        super(name, id);
        this.race = race;
        this.hp = hp;
        this.damage = damage;
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
    
    
}
