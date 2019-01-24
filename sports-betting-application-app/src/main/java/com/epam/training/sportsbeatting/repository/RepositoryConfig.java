package com.epam.training.sportsbeatting.repository;

import com.epam.training.sportsbeatting.repository.generic.CustomJpaRepositoryImpl;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class RepositoryConfig {
}
