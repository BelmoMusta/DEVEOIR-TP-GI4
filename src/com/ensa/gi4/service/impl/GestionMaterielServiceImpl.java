package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.AllocationDetailsDao;
import com.ensa.gi4.datatabase.api.ChaiseDao;
import com.ensa.gi4.datatabase.api.LivreDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.*;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.I18nService;
import com.ensa.gi4.service.api.UserService;
import com.ensa.gi4.utils.EntityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GestionMaterielServiceImpl implements GestionMaterielService {
    final static Map<Integer, Function<AllocationDetails, Object>> groupFunction = new HashMap<>(){{
        put(1, AllocationDetails::getMateriel);
        put(2, AllocationDetails::getUser);
    }};
    Scanner scanner = new Scanner(System.in);

    @Value("${materiel.row.format}")
    private String format;
    @Value("${materiel.fields}")
    private String MaterielFields;

    final MaterielDao materielDao;
    final LivreDao livreDao;
    final ChaiseDao chaiseDao;
    final I18nService i18nService;
    final EntityUtils entityUtils;
    final UserService userService;
    final AllocationDetailsDao allocationDetailsDao;
    final ApplicationPublisher applicationPublisher;


    @Override
    public void listerMateriel() {
        System.out.format(format, i18nService.getText("table.header").split(","));
        materielDao.findAll().forEach(materiel -> {
            System.out.format(format, entityUtils.extractFromEntity(materiel, null));
        });
    }

    @Override
    public void ajouterNouveauMateriel(String[] materielFields) {
        String type = materielFields[1];
        if ("LIVRE".equals(type)){
            Livre livre = new Livre();
            entityUtils.populateInputFields(livre, materielFields);
            livre = livreDao.save(livre);
            applicationPublisher.publish(new MyEvent<>(livre, EventType.ADD));
        }else{
            Chaise chaise = new Chaise();
            entityUtils.populateInputFields(chaise, materielFields);
            chaise = chaiseDao.save(chaise);
            applicationPublisher.publish(new MyEvent<>(chaise, EventType.ADD));
        }
    }

    @Override
    public void chercherMateriel(String searchInput) {
        //Traitement pour que l'utilisateur entre les critères dans la langue courante
        List<String> queryConditions = new ArrayList<>();
        List<String> queryValues = new ArrayList<>();
        String[] materielFields = MaterielFields.split(",");
        List<String> fieldsI18n = Arrays.stream(i18nService.getText("table.header").split(",")).map(String::toLowerCase).toList();
        String[] criteria = Arrays.stream(searchInput.split(",")).map(String::toLowerCase).toArray(String[]::new);

        for(String cr : criteria){
            if(cr.split(":").length != 2){
                System.out.println(i18nService.getText("message.wrong.input"));
                return;
            }
            String field = cr.split(":")[0];
            String value = cr.split(":")[1];
            int index = fieldsI18n.indexOf(field);
            if (index == -1){
                System.out.println(i18nService.getFormattedText("message.wrong.field.notfound", field));
                return;
            }
            else if (index == 0){
                queryConditions.add("id=?");
                queryValues.add(value);
            }else{
                queryConditions.add(String.format("LOWER(%s) LIKE ?", materielFields[index]));
                queryValues.add("%" + value + "%");
            }
        }
        List<Materiel> materiels = materielDao.findAllWithCriteria(queryConditions, queryValues);
        if (materiels.size()>0){
            System.out.format(format, i18nService.getText("table.header").split(","));
            materiels.forEach(materiel -> {
                System.out.format(format, entityUtils.extractFromEntity(materiel, null));
            });
        }else{
            System.out.println(i18nService.getText("message.materiel.notfound"));
        }

    }

    @Override
    public void modifierMateriel(int id) {
        scanner = new Scanner(System.in); //for JUnit to be able to test with custom inputs
        Materiel materiel = this.materielDao.findOne(id);
        if (materiel == null){
            System.out.println(i18nService.getText("message.materiel.notfound"));
            return;
        }
        String[] values = entityUtils.extractInputFields(entityUtils.convertToType(materiel));
        String[] fields = i18nService.getText(String.format("materiel.%s.input.fields", values[1].toLowerCase())).split(",");
        System.out.println(i18nService.getText("message.info.edition"));
        for(int i=0; i<fields.length; i++){
            if(i==1) continue; //TYPE FIELD
            String old = String.format("(old: %s)", values[i]);
            System.out.print(i18nService.getFormattedText("prompt.input.value", fields[i]) + old);
            String value = scanner.nextLine();
            values[i] = value.isBlank()?values[i]:value;
        }
        this.materielDao.update(id, fields, values);
        this.applicationPublisher.publish(new MyEvent<>(this.materielDao.findOne(id), EventType.UPDATE));
    }

    @Override
    public void supprimerMateriel(int id) {
        Materiel materiel = this.materielDao.findOne(id);
        if (materiel == null){
            System.out.println(i18nService.getText("message.materiel.notfound"));
            return;
        }
        this.allocationDetailsDao.deleteAllByMaterialId(id);
        this.materielDao.delete(id);
        this.applicationPublisher.publish(new MyEvent<>(materiel, EventType.REMOVE));
    }

    @Override
    public void allouerMateriel(int id) {
        User currentUser = userService.getLoggedUser();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        if(!this.materielDao.isAvailable(id)){
            System.out.println(i18nService.getFormattedText("message.materiel.alreadyallocated", id));
            return;
        }
        this.materielDao.allocate(id);
        Materiel materiel = this.materielDao.findOne(id);
        if (materiel==null){
            System.out.println(i18nService.getText("message.materiel.notfound"));
            return;
        }
        AllocationDetails allocationDetails = new AllocationDetails();
        allocationDetails.setUser(currentUser);
        allocationDetails.setDate(Timestamp.valueOf(LocalDateTime.now()));
        allocationDetails.setMateriel(materiel);
        allocationDetailsDao.save(allocationDetails);
        System.out.println(i18nService.getFormattedText("message.info.allocation", materiel.getId(), currentUser.getUsername(), dateFormat.format(new Date(allocationDetails.getDate().getTime()))));
    }

    @Override
    public void desallouerMateriel(int id) {
        Materiel materiel = this.materielDao.findOne(id);
        if (materiel == null){
            System.out.println(i18nService.getText("message.materiel.notfound"));
            return;
        }
        if (materiel.getAvailable().equals(materiel.getStock())){
            System.out.println(i18nService.getText("message.materiel.notallocated"));
            return;
        }
        this.materielDao.deallocate(id);
        System.out.println(i18nService.getFormattedText("message.info.deallocation", id));
    }

    @Override
    public void listerMaterielAlloue() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String[] tableHeader = i18nService.getText("table.header.allocation").split(",");
        String rowFormat = "%-15s %-50s %-20s %-20s %-5s\n";
        Map<Materiel,List<AllocationDetails>> grouped = this.allocationDetailsDao.findAll().stream().collect(groupingBy(AllocationDetails::getMateriel));
        System.out.format(rowFormat, tableHeader);
        for(Materiel materiel : grouped.keySet()){
            if (materiel.getAllocated()==0) continue;
            List<AllocationDetails> productAllocations = grouped.get(materiel);
            productAllocations.sort(Comparator.comparing(AllocationDetails::getDate));
            AllocationDetails lastAllocation = productAllocations.get(0);
            System.out.format(rowFormat, lastAllocation.getMateriel().getId().toString(),
                    lastAllocation.getMateriel().getName(),
                    lastAllocation.getUser().getUsername(),
                    simpleDateFormat.format(new Date(lastAllocation.getDate().getTime())),
                    lastAllocation.getMateriel().getAllocated());
        }
    }

    @Override
    public void listerAllocationHistory(int groupId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String rowFormat = "\t\t%-15s %-30s %-20s\n";
        Map<Object,List<AllocationDetails>> grouped = this.allocationDetailsDao.findAll().stream().collect(groupingBy(groupFunction.get(groupId)));
        for(Object field : grouped.keySet()){
            try {
                String id = field.getClass().getMethod("getId").invoke(field).toString();
                String name = (String) field.getClass().getMethod("getName").invoke(field);
                System.out.format("[+] %s %s\n", id, name);

                System.out.format(rowFormat, "ID", "NAME", "DATE");
                for(AllocationDetails detail : grouped.get(field)){
                    Object other = field.getClass().equals(User.class)?detail.getMateriel():detail.getUser();
                    System.out.format(rowFormat, other.getClass().getMethod("getId").invoke(other).toString(),
                            other.getClass().getMethod("getName").invoke(other),
                            simpleDateFormat.format(new Date(detail.getDate().getTime())));
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}
