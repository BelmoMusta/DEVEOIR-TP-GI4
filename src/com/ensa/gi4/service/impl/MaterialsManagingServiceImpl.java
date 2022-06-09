package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.DAO;
import com.ensa.gi4.datatabase.impl.AllocationsDAO;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Material;
import com.ensa.gi4.service.api.MaterialsManagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.Event;

import java.util.List;
import java.util.Scanner;

@Service
public class MaterialsManagingServiceImpl implements MaterialsManagingService {
    Scanner sc = new Scanner(System.in);

    @Autowired
    DAO<Material> materialsDAO;
    @Autowired
    AllocationsDAO allocationsDAO;
    @Autowired
    ApplicationPublisher publisher;

    @Override
    public void addMaterial() {

        System.out.println("Enter material name: ");
        String name = sc.next();
        System.out.println("Enter Quantity: ");
        int quantity = sc.nextInt();
        System.out.println("Enter Material Type CH (for Chair), BK (for Book) : ");
        String type = sc.next();
        Material material = new Material(name,quantity,type);
        //Using publisher pattern to add entity
        publisher.publish(new MyEvent(material, EventType.ADD));
    }

    @Override
    public void deleteMaterial() {
        System.out.println("Enter id of material to delete");
        int id = sc.nextInt();
        List<Allocation> allocations = allocationsDAO.getAllocationsByMaterialId(id);
        if(!allocations.isEmpty())
            System.out.println("There are still some allocations with this material, please return them before proceeding with delete");
        //using publisher pattern to remove entity
        else publisher.publish(new MyEvent(id, EventType.REMOVE));
    }

    @Override
    public void updateMaterial() {
        System.out.println("Enter id of material to update: ");
        int id = sc.nextInt();

        System.out.println("Enter new material name: ");
        String name = sc.next();
        System.out.println("Enter new Quantity: ");
        int quantity = sc.nextInt();
        System.out.println("Enter new Material Type CH (for Chair), BK (for Book) : ");
        String type = sc.next();
        Material material = new Material(name,quantity,type);
        materialsDAO.update(id,material);

    }

    @Override
    public void listAllMaterials() {
        materialsDAO.getAll().forEach(System.out::println);
    }

    @Override
    public void showMaterial() {
        System.out.println("Enter id of material to show: ");
        int id = sc.nextInt();
        System.out.println(materialsDAO.getById(id).get());
    }

    @Override
    public void borrowMaterial(int userId) {
        System.out.println("Enter id of material to borrow: ");
        int id = sc.nextInt();

        Material material = materialsDAO.getById(id).get();
        //Check if material is available or enough quantity is available before borrowing
        if(material != null)
        {
            if(material.getQuantity() > 0)
            {
                //decreasing quantity of material and adding it to borrowed materials
                material.setQuantity(material.getQuantity() - 1);
                allocationsDAO.add(new Allocation(userId,id,0));
                materialsDAO.update(id,material);
            }
            else System.out.println("Not enough quantity available");
        }
        else System.out.println("Material not found");

    }

    @Override
    public void returnMaterial(int userId) {
        System.out.println("Enter id of material to return: ");
        int id = sc.nextInt();
        Material material = materialsDAO.getById(id).get();
        if(material != null)
        {
            material.setQuantity(material.getQuantity() + 1);
            allocationsDAO.deleteByUserIdAndMaterialId(id,userId);
            materialsDAO.update(id,material);
        }else System.out.println("Material not found!");
    }

    @Override
    public void showMaterialsBorrowedByMe(int userId) {
        showUsersAllocations(userId);
    }

    @Override
    public void showMaterialsBorrowedByAUser() {
        System.out.println("Enter id of user of whom you want to see allocations.");
        int id = sc.nextInt();
        showUsersAllocations(id);
    }

    public void showUsersAllocations(int userId)
    {
        allocationsDAO.getAllByUserId(userId).forEach(System.out::println);
    }
}
