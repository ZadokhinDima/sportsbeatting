package com.epam.training.sportsbeatting.repository;

import com.epam.training.sportsbeatting.repository.generic.CustomJpaRepositoryImpl;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
@EnableRetry
public class RepositoryConfig {
}
