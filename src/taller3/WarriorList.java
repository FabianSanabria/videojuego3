/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3;

import ucn.StdOut;

/**
 *
 * @author fabianxd
 */
class WarriorList {
    private Warrior [] warriors;
    private int warriorQty;
    private int max;

    public WarriorList(int max) {
        warriorQty=0;
        this.max = max;
        warriors= new Warrior[max];
    }

    public Warrior getWarrior(int i) {
        Warrior warrior;
        if(i<warriorQty){
            warrior= warriors[i];
            return warrior;
        }
        else{
         return null;   
        }
        
    }
    public boolean addWarrior(Warrior warrior){
        if(warriorQty>=max){
            StdOut.println("cant add more warriors, list is full");
            return false;           
        }
        else{
            warriors[warriorQty]=warrior;
            warriorQty++;
            return true;
        }
    }

    public int getWarriorQty() {
        return warriorQty;
    }
}
