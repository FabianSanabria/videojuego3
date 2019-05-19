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
public class Hero {
    private String alias;
    private Deck deck;
    private double hp;
    private double damageDealt;
            
    public Hero(String alias,Deck deck) {
        this.alias = alias;
        this.hp = 15;
        this.deck=deck;
        damageDealt=0;
    }

    public String getAlias() {
        return alias;
    }

    public Deck getDeck() {
        return deck;
    }

    public double getHp() {
        return hp;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    public void loseHp(double damage) {
        if(damage>=hp){
            hp=0;
        }
        else{
           hp= hp-damage; 
        }
    }
    public void dealDamage(double damage){
        damageDealt= damageDealt + damage;
    }
    
    
}
