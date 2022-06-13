/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Allocation;
import java.util.List;


public interface AllocationDAO {
    
    List<Allocation> findSome(int idM);
    int insertAllocation(Allocation allocation);
    Allocation estAllouer(int idM, int idU);
    void updateAllocation(Allocation alloc);
    java.util.List<Allocation> getMyAllocations(int idU);
    
}
