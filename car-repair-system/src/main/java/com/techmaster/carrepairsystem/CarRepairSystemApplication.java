package com.techmaster.carrepairsystem;

import com.techmaster.carrepairsystem.model.RepairService;
import com.techmaster.carrepairsystem.model.Staff;
import com.techmaster.carrepairsystem.model.Wallet;
import com.techmaster.carrepairsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CarRepairSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CarRepairSystemApplication.class, args);
	}
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
	@Autowired
	private WalletRepository walletRepository;

	@Override
	public void run(String... args) throws Exception {
//		List<Staff> staffList =
//				new ArrayList<>(Arrays.asList
//						(new Staff("Steven","1058924234"),
//						new Staff("Brian","1435892425"),
//						new Staff("Peter","1258545423")));
//		staffRepository.saveAll(staffList);
		staffRepository.save(new Staff("Admin","8888888"));
//		List<RepairService> repairServices = new ArrayList<>(Arrays.asList(new RepairService("change front door",1000,1),new RepairService("change rear door",1500,2),
//				new RepairService("change each tire",300,1)));
//		serviceRepository.saveAll(repairServices);
//		List<Wallet> walletList = new ArrayList<>(Arrays.asList(new Wallet(0),new Wallet(3000)));
//		walletRepository.saveAll(walletList);
	}
}
