package com.techmaster.carrepairsystem.service;

import com.techmaster.carrepairsystem.dto.DemandDTO;
import com.techmaster.carrepairsystem.exception.NotFoundException;
import com.techmaster.carrepairsystem.model.*;
import com.techmaster.carrepairsystem.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class DemandServiceImpl implements DemandService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DemandRepository demandRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RepairServiceRepository serviceRepository;
    @Autowired
    private StaffRepository staffRepository;

    @Transactional
    @Override
    public DemandDTO createDemand(DemandDTO demandDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Demand demand = new Demand();
        // thong tin khach:
        // check customter ton tai hay chua
        Customer customer = new Customer();
        if(customerRepository.findByPhone(demandDTO.getCustomerDTO().getPhone()) == null){
            customer.setName(demandDTO.getCustomerDTO().getName());
            customer.setPhone(demandDTO.getCustomerDTO().getPhone());
            customerRepository.save(customer);
        }
        // da ton tai
        else {
            customer =  customerRepository.findByPhone(demandDTO.getCustomerDTO().getPhone());
        }
        System.out.println(demandDTO.getProductDTOs());
        // thong tin product
        Set<Product> products = demandDTO
                .getProductDTOs()
                .stream()
                .map(productDTO -> modelMapper.map(productDTO,Product.class))
                .collect(Collectors.toSet());
        // luu thong tin vao order
        demand.setCustomer(customer);
        demand.setTimeOrder(demandDTO.getTimeOrder());
        demand.setStatus("processing...");
        demand.setNote(demandDTO.getNote());
        Set<RepairService> dummy = new HashSet<>(serviceRepository.findAll());
        demand.setRepairServices(dummy);
        demandRepository.save(demand);
        for (Product product:products) {
            product.setDemand(demand);
        }
        productRepository.saveAll(products);
        return demandDTO;
    }

    @Override
    public DemandDTO addStaff(DemandDTO demandDTO) {
        // add staff v??o demand
        return null;
    }

    @Override
    public String updateDemand(Integer id) {
        // l???y demand ra t??? repo
        Demand demand = demandRepository.findById(id).orElse(null);
        if(demand == null){
            throw new RuntimeException("demand id "+id+" is not exist !!!");
        }
        else {
            // c???p nh???t total price d???a theo s??? services ???????c ch???n
            double totalPrice = demand
                    .getRepairServices()
                    .stream()
                    .mapToDouble(RepairService::getFee)
                    .reduce(0.0,Double::sum);
            demand.setTotalPrice(totalPrice);
            // s??? l?????ng services trong demand y??u c???u
            int numberOfServices = demand.getRepairServices().size();
            // l???y ra s??? l?????ng staff ??ang c?? demand id = null ???ng v???i s??? l?????ng repair service
//            Set<Staff> getStaffRepo = staffRepository
//                    .findAll()
//                    .stream()
//                    .filter(staff -> staff.getDemand() == null).collect(Collectors.toSet());
            Set<Staff> getStaffRepo = new HashSet<>(staffRepository.findAll());
            int amountOfStaffs = getStaffRepo.size();
            if(amountOfStaffs < numberOfServices){
                // d???a theo ph??n chia c???a manager
            }else {
                // 1 staff ???ng v???i 1 service
//                demand.setStaffs(getStaffRepo);
                getStaffRepo.forEach(staff -> staff.setDemand(demand));
                staffRepository.saveAll(getStaffRepo);
            }
            // estimate time = t???ng th???i gian c???a c??c repair service
            int estimateTime = demand
                    .getRepairServices()
                    .stream()
                    .map(RepairService::getTimeRequired)
                    .reduce(0,Integer::sum);
            demand.setTimeEstimate(demand.getTimeOrder().plusDays(estimateTime));
            // set l???i status
            demand.setStatus("done");
        }
        demandRepository.save(demand);
        return "ok";
    }
}
