package com.techmaster.carrepairsystem.model;

import com.techmaster.carrepairsystem.common.MyGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet implements Serializable {
    @Id
    @GeneratedValue(generator = MyGenerator.generatorName)
    @GenericGenerator(name = MyGenerator.generatorName,strategy = "a.b.c.MyGenerator")
    private Long account_num;
    private int balance;

    @Override
    public String toString() {
        return "Wallet{" +
                "account_num=" + account_num +
                ", balance=" + balance +
                '}';
    }

    public static void main(String[] args) {
        Wallet wallet = new Wallet();
        wallet.setBalance(3000);
        System.out.println(wallet);
    }
}
