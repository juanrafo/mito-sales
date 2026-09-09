package com.mitocode.service.impl;

import com.mitocode.model.Customer;
import com.mitocode.repo.ICustomerRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends CRUDImpl<Customer, Integer> implements ICustomerService {

    private final ICustomerRepo repo;

    @Override
    protected IGenericRepo<Customer, Integer> getRepo() {
        return repo;
    }
}
