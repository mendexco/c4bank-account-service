package com.mdxco.c4bank.account.infrastructure.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.mdxco.c4bank.account.infrastructure.jpa.repositories"])
class InfraConfig