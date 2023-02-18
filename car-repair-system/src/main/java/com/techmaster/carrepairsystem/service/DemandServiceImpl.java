package com.techmaster.carrepairsystem.service;

import com.techmaster.carrepairsystem.dto.DemandDTO;
import com.techmaster.carrepairsystem.exception.NotFoundException;
import com.techmaster.carrepairsystem.model.Customer;
import com.techmaster.carrepairsystem.model.Demand;
import com.techmaster.carrepairsystem.model.Product;
import com.techmaster.carrepairsystem.model.RepairService;
import com.techmaster.carrepairsystem.repository.CustomerRepository;
import com.techmaster.carrepairsystem.repository.DemandRepository;
import com.techmaster.carrepairsystem.repository.ProductRepository;
import com.techmaster.carrepairsystem.repository.RepairServiceRepository;
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
    @Transactional
    @Override
    public DemandDTO createDemand(DemandDTO demandDTO) {
        System.out.println(demandDTO);
        ModelMapper modelMapper = new ModelMapper();
        Demand demand = new Demand();
        // thong tin khach:
        // check customter ton tai hay chua
        Customer customer = new Customer();
        if(customerRepository.findByPhone(demandDTO.getCustomerDTO().getPhone()) == null){
            // chua ton tai
            // tao moi
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
//        Set<RepairService> repairServicesTmp = new HashSet<>();
        Set<RepairService> dummy = new HashSet<>(serviceRepository.findAll());
        demand.setRepairServices(dummy);
//        demand.getRepairServices();
        demandRepository.save(demand);
//        int demandId = demand.getId();
        for (Product product:products) {
            product.setDemand(demand);
        }
        productRepository.saveAll(products);
        return demandDTO;
    }
    // check the condition of that car and add service
    // kiem tra status trong product
    // them service vao bang service_demand

    // update order
//    public DemandDTO updateOrder(Integer demandId){
//        // tim demand trong demand repo
//        // neu khong ton tai tra ra null
//        // neu ton tai thuc hien update
//    }
    // them cac dich vu can sua chua
    // tinh tong tien tu cac dich vu can sua
    // lay ra id demand
}
