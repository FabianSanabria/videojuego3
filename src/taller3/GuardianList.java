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
class GuardianList {
    private Guardian [] guardians;
    private int guardianQty;
    private int max;

    public GuardianList(int max) {
        guardianQty=0;
        this.max = max;
        guardians= new Guardian[max];
    }

    public Guardian getGuardian(int i) {
        Guardian guardian;
        if(i<guardianQty){
            guardian= guardians[i];
            return guardian;
        }
        else{
         return null;   
        }
        
    }
    public boolean addGuardian(Guardian guardian){
        if(guardianQty>=max){
            StdOut.println("cant add more guardians, list is full");
            return false;           
        }
        else{
            guardians[guardianQty]=guardian;
            guardianQty++;
            return true;
        }
    }

    public int getGuardianQty() {
        return guardianQty;
    }
}
