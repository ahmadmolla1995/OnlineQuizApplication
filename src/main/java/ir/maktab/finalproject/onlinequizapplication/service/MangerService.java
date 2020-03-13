package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MangerService {
    @Autowired
    private final ManagerRepository managerRepository;

    public MangerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }
}
