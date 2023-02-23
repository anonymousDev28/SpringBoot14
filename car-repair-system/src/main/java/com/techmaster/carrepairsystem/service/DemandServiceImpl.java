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
        // add staff vào demand
        return null;
    }

    @Override
    public String updateDemand(Integer id) {
        // lấy demand ra từ repo
        Demand demand = demandRepository.findById(id).orElse(null);
        if(demand == null){
            throw new RuntimeException("demand id "+id+" is not exist !!!");
        }
        else {
            // cập nhật total price dựa theo số services được chọn
            double totalPrice = demand
                    .getRepairServices()
                    .stream()
                    .mapToDouble(RepairService::getFee)
                    .reduce(0.0,Double::sum);
            demand.setTotalPrice(totalPrice);
            // số lượng services trong demand yêu cầu
            int numberOfServices = demand.getRepairServices().size();
            // lấy ra số lượng staff đang có demand id = null ứng với số lượng repair service
//            Set<Staff> getStaffRepo = staffRepository
//                    .findAll()
//                    .stream()
//                    .filter(staff -> staff.getDemand() == null).collect(Collectors.toSet());
            Set<Staff> getStaffRepo = new HashSet<>(staffRepository.findAll());
            int amountOfStaffs = getStaffRepo.size();
            if(amountOfStaffs < numberOfServices){
                // dựa theo phân chia của manager
            }else {
                // 1 staff ứng với 1 service
//                demand.setStaffs(getStaffRepo);
                getStaffRepo.forEach(staff -> staff.setDemand(demand));
                staffRepository.saveAll(getStaffRepo);
            }
            // estimate time = tổng thời gian của các repair service
            int estimateTime = demand
                    .getRepairServices()
                    .stream()
                    .map(RepairService::getTimeRequired)
                    .reduce(0,Integer::sum);
            demand.setTimeEstimate(demand.getTimeOrder().plusDays(estimateTime));
            // set lại status
            demand.setStatus("done");
        }
        demandRepository.save(demand);
        return "ok";
    }
}
