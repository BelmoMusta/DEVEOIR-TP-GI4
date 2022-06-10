package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.AllocationDAO;
import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.service.api.AllocationService;
import com.ensa.gi4.service.api.AuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AllocationServiceImpl implements AllocationService {
    @Autowired
    public AllocationDAO allocationdao;
    @Autowired
    AuthentificationService authentificationService ;
    @Override
    public void allocateMaterial(Long uid, Long mid) {
        allocationdao.allocateMaterialDAO(mid,uid);
    }

    @Override
    public void returnMaterial(Long id) {
        allocationdao.returnMaterialDAO(id);
    }

    /*@Override
    public List<Allocation> findMaterialAllocated() {
        return allocationdao.findMaterialAllocatedDAO();
    }*/
    @Override
    public void findMaterialAllocated() {
        List<Allocation> list= allocationdao.findMaterialAllocatedDAO();

        int size= authentificationService.findAll().size();
        for(Long i = Long.valueOf(1); i<size+1; i++){
            System.out.println(("user: "+i));
            System.out.println(findMaterialAllocated(i));
        }
    }

    @Override
    public List<Allocation> findMaterialAllocated(Long id) {
        return allocationdao.findMaterialAllocatedDAO(id);
    }
}
