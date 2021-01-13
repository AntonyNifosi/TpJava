package fr.isima.tp1.service;

import fr.isima.tp1.model.Rule;
import fr.isima.tp1.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ProductService {

    @Autowired
    protected RuleRepository ruleRepository;

    public List<Rule> getAll() {

        return ruleRepository.findAll();
    }
}
