package com.techmaster.carrepairsystem.service;

import com.techmaster.carrepairsystem.dto.DemandDTO;
import com.techmaster.carrepairsystem.exception.NotFoundException;
import com.techmaster.carrepairsystem.model.Customer;
import com.techmaster.carrepairsystem.model.Demand;
import com.techmaster.carrepairsystem.model.Product;
import com.techmaster.carrepairsystem.repository.CustomerRepository;
import com.techmaster.carrepairsystem.repository.DemandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        demand.setProducts(products);
        // luu thong tin vao order
        demand.setCustomer(customer);
        demandRepository.save(demand);
        return demandDTO;
    }
    // create order
    // add staff
    // update order
}
