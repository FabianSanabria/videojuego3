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
class SpellList {
    private Spell [] spells;
    private int spellQty;
    private int max;

    public SpellList(int max) {
        spellQty=0;
        this.max = max;
        spells= new Spell[max];
    }

    public Spell getSpell(int i) {
        Spell spell;
        if(i<spellQty){
            spell= spells[i];
            return spell;
        }
        else{
         return null;   
        }
        
    }
    public boolean addSpell(Spell spell){
        if(spellQty>=max){
            StdOut.println("cant add more spells, list is full");
            return false;           
        }
        else{
            spells[spellQty]=spell;
            spellQty++;
            return true;
        }
    }

    public int getSpellQty() {
        return spellQty;
    }
}
